package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;
import com.android.common.utils.FileUtils;

public class RemoveTypeFileSmall implements CacheFullRemoveType<String> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<String> obj1, CacheObject<String> obj2) {
        long sizeOfFile2 = -1;
        long sizeOfFile1 = obj1 == null ? -1 : FileUtils.getFileSize((String) obj1.getData());
        if (obj2 != null) {
            sizeOfFile2 = FileUtils.getFileSize((String) obj2.getData());
        }
        if (sizeOfFile1 == sizeOfFile2) {
            if (obj1.getUsedCount() == obj2.getUsedCount()) {
                int i = obj1.getEnterTime() > obj2.getEnterTime() ? 1 : obj1.getEnterTime() == obj2.getEnterTime() ? 0 : -1;
                return i;
            } else if (obj1.getUsedCount() <= obj2.getUsedCount()) {
                return -1;
            } else {
                return 1;
            }
        } else if (sizeOfFile1 <= sizeOfFile2) {
            return -1;
        } else {
            return 1;
        }
    }
}
