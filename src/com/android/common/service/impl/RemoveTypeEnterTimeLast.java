package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeEnterTimeLast<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj2.getEnterTime() > obj1.getEnterTime()) {
            return 1;
        }
        return obj2.getEnterTime() == obj1.getEnterTime() ? 0 : -1;
    }
}
