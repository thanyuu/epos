package com.ta.utdid2.android.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

    public static String convertObjectToString(Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            return ((String) o).toString();
        }
        if (o instanceof Integer) {
            return "" + ((Integer) o).intValue();
        }
        if (o instanceof Long) {
            return "" + ((Long) o).longValue();
        }
        if (o instanceof Double) {
            return "" + ((Double) o).doubleValue();
        }
        if (o instanceof Float) {
            return "" + ((Float) o).floatValue();
        }
        if (o instanceof Short) {
            return "" + ((Short) o).shortValue();
        }
        if (o instanceof Byte) {
            return "" + ((Byte) o).byteValue();
        }
        if (o instanceof Boolean) {
            return ((Boolean) o).toString();
        }
        if (o instanceof Character) {
            return ((Character) o).toString();
        }
        return o.toString();
    }

    public static int hashCode(String value) {
        int h = 0;
        if (null == null && value.length() > 0) {
            for (char c : value.toCharArray()) {
                h = (h * 31) + c;
            }
        }
        return h;
    }
}
