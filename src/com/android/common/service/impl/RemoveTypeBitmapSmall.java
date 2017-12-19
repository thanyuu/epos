package com.android.common.service.impl;

import android.graphics.Bitmap;
import com.android.common.entity.CacheObject;
import com.android.common.service.CacheFullRemoveType;
import com.android.common.utils.ImageUtils;

public class RemoveTypeBitmapSmall implements CacheFullRemoveType<Bitmap> {
    private static final long serialVersionUID = 1;

    public int compare(CacheObject<Bitmap> obj1, CacheObject<Bitmap> obj2) {
        long sizeOfFile1 = getSize(obj1);
        long sizeOfFile2 = getSize(obj2);
        if (sizeOfFile1 == sizeOfFile2) {
            if (obj1.getUsedCount() == obj2.getUsedCount()) {
                if (obj1.getEnterTime() > obj2.getEnterTime()) {
                    return 1;
                }
                return obj1.getEnterTime() == obj2.getEnterTime() ? 0 : -1;
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

    private long getSize(CacheObject<Bitmap> o) {
        if (o == null) {
            return -1;
        }
        byte[] b = ImageUtils.bitmapToByte((Bitmap) o.getData());
        return (long) (b == null ? -1 : b.length);
    }
}
