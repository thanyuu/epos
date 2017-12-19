package com.cloudpos.apidemo.function;

import android.os.Handler;
import android.os.Message;
import com.android.common.utils.ShellUtils;
import com.cloudpos.apidemo.common.Common;
import com.cloudpos.mvc.base.ActionCallback;

public class ActionCallbackImpl extends ActionCallback {
    private Handler handler;

    public ActionCallbackImpl(Handler handler) {
        this.handler = handler;
    }

    public void sendResponse(int code, String msgString) {
        Message msg = new Message();
        msg.what = code;
        msg.obj = "\t\t" + msgString + ShellUtils.COMMAND_LINE_END;
        this.handler.sendMessage(msg);
    }

    public void sendResponse(String msgString) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = "\t\t" + msgString + ShellUtils.COMMAND_LINE_END;
        this.handler.sendMessage(msg);
    }

    public void sendFailedMsg(String msg) {
        sendResponse(3, msg);
    }

    public void sendSuccessMsg(String msg) {
        sendResponse(2, msg);
    }

    public void sendFailedMsgInCheck(String msg) {
        sendResponse(3, Common.getMethodName() + " " + msg);
    }

    public void sendSuccessMsgInCheck(String msg) {
        sendResponse(2, Common.getMethodName() + " " + msg);
    }
}
