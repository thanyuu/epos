package com.cloudpos.apidemo.util;

import android.support.v4.internal.view.SupportMenu;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

public class TextViewUtil {
    public static void infoColorfulTextView(TextView log_text, String msg, int textColor) {
        int start = 0;
        if (log_text.getText().length() != 0) {
            start = log_text.getText().length();
        }
        log_text.append(msg);
        ((Spannable) log_text.getText()).setSpan(new ForegroundColorSpan(textColor), start, start + msg.length(), 33);
        moveScroller(log_text);
    }

    public static void infoRedTextView(TextView log_text, String msg) {
        infoColorfulTextView(log_text, msg, SupportMenu.CATEGORY_MASK);
    }

    public static void infoBlueTextView(TextView log_text, String msg) {
        infoColorfulTextView(log_text, msg, -16776961);
    }

    public static void infoMAGENTATextView(TextView log_text, String msg) {
        infoColorfulTextView(log_text, msg, -65281);
    }

    private static void moveScroller(TextView text) {
        int scrollAmount = text.getLayout().getLineTop(text.getLineCount()) - 260;
        Log.e("nihao", "wo hao maLineCount()):" + text.getLayout().getLineTop(text.getLineCount()) + "text.getHeight():" + text.getHeight());
        if (scrollAmount > 0) {
            text.scrollTo(0, scrollAmount);
            Log.e("nihao", "wo hao mascrollAmount:" + scrollAmount + "text.getHeight():" + text.getHeight());
            return;
        }
        text.scrollTo(0, 0);
        Log.e("nihao", "wo hao");
    }
}
