package com.android.common.service.impl;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.android.common.dao.impl.ImageSDCardCacheDaoImpl;
import com.android.common.entity.CacheObject;
import com.android.common.entity.FailedReason;
import com.android.common.entity.FailedReason.FailedType;
import com.android.common.service.FileNameRule;
import com.android.common.service.impl.PreloadDataCache.OnGetDataListener;
import com.android.common.utils.FileUtils;
import com.android.common.utils.ImageUtils;
import com.android.common.utils.SizeUtils;
import com.android.common.utils.SqliteUtils;
import com.android.common.utils.StringUtils;
import com.android.common.utils.SystemUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageSDCardCache extends PreloadDataCache<String, String> {
    public static final String DEFAULT_CACHE_FOLDER = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Trinea" + File.separator + "AndroidCommon" + File.separator + TAG);
    public static final int DEFAULT_MAX_SIZE = getDefaultMaxSize();
    private static final String TAG = "ImageSDCardCache";
    private static final int WHAT_GET_IMAGE_FAILED = 2;
    private static final int WHAT_GET_IMAGE_SUCCESS = 1;
    private static final long serialVersionUID = 1;
    private String cacheFolder;
    private FileNameRule fileNameRule;
    private transient Handler handler;
    private int httpReadTimeOut;
    private boolean isOpenWaitingQueue;
    private OnImageSDCallbackListener onImageSDCallbackListener;
    private Map<String, String> requestProperties;
    private transient ExecutorService threadPool;
    private transient Map<String, View> viewMap;
    private transient Map<String, HashSet<View>> viewSetMap;

    private class MessageObject {
        FailedReason failedReason;
        String imagePath;
        String imageUrl;

        public MessageObject(String imageUrl, String imagePath) {
            this.imageUrl = imageUrl;
            this.imagePath = imagePath;
        }

        public MessageObject(String imageUrl, String imagePath, FailedReason failedReason) {
            this.imageUrl = imageUrl;
            this.imagePath = imagePath;
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
                        String imagePath = object.imagePath;
                        if (ImageSDCardCache.this.onImageSDCallbackListener != null) {
                            View view;
                            if (ImageSDCardCache.this.isOpenWaitingQueue) {
                                synchronized (ImageSDCardCache.this.viewSetMap) {
                                    HashSet<View> viewSet = (HashSet) ImageSDCardCache.this.viewSetMap.get(imageUrl);
                                    if (viewSet != null) {
                                        Iterator it = viewSet.iterator();
                                        while (it.hasNext()) {
                                            view = (View) it.next();
                                            if (view != null) {
                                                if (1 == message.what) {
                                                    ImageSDCardCache.this.onGetSuccess(imageUrl, imagePath, view, false);
                                                } else {
                                                    ImageSDCardCache.this.onImageSDCallbackListener.onGetFailed(imageUrl, imagePath, view, object.failedReason);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                view = (View) ImageSDCardCache.this.viewMap.get(imageUrl);
                                if (view != null) {
                                    if (1 == message.what) {
                                        ImageSDCardCache.this.onGetSuccess(imageUrl, imagePath, view, false);
                                    } else {
                                        ImageSDCardCache.this.onImageSDCallbackListener.onGetFailed(imageUrl, imagePath, view, object.failedReason);
                                    }
                                }
                            }
                        }
                        if (ImageSDCardCache.this.isOpenWaitingQueue) {
                            synchronized (ImageSDCardCache.this.viewSetMap) {
                                ImageSDCardCache.this.viewSetMap.remove(imageUrl);
                            }
                            return;
                        }
                        ImageSDCardCache.this.viewMap.remove(imageUrl);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public interface OnImageSDCallbackListener {
        void onGetFailed(String str, String str2, View view, FailedReason failedReason);

        void onGetNotInCache(String str, View view);

        void onGetSuccess(String str, String str2, View view, boolean z);

        void onPreGet(String str, View view);
    }

    class C04293 implements OnGetDataListener<String, String> {
        private static final long serialVersionUID = 1;

        C04293() {
        }

        public CacheObject<String> onGetData(String key) {
            String savePath = null;
            InputStream stream = null;
            try {
                stream = ImageUtils.getInputStreamFromUrl(key, ImageSDCardCache.this.httpReadTimeOut, ImageSDCardCache.this.requestProperties);
            } catch (Exception e) {
                Log.e(ImageSDCardCache.TAG, "get image exception, imageUrl is:" + key, e);
            }
            if (stream != null) {
                savePath = new StringBuilder(String.valueOf(ImageSDCardCache.this.cacheFolder)).append(File.separator).append(ImageSDCardCache.this.fileNameRule.getFileName(key)).toString();
                try {
                    FileUtils.writeFile(savePath, stream);
                } catch (Exception e1) {
                    try {
                        if (e1.getCause() instanceof FileNotFoundException) {
                            FileUtils.makeFolders(savePath);
                            FileUtils.writeFile(savePath, stream);
                        } else {
                            Log.e(ImageSDCardCache.TAG, "get image exception while write to file, imageUrl is: " + key + ", savePath is " + savePath, e1);
                        }
                    } catch (Exception e2) {
                        Log.e(ImageSDCardCache.TAG, "get image exception while write to file, imageUrl is: " + key + ", savePath is " + savePath, e2);
                    }
                }
            }
            if (StringUtils.isEmpty(savePath)) {
                return null;
            }
            return new CacheObject(savePath);
        }
    }

    public boolean get(String imageUrl, View view) {
        return get(imageUrl, null, view);
    }

    public boolean get(String imageUrl, List<String> urlList, View view) {
        if (this.onImageSDCallbackListener != null) {
            this.onImageSDCallbackListener.onPreGet(imageUrl, view);
        }
        if (!StringUtils.isEmpty(imageUrl)) {
            CacheObject<String> object = getFromCache(imageUrl, urlList);
            if (object != null) {
                String imagePath = (String) object.getData();
                if (StringUtils.isEmpty(imagePath) || !FileUtils.isFileExist(imagePath)) {
                    remove(imageUrl);
                } else {
                    onGetSuccess(imageUrl, imagePath, view, true);
                    return true;
                }
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
            if (this.onImageSDCallbackListener != null) {
                this.onImageSDCallbackListener.onGetNotInCache(imageUrl, view);
            }
            if (isExistGettingDataThread(imageUrl)) {
                return false;
            }
            startGetImageThread(imageUrl, urlList);
            return false;
        } else if (this.onImageSDCallbackListener == null) {
            return false;
        } else {
            this.onImageSDCallbackListener.onGetNotInCache(imageUrl, view);
            return false;
        }
    }

    public String getCacheFolder() {
        return this.cacheFolder;
    }

    public void setCacheFolder(String cacheFolder) {
        if (StringUtils.isEmpty(cacheFolder)) {
            throw new IllegalArgumentException("The cacheFolder of cache can not be null.");
        }
        this.cacheFolder = cacheFolder;
    }

    public FileNameRule getFileNameRule() {
        return this.fileNameRule;
    }

    public void setFileNameRule(FileNameRule fileNameRule) {
        if (fileNameRule == null) {
            throw new IllegalArgumentException("The fileNameRule of cache can not be null.");
        }
        this.fileNameRule = fileNameRule;
    }

    public OnImageSDCallbackListener getOnImageSDCallbackListener() {
        return this.onImageSDCallbackListener;
    }

    public void setOnImageSDCallbackListener(OnImageSDCallbackListener onImageSDCallbackListener) {
        this.onImageSDCallbackListener = onImageSDCallbackListener;
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

    public ImageSDCardCache() {
        this(DEFAULT_MAX_SIZE, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageSDCardCache(int maxSize) {
        this(maxSize, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageSDCardCache(int maxSize, int threadPoolSize) {
        super(maxSize, threadPoolSize);
        this.cacheFolder = DEFAULT_CACHE_FOLDER;
        this.fileNameRule = new FileNameRuleImageUrl();
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

    private void onGetSuccess(String imageUrl, String imagePath, View view, boolean isInCache) {
        if (this.onImageSDCallbackListener != null) {
            try {
                this.onImageSDCallbackListener.onGetSuccess(imageUrl, imagePath, view, isInCache);
            } catch (Throwable e) {
                this.onImageSDCallbackListener.onGetFailed(imageUrl, imagePath, view, new FailedReason(FailedType.ERROR_OUT_OF_MEMORY, e));
            }
        }
    }

    private void startGetImageThread(final String imageUrl, final List<String> urlList) {
        this.threadPool.execute(new Runnable() {
            public void run() {
                CacheObject<String> object = ImageSDCardCache.this.get(imageUrl, urlList);
                String imagePath = object == null ? null : (String) object.getData();
                if (StringUtils.isEmpty(imagePath) || !FileUtils.isFileExist(imagePath)) {
                    ImageSDCardCache.this.remove(imageUrl);
                    ImageSDCardCache.this.handler.sendMessage(ImageSDCardCache.this.handler.obtainMessage(2, new MessageObject(imageUrl, imagePath, new FailedReason(FailedType.ERROR_IO, "get image from network or save image to sdcard error. please make sure you have added permission android.permission.WRITE_EXTERNAL_STORAGE and android.permission.ACCESS_NETWORK_STATE"))));
                    return;
                }
                ImageSDCardCache.this.handler.sendMessage(ImageSDCardCache.this.handler.obtainMessage(1, new MessageObject(imageUrl, imagePath)));
            }
        });
    }

    protected CacheObject<String> fullRemoveOne() {
        CacheObject<String> o = super.fullRemoveOne();
        if (o != null) {
            deleteFile((String) o.getData());
        }
        return o;
    }

    public CacheObject<String> remove(String key) {
        CacheObject<String> o = super.remove(key);
        if (o != null) {
            deleteFile((String) o.getData());
        }
        return o;
    }

    public void clear() {
        for (CacheObject<String> value : values()) {
            if (value != null) {
                deleteFile((String) value.getData());
            }
        }
        super.clear();
    }

    public void deleteUnusedFiles() {
        int size = getSize();
        if (size <= 16) {
            size = 16;
        }
        final HashSet<String> filePathSet = new HashSet(size);
        for (CacheObject<String> value : values()) {
            if (value != null) {
                filePathSet.add((String) value.getData());
            }
        }
        this.threadPool.execute(new Runnable() {
            public void run() {
                try {
                    File file = new File(ImageSDCardCache.this.getCacheFolder());
                    if (file != null && file.exists() && file.isDirectory()) {
                        for (File f : file.listFiles()) {
                            if (f.isFile() && !filePathSet.contains(f.getPath())) {
                                f.delete();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(ImageSDCardCache.TAG, "delete unused files fail.");
                }
            }
        });
    }

    public void initData(Context context, String tag) {
        loadDataFromDb(context, this, tag);
        deleteUnusedFiles();
    }

    public boolean loadDataFromDb(Context context, String tag) {
        return loadDataFromDb(context, this, tag);
    }

    public boolean saveDataToDb(Context context, String tag) {
        return saveDataToDb(context, this, tag);
    }

    public static boolean loadDataFromDb(Context context, ImageSDCardCache imageSDCardCache, String tag) {
        if (context == null || imageSDCardCache == null) {
            throw new IllegalArgumentException("The context and cache both can not be null.");
        } else if (!StringUtils.isEmpty(tag)) {
            return new ImageSDCardCacheDaoImpl(SqliteUtils.getInstance(context)).putIntoImageSDCardCache(imageSDCardCache, tag);
        } else {
            throw new IllegalArgumentException("The tag can not be null or empty.");
        }
    }

    public static boolean saveDataToDb(Context context, ImageSDCardCache imageSDCardCache, String tag) {
        if (context == null || imageSDCardCache == null) {
            throw new IllegalArgumentException("The context and cache both can not be null.");
        } else if (!StringUtils.isEmpty(tag)) {
            return new ImageSDCardCacheDaoImpl(SqliteUtils.getInstance(context)).deleteAndInsertImageSDCardCache(imageSDCardCache, tag);
        } else {
            throw new IllegalArgumentException("The tag can not be null or empty.");
        }
    }

    public String getImagePath(String imageUrl) {
        return containsKey(imageUrl) ? new StringBuilder(this.cacheFolder).append(File.separator).append(this.fileNameRule.getFileName(imageUrl)).toString() : null;
    }

    private boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path) || FileUtils.deleteFile(path)) {
            return true;
        }
        Log.e(TAG, "delete file fail, path is " + path);
        return false;
    }

    public OnGetDataListener<String, String> getDefaultOnGetImageListener() {
        return new C04293();
    }

    static int getDefaultMaxSize() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        if (maxMemory > SizeUtils.GB_2_BYTE) {
            return 256;
        }
        int mb = (int) (maxMemory / SizeUtils.MB_2_BYTE);
        return mb <= 8 ? 8 : mb;
    }
}
