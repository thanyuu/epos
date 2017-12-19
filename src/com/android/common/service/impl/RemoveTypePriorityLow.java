package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;

public class RemoveTypePriorityLow<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        if (obj1.getPriority() > obj2.getPriority()) {
            return 1;
        }
        return obj1.getPriority() == obj2.getPriority() ? 0 : -1;
    }
}
