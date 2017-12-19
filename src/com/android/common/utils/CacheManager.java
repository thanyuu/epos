package com.android.common.utils;

import android.content.Context;
import com.android.common.service.HttpCache;
import com.android.common.service.impl.ImageCache;
import com.android.common.service.impl.ImageSDCardCache;

public class CacheManager {
    private static HttpCache httpCache = null;

    private CacheManager() {
    }

    public static HttpCache getHttpCache(Context context) {
        if (httpCache == null) {
            synchronized (CacheManager.class) {
                if (httpCache == null) {
                    httpCache = new HttpCache(context);
                }
            }
        }
        return httpCache;
    }

    public static ImageCache getImageCache() {
        return ImageCacheManager.getImageCache();
    }

    public static ImageSDCardCache getImageSDCardCache() {
        return ImageCacheManager.getImageSDCardCache();
    }
}
