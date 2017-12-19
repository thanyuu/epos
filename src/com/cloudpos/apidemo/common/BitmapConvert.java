package com.cloudpos.apidemo.common;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class BitmapConvert {
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteBuffer dst = ByteBuffer.allocate((bm.getWidth() * bm.getHeight()) * 4);
        bm.copyPixelsToBuffer(dst);
        dst.flip();
        return dst.array();
    }
}
