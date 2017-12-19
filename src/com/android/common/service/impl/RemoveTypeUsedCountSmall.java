package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypeUsedCountSmall<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj1.getUsedCount() > obj2.getUsedCount()) {
            return 1;
        }
        return obj1.getUsedCount() == obj2.getUsedCount() ? 0 : -1;
    }
}
