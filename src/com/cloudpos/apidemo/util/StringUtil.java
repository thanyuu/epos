package com.cloudpos.apidemo.util;

public class StringUtil {
    public static String getFormatString(byte[] bytes) {
        String value = "";
        for (byte b : bytes) {
            value = value + String.format("%02X ", new Object[]{Byte.valueOf(b)});
        }
        return value;
    }

    public static String getFormatString(byte[] bytes, int length) {
        String value = "";
        for (int i = 0; i < length; i++) {
            value = value + String.format("%02X ", new Object[]{Byte.valueOf(bytes[i])});
        }
        return value;
    }

    public static boolean isExist(String[] ss, String str) {
        String[] t = ss;
        for (String str2 : t) {
            if (str.equals(str2.toString())) {
                return true;
            }
        }
        return false;
    }
}
