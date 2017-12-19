package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;
import com.android.common.utils.ObjectUtils;

public class RemoveTypeDataSmall<T> implements CacheFullRemoveType<T> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        return ObjectUtils.compare(obj1.getData(), obj2.getData());
    }
}
