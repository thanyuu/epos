package com.cloudpos.apidemo.function;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import com.cloudpos.apidemo.util.LogHelper;

public class HandlerImpl extends Handler {
    private TextView txtResult;

    public HandlerImpl(TextView textView) {
        this.txtResult = textView;
    }

    public void handleMessage(Message msg) {
        Log.e("==========", "handleMessage: " + msg.obj);
        switch (msg.what) {
            case 1:
                LogHelper.infoAppendMsg((String) msg.obj, this.txtResult);
                return;
            case 2:
                LogHelper.infoAppendMsgForSuccess((String) msg.obj, this.txtResult);
                return;
            case 3:
                LogHelper.infoAppendMsgForFailed((String) msg.obj, this.txtResult);
                return;
            default:
                LogHelper.infoAppendMsg((String) msg.obj, this.txtResult);
                return;
        }
    }
}
