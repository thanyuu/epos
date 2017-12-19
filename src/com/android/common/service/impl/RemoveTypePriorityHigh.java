package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypePriorityHigh<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj2.getPriority() > obj1.getPriority()) {
            return 1;
        }
        return obj2.getPriority() == obj1.getPriority() ? 0 : -1;
    }
}
