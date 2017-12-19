package com.cloudpos.apidemo.util;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class LogHelper {
    @Deprecated
    public static void infoException(TextView text, String infoMsg) {
        text.setText(infoMsg, BufferType.SPANNABLE);
        int start = text.getText().length();
        ((Spannable) text.getText()).setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), start, start + infoMsg.length(), 33);
    }

    @Deprecated
    public static void info(TextView text, String infoMsg) {
        text.setText(infoMsg);
    }

    @Deprecated
    public static void infoWarning(TextView text, String infoMsg) {
        text.setText(infoMsg);
        Spannable style = (Spannable) text.getText();
        int start = text.getText().length();
        style.setSpan(new ForegroundColorSpan(InputDeviceCompat.SOURCE_ANY), start, start + infoMsg.length(), 33);
    }

    public static void infoMsgAndColor(TextView text, String infoMsg, int order) {
        ForegroundColorSpan color;
        text.setText(infoMsg);
        Spannable style = (Spannable) text.getText();
        int end = 0 + infoMsg.length();
        switch (order) {
            case 1:
                color = new ForegroundColorSpan(-16777216);
                break;
            case 2:
                color = new ForegroundColorSpan(InputDeviceCompat.SOURCE_ANY);
                break;
            case 3:
                color = new ForegroundColorSpan(-16776961);
                break;
            case 4:
                color = new ForegroundColorSpan(SupportMenu.CATEGORY_MASK);
                break;
            default:
                color = new ForegroundColorSpan(-16777216);
                break;
        }
        style.setSpan(color, 0, end, 33);
    }

    public static void infoAppendMsgAndColor(TextView text, String infoMsg, int order) {
        ForegroundColorSpan color;
        int start = 0;
        if (text.getText().length() != 0) {
            start = text.getText().length();
        }
        text.append(infoMsg);
        Spannable style = (Spannable) text.getText();
        int end = start + infoMsg.length();
        switch (order) {
            case 1:
                color = new ForegroundColorSpan(-16777216);
                break;
            case 2:
                color = new ForegroundColorSpan(InputDeviceCompat.SOURCE_ANY);
                break;
            case 3:
                color = new ForegroundColorSpan(-16776961);
                break;
            case 4:
                color = new ForegroundColorSpan(SupportMenu.CATEGORY_MASK);
                break;
            case 5:
                color = new ForegroundColorSpan(SupportMenu.CATEGORY_MASK);
                break;
            default:
                color = new ForegroundColorSpan(InputDeviceCompat.SOURCE_ANY);
                break;
        }
        style.setSpan(color, start, end, 33);
    }

    public static void infoAppendMsgForSuccess(String msg, TextView text) {
    }

    public static void infoAppendMsgForFailed(String msg, TextView text) {
    }

    public static void infoAppendMsg(String msg, TextView text) {
    }

    private static void moveScroller(TextView text) {
        int scrollAmount = text.getLayout().getLineTop(text.getLineCount()) - text.getHeight();
        if (scrollAmount > 0) {
            text.scrollTo(0, scrollAmount + 30);
        } else {
            text.scrollTo(0, 0);
        }
    }
}
