package com.cloudpos.mvc.base;

import android.content.Context;
import java.util.Map;

public abstract class AbstractAction {
    protected Context mContext;

    public void setContext(Context context) {
        this.mContext = context;
    }

    protected void doBefore(Map<String, Object> map, ActionCallback callback) {
    }

    protected void doAfter(Map<String, Object> map, ActionCallback callback) {
    }
}
