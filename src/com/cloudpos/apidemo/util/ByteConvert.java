package com.cloudpos.apidemo.util;

import android.support.v4.view.MotionEventCompat;
import java.util.StringTokenizer;

public final class ByteConvert {
    public static final int DEFAULT_BUFFERLENGTH = 3072;
    public static final int DEFAULT_TABLELENGTH = 256;
    private static String[] convertTable = new String[256];
    public static final String lineSeperate = System.getProperty("line.separator");
    private static StringBuffer sBuf = new StringBuffer(3072);

    static {
        int i = 0;
        while (i < 16) {
            convertTable[i] = "0" + Integer.toHexString(i) + " ";
            i++;
        }
        while (i < 256) {
            convertTable[i] = Integer.toHexString(i) + " ";
            i++;
        }
    }

    public static int byte2int2(byte[] convertByte) {
        return byte2int2(convertByte, 0, true);
    }

    public static int byte2int2(byte[] convertByte, boolean bigIndian) {
        return byte2int2(convertByte, 0, bigIndian);
    }

    public static int byte2int2(byte[] convertByte, int offset) {
        return byte2int2(convertByte, offset, true);
    }

    public static int byte2int2(byte[] convertByte, int offset, boolean bigIndian) {
        int byte0 = convertByte[offset + 0] < (byte) 0 ? convertByte[offset + 0] + 256 : convertByte[offset + 0];
        int byte1 = convertByte[offset + 1] < (byte) 0 ? convertByte[offset + 1] + 256 : convertByte[offset + 1];
        if (bigIndian) {
            return (byte0 * 256) + byte1;
        }
        return (byte1 * 256) + byte0;
    }

    public static int byte2int4(byte[] convertByte) {
        return byte2int4(convertByte, 0, true);
    }

    public static int byte2int4(byte[] convertByte, boolean bigIndian) {
        return byte2int4(convertByte, 0, bigIndian);
    }

    public static int byte2int4(byte[] convertByte, int offset) {
        return byte2int4(convertByte, offset, true);
    }

    public static int byte2int4(byte[] convertByte, int offset, boolean bigIndian) {
        int byte0 = convertByte[offset + 0] < (byte) 0 ? convertByte[offset + 0] + 256 : convertByte[offset + 0];
        int byte1 = convertByte[offset + 1] < (byte) 0 ? convertByte[offset + 1] + 256 : convertByte[offset + 1];
        int byte2 = convertByte[offset + 2] < (byte) 0 ? convertByte[offset + 2] + 256 : convertByte[offset + 2];
        int byte3 = convertByte[offset + 3] < (byte) 0 ? convertByte[offset + 3] + 256 : convertByte[offset + 3];
        if (bigIndian) {
            return (((byte0 << 24) + (byte1 << 16)) + (byte2 << 8)) + byte3;
        }
        return (((byte3 << 24) + (byte2 << 16)) + (byte1 << 8)) + byte0;
    }

    public static byte[] int2byte2(int value) {
        return int2byte2(value, true);
    }

    public static byte[] int2byte2(int value, boolean bigIndian) {
        byte[] byteValue = new byte[2];
        if (bigIndian) {
            byteValue[1] = (byte) (value & 255);
            byteValue[0] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
        } else {
            byteValue[0] = (byte) (value & 255);
            byteValue[1] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
        }
        return byteValue;
    }

    public static void int2byte2(int value, byte[] fillByte) {
        int2byte2(value, fillByte, 0, true);
    }

    public static void int2byte2(int value, byte[] fillByte, boolean bigIndian) {
        int2byte2(value, fillByte, 0, bigIndian);
    }

    public static void int2byte2(int value, byte[] fillByte, int offset) {
        int2byte2(value, fillByte, offset, true);
    }

    public static void int2byte2(int value, byte[] fillByte, int offset, boolean bigIndian) {
        if (bigIndian) {
            fillByte[offset + 1] = (byte) (value & 255);
            fillByte[offset + 0] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
            return;
        }
        fillByte[offset + 0] = (byte) (value & 255);
        fillByte[offset + 1] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
    }

