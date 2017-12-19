package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.common.Common;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.util.StringUtility;
import com.cloudpos.jniinterface.PINPadInterface;
import java.util.Map;

public class PINPadAction extends ConstantAction {
    private int masterKeyID = 0;
    private int maxPINLength = 12;
    private int minPINLength = 4;
    private int userKeyID = 0;

    class PINBlockThread extends Thread {
        PINBlockThread() {
        }

        public void run() {
            PINPadAction.this.userKeyID = 0;
            PINPadAction.this.selectKey(PINPadAction.this.masterKeyID, PINPadAction.this.userKeyID);
            String pan = "1234567890123456789";
            byte[] arryPINblock = new byte[18];
            try {
                int result = PINPadInterface.calculatePINBlock(pan.getBytes(), pan.length(), arryPINblock, -1, 0);
                if (result < 0) {
                    PINPadAction.this.mCallback.sendFailedMsg(PINPadAction.this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                } else {
                    PINPadAction.this.mCallback.sendSuccessMsg("PIN Block = " + StringUtility.ByteArrayToString(arryPINblock, result));
                }
            } catch (Throwable e) {
                e.printStackTrace();
                PINPadAction.this.mCallback.sendFailedMsg(PINPadAction.this.mContext.getResources().getString(C0223R.string.operation_failed));
            }
        }
    }

    class C04701 implements DataAction {
        C04701() {
        }

        public int getResult() {
            PINPadAction.this.isOpened = false;
            return PINPadInterface.close();
        }
    }

    class C04712 implements DataAction {
        C04712() {
        }

        public int getResult() {
            return PINPadInterface.setPinLength(PINPadAction.this.minPINLength, 0);
        }
    }

    class C04723 implements DataAction {
        C04723() {
        }

        public int getResult() {
            return PINPadInterface.setPinLength(PINPadAction.this.maxPINLength, 1);
        }
    }

    class C04756 implements DataAction {
        C04756() {
        }

        public int getResult() {
            return PINPadInterface.showText(0, null, 0, 0);
        }
    }

    class C04767 implements DataAction {
        C04767() {
        }

        public int getResult() {
            return PINPadInterface.showText(1, null, 0, 0);
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
            int result = PINPadInterface.open();
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
        checkOpenedAndGetData(new C04701());
    }

    public void setPINLength(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04712());
        checkOpenedAndGetData(new C04723());
    }

    public void showText(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryTextLine0 = "Show Test:".getBytes();
        final byte[] arryTextLine1 = new byte[]{(byte) -125, (byte) -124, (byte) -123, (byte) -122, (byte) -121};
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.showText(0, arryTextLine0, arryTextLine0.length, 0);
            }
        });
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.showText(1, arryTextLine1, arryTextLine1.length, 0);
            }
        });
    }

    public void clearText(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04756());
        checkOpenedAndGetData(new C04767());
    }

    public void updateMasterKey(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryCipherNewMasterKey = new byte[16];
        StringUtility.StringToByteArray("09 FA 17 0B 03 11 22 76 09 FA 17 0B 03 11 22 76", arryCipherNewMasterKey);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.updateCipherMasterKey(0, arryCipherNewMasterKey, arryCipherNewMasterKey.length);
            }
        });
    }

    public void updateUserKey(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryCipherNewUserKey = new byte[16];
        StringUtility.StringToByteArray("09 FA 17 0B 03 11 22 76 09 FA 17 0B 03 11 22 76", arryCipherNewUserKey);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.updateUserKey(0, 0, arryCipherNewUserKey, arryCipherNewUserKey.length);
            }
        });
    }

    public void updateUserKeyWithCheck(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryCipherNewUserKey = new byte[16];
        StringUtility.StringToByteArray("09 FA 17 0B 03 11 22 76 09 FA 17 0B 03 11 22 76", arryCipherNewUserKey);
        final byte[] arryCheckValue = new byte[4];
        StringUtility.StringToByteArray("A5 17 3A D5", arryCheckValue);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.updateUserKeyWithCheck(0, 0, arryCipherNewUserKey, arryCipherNewUserKey.length, 0, arryCheckValue, arryCheckValue.length);
            }
        });
    }

    public void getSerialNo(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arrySerialNo = new byte[40];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = PINPadInterface.getSerialNo(arrySerialNo);
                if (result >= 0) {
                    PINPadAction.this.mCallback.sendSuccessMsg("SerialNo = " + StringUtility.ByteArrayToString(arrySerialNo, result));
                }
                return result;
            }
        });
    }

    private void selectKey(final int masterKeyID, final int userKeyID) {
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.selectKey(2, masterKeyID, userKeyID, 0);
            }
        });
    }

    public void selectKey(Map<String, Object> map, ActionCallbackImpl callback) {
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return PINPadInterface.selectKey(2, 0, 0, 0);
            }
        });
    }

    public void encryptData(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        this.userKeyID = 2;
        selectKey(this.masterKeyID, this.userKeyID);
        final byte[] arryPlainData = Common.createMasterKey(8);
        callback.sendSuccessMsg("Plain Data = " + StringUtility.ByteArrayToString(arryPlainData, 8));
        final byte[] arryCipherData = new byte[255];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = PINPadInterface.encrypt(arryPlainData, arryPlainData.length, arryCipherData);
                if (result >= 0) {
                    PINPadAction.this.mCallback.sendSuccessMsg("Encrypted Data = " + StringUtility.ByteArrayToString(arryCipherData, result));
                }
                return result;
            }
        });
    }

    public void calculateMAC(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        this.userKeyID = 1;
        selectKey(this.masterKeyID, this.userKeyID);
        final byte[] arryMACInData = Common.createMasterKey(8);
        callback.sendSuccessMsg("MAC In Data = " + StringUtility.ByteArrayToString(arryMACInData, 8));
        final byte[] arryMACOutData = new byte[32];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = PINPadInterface.calculateMac(arryMACInData, arryMACInData.length, 0, arryMACOutData);
                if (result >= 0) {
                    PINPadAction.this.mCallback.sendSuccessMsg("MAC Out Data = " + StringUtility.ByteArrayToString(arryMACOutData, result));
                }
                return result;
            }
        });
    }

    public void calculatePINBlock(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        new PINBlockThread().start();
    }
}
