package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.jniinterface.CashDrawerInterface;
import java.util.Map;

public class CashDrawerAction extends ConstantAction {

    class C04471 implements DataAction {
        C04471() {
        }

        public int getResult() {
            CashDrawerAction.this.isOpened = false;
            return CashDrawerInterface.close();
        }
    }

    class C04482 implements DataAction {
        C04482() {
        }

        public int getResult() {
            return CashDrawerInterface.kickOut();
        }
    }

    private void setParams(Map<String, Object> map, ActionCallbackImpl callback) {
        this.mCallback = callback;
    }

    public void open(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        if (this.isOpened) {
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.device_opened));
            return;
        }
        try {
            int result = CashDrawerInterface.open();
            if (result < 0) {
                this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.isOpened = true;
            this.mCallback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void close(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04471());
    }

    public void kickOut(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04482());
    }
}
