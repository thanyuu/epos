package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeLastUsedTimeFirst<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj1.getLastUsedTime() > obj2.getLastUsedTime()) {
            return 1;
        }
        return obj1.getLastUsedTime() == obj2.getLastUsedTime() ? 0 : -1;
    }
}
