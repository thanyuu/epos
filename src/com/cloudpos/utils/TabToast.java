package com.cloudpos.utils;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class TabToast {
    private static final int DEFAULT_BG_COLOR = -420124326;
    private static final int DEFAULT_TEXT_COLOR = -1;
    private static final float DEFAULT_TEXT_SIZE = 14.0f;
    private static final float DEFAULT_TOAST_HEIGHT = 50.0f;
    private static Context mContext;
    private static volatile TabToast mInstance;
    private static Toast mToast;
    private View layout;
    private TextView tv;

    public TabToast(Context context) {
        mContext = context;
    }

    private static TabToast getInstance(Context context) {
        if (mInstance == null) {
            synchronized (TabToast.class) {
                if (mInstance == null) {
                    mInstance = new TabToast(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private static void getToast(int duration) {
        int i = 1;
        if (mToast == null) {
            mToast = new Toast(mContext);
            mToast.setGravity(80, 0, 0);
            Toast toast = mToast;
            if (duration != 1) {
                i = 0;
            }
            toast.setDuration(i);
        }
    }

    public static void makeText(Context context, String text) {
        makeText(context, text, 0);
    }

    public static void makeText(Context context, String text, int duration) {
        getInstance(context);
        getToast(duration);
        if (mInstance.layout == null || mInstance.tv == null) {
            LinearLayout container = new LinearLayout(mContext);
            container.setLayoutParams(new LayoutParams(-1, -2));
            container.setBackgroundColor(DEFAULT_BG_COLOR);
            container.setGravity(17);
            mInstance.tv = new TextView(mContext);
            mInstance.tv.setLayoutParams(new LayoutParams(getScreenWidth(mContext), dp2px(DEFAULT_TOAST_HEIGHT)));
            mInstance.tv.setPadding(dp2px(5.0f), dp2px(2.0f), dp2px(5.0f), dp2px(2.0f));
            mInstance.tv.setGravity(17);
            mInstance.tv.setTextColor(-1);
            mInstance.tv.setMaxLines(2);
            mInstance.tv.setEllipsize(TruncateAt.END);
            mInstance.tv.setBackgroundColor(DEFAULT_BG_COLOR);
            mInstance.tv.setTextSize(DEFAULT_TEXT_SIZE);
            container.addView(mInstance.tv);
            mInstance.layout = container;
            mToast.setView(mInstance.layout);
        }
        mInstance.tv.setText(text);
        mToast.show();
    }

    public static int dp2px(float value) {
        return (int) ((value * mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService("window");
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return (int) (((float) outMetrics.widthPixels) * outMetrics.density);
    }
}
