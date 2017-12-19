package com.epos.pospay.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.inputmethod.InputMethodManager;
import com.android.common.utils.HttpUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    public static final int BLACK = -16777216;
    public static final String TOKEN = "epos@ePayments";
    public static final int WHITE = -1;

    public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException, FileNotFoundException {
        new Hashtable().put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[(y * width) + x] = -16777216;
                } else {
                    pixels[(y * width) + x] = -1;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static String createSignature(Map<String, Object> map) {
        List list = new ArrayList(map.keySet());
        Collections.sort(list);
        String encode = "";
        int i = 0;
        while (i < list.size()) {
            if (!(TOKEN.equals(list.get(i).toString()) || map.get(list.get(i)) == null)) {
                encode = encode + HttpUtils.PARAMETERS_SEPARATOR + list.get(i) + HttpUtils.EQUAL_SIGN + map.get(list.get(i)).toString();
            }
            i++;
        }
        encode = trimFirstAndLastChar(encode, '&') + TOKEN;
        System.out.println("key~~:" + encode);
        try {
            encode = MD5Util.MD5(encode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return encode;
    }

    public static String trimFirstAndLastChar(String source, char element) {
        while (true) {
            int beginIndex;
            boolean beginIndexFlag;
            if (source.indexOf(element) == 0) {
                beginIndex = 1;
            } else {
                beginIndex = 0;
            }
            source = source.substring(beginIndex, source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length());
            if (source.indexOf(element) == 0) {
                beginIndexFlag = true;
            } else {
                beginIndexFlag = false;
            }
            boolean endIndexFlag;
            if (source.lastIndexOf(element) + 1 == source.length()) {
                endIndexFlag = true;
            } else {
                endIndexFlag = false;
            }
            if (!beginIndexFlag && !endIndexFlag) {
                return source;
            }
        }
    }

    public static Float getFee(String content) {
        if (content != null) {
            return Float.valueOf(Float.valueOf(content.split("%")[0]).floatValue() / 100.0f);
        }
        return Float.valueOf(0.0f);
    }

    public static void hideKeyBoard(Activity context) {
        if (context != null) {
            try {
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
