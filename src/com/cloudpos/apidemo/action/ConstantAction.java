package com.cloudpos.apidemo.action;

import android.util.Log;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.mvc.base.AbstractAction;
import com.cloudpos.mvc.base.ActionCallback;
import java.util.Map;

public class ConstantAction extends AbstractAction {
    protected static final int EVENT_ID_CANCEL = -1;
    public static String TAG = null;
    public boolean isOpened = false;
    protected ActionCallbackImpl mCallback;

    protected void doBefore(Map<String, Object> map, ActionCallback callback) {
        TAG = getClass().getName().substring(getClass().getName().indexOf("action.") + "action.".length());
        Log.e("TAG", "TAG = " + TAG);
    }

    int checkOpenedAndGetData(DataAction action) {
        int result = -1;
        if (this.isOpened) {
            try {
                result = action.getResult();
                if (result < 0) {
                    this.mCallback.sendFailedMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                } else {
                    this.mCallback.sendSuccessMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_successful));
                }
            } catch (Throwable e) {
                e.printStackTrace();
                this.mCallback.sendFailedMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_failed));
            }
        } else {
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.device_not_open));
        }
        return result;
    }

    int getData(DataAction action) {
        int result = 0;
        try {
            result = action.getResult();
            if (result < 0) {
                this.mCallback.sendFailedMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            } else {
                this.mCallback.sendSuccessMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_successful));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsgInCheck(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
        return result;
    }
}
