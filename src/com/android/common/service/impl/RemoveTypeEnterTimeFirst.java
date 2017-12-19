package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeEnterTimeFirst<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj1.getEnterTime() > obj2.getEnterTime()) {
            return 1;
        }
        return obj1.getEnterTime() == obj2.getEnterTime() ? 0 : -1;
    }
}
