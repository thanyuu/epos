package com.android.common.service;

import com.android.common.entity.CacheObject;
import java.io.Serializable;

public interface CacheFullRemoveType<V> extends Serializable {
    int compare(CacheObject<V> cacheObject, CacheObject<V> cacheObject2);
}
