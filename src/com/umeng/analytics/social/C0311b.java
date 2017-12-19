package com.umeng.analytics.social;

import android.util.Log;

/* compiled from: UMLog */
public class C0311b {
    public static void m108a(String str, String str2) {
        if (C0314e.f139v) {
            Log.i(str, str2);
        }
    }

    public static void m109a(String str, String str2, Exception exception) {
        if (C0314e.f139v) {
            Log.i(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void m110b(String str, String str2) {
        if (C0314e.f139v) {
            Log.e(str, str2);
        }
    }

    public static void m111b(String str, String str2, Exception exception) {
        if (C0314e.f139v) {
            Log.e(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.e(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }

    public static void m112c(String str, String str2) {
        if (C0314e.f139v) {
            Log.d(str, str2);
        }
    }

    public static void m113c(String str, String str2, Exception exception) {
        if (C0314e.f139v) {
            Log.d(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void m114d(String str, String str2) {
        if (C0314e.f139v) {
            Log.v(str, str2);
        }
    }

    public static void m115d(String str, String str2, Exception exception) {
        if (C0314e.f139v) {
            Log.v(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void m116e(String str, String str2) {
        if (C0314e.f139v) {
            Log.w(str, str2);
        }
    }

    public static void m117e(String str, String str2, Exception exception) {
        if (C0314e.f139v) {
            Log.w(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.w(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }
}
