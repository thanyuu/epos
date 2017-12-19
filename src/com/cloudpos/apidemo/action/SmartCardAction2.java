package com.cloudpos.apidemo.action;

import android.util.Log;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.common.Common;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.util.StringUtility;
import com.cloudpos.jniinterface.SmartCardEvent;
import com.cloudpos.jniinterface.SmartCardInterface;
import com.cloudpos.jniinterface.SmartCardSlotInfo;
import java.util.Map;

public class SmartCardAction2 extends ConstantAction {
    private static boolean isRun = false;
    private int handle = 0;
    private int slotIndex = 1;

    class CallBackThread extends Thread {
        private Object object;

        public CallBackThread(ActionCallbackImpl callback, Object object) {
            this.object = object;
        }

        public void run() {
            SmartCardAction2.isRun = true;
            while (SmartCardAction2.isRun) {
                synchronized (this.object) {
                    try {
                        this.object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (SmartCardInterface.event.nEventID == -1) {
                    break;
                } else if (SmartCardInterface.event.nEventID == SmartCardEvent.SMART_CARD_EVENT_INSERT_CARD) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg(String.format("SlotIndex : %d Event : %s", new Object[]{Integer.valueOf(SmartCardInterface.event.nSlotIndex), "Inserted"}));
                } else if (SmartCardInterface.event.nEventID == SmartCardEvent.SMART_CARD_EVENT_REMOVE_CARD) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg(String.format("SlotIndex : %d Event : %s", new Object[]{Integer.valueOf(SmartCardInterface.event.nSlotIndex), "Removed"}));
                }
            }
            SmartCardAction2.isRun = false;
        }
    }

    class C05041 implements DataAction {
        C05041() {
        }

        public int getResult() {
            return SmartCardInterface.queryMaxNumber();
        }
    }

    class C05052 implements DataAction {
        C05052() {
        }

        public int getResult() {
            return SmartCardInterface.queryPresence(SmartCardAction2.this.slotIndex);
        }
    }

    class C05063 implements DataAction {
        C05063() {
        }

        public int getResult() {
            SmartCardAction2.this.isOpened = false;
            Log.e(ConstantAction.TAG, "handle = " + SmartCardAction2.this.handle);
            return SmartCardInterface.close(SmartCardAction2.this.handle);
        }
    }

    class C05085 implements DataAction {
        C05085() {
        }

        public int getResult() {
            return SmartCardInterface.powerOff(SmartCardAction2.this.handle);
        }
    }

    private void setParams(Map<String, Object> map, ActionCallbackImpl callback) {
        this.mCallback = callback;
    }

    public void queryMaxNumber(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        int result = getData(new C05041());
        if (result >= 0) {
            this.mCallback.sendSuccessMsg("Max Slot Number = " + result);
        }
    }

    public void queryPresence(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        int result = getData(new C05052());
        if (result >= 0) {
            String str = "SlotIndex : %d Event : %s";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(this.slotIndex);
            objArr[1] = result == 0 ? "Absence" : "Presence";
            this.mCallback.sendSuccessMsg(String.format(str, objArr));
        }
    }

    public void open(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        if (this.isOpened) {
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.device_opened));
            return;
        }
        try {
            int result = SmartCardInterface.open(this.slotIndex);
            if (result < 0) {
                this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.isOpened = true;
            this.handle = result;
            this.mCallback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void close(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C05063());
    }

    public void powerOn(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final SmartCardSlotInfo slotInfo = new SmartCardSlotInfo();
        final byte[] arryATR = new byte[64];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = SmartCardInterface.powerOn(SmartCardAction2.this.handle, arryATR, slotInfo);
                if (result >= 0) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg("Data = " + StringUtility.ByteArrayToString(arryATR, result));
                }
                return result;
            }
        });
    }

    public void powerOff(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C05085());
    }

    public void transmit(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryAPDU = new byte[]{(byte) 0, (byte) -124, (byte) 0, (byte) 0, (byte) 8};
        final byte[] arryResponse = new byte[32];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = SmartCardInterface.transmit(SmartCardAction2.this.handle, arryAPDU, arryResponse);
                if (result >= 0) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg("APDUResponse = " + StringUtility.ByteArrayToString(arryResponse, result));
                }
                return result;
            }
        });
    }

    public void verify(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryKey = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return SmartCardInterface.verify(SmartCardAction2.this.handle, arryKey);
            }
        });
    }

    public void read(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryData = new byte[16];
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = SmartCardInterface.read(SmartCardAction2.this.handle, 0, arryData, 0);
                if (result >= 0) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg("Read data = " + StringUtility.ByteArrayToString(arryData, result));
                }
                return result;
            }
        });
    }

    public void write(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final byte[] arryData = Common.createMasterKey(16);
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                int result = SmartCardInterface.read(SmartCardAction2.this.handle, 0, arryData, 0);
                if (result >= 0) {
                    SmartCardAction2.this.mCallback.sendSuccessMsg("Written data = " + StringUtility.ByteArrayToString(arryData, result));
                }
                return result;
            }
        });
    }

    public void cancelRequest() {
        if (isRun) {
            synchronized (SmartCardInterface.objPresent) {
                SmartCardInterface.event = new SmartCardEvent();
                SmartCardInterface.event.nEventID = -1;
                SmartCardInterface.objPresent.notifyAll();
            }
            synchronized (SmartCardInterface.objAbsent) {
                SmartCardInterface.event = new SmartCardEvent();
                SmartCardInterface.event.nEventID = -1;
                SmartCardInterface.objAbsent.notifyAll();
            }
            return;
        }
        this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
    }
}
