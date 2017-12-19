package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.jniinterface.LEDInterface;
import java.util.Map;

public class LEDAction extends ConstantAction {
    private static int index = 0;

    class C04621 implements DataAction {
        C04621() {
        }

        public int getResult() {
            LEDAction.this.isOpened = false;
            return LEDInterface.close();
        }
    }

    class C04632 implements DataAction {
        C04632() {
        }

        public int getResult() {
            return LEDInterface.turnOn(LEDAction.index);
        }
    }

    class C04643 implements DataAction {
        C04643() {
        }

        public int getResult() {
            return LEDInterface.turnOff(LEDAction.index);
        }
    }

    class C04654 implements DataAction {
        C04654() {
        }

        public int getResult() {
            int result = LEDInterface.getStatus(LEDAction.index);
            if (result == 0) {
                LEDAction.this.mCallback.sendSuccessMsg("LED " + LEDAction.index + " is OFF");
            } else if (result > 0) {
                LEDAction.this.mCallback.sendSuccessMsg("LED " + LEDAction.index + " is ON");
            }
            return result;
        }
    }

    private void setParams(Map<String, Object> map, ActionCallbackImpl callback) {
        this.mCallback = callback;
    }

    public void open(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        if (this.isOpened) {
            callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.device_opened));
            return;
        }
        try {
            int result = LEDInterface.open();
            if (result < 0) {
                callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.isOpened = true;
            callback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
        } catch (Throwable e) {
            e.printStackTrace();
            callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void close(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04621());
    }

    public void turnOn(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04632());
    }

    public void turnOff(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04643());
    }

    public void getStatus(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04654());
    }
}
