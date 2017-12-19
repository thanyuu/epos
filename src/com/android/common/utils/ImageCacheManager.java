package com.android.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import com.android.common.entity.FailedReason;
import com.android.common.service.impl.FileNameRuleImageUrl;
import com.android.common.service.impl.ImageCache;
import com.android.common.service.impl.ImageMemoryCache.OnImageCallbackListener;
import com.android.common.service.impl.ImageSDCardCache;
import com.android.common.service.impl.ImageSDCardCache.OnImageSDCallbackListener;
import com.android.common.service.impl.RemoveTypeLastUsedTimeFirst;
import com.android.volley.DefaultRetryPolicy;

public class ImageCacheManager {
    private static ImageCache imageCache = null;
    private static ImageSDCardCache imageSDCardCache = null;

    class C04301 implements OnImageCallbackListener {
        C04301() {
        }

        public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
            if (view != null && loadedImage != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
                if (!isInCache) {
                    imageView.startAnimation(ImageCacheManager.getInAlphaAnimation(2000));
                }
            }
        }

        public void onPreGet(String imageUrl, View view) {
        }

        public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {
        }

        public void onGetNotInCache(String imageUrl, View view) {
        }
    }

    class C04312 implements OnImageSDCallbackListener {
        private static final long serialVersionUID = 1;

        C04312() {
        }

        public void onGetSuccess(String imageUrl, String imagePath, View view, boolean isInCache) {
            ImageView imageView = (ImageView) view;
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            if (bm != null) {
                imageView.setImageBitmap(bm);
                if (!isInCache) {
                    imageView.startAnimation(ImageCacheManager.getInAlphaAnimation(2000));
                }
            }
        }

        public void onPreGet(String imageUrl, View view) {
        }

        public void onGetNotInCache(String imageUrl, View view) {
        }

        public void onGetFailed(String imageUrl, String imagePath, View view, FailedReason failedReason) {
        }
    }

    public static ImageCache getImageCache() {
        if (imageCache == null) {
            synchronized (CacheManager.class) {
                if (imageCache == null) {
                    imageCache = new ImageCache(128, 512);
                    setImageCache();
                }
            }
        }
        return imageCache;
    }

    public static ImageSDCardCache getImageSDCardCache() {
        if (imageSDCardCache == null) {
            synchronized (CacheManager.class) {
                if (imageSDCardCache == null) {
                    imageSDCardCache = new ImageSDCardCache();
                    setImageSDCardCache();
                }
            }
        }
        return imageSDCardCache;
    }

    private static void setImageCache() {
        if (imageCache != null) {
            imageCache.setOnImageCallbackListener(new C04301());
            imageCache.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst());
            imageCache.setHttpReadTimeOut(10000);
            imageCache.setValidTime(-1);
        }
    }

    private static void setImageSDCardCache() {
        if (imageSDCardCache != null) {
            imageSDCardCache.setOnImageSDCallbackListener(new C04312());
            imageSDCardCache.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst());
            imageSDCardCache.setFileNameRule(new FileNameRuleImageUrl());
            imageSDCardCache.setHttpReadTimeOut(10000);
            imageSDCardCache.setValidTime(-1);
        }
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    private ImageCacheManager() {
    }
}
