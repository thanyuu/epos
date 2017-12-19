package com.cloudpos.mvc.base;

import android.content.Context;

public abstract class ActionCallback {
    protected Context context;

    public ActionCallback(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return getContext();
    }

    public void sendResponse(int code) {
    }

    public void sendResponse(String msg) {
    }

    public void sendResponse(int code, String msg) {
    }
}
