package com.cloudpos.apidemo.util;

import android.text.TextUtils;

public class StringUtility {
    public static boolean isEmpty(String strInput) {
        return TextUtils.isEmpty(strInput);
    }

    protected static boolean CheckByte(byte byteIn) {
        if (byteIn <= (byte) 57 && byteIn >= (byte) 48) {
            return true;
        }
        if (byteIn <= (byte) 70 && byteIn >= (byte) 65) {
            return true;
        }
        if (byteIn > (byte) 102 || byteIn < (byte) 97) {
            return false;
        }
        return true;
    }

    protected static boolean CheckString(String strInput) {
        strInput = strInput.trim();
        if (strInput.length() != 2) {
            return false;
        }
        byte[] byteArry = strInput.getBytes();
        for (int i = 0; i < 2; i++) {
            if (!CheckByte(byteArry[i])) {
                return false;
            }
        }
        return true;
    }

    protected static byte StringToByte(String strInput) {
        byte[] byteArry = strInput.getBytes();
        int i = 0;
        while (i < 2) {
            if (byteArry[i] <= (byte) 57 && byteArry[i] >= (byte) 48) {
                byteArry[i] = (byte) (byteArry[i] - 48);
            } else if (byteArry[i] <= (byte) 70 && byteArry[i] >= (byte) 65) {
                byteArry[i] = (byte) (byteArry[i] - 55);
            } else if (byteArry[i] <= (byte) 102 && byteArry[i] >= (byte) 97) {
                byteArry[i] = (byte) (byteArry[i] - 87);
            }
            i++;
        }
        return (byte) ((byteArry[0] << 4) | (byteArry[1] & 15));
    }

    public static int StringToByteArray(String strInput, byte[] arryByte) {
        String[] arryString = strInput.trim().split(" ");
        if (arryByte.length < arryString.length) {
            return -1;
        }
        for (int i = 0; i < arryString.length; i++) {
            if (!CheckString(arryString[i])) {
                return -1;
            }
            arryByte[i] = StringToByte(arryString[i]);
        }
        return arryString.length;
    }

    public static String ByteArrayToString(byte[] arryByte, int nDataLength) {
        String strOut = new String();
        for (int i = 0; i < nDataLength; i++) {
            strOut = strOut + String.format("%02X ", new Object[]{Byte.valueOf(arryByte[i])});
        }
        return strOut;
    }

    public static String ByteArrayToString(byte[] arryByte, int offset, int nDataLength) {
        String strOut = "";
        int i = offset;
        while (i < nDataLength) {
            try {
                strOut = strOut + String.format("%02X ", new Object[]{Byte.valueOf(arryByte[i])});
                i++;
            } catch (Exception e) {
                return "";
            }
        }
        return strOut;
    }

    public static String[] spiltStrings(String str, String reg) {
        return str.split(reg);
    }
}
