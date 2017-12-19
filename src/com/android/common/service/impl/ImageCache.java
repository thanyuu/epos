package com.android.common.service.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;
import com.android.common.service.FileNameRule;
import com.android.common.service.impl.PreloadDataCache.OnGetDataListener;
import com.android.common.utils.FileUtils;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ImageCache extends ImageMemoryCache {
    public static final String DEFAULT_CACHE_FOLDER = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Trinea" + File.separator + "AndroidCommon" + File.separator + "ImageCache");
    private static final long serialVersionUID = 1;
    private CompressListener compressListener;
    private int compressSize;
    private ImageSDCardCache secondaryCache;

    public interface CompressListener {
        int getCompressSize(String str);
    }

    class C04271 implements OnGetDataListener<String, Bitmap> {
        private static final long serialVersionUID = 1;

        C04271() {
        }

        public CacheObject<Bitmap> onGetData(String key) {
            String imagePath;
            CacheObject<String> object = ImageCache.this.secondaryCache.get(key);
            if (object == null) {
                imagePath = null;
            } else {
                imagePath = (String) object.getData();
            }
            if (FileUtils.isFileExist(imagePath)) {
                Bitmap bm;
                if (ImageCache.this.compressListener != null) {
                    ImageCache.this.compressSize = ImageCache.this.compressListener.getCompressSize(imagePath);
                }
                if (ImageCache.this.compressSize > 1) {
                    Options option = new Options();
                    option.inSampleSize = ImageCache.this.compressSize;
                    bm = BitmapFactory.decodeFile(imagePath, option);
                } else {
                    bm = BitmapFactory.decodeFile(imagePath);
                }
                if (bm == null) {
                    return null;
                }
                return new CacheObject(bm);
            }
            ImageCache.this.secondaryCache.remove(key);
            return null;
        }
    }

    public ImageCache() {
        this(ImageMemoryCache.DEFAULT_MAX_SIZE, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE, ImageSDCardCache.DEFAULT_MAX_SIZE, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageCache(int primaryCacheMaxSize) {
        this(primaryCacheMaxSize, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE, ImageSDCardCache.DEFAULT_MAX_SIZE, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageCache(int primaryCacheMaxSize, int secondaryCacheMaxSize) {
        this(primaryCacheMaxSize, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE, secondaryCacheMaxSize, PreloadDataCache.DEFAULT_THREAD_POOL_SIZE);
    }

    public ImageCache(int primaryCacheMaxSize, int primaryCacheThreadPoolSize, int secondaryCacheMaxSize, int secondaryCacheThreadPoolSize) {
        super(primaryCacheMaxSize, primaryCacheThreadPoolSize);
        this.compressSize = 1;
        setOnGetDataListener(new C04271());
        super.setCheckNetwork(false);
        setCacheFullRemoveType(new RemoveTypeUsedCountSmall());
        this.secondaryCache = new ImageSDCardCache(secondaryCacheMaxSize, secondaryCacheThreadPoolSize);
        this.secondaryCache.setCacheFolder(DEFAULT_CACHE_FOLDER);
        this.secondaryCache.setFileNameRule(new FileNameRuleImageUrl().setFileExtension(""));
    }

    public int getCompressSize() {
        return this.compressSize;
    }

    public void setCompressSize(int compressSize) {
        this.compressSize = compressSize;
    }

    public void setCompressListener(CompressListener compressListener) {
        this.compressListener = compressListener;
    }

    public CompressListener getCompressListener() {
        return this.compressListener;
    }

    public int getHttpReadTimeOut() {
        return this.secondaryCache.getHttpReadTimeOut();
    }

    public void setHttpReadTimeOut(int readTimeOutMillis) {
        this.secondaryCache.setHttpReadTimeOut(readTimeOutMillis);
    }

    public void clear() {
        super.clear();
        this.secondaryCache.clear();
    }

    public void setForwardCacheNumber(int forwardCacheNumber) {
        super.setForwardCacheNumber(forwardCacheNumber);
        this.secondaryCache.setForwardCacheNumber(forwardCacheNumber);
    }

    public void setBackwardCacheNumber(int backwardCacheNumber) {
        super.setForwardCacheNumber(backwardCacheNumber);
        this.secondaryCache.setForwardCacheNumber(backwardCacheNumber);
    }

    public int getAllowedNetworkTypes() {
        return this.secondaryCache.getAllowedNetworkTypes();
    }

    public void setAllowedNetworkTypes(int allowedNetworkTypes) {
        this.secondaryCache.setAllowedNetworkTypes(allowedNetworkTypes);
    }

    public boolean isCheckNetwork() {
        return this.secondaryCache.isCheckNetwork();
    }

    public void setCheckNetwork(boolean isCheckNetwork) {
        this.secondaryCache.setCheckNetwork(isCheckNetwork);
    }

    public boolean checkIsNetworkTypeAllowed() {
        return this.secondaryCache.checkIsNetworkTypeAllowed();
    }

    public Context getContext() {
        return this.secondaryCache.getContext();
    }

    public void setContext(Context context) {
        this.secondaryCache.setContext(context);
    }

    public void setRequestProperties(Map<String, String> requestProperties) {
        this.secondaryCache.setRequestProperties(requestProperties);
    }

    public Map<String, String> getRequestProperties() {
        return this.secondaryCache.getRequestProperties();
    }

    public void setRequestProperty(String field, String newValue) {
        this.secondaryCache.setRequestProperty(field, newValue);
    }

    public String getCacheFolder() {
        return this.secondaryCache.getCacheFolder();
    }

    public void setCacheFolder(String cacheFolder) {
        this.secondaryCache.setCacheFolder(cacheFolder);
    }

    public FileNameRule getFileNameRule() {
        return this.secondaryCache.getFileNameRule();
    }

    public void setFileNameRule(FileNameRule fileNameRule) {
        this.secondaryCache.setFileNameRule(fileNameRule);
    }

    public void initData(Context context, String tag) {
        loadDataFromDb(context, tag);
        deleteUnusedFiles();
    }

    public void deleteUnusedFiles() {
        this.secondaryCache.deleteUnusedFiles();
    }

    public boolean loadDataFromDb(Context context, String tag) {
        return ImageSDCardCache.loadDataFromDb(context, this.secondaryCache, tag);
    }

    public boolean saveDataToDb(Context context, String tag) {
        return ImageSDCardCache.saveDataToDb(context, this.secondaryCache, tag);
    }

    public String getImagePath(String imageUrl) {
        return this.secondaryCache.getImagePath(imageUrl);
    }

    protected void shutdown() {
        this.secondaryCache.shutdown();
        super.shutdown();
    }

    public List<Runnable> shutdownNow() {
        this.secondaryCache.shutdownNow();
        return super.shutdownNow();
    }

    public OnGetDataListener<String, Bitmap> getOnGetImageListenerOfPrimaryCache() {
        return getOnGetDataListener();
    }

    public void setOnGetImageListenerOfPrimaryCache(OnGetDataListener<String, Bitmap> onGetImageListener) {
        this.onGetDataListener = onGetImageListener;
    }

    public OnGetDataListener<String, String> getOnGetImageListenerOfSecondaryCache() {
        return this.secondaryCache.getOnGetDataListener();
    }

    public void setOnGetImageListenerOfSecondaryCache(OnGetDataListener<String, String> onGetImageListener) {
        this.secondaryCache.setOnGetDataListener(onGetImageListener);
    }

    public CacheFullRemoveType<String> getCacheFullRemoveTypeOfSecondaryCache() {
        return this.secondaryCache.getCacheFullRemoveType();
    }

    public void setCacheFullRemoveTypeOfSecondaryCache(CacheFullRemoveType<String> cacheFullRemoveType) {
        this.secondaryCache.setCacheFullRemoveType(cacheFullRemoveType);
    }
}
