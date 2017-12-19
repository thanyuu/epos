package com.android.common.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceUtils {
    public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }
        StringBuilder s = new StringBuilder("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return s.toString();
                }
                s.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }
        StringBuilder s = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resId)));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return s.toString();
                }
                s.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