    public static byte[] int2byte4(int value) {
        return int2byte4(value, true);
    }

    public static byte[] int2byte4(int value, boolean bigIndian) {
        byte[] byteValue = new byte[4];
        if (bigIndian) {
            byteValue[3] = (byte) (value & 255);
            byteValue[2] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & value) >>> 8);
            byteValue[1] = (byte) ((16711680 & value) >>> 16);
            byteValue[0] = (byte) ((value & -16777216) >>> 24);
        } else {
            byteValue[0] = (byte) (value & 255);
            byteValue[1] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & value) >>> 8);
            byteValue[2] = (byte) ((16711680 & value) >>> 16);
            byteValue[3] = (byte) ((value & -16777216) >>> 24);
        }
        return byteValue;
    }

    public static void int2byte4(int value, byte[] fillByte) {
        int2byte4(value, fillByte, 0, true);
    }

    public static void int2byte4(int value, byte[] fillByte, boolean bigIndian) {
        int2byte4(value, fillByte, 0, bigIndian);
    }

    public static void int2byte4(int value, byte[] fillByte, int offset) {
        int2byte4(value, fillByte, offset, true);
    }

    public static void int2byte4(int value, byte[] fillByte, int offset, boolean bigIndian) {
        if (bigIndian) {
            fillByte[offset + 3] = (byte) (value & 255);
            fillByte[offset + 2] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
            fillByte[offset + 1] = (byte) ((value & 16711680) >>> 16);
            fillByte[offset + 0] = (byte) ((value & -16777216) >>> 24);
            return;
        }
        fillByte[offset + 0] = (byte) (value & 255);
        fillByte[offset + 1] = (byte) ((value & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
        fillByte[offset + 2] = (byte) ((value & 16711680) >>> 16);
        fillByte[offset + 3] = (byte) ((value & -16777216) >>> 24);
    }

    public static synchronized String buf2String(String info, byte[] buf) {
        String buf2String;
        synchronized (ByteConvert.class) {
            buf2String = buf2String(info, buf, 0, buf.length, true);
        }
        return buf2String;
    }

    public static synchronized String buf2String(String info, byte[] buf, boolean oneLine16) {
        String buf2String;
        synchronized (ByteConvert.class) {
            buf2String = buf2String(info, buf, 0, buf.length, oneLine16);
        }
        return buf2String;
    }

    public static synchronized String buf2String(String info, byte[] buf, int offset, int length) {
        String buf2String;
        synchronized (ByteConvert.class) {
            buf2String = buf2String(info, buf, offset, length, true);
        }
        return buf2String;
    }

    public static synchronized String buf2String(String info, byte[] buf, int offset, int length, boolean oneLine16) {
        String stringBuffer;
        synchronized (ByteConvert.class) {
            sBuf.delete(0, sBuf.length());
            sBuf.append(info);
            int i = offset + 0;
            while (i < length + offset) {
                if (i % 16 == 0) {
                    if (oneLine16) {
                        sBuf.append(lineSeperate);
                    }
                } else if (i % 8 == 0 && oneLine16) {
                    sBuf.append("   ");
                }
                sBuf.append(convertTable[buf[i] < (byte) 0 ? buf[i] + 256 : buf[i]]);
                i++;
            }
            stringBuffer = sBuf.toString();
        }
        return stringBuffer;
    }

    public static byte[] byteString2Bytes(String s) {
        if (s == null) {
            return new byte[0];
        }
        StringTokenizer st = new StringTokenizer(s, " \n\t");
        int ilen = st.countTokens();
        if (ilen <= 0) {
            return new byte[0];
        }
        byte[] buf = new byte[ilen];
        int i = 0;
        while (st.hasMoreElements()) {
            try {
                buf[i] = (byte) (Integer.parseInt(st.nextToken(), 16) & 255);
            } catch (NumberFormatException ex) {
                System.out.println("Exception in string2Bytes : " + ex.getMessage());
                buf[i] = (byte) -1;
            }
            i++;
        }
        return buf;
    }
}
