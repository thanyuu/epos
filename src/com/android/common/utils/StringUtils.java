package com.android.common.utils;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEquals(String actual, String expected) {
        return ObjectUtils.isEquals(actual, expected);
    }

    public static String nullStrToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char c = str.charAt(0);
        if (!Character.isLetter(c) || Character.isUpperCase(c)) {
            return str;
        }
        return new StringBuilder(str.length()).append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    public static String utf8Encode(String str) {
        if (!(isEmpty(str) || str.getBytes().length == str.length())) {
            try {
                str = URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    public static String utf8Encode(String str, String defultReturn) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return defultReturn;
        }
    }

    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }
        Matcher hrefMatcher = Pattern.compile(".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*", 2).matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    public static String htmlEscapeCharsToString(String source) {
        if (isEmpty(source)) {
            return source;
        }
        return source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", HttpUtils.PARAMETERS_SEPARATOR).replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] source = s.toCharArray();
        int i = 0;
        while (i < source.length) {
            if (source[i] == '　') {
                source[i] = ' ';
            } else if (source[i] < '！' || source[i] > '～') {
                source[i] = source[i];
            } else {
                source[i] = (char) (source[i] - 65248);
            }
            i++;
        }
        return new String(source);
    }

    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] source = s.toCharArray();
        int i = 0;
        while (i < source.length) {
            if (source[i] == ' ') {
                source[i] = '　';
            } else if (source[i] < '!' || source[i] > '~') {
                source[i] = source[i];
            } else {
                source[i] = (char) (source[i] + 65248);
            }
            i++;
        }
        return new String(source);
    }

    public static String getBestString(byte[] random) {
        String str = "";
        for (byte b : random) {
            str = new StringBuilder(String.valueOf(str)).append(String.format("%02X ", new Object[]{Byte.valueOf(b)})).toString();
        }
        return str;
    }
}
