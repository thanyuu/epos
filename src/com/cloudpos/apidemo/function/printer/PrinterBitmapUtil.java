package com.cloudpos.apidemo.function.printer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import com.android.common.utils.ShellUtils;
import com.cloudpos.jniinterface.PrinterInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class PrinterBitmapUtil {
    public static final int BIT_WIDTH = 384;
    private static final int DC2V_HEAD = 4;
    private static final int DOT_LINE_LIMIT = 200;
    private static final int GSV_HEAD = 8;
    private static final int WIDTH = 48;

    private static String getSystemProperty(String name) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + name).getInputStream()));
            String value = in.readLine();
            in.close();
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public static void printBitmap(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        printBitmap(bm, bitMarginLeft, bitMarginTop, false);
    }

    public static void printBitmap(Bitmap bm, int bitMarginLeft, int bitMarginTop, boolean alreadyOpen) {
        if ("WIZARPOS 1".equals(Build.MODEL) || "WIZARPOS_1".equals(Build.MODEL)) {
            String printerBaud = getSystemProperty("wp.printer.baud");
            if ("115200".equals(printerBaud)) {
                Log.d("PrintUI", "GSV " + printerBaud);
                printBitmapGSVMSB(bm, bitMarginLeft, bitMarginTop, alreadyOpen);
                return;
            } else if ("9600".equals(printerBaud)) {
                Log.d("PrintUI", "DC2V" + printerBaud);
                printBitmapDV2VMSB(bm, bitMarginLeft, bitMarginTop, alreadyOpen);
                return;
            } else {
                Log.d("PrintUI", "DC2V slow" + printerBaud);
                printBitmapDV2VMSBslow(bm, bitMarginLeft, bitMarginTop, alreadyOpen);
                return;
            }
        }
        printBitmapGSVMSB(bm, bitMarginLeft, bitMarginTop, alreadyOpen);
    }

    private static void printBitmapDV2VMSB(Bitmap bm, int bitMarginLeft, int bitMarginTop, boolean alreadyOpen) {
        byte[] result = generateBitmapArrayDC2V_MSB(bm, bitMarginLeft, bitMarginTop);
        if (!alreadyOpen) {
            PrinterInterface.open();
            PrinterInterface.begin();
        }
        PrinterInterface.write(new byte[]{(byte) 27, (byte) 55, (byte) 7, (byte) -16, (byte) 2}, 5);
        int lines = (result.length - 4) / 48;
        System.arraycopy(new byte[]{(byte) 18, (byte) 86, (byte) (lines & 255), (byte) ((lines >> 8) & 255)}, 0, result, 0, 4);
        PrinterInterface.write(result, result.length);
        PrinterInterface.write(new byte[]{(byte) 27, (byte) 55, (byte) 7, Byte.MIN_VALUE, (byte) 2}, 5);
        if (!alreadyOpen) {
            PrinterInterface.end();
            PrinterInterface.close();
        }
    }

    private static void printBitmapDV2VMSBslow(Bitmap bm, int bitMarginLeft, int bitMarginTop, boolean alreadyOpen) {
        byte[] result = generateBitmapArrayDC2V_MSB(bm, bitMarginLeft, bitMarginTop);
        if (!alreadyOpen) {
            PrinterInterface.open();
            PrinterInterface.begin();
        }
        PrinterInterface.write(new byte[]{(byte) 27, (byte) 55, (byte) 7, (byte) -16, (byte) 2}, 5);
        Vector<byte[]> vData = checkBufferLength(result);
        for (int i = 0; i < vData.size(); i++) {
            byte[] temp = (byte[]) vData.elementAt(i);
            int lines = temp.length / 48;
            PrinterInterface.write(new byte[]{(byte) 18, (byte) 86, (byte) (lines & 255), (byte) ((lines >> 8) & 255)}, 4);
            PrinterInterface.write(temp, temp.length);
        }
        PrinterInterface.write(new byte[]{(byte) 27, (byte) 55, (byte) 7, Byte.MIN_VALUE, (byte) 2}, 5);
        if (!alreadyOpen) {
            PrinterInterface.end();
            PrinterInterface.close();
        }
    }

    private static void printBitmapGSVMSB(Bitmap bm, int bitMarginLeft, int bitMarginTop, boolean alreadyOpen) {
        byte[] result = generateBitmapArrayGSV_MSB(bm, bitMarginLeft, bitMarginTop);
        if (!alreadyOpen) {
            PrinterInterface.open();
            PrinterInterface.begin();
        }
        int lines = (result.length - 8) / 48;
        System.arraycopy(new byte[]{(byte) 29, (byte) 118, 48, null, 48, null, (byte) (lines & 255), (byte) ((lines >> 8) & 255)}, 0, result, 0, 8);
        PrinterInterface.write(result, result.length);
        if (!alreadyOpen) {
            PrinterInterface.end();
            PrinterInterface.close();
        }
    }

    private static byte[] generateBitmapArrayDC2V_MSB(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        byte[] result = new byte[(((bm.getHeight() + bitMarginTop) * 48) + 4)];
        for (int y = 0; y < bm.getHeight(); y++) {
            int x = 0;
            while (x < bm.getWidth() && x + bitMarginLeft < BIT_WIDTH) {
                int color = bm.getPixel(x, y);
                int alpha = Color.alpha(color);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                if (alpha > 128 && (red < 128 || green < 128 || blue < 128)) {
                    int bitX = bitMarginLeft + x;
                    int byteX = bitX / 8;
                    int i = (((y + bitMarginTop) * 48) + 4) + byteX;
                    result[i] = (byte) (result[i] | (128 >> (bitX - (byteX * 8))));
                }
                x++;
            }
        }
        return result;
    }

    private static byte[] generateBitmapArrayGSV_MSB(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        byte[] result = new byte[(((bm.getHeight() + bitMarginTop) * 48) + 8)];
        for (int y = 0; y < bm.getHeight(); y++) {
            int x = 0;
            while (x < bm.getWidth() && x + bitMarginLeft < BIT_WIDTH) {
                int color = bm.getPixel(x, y);
                int alpha = Color.alpha(color);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                if (alpha > 128 && (red < 128 || green < 128 || blue < 128)) {
                    int bitX = bitMarginLeft + x;
                    int byteX = bitX / 8;
                    int i = (((y + bitMarginTop) * 48) + 8) + byteX;
                    result[i] = (byte) (result[i] | (128 >> (bitX - (byteX * 8))));
                }
                x++;
            }
        }
        return result;
    }

    public static void printBitmapESCStar(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        Vector<byte[]> vData = generateCmdBitmapArrayESCStar(bm, bitMarginLeft, bitMarginTop);
        PrinterInterface.open();
        PrinterInterface.begin();
        for (int i = 0; i < vData.size(); i++) {
            byte[] temp = (byte[]) vData.elementAt(i);
            if (i > 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            PrinterInterface.write(temp, temp.length);
        }
        byte[] bufText = "This is a text tset...".getBytes();
        PrinterInterface.write(bufText, bufText.length);
        PrinterInterface.write(ShellUtils.COMMAND_LINE_END.getBytes(), 1);
        PrinterInterface.end();
        PrinterInterface.close();
    }

    private static Vector<byte[]> generateCmdBitmapArrayESCStar(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        Vector<byte[]> v = new Vector();
        int n = bm.getHeight() + bitMarginTop;
        int pxHeight = bm.getHeight();
        int pxWidth = bm.getWidth();
        for (int line = 0; line < pxHeight; line += 24) {
            v.add(new byte[]{(byte) 27, (byte) 42, (byte) 33, (byte) 128, (byte) 1});
            byte[] block = new byte[1152];
            int y = 0;
            while (y + line < pxHeight && y < 24) {
                for (int x = 0; x < pxWidth; x++) {
                    int posBit = (x * 24) + y;
                    int posByte = posBit / 8;
                    int posBitInByteLeft = posBit % 8;
                    if (x >= 384) {
                        break;
                    }
                    int color = bm.getPixel(x, y + line);
                    int alpha = Color.alpha(color);
                    int red = Color.red(color);
                    int green = Color.green(color);
                    int blue = Color.blue(color);
                    if (red < 128 || green < 128 || blue < 128) {
                        block[posByte] = (byte) (block[posByte] | (128 >> posBitInByteLeft));
                    }
                }
                y++;
            }
            v.add(block);
            v.add(new byte[]{(byte) 10});
        }
        return v;
    }

    private static void tracelogCmdBitmapArrayESCStar(Vector<byte[]> vData) {
        int i;
        StringBuffer[] arysbBlock = null;
        int m = 0;
        for (int v = 0; v < vData.size(); v++) {
            byte[] buffer = (byte[]) vData.elementAt(v);
            if (buffer.length >= 5) {
                if (buffer.length == 5) {
                    if (arysbBlock != null) {
                        for (StringBuffer stringBuffer : arysbBlock) {
                            Log.d("PrintPNG", stringBuffer.toString());
                        }
                    }
                    m = buffer[2];
                    int blockWidth = (buffer[3] & 255) + ((buffer[4] & 255) * 256);
                    if (m == 1) {
                        arysbBlock = new StringBuffer[8];
                    } else if (m == 33) {
                        arysbBlock = new StringBuffer[24];
                    }
                    for (i = 0; i < arysbBlock.length; i++) {
                        arysbBlock[i] = new StringBuffer();
                    }
                } else {
                    i = 0;
                    while (i < buffer.length) {
                        int pos;
                        byte b = buffer[i];
                        for (pos = 0; pos < 8; pos++) {
                            if (((128 >> pos) & b) != 0) {
                                arysbBlock[pos].append('*');
                            } else {
                                arysbBlock[pos].append(' ');
                            }
                        }
                        i++;
                        if (m == 33) {
                            b = buffer[i];
                            for (pos = 0; pos < 8; pos++) {
                                if (((128 >> pos) & b) != 0) {
                                    arysbBlock[pos + 8].append('*');
                                } else {
                                    arysbBlock[pos + 8].append(' ');
                                }
                            }
                            i++;
                            b = buffer[i];
                            for (pos = 0; pos < 8; pos++) {
                                if (((128 >> pos) & b) != 0) {
                                    arysbBlock[pos + 16].append('*');
                                } else {
                                    arysbBlock[pos + 16].append(' ');
                                }
                            }
                            i++;
                        }
                    }
                }
            }
        }
        if (arysbBlock != null) {
            for (StringBuffer stringBuffer2 : arysbBlock) {
                Log.d("PrintPNG", stringBuffer2.toString());
            }
        }
    }

    private static byte[] generateBitmapArrayDot8(Bitmap bm, int bitMarginLeft, int bitMarginTop) {
        return null;
    }

    private static void tracelogMSB(byte[] bufMSB, int widthBytes) {
        StringBuffer sbline = new StringBuffer();
        for (int i = 0; i < bufMSB.length; i++) {
            if (i % widthBytes == 0) {
                Log.d("PrintPNG", sbline.toString());
                sbline = new StringBuffer();
            }
            byte b = bufMSB[i];
            for (int pos = 0; pos < 8; pos++) {
                if (((128 >> pos) & b) != 0) {
                    sbline.append('*');
                } else {
                    sbline.append(' ');
                }
            }
        }
    }

    private static Vector<byte[]> checkBufferLength(byte[] buffer) {
        Vector<byte[]> v = new Vector();
        if (buffer.length <= 9600) {
            v.add(buffer);
        } else {
            int offset = 4;
            while (offset < buffer.length) {
                byte[] buftemp = new byte[(offset + 9600 < buffer.length ? 9600 : buffer.length - offset)];
                System.arraycopy(buffer, offset, buftemp, 0, buftemp.length);
                v.add(buftemp);
                offset += buftemp.length;
            }
        }
        return v;
    }
}
