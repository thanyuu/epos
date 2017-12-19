package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: InternalConfig */
public class C0294e {
    private static String[] f59a = new String[2];

    public static void m43a(Context context, String str, String str2) {
        f59a[0] = str;
        f59a[1] = str2;
        if (context != null) {
            C0306h.m71a(context).m83a(str, str2);
        }
    }

    public static String[] m44a(Context context) {
        if (!TextUtils.isEmpty(f59a[0]) && !TextUtils.isEmpty(f59a[1])) {
            return f59a;
        }
        if (context != null) {
            String[] a = C0306h.m71a(context).m86a();
            if (a != null) {
                f59a[0] = a[0];
                f59a[1] = a[1];
                return f59a;
            }
        }
        return null;
    }

    public static void m45b(Context context) {
        f59a[0] = null;
        f59a[1] = null;
        if (context != null) {
            C0306h.m71a(context).m87b();
        }
    }
}
