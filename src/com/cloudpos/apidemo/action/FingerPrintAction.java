package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.jniinterface.FingerPrintInterface;
import java.util.Map;

public class FingerPrintAction extends ConstantAction {
    static byte[] arryFea1 = new byte[4096];

    class C04541 implements DataAction {
        C04541() {
        }

        public int getResult() {
            FingerPrintAction.this.isOpened = false;
            return FingerPrintInterface.close();
        }
    }

    class C04596 implements DataAction {
        C04596() {
        }

        public int getResult() {
            return FingerPrintInterface.cancel();
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
            int result = FingerPrintInterface.open();
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
        checkOpenedAndGetData(new C04541());
    }

    public void getFea(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final int[] pRealFeaLength = new int[1];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return FingerPrintInterface.getFea(FingerPrintAction.arryFea1, 4096, pRealFeaLength, -1);
            }
        });
    }

    public void getLastImage(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] pImgBuffer = new byte[33792];
        final int[] pRealImaLength = new int[1];
        final int[] pImgWidth = new int[1];
        final int[] pImgHeight = new int[1];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return FingerPrintInterface.getLastImage(pImgBuffer, 33792, pRealImaLength, pImgWidth, pImgHeight);
            }
        });
    }

    public void getFea(final byte[] arryFea) {
        final int[] pRealFeaLength = new int[1];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return FingerPrintInterface.getFea(arryFea, 4096, pRealFeaLength, -1);
            }
        });
    }

    public void match(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryFea2 = new byte[4096];
        getFea(arryFea2);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return FingerPrintInterface.match(FingerPrintAction.arryFea1, FingerPrintAction.arryFea1.length, arryFea2, arryFea2.length);
            }
        });
    }

    public void cancel(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04596());
    }
}
