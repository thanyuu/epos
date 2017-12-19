package com.cloudpos.mvc.common;

import android.util.Log;
import com.android.common.utils.MapUtils;

public class Logger {
    public static int level = 3;

    public static void debug(String msg) {
        if (level <= 3) {
            Log.d(createTag(), msg);
        }
    }

    public static void debug(String msg, Throwable tr) {
        if (level <= 3) {
            Log.d(createTag(), msg, tr);
        }
    }

    public static void info(String msg) {
        if (level <= 4) {
            Log.i(createTag(), msg);
        }
    }

    public static void info(String msg, Throwable tr) {
        if (level <= 4) {
            Log.i(createTag(), msg, tr);
        }
    }

    public static void warn(String msg) {
        if (level <= 5) {
            Log.w(createTag(), msg);
        }
    }

    public static void warn(String msg, Throwable tr) {
        if (level <= 5) {
            Log.w(createTag(), msg, tr);
        }
    }

    public static void error(String msg) {
        if (level <= 6) {
            Log.e(createTag(), msg);
        }
    }

    public static void error(String msg, Throwable tr) {
        if (level <= 6) {
            Log.e(createTag(), msg, tr);
        }
    }

    private static String createTag() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (!st.isNativeMethod() && !st.getClassName().equals(Thread.class.getName()) && !st.getClassName().equals(Logger.class.getName())) {
                return st.getLineNumber() + MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR + st.getFileName();
            }
        }
        return "";
    }
}
