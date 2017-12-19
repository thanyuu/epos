package com.umeng.analytics.social;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.common.utils.HttpUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0291a;
import com.umeng.analytics.social.UMPlatformData.GENDER;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* compiled from: UMUtils */
public abstract class C0315f {
    private static Map<String, String> f140a;

    protected static String[] m132a(Context context, String str, UMPlatformData... uMPlatformDataArr) throws C0310a {
        if (uMPlatformDataArr == null || uMPlatformDataArr.length == 0) {
            throw new C0310a("platform data is null");
        }
        Object appkey = AnalyticsConfig.getAppkey(context);
        if (TextUtils.isEmpty(appkey)) {
            throw new C0310a("can`t get appkey.");
        }
        List arrayList = new ArrayList();
        String str2 = "http://log.umsns.com/share/api/" + appkey + HttpUtils.PATHS_SEPARATOR;
        if (f140a == null || f140a.isEmpty()) {
            f140a = C0315f.m134b(context);
        }
        if (!(f140a == null || f140a.isEmpty())) {
            for (Entry entry : f140a.entrySet()) {
                arrayList.add(((String) entry.getKey()) + HttpUtils.EQUAL_SIGN + ((String) entry.getValue()));
            }
        }
        arrayList.add("date=" + String.valueOf(System.currentTimeMillis()));
        arrayList.add("channel=" + C0314e.f122e);
        if (!TextUtils.isEmpty(str)) {
            arrayList.add("topic=" + str);
        }
        arrayList.addAll(C0315f.m129a(uMPlatformDataArr));
        String b = C0315f.m133b(uMPlatformDataArr);
        if (b == null) {
            b = "null";
        }
        String str3 = str2 + HttpUtils.URL_AND_PARA_SEPARATOR + C0315f.m128a(arrayList);
        while (str3.contains("%2C+")) {
            str3 = str3.replace("%2C+", HttpUtils.PARAMETERS_SEPARATOR);
        }
        while (str3.contains("%3D")) {
            str3 = str3.replace("%3D", HttpUtils.EQUAL_SIGN);
        }
        while (str3.contains("%5B")) {
            str3 = str3.replace("%5B", "");
        }
        while (str3.contains("%5D")) {
            str3 = str3.replace("%5D", "");
        }
        C0311b.m112c(C0291a.f35d, "URL:" + str3);
        C0311b.m112c(C0291a.f35d, "BODY:" + b);
        return new String[]{str3, b};
    }

    private static String m128a(List<String> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(URLEncoder.encode(list.toString()).getBytes());
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> m129a(UMPlatformData... uMPlatformDataArr) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            stringBuilder.append(uMPlatformData.getMeida().toString());
            stringBuilder.append(',');
            stringBuilder2.append(uMPlatformData.getUsid());
            stringBuilder2.append(',');
            stringBuilder3.append(uMPlatformData.getWeiboId());
            stringBuilder3.append(',');
        }
        if (uMPlatformDataArr.length > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
            stringBuilder3.deleteCharAt(stringBuilder3.length() - 1);
        }
        List<String> arrayList = new ArrayList();
        arrayList.add("platform=" + stringBuilder.toString());
        arrayList.add("usid=" + stringBuilder2.toString());
        if (stringBuilder3.length() > 0) {
            arrayList.add("weiboid=" + stringBuilder3.toString());
        }
        return arrayList;
    }

    private static String m133b(UMPlatformData... uMPlatformDataArr) {
        JSONObject jSONObject = new JSONObject();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            Object obj;
            GENDER gender = uMPlatformData.getGender();
            CharSequence name = uMPlatformData.getName();
            if (gender == null) {
                try {
                    if (TextUtils.isEmpty(name)) {
                    }
                } catch (Throwable e) {
                    throw new C0310a("build body exception", e);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            String str = "gender";
            if (gender == null) {
                obj = "";
            } else {
                obj = String.valueOf(gender.value);
            }
            jSONObject2.put(str, obj);
            jSONObject2.put("name", name == null ? "" : String.valueOf(name));
            jSONObject.put(uMPlatformData.getMeida().toString(), jSONObject2);
        }
        if (jSONObject.length() == 0) {
            return null;
        }
        return jSONObject.toString();
    }

    private static Map<String, String> m134b(Context context) throws C0310a {
        Map<String, String> hashMap = new HashMap();
        Map a = C0315f.m130a(context);
        if (a == null || a.isEmpty()) {
            throw new C0310a("can`t get device id.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (Entry entry : a.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getValue())) {
                stringBuilder2.append((String) entry.getKey()).append(",");
                stringBuilder.append((String) entry.getValue()).append(",");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hashMap.put("deviceid", stringBuilder.toString());
        }
        if (stringBuilder2.length() > 0) {
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
            hashMap.put("idtype", stringBuilder2.toString());
        }
        return hashMap;
    }

    public static Map<String, String> m130a(Context context) {
        CharSequence deviceId;
        CharSequence c;
        CharSequence string;
        Map<String, String> hashMap = new HashMap();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            C0311b.m116e(C0291a.f35d, "No IMEI.");
        }
        try {
            if (C0315f.m131a(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                c = C0315f.m135c(context);
                string = Secure.getString(context.getContentResolver(), "android_id");
                if (!TextUtils.isEmpty(c)) {
                    hashMap.put("mac", c);
                }
                if (!TextUtils.isEmpty(deviceId)) {
                    hashMap.put("imei", deviceId);
                }
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put("android_id", string);
                }
                return hashMap;
            }
        } catch (Exception e) {
            C0311b.m117e(C0291a.f35d, "No IMEI.", e);
        }
        deviceId = null;
        c = C0315f.m135c(context);
        string = Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(c)) {
            hashMap.put("mac", c);
        }
        if (TextUtils.isEmpty(deviceId)) {
            hashMap.put("imei", deviceId);
        }
        if (TextUtils.isEmpty(string)) {
            hashMap.put("android_id", string);
        }
        return hashMap;
    }

    private static boolean m131a(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    private static String m135c(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (C0315f.m131a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            C0311b.m116e(C0291a.f35d, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            C0311b.m116e(C0291a.f35d, "Could not get mac address." + e.toString());
        }
    }
}
