package com.android.common.dao;

import com.android.common.service.impl.ImageSDCardCache;

public interface ImageSDCardCacheDao {
    boolean deleteAndInsertImageSDCardCache(ImageSDCardCache imageSDCardCache, String str);

    boolean putIntoImageSDCardCache(ImageSDCardCache imageSDCardCache, String str);
}
