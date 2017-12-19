package com.cloudpos.utils;

import android.util.Log;

public class C0228L {
    private static final String TAG = "way";
    public static boolean isDebug = true;

    private C0228L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void m17i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void m13d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void m15e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void m19v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void m18i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m14d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m16e(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void m20v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
}
