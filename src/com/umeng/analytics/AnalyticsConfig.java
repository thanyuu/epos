package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import p000u.aly.bj;
import p000u.aly.bl;

public class AnalyticsConfig {
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static boolean COMPRESS_DATA = true;
    public static boolean ENABLE_MEMORY_BUFFER = true;
    public static final boolean FLAG_INTERNATIONAL = false;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";
    static double[] f13a = null;
    private static String f14b = null;
    private static String f15c = null;
    private static String f16d = null;
    private static int f17e = 0;
    public static long kContinueSessionMillis = 30000;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;
    public static boolean sEncrypt;
    public static int sLatentWindow;

    static {
        sEncrypt = false;
        sEncrypt = false;
    }

    static void m26a(boolean z) {
        sEncrypt = z;
    }

    static void m24a(Context context, String str) {
        if (context == null) {
            f14b = str;
            return;
        }
        String s = bj.m291s(context);
        if (TextUtils.isEmpty(s)) {
            Object c = C0306h.m71a(context).m90c();
            if (TextUtils.isEmpty(c)) {
                C0306h.m71a(context).m82a(str);
            } else if (!c.equals(str)) {
                bl.m334d("Appkey和上次配置的不一致 ");
                C0306h.m71a(context).m82a(str);
            }
            f14b = str;
            return;
        }
        f14b = s;
        if (!s.equals(str)) {
            bl.m334d("Appkey和AndroidManifest.xml中配置的不一致 ");
        }
    }

    static void m25a(String str) {
        f15c = str;
    }

    public static String getAppkey(Context context) {
        if (TextUtils.isEmpty(f14b)) {
            f14b = bj.m291s(context);
            if (TextUtils.isEmpty(f14b)) {
                f14b = C0306h.m71a(context).m90c();
            }
        }
        return f14b;
    }

    public static String getChannel(Context context) {
        if (TextUtils.isEmpty(f15c)) {
            f15c = bj.m297y(context);
        }
        return f15c;
    }

    public static double[] getLocation() {
        return f13a;
    }

    static void m27b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            f16d = str;
            C0306h.m71a(context).m91c(f16d);
        }
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(f16d)) {
            f16d = C0306h.m71a(context).m93e();
        }
        return f16d;
    }

    static void m23a(Context context, int i) {
        f17e = i;
        C0306h.m71a(context).m81a(f17e);
    }

    public static int getVerticalType(Context context) {
        if (f17e == 0) {
            f17e = C0306h.m71a(context).m94f();
        }
        return f17e;
    }

    public static String getSDKVersion(Context context) {
        return C0291a.f34c;
    }
}
