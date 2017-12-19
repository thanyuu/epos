package com.cloudpos.apidemo.action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.common.BitmapConvert;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.jniinterface.SecondaryDisplayInterface;
import java.util.Map;

public class CustomerDisplayAction extends ConstantAction {

    class C04491 implements DataAction {
        C04491() {
        }

        public int getResult() {
            CustomerDisplayAction.this.isOpened = false;
            return SecondaryDisplayInterface.close();
        }
    }

    class C04502 implements DataAction {
        C04502() {
        }

        public int getResult() {
            return SecondaryDisplayInterface.buzzerBeep();
        }
    }

    class C04513 implements DataAction {
        C04513() {
        }

        public int getResult() {
            return SecondaryDisplayInterface.displayDefaultScreen();
        }
    }

    class C04524 implements DataAction {
        C04524() {
        }

        public int getResult() {
            return SecondaryDisplayInterface.setBackground(31);
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
            int result = SecondaryDisplayInterface.open();
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
        checkOpenedAndGetData(new C04491());
    }

    public void buzzerBeep(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04502());
    }

    public void displayDefaultScreen(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04513());
    }

    public void setBackground(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04524());
    }

    public void writePicture(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final Bitmap bitmap = BitmapFactory.decodeResource(this.mContext.getResources(), C0223R.drawable.customer_display_picture);
        final byte[] arryBitmapData = BitmapConvert.Bitmap2Bytes(bitmap);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return SecondaryDisplayInterface.writePicture(0, 0, bitmap.getWidth(), bitmap.getHeight(), arryBitmapData, arryBitmapData.length);
            }
        });
    }
}
