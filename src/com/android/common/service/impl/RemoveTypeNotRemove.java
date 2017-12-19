package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeNotRemove<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> cacheObject, CacheObject<T> cacheObject2) {
        return 0;
    }
}
