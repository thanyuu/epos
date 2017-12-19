package com.synjones.bluetooth;

import android.util.Log;

public class DecodeWlt {
    public static native int Wlt2Bmp(String str, String str2);

    static {
        try {
            System.loadLibrary("DecodeWlt");
        } catch (Exception e) {
            Log.e("jni", "i can't find this so!");
            e.printStackTrace();
        }
    }
}
