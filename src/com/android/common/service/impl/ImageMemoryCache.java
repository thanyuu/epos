package com.android.common.service.impl;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.android.common.entity.CacheObject;
import com.android.common.entity.FailedReason;
import com.android.common.entity.FailedReason.FailedType;
import com.android.common.service.impl.PreloadDataCache.OnGetDataListener;
import com.android.common.utils.ImageUtils;
import com.android.common.utils.SizeUtils;
import com.android.common.utils.StringUtils;
import com.android.common.utils.SystemUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageMemoryCache extends PreloadDataCache<String, Bitmap> {
    public static final int DEFAULT_MAX_SIZE = getDefaultMaxSize();
    private static final String TAG = "ImageCache";
    private static final int WHAT_GET_IMAGE_FAILED = 2;
    private static final int WHAT_GET_IMAGE_SUCCESS = 1;
    private static final long serialVersionUID = 1;
    private transient Handler handler;
    private int httpReadTimeOut;
    private boolean isOpenWaitingQueue;
    private OnImageCallbackListener onImageCallbackListener;
    private Map<String, String> requestProperties;
    private transient ExecutorService threadPool;
    private transient Map<String, View> viewMap;
    private transient Map<String, HashSet<View>> viewSetMap;

    private class MessageObject {
        Bitmap bitmap;
        FailedReason failedReason;
        String imageUrl;

        public MessageObject(String imageUrl, Bitmap bitmap) {
            this.imageUrl = imageUrl;
            this.bitmap = bitmap;
        }

        public MessageObject(String imageUrl, Bitmap bitmap, FailedReason failedReason) {
            this.imageUrl = imageUrl;
            this.bitmap = bitmap;
            this.failedReason = failedReason;
        }
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                case 2:
                    MessageObject object = message.obj;
                    if (object != null) {
                        String imageUrl = object.imageUrl;
                        Bitmap bitmap = object.bitmap;
                        if (ImageMemoryCache.this.onImageCallbackListener != null) {
                            View view;
                            if (ImageMemoryCache.this.isOpenWaitingQueue) {
                                synchronized (ImageMemoryCache.this.viewSetMap) {
                                    HashSet<View> viewSet = (HashSet) ImageMemoryCache.this.viewSetMap.get(imageUrl);
                                    if (viewSet != null) {
                                        Iterator it = viewSet.iterator();
                                        while (it.hasNext()) {
                                            view = (View) it.next();
                                            if (view != null) {
                                                if (1 == message.what) {
                                                    ImageMemoryCache.this.onGetSuccess(imageUrl, bitmap, view, false);
                                                } else {
                                                    ImageMemoryCache.this.onImageCallbackListener.onGetFailed(imageUrl, bitmap, view, object.failedReason);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                view = (View) ImageMemoryCache.this.viewMap.get(imageUrl);
                                if (view != null) {
                                    if (1 == message.what) {
                                        ImageMemoryCache.this.onGetSuccess(imageUrl, bitmap, view, false);
                                    } else {
                                        ImageMemoryCache.this.onImageCallbackListener.onGetFailed(imageUrl, bitmap, view, object.failedReason);
                                    }
                                }
                            }
                        }
                        if (ImageMemoryCache.this.isOpenWaitingQueue) {
                            synchronized (ImageMemoryCache.this.viewSetMap) {
                                ImageMemoryCache.this.viewSetMap.remove(imageUrl);
                            }
                            return;
                        }
                        ImageMemoryCache.this.viewMap.remove(imageUrl);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface OnImageCallbackListener {
        void onGetFailed(String str, Bitmap bitmap, View view, FailedReason failedReason);

        void onGetNotInCache(String str, View view);

        void onGetSuccess(String str, Bitmap bitmap, View view, boolean z);

        void onPreGet(String str, View view);
    }

    class C04282 implements OnGetDataListener<String, Bitmap> {
        private static final long serialVersionUID = 1;

        C04282() {
        }

        public CacheObject<Bitmap> onGetData(String key) {
            Bitmap d = null;
            try {
                d = ImageUtils.getBitmapFromUrl(key, ImageMemoryCache.this.httpReadTimeOut, ImageMemoryCache.this.requestProperties);
            } catch (Exception e) {
                Log.e(ImageMemoryCache.TAG, "get image exception, imageUrl is:" + key, e);
            }
            return d == null ? null : new CacheObject(d);
        }
    }

    public boolean get(String imageUrl, View view) {
        return get(imageUrl, null, view);
    }

    public boolean get(String imageUrl, List<String> urlList, View view) {
        if (this.onImageCallbackListener != null) {
            this.onImageCallbackListener.onPreGet(imageUrl, view);
        }
        if (!StringUtils.isEmpty(imageUrl)) {
            CacheObject<Bitmap> object = getFromCache(imageUrl, urlList);
            if (object != null) {
                Bitmap bitmap = (Bitmap) object.getData();
                if (bitmap != null) {
                    onGetSuccess(imageUrl, bitmap, view, true);
                    return true;
                }
                remove(imageUrl);
            }
            if (this.isOpenWaitingQueue) {
                synchronized (this.viewSetMap) {
                    HashSet<View> viewSet = (HashSet) this.viewSetMap.get(imageUrl);
                    if (viewSet == null) {
                        viewSet = new HashSet();
                        this.viewSetMap.put(imageUrl, viewSet);
                    }
                    viewSet.add(view);
                }
            } else {
                this.viewMap.put(imageUrl, view);
            }
            if (this.onImageCallbackListener != null) {
                this.onImageCallbackListener.onGetNotInCache(imageUrl, view);
            }
            if (isExistGettingDataThread(imageUrl)) {
                return false;
            }
            startGetImageThread(imageUrl, urlList);
            return false;
        } else if (this.onImageCallbackListener == null) {
            return false;
        } else {
            this.onImageCallbackListener.onGetNotInCache(imageUrl, view);
            return false;
        }
    }

    public OnImageCallbackListener getOnImageCallbackListener() {
        return this.onImageCallbackListener;
    }

    public void setOnImageCallbackListener(OnImageCallbackListener onImageCallbackListener) {
        this.onImageCallbackListener = onImageCallbackListener;
    }

    public int getHttpReadTimeOut() {
        return this.httpReadTimeOut;
    }

    public void setHttpReadTimeOut(int readTimeOutMillis) {
        this.httpReadTimeOut = readTimeOutMillis;
    }

    public boolean isOpenWaitingQueue() {
        return this.isOpenWaitingQueue;
    }

    public void setOpenWaitingQueue(boolean isOpenWaitingQueue) {
        this.isOpenWaitingQueue = isOpenWaitingQueue;
    }

    public void setRequestProperties(Map<String, String> requestProperties) {
        this.requestProperties = requestProperties;
    }

    public Map<String, String> getRequestProperties() {
        return this.requestProperties;
    }

    public void setRequestProperty(String field, String newValue) {
        if (!StringUtils.isEmpty(field)) {
            if (this.requestProperties == null) {
                this.requestProperties = new HashMap();
            }
            this.requestProperties.put(field, newValue);
        }
    }

    public ImageMemoryCache() {
        this(DEFAULT_MAX_SIZE, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageMemoryCache(int maxSize) {
        this(maxSize, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageMemoryCache(int maxSize, int threadPoolSize) {
        super(maxSize, threadPoolSize);
        this.httpReadTimeOut = -1;
        this.isOpenWaitingQueue = true;
        this.requestProperties = null;
        this.threadPool = Executors.newFixedThreadPool(SystemUtils.DEFAULT_THREAD_POOL_SIZE);
        super.setOnGetDataListener(getDefaultOnGetImageListener());
        super.setCacheFullRemoveType(new RemoveTypeUsedCountSmall());
        this.viewMap = new ConcurrentHashMap();
        this.viewSetMap = new HashMap();
        this.handler = new MyHandler();
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    protected void shutdown() {
        this.threadPool.shutdown();
        super.shutdown();
    }

    public List<Runnable> shutdownNow() {
        this.threadPool.shutdownNow();
        return super.shutdownNow();
    }

    private void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
        if (this.onImageCallbackListener != null) {
            try {
                this.onImageCallbackListener.onGetSuccess(imageUrl, loadedImage, view, isInCache);
            } catch (Throwable e) {
                this.onImageCallbackListener.onGetFailed(imageUrl, loadedImage, view, new FailedReason(FailedType.ERROR_OUT_OF_MEMORY, e));
            }
        }
    }

    private void startGetImageThread(final String imageUrl, final List<String> urlList) {
        this.threadPool.execute(new Runnable() {
            public void run() {
                CacheObject<Bitmap> object = ImageMemoryCache.this.get(imageUrl, urlList);
                Bitmap bitmap = object == null ? null : (Bitmap) object.getData();
                if (bitmap == null) {
                    ImageMemoryCache.this.remove(imageUrl);
                    ImageMemoryCache.this.handler.sendMessage(ImageMemoryCache.this.handler.obtainMessage(2, new MessageObject(imageUrl, bitmap, new FailedReason(FailedType.ERROR_IO, "get image from network or save image to sdcard error. please make sure you have added permission android.permission.WRITE_EXTERNAL_STORAGE and android.permission.ACCESS_NETWORK_STATE"))));
                    return;
                }
                ImageMemoryCache.this.handler.sendMessage(ImageMemoryCache.this.handler.obtainMessage(1, new MessageObject(imageUrl, bitmap)));
            }
        });
    }

    public OnGetDataListener<String, Bitmap> getDefaultOnGetImageListener() {
        return new C04282();
    }

    static int getDefaultMaxSize() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        if (maxMemory > SizeUtils.GB_2_BYTE) {
            return 512;
        }
        int mb = (int) (maxMemory / SizeUtils.MB_2_BYTE);
        if (mb > 16) {
            return mb * 2;
        }
        return 16;
    }
}
