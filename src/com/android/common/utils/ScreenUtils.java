package com.android.common.utils;

import android.content.Context;

public class ScreenUtils {
    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1.0f;
        }
        return context.getResources().getDisplayMetrics().density * dp;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1.0f;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPxInt(Context context, float dp) {
        return (float) ((int) (dpToPx(context, dp) + 0.5f));
    }

    public static float pxToDpCeilInt(Context context, float px) {
        return (float) ((int) (pxToDp(context, px) + 0.5f));
    }
}
