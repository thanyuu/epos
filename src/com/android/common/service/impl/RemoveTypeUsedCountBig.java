package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeUsedCountBig<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj2.getUsedCount() > obj1.getUsedCount()) {
            return 1;
        }
        return obj2.getUsedCount() == obj1.getUsedCount() ? 0 : -1;
    }
}
