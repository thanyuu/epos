package com.cloudpos.apidemo.util;

import com.android.common.utils.MapUtils;
import java.util.Scanner;

public class ByteConvertStringUtil {
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            String hv = Integer.toHexString(b & 255);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String byteToHexString(byte src) {
        StringBuilder stringBuilder = new StringBuilder("");
        return Integer.toHexString(src & 255);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("input:");
        System.out.println(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR + byteToHexString(Byte.valueOf(scan.nextByte()).byteValue()));
    }
}
