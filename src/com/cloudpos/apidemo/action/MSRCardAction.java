package com.cloudpos.apidemo.action;

import android.util.Log;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.jniinterface.MSRInterface;
import java.util.Map;

public class MSRCardAction extends ConstantAction {

    class CallBackThread extends Thread {
        CallBackThread() {
        }

        public void run() {
            synchronized (MSRInterface.object) {
                try {
                    MSRInterface.object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (MSRInterface.eventID == MSRInterface.CONTACTLESS_CARD_EVENT_FOUND_CARD) {
                MSRCardAction.this.mCallback.sendSuccessMsg("Find a card");
                for (int trackNo = 0; trackNo < MSRInterface.TRACK_COUNT; trackNo++) {
                    if (MSRCardAction.this.getTrackError(trackNo) >= 0) {
                        int result = MSRCardAction.this.getTrackDataLength(trackNo);
                        if (result >= 0) {
                            if (MSRCardAction.this.getTrackData(trackNo, new byte[result]) < 0) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            } else if (MSRInterface.eventID == -1) {
                MSRCardAction.this.mCallback.sendSuccessMsg("Cancel notifier");
            }
        }
    }

    class C04661 implements DataAction {
        C04661() {
        }

        public int getResult() {
            MSRCardAction.this.isOpened = false;
            return MSRInterface.close();
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
            int result = MSRInterface.open();
            if (result < 0) {
                callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.isOpened = true;
            callback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
            new CallBackThread().start();
        } catch (Throwable e) {
            e.printStackTrace();
            callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void close(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        cancelCallBack();
        checkOpenedAndGetData(new C04661());
    }

    public void cancelCallBack() {
        synchronized (MSRInterface.object) {
            Log.i("MSRCard", "notify");
            MSRInterface.object.notifyAll();
            MSRInterface.eventID = -1;
        }
    }

    private int getTrackData(final int trackNo, final byte[] arryData) {
        return checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return MSRInterface.getTrackData(trackNo, arryData, arryData.length);
            }
        });
    }

    private int getTrackDataLength(final int trackNo) {
        return checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return MSRInterface.getTrackDataLength(trackNo);
            }
        });
    }

    private int getTrackError(final int trackNo) {
        return checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                return MSRInterface.getTrackError(trackNo);
            }
        });
    }
}
