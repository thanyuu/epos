package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.util.StringUtility;
import com.cloudpos.jniinterface.SerialPortInterface;
import com.cloudpos.jniinterface.SerialPortInterface2;
import java.util.Map;

public class SerialPortAction extends ConstantAction {
    private static String deviceName = "";
    private int baudate = 115200;
    private String testString = "wizarpos";

    class C02161 extends Thread {
        C02161() {
        }

        public void run() {
            SerialPortAction.this.open("PRINTER_WARNING_1");
            SerialPortAction.this.write();
            SerialPortAction.this.close();
        }
    }

    class C02172 extends Thread {
        C02172() {
        }

        public void run() {
            SerialPortAction.this.open2("/dev/s3c2410_serial2");
            SerialPortAction.this.write2();
            SerialPortAction.this.read2();
            SerialPortAction.this.close2();
        }
    }

    class C02183 extends Thread {
        C02183() {
        }

        public void run() {
            SerialPortAction.this.sendMessage();
        }
    }

    class C02194 extends Thread {
        C02194() {
        }

        public void run() {
            SerialPortAction.this.receiveMessage();
        }
    }

    private void setParams(Map<String, Object> map, ActionCallbackImpl callback) {
        this.mCallback = callback;
    }

    private void open(String deviceName) {
        try {
            int result = SerialPortInterface.open(deviceName);
            if (result == 0) {
                this.mCallback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.mCallback.sendSuccessMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_successful));
            setBaudrate();
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void close() {
        try {
            int result = SerialPortInterface.close();
            if (result < 0) {
                this.isOpened = false;
                this.mCallback.sendFailedMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.mCallback.sendSuccessMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_successful));
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void open2(String deviceName) {
        try {
            int result = SerialPortInterface2.open(deviceName);
            if (result == 0) {
                this.mCallback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            this.mCallback.sendSuccessMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_successful));
            setBaudrate2();
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg("open " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void close2() {
        try {
            int result = SerialPortInterface2.close();
            if (result < 0) {
                this.mCallback.sendFailedMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            } else {
                this.mCallback.sendSuccessMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_successful));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg("close " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void read2() {
        byte[] arryData = new byte[this.testString.length()];
        int length = this.testString.length();
        int result = 0;
        try {
            result = SerialPortInterface2.read(arryData, 0, length, 3000);
            if (result < 0) {
                this.mCallback.sendFailedMsg("read " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            } else {
                this.mCallback.sendSuccessMsg("Data = " + StringUtility.ByteArrayToString(arryData, result));
            }
        } catch (Throwable th) {
            this.mCallback.sendFailedMsg("read " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
        if (result > 0) {
            this.mCallback.sendSuccessMsg("Read Data =  " + StringUtility.ByteArrayToString(arryData, length));
        }
    }

    private void write() {
        byte[] arryData = new String(this.testString).getBytes();
        this.mCallback.sendSuccessMsg("Write Data =  " + StringUtility.ByteArrayToString(arryData, 2, 2));
        try {
            int result = SerialPortInterface.write(arryData, 2, 2);
            if (result < 0) {
                this.mCallback.sendFailedMsg("write " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            }
        } catch (Throwable th) {
            this.mCallback.sendFailedMsg("write " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void write2() {
        byte[] arryData = new String(this.testString).getBytes();
        int length = arryData.length;
        this.mCallback.sendSuccessMsg("Write Data =  " + StringUtility.ByteArrayToString(arryData, 2, length));
        try {
            int result = SerialPortInterface2.write(arryData, length);
            if (result < 0) {
                this.mCallback.sendFailedMsg("write " + this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            }
        } catch (Throwable th) {
            this.mCallback.sendFailedMsg("write " + this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void setBaudrate() {
        try {
            int result = SerialPortInterface.setBaudrate(this.baudate);
            if (result < 0) {
                this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            } else {
                this.mCallback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void setBaudrate2() {
        try {
            int result = SerialPortInterface2.setBaudrate(this.baudate);
            if (result < 0) {
                this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_with_error) + result);
            } else {
                this.mCallback.sendSuccessMsg(this.mContext.getResources().getString(C0223R.string.operation_successful));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            this.mCallback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.operation_failed));
        }
    }

    public void sendMessage() {
        open("PRINTER_WARNING_1");
        write();
        close();
    }

    public void receiveMessage() {
        open2("PRINTER_WARNING_2");
        read2();
        close2();
    }

    public void writeMessage(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        new C02161().start();
    }

    public void readMessage(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        new C02172().start();
    }

    public void exchangeMessage(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        new C02183().start();
        new C02194().start();
    }
}
