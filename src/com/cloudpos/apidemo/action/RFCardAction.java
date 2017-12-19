package com.cloudpos.apidemo.action;

import android.util.Log;
import com.android.common.utils.SizeUtils;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.common.Common;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.util.ByteConvert;
import com.cloudpos.apidemo.util.StringUtility;
import com.cloudpos.jniinterface.RFCardInterface;
import java.util.Map;

public class RFCardAction extends ConstantAction {
    private int blockIndex = 1;
    private int pinType = 0;
    private int sectorIndex = 0;

    class CallBackThread extends Thread {
        CallBackThread() {
        }

        public void run() {
            synchronized (RFCardInterface.object) {
                try {
                    RFCardInterface.object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (RFCardInterface.nEventID == 0) {
                RFCardAction.this.mCallback.sendSuccessMsg("ATS = " + StringUtility.ByteArrayToString(RFCardInterface.arryEventData, RFCardInterface.arryEventData.length));
            }
        }
    }

    class C04861 implements DataAction {
        C04861() {
        }

        public int getResult() {
            RFCardAction.this.isOpened = false;
            return RFCardInterface.close();
        }
    }

    class C04872 implements DataAction {
        C04872() {
        }

        public int getResult() {
            return RFCardInterface.searchBegin(RFCardInterface.CONTACTLESS_CARD_MODE_AUTO, 1, -1);
        }
    }

    class C04883 implements DataAction {
        C04883() {
        }

        public int getResult() {
            return RFCardInterface.searchEnd();
        }
    }

    class C04949 implements DataAction {
        C04949() {
        }

        public int getResult() {
            return RFCardInterface.detach();
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
            int result = RFCardInterface.open();
            if (result < 0) {
                callback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.isOpened = true;
            callback.sendSuccessMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_successful));
            new CallBackThread().start();
            Thread.sleep(100);
            searchBegin();
        } catch (Throwable e) {
            e.printStackTrace();
            callback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void close(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        searchEnd();
        checkOpenedAndGetData(new C04861());
    }

    public void searchBegin() {
        checkOpenedAndGetData(new C04872());
    }

    public void searchEnd() {
        checkOpenedAndGetData(new C04883());
    }

    public void queryInfo(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final int[] hasMoreCard = new int[1];
        final int[] cardType = new int[1];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.queryInfo(hasMoreCard, cardType);
            }
        });
        Log.e(TAG, "hasMoreCard = " + hasMoreCard[0]);
        Log.e(TAG, "cardType = " + cardType[0]);
        if (result < 0) {
            return;
        }
        if (hasMoreCard[0] != 0) {
            callback.sendFailedMsg("There is more than one card in the field!");
        } else if (cardType[0] == 0 || cardType[0] == 256) {
            callback.sendSuccessMsg("CPU_Card");
        } else if (cardType[0] == 1 || cardType[0] == 2 || cardType[0] == 3) {
            callback.sendSuccessMsg("Mifare_Card");
        } else if (cardType[0] == 4 || cardType[0] == 5) {
            callback.sendSuccessMsg("Mifare_Ultralight_Card");
        } else {
            callback.sendSuccessMsg("Unknown_Type_Card");
        }
    }

    public void verify(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryKey = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.verify(RFCardAction.this.sectorIndex, RFCardAction.this.pinType, arryKey, arryKey.length);
            }
        });
    }

    public void read(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryData = new byte[16];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.read(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, arryData, arryData.length);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("Read Data = " + StringUtility.ByteArrayToString(arryData, result));
        }
    }

    public void write(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryData = Common.createMasterKey(16);
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.write(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, arryData, arryData.length);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("Written Data = " + StringUtility.ByteArrayToString(arryData, result));
        }
    }

    public void attach(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryATR = new byte[255];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.attach(arryATR);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("ATR = " + StringUtility.ByteArrayToString(arryATR, result));
        }
    }

    public void detach(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04949());
    }

    public void transmit(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryAPDU = new byte[]{(byte) 0, (byte) -124, (byte) 0, (byte) 0, (byte) 8};
        final byte[] arryAPDUResponse = new byte[255];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.transmit(arryAPDU, arryAPDU.length, arryAPDUResponse);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("APDU Response = " + StringUtility.ByteArrayToString(arryAPDUResponse, result));
        }
    }

    public void readMoneyValue(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryMoney = new byte[4];
        final byte[] arryUserData = new byte[1];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.readValue(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, arryMoney, arryMoney.length, arryUserData);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("Read Money Value = " + StringUtility.ByteArrayToString(arryMoney, result));
        }
    }

    public void readMoneyValue2(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryMoney = new byte[4];
        final byte[] arryUserData = new byte[1];
        int result = checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.readValue(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex + 1, arryMoney, arryMoney.length, arryUserData);
            }
        });
        if (result >= 0) {
            callback.sendSuccessMsg("Read Money Value = " + StringUtility.ByteArrayToString(arryMoney, result));
        }
    }

    public void writeMoneyValue(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final int money = (int) Math.round((((Math.random() * 2.0d) - 1.0d) * ((double) SizeUtils.GB_2_BYTE)) * 2.0d);
        if (checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.writeValue(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, money, 4, (byte) 0);
            }
        }) >= 0) {
            callback.sendSuccessMsg("Written Money Value = " + StringUtility.ByteArrayToString(ByteConvert.int2byte4(money), 4));
        }
    }

    public void increment(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        restore(param, callback);
        if (checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.increment(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, 2);
            }
        }) >= 0) {
            callback.sendSuccessMsg("Increment Value = " + StringUtility.ByteArrayToString(ByteConvert.int2byte4(2), 4));
        }
        transfer(param, callback);
    }

    public void decrement(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        if (checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.decrement(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex, 2);
            }
        }) >= 0) {
            callback.sendSuccessMsg("Decrement Value = " + StringUtility.ByteArrayToString(ByteConvert.int2byte4(2), 4));
        }
        transfer2(param, callback);
    }

    public void transfer(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.transfer(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex + 1);
            }
        });
    }

    public void transfer2(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.transfer(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex);
            }
        });
    }

    public void restore(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return RFCardInterface.restore(RFCardAction.this.sectorIndex, RFCardAction.this.blockIndex);
            }
        });
    }
}
