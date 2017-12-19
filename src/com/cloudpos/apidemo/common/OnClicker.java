package com.cloudpos.apidemo.common;

import android.app.Activity;
import android.view.View.OnClickListener;

public class OnClicker {
    public static void setOnClickListenerByIds(Activity host, int[] widget, OnClickListener listener) {
        for (int viewId : widget) {
            setOnClickListenerById(host, viewId, listener);
        }
    }

    public static void setOnClickListenerById(Activity host, int viewId, OnClickListener listener) {
        host.findViewById(viewId).setOnClickListener(listener);
    }
}
