package com.cloudpos.apidemo.action;

import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.common.PrintBean;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.function.printer.PrintTagForQ1.PurchaseBillTag;
import com.cloudpos.apidemo.function.printer.PrinterBitmapUtil;
import com.cloudpos.apidemo.function.printer.PrinterCommand;
import com.cloudpos.jniinterface.PrinterInterface;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class PrinterAction extends ConstantAction {

    class C04802 implements DataAction {
        C04802() {
        }

        public int getResult() {
            PrinterAction.this.isOpened = false;
            return PrinterInterface.close();
        }
    }

    class C04813 implements DataAction {
        C04813() {
        }

        public int getResult() {
            PrinterAction.this.isOpened = false;
            return PrinterInterface.close();
        }
    }

    class C04824 implements DataAction {
        C04824() {
        }

        public int getResult() {
            int result = PrinterInterface.queryStatus();
            if (result > 0) {
                PrinterAction.this.mCallback.sendSuccessMsg("PAPER_ON");
            } else if (result == 0) {
                PrinterAction.this.mCallback.sendFailedMsg("PAPER_OUT");
            }
            return result;
        }
    }

    class C04835 implements DataAction {
        C04835() {
        }

        public int getResult() {
            return PrinterInterface.begin();
        }
    }

    class C04846 implements DataAction {
        C04846() {
        }

        public int getResult() {
            return PrinterInterface.end();
        }
    }

    private void setParams(Map<String, Object> map, ActionCallbackImpl callback) {
        this.mCallback = callback;
    }

    public void queryVoltage(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        final int[] pCapacity = new int[1];
        final int[] pVoltage = new int[1];
        if (getData(new DataAction() {
            public int getResult() {
                return PrinterInterface.queryVoltage(pCapacity, pVoltage);
            }
        }) >= 0) {
            this.mCallback.sendSuccessMsg("pCapacity = " + pCapacity[0] + ", Battery Voltage : " + pVoltage[0]);
        }
    }

    public void open(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        if (this.isOpened) {
            callback.sendFailedMsg(this.mContext.getResources().getString(C0223R.string.device_opened));
            return;
        }
        try {
            int result = PrinterInterface.open();
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
        checkOpenedAndGetData(new C04802());
    }

    public void close() {
        checkOpenedAndGetData(new C04813());
    }

    public void queryStatus(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        checkOpenedAndGetData(new C04824());
    }

    private void begin() {
        checkOpenedAndGetData(new C04835());
    }

    private void end() {
        checkOpenedAndGetData(new C04846());
    }

    public void write(Map<String, Object> param, ActionCallbackImpl callback) {
        setParams(param, callback);
        List<PrintBean> list = (List) param.get("content");
        begin();
        for (int i = 0; i < list.size(); i++) {
            switch (((PrintBean) list.get(i)).getType()) {
                case 1:
                    write(PrinterCommand.getCmdEscAN(0));
                    write(PrinterCommand.getCmdEsc_N(65536));
                    byte[] byte1 = null;
                    try {
                        byte1 = ((PrintBean) list.get(i)).getContent().getBytes(StringUtils.GB2312);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    write(byte1);
                    writeLineBreak(2);
                    try {
                        Thread.sleep(500);
                        break;
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        break;
                    }
                case 2:
                    write(PrinterCommand.getCmdEscAN(1));
                    write(PrinterCommand.getCmdEsc_N(65536));
                    byte[] byte2 = null;
                    try {
                        byte2 = ((PrintBean) list.get(i)).getContent().getBytes(StringUtils.GB2312);
                    } catch (UnsupportedEncodingException e3) {
                        e3.printStackTrace();
                    }
                    write(byte2);
                    writeLineBreak(2);
                    try {
                        Thread.sleep(500);
                        break;
                    } catch (InterruptedException e22) {
                        e22.printStackTrace();
                        break;
                    }
                case 3:
                    write(PrinterCommand.getCmdEscAN(0));
                    write(PrinterCommand.getCmdEsc_N(65536));
                    byte[] byte3 = null;
                    try {
                        byte3 = PurchaseBillTag.SEPARATE.getBytes(StringUtils.GB2312);
                    } catch (UnsupportedEncodingException e32) {
                        e32.printStackTrace();
                    }
                    write(byte3);
                    writeLineBreak(2);
                    break;
                case 4:
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e222) {
                        e222.printStackTrace();
                    }
                    write(PrinterCommand.getCmdEscAN(1));
                    PrinterBitmapUtil.printBitmap(((PrintBean) list.get(i)).getBitmap(), 17, 0, true);
                    try {
                        Thread.sleep(500);
                        break;
                    } catch (InterruptedException e2222) {
                        e2222.printStackTrace();
                        break;
                    }
                default:
                    break;
            }
        }
        end();
        close();
    }

    private void writeLineBreak(int lineNumber) {
        write(PrinterCommand.getCmdEscDN(lineNumber));
    }

    private void write(final byte[] arryData) {
        checkOpenedAndGetData(new DataAction() {
            public int getResult() {
                if (arryData == null) {
                    return PrinterInterface.write(null, 0);
                }
                return PrinterInterface.write(arryData, arryData.length);
            }
        });
    }
}
