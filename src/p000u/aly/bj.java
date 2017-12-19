package p000u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0291a;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: DeviceConfig */
public class bj {
    protected static final String f347a = bj.class.getName();
    public static final String f348b = "";
    public static final String f349c = "2G/3G";
    public static final String f350d = "Wi-Fi";
    public static final int f351e = 8;
    private static final String f352f = "ro.miui.ui.version.name";

    public static boolean m265a(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m263a(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean m270b(Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return true;
        }
        return false;
    }

    public static String m272c(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String m274d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean m264a(Context context, String str) {
        if (VERSION.SDK_INT >= 23) {
            try {
                boolean z;
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } catch (Exception e) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String m275e(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
    }

    public static String[] m266a(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Throwable e) {
            bl.m341e(f347a, "Could not read gpu infor:", e);
            return new String[0];
        }
    }

    private static String m273d() {
        int i = 0;
        if (C0291a.f36e) {
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            while (i < strArr.length) {
                try {
                    String b = bj.m267b(strArr[i]);
                    if (b != null) {
                        return b;
                    }
                    i++;
                } catch (Throwable e) {
                    bl.m341e(f347a, "open file  Failed", e);
                }
            }
        }
        return null;
    }

    private static String m267b(String str) throws FileNotFoundException {
        Throwable e;
        Throwable th;
        String str2 = null;
        Reader fileReader = new FileReader(str);
        if (fileReader != null) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(fileReader, 1024);
                try {
                    str2 = bufferedReader.readLine();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        bl.m341e(f347a, "Could not read from file " + str, e);
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
                bufferedReader = str2;
                bl.m341e(f347a, "Could not read from file " + str, e);
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str2;
            } catch (Throwable e5) {
                bufferedReader = str2;
                th = e5;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return str2;
    }

    public static String m258a() {
        Throwable th;
        String str;
        Throwable th2;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable e) {
                    try {
                        bl.m341e(f347a, "Could not read from file /proc/cpuinfo", e);
                    } catch (Throwable e2) {
                        th = e2;
                        str = str2;
                        th2 = th;
                        bl.m341e(f347a, "Could not open file /proc/cpuinfo", th2);
                        str2 = str;
                        if (str2 != null) {
                            return "";
                        }
                        return str2.substring(str2.indexOf(58) + 1).trim();
                    }
                }
            }
        } catch (Throwable e22) {
            th = e22;
            str = str2;
            th2 = th;
            bl.m341e(f347a, "Could not open file /proc/cpuinfo", th2);
            str2 = str;
            if (str2 != null) {
                return str2.substring(str2.indexOf(58) + 1).trim();
            }
            return "";
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String m277f(Context context) {
        if (EScenarioType.E_UM_ANALYTICS_OEM.toValue() == AnalyticsConfig.getVerticalType(context) || EScenarioType.E_UM_GAME_OEM.toValue() == AnalyticsConfig.getVerticalType(context)) {
            return bj.m255J(context);
        }
        return bj.m254I(context);
    }

    public static String m279g(Context context) {
        return bk.m309b(bj.m277f(context));
    }

    public static String m280h(Context context) {
        if (bj.m281i(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", new Object[]{Integer.valueOf(i2)});
        }
        return new StringBuffer().append(String.valueOf(i)).append(valueOf).toString();
    }

    public static String m281i(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    public static String m282j(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getNetworkOperator();
        }
        return null;
    }

    public static String m283k(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            if (telephonyManager == null) {
                return "";
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String m284l(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String[] m285m(Context context) {
        String[] strArr = new String[]{"", ""};
        try {
            if (bj.m264a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    strArr[0] = "";
                    return strArr;
                } else if (connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED) {
                    strArr[0] = "Wi-Fi";
                    return strArr;
                } else {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                    if (networkInfo.getState() == State.CONNECTED) {
                        strArr[0] = f349c;
                        strArr[1] = networkInfo.getSubtypeName();
                        return strArr;
                    }
                    return strArr;
                }
            }
            strArr[0] = "";
            return strArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean m286n(Context context) {
        return "Wi-Fi".equals(bj.m285m(context)[0]);
    }

    public static boolean m287o(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean m269b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static int m288p(Context context) {
        try {
            Calendar instance = Calendar.getInstance(bj.m252G(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable e) {
            bl.m329c(f347a, "error in getTimeZone", e);
        }
        return 8;
    }

    public static boolean m289q(Context context) {
        Object c = C0384x.m688a(context).m696b().m683c("");
        if (TextUtils.isEmpty(c)) {
            if (bj.m281i(context) == null) {
                c = bj.m290r(context)[0];
                if (!TextUtils.isEmpty(c) && c.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
            int i = context.getResources().getConfiguration().mcc;
            if (i == 460 || i == 461) {
                return true;
            }
            if (i == 0) {
                c = bj.m290r(context)[0];
                if (!TextUtils.isEmpty(c) && c.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
            return false;
        } else if (c.equals("cn")) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] m290r(Context context) {
        String[] strArr = new String[2];
        try {
            Locale G = bj.m252G(context);
            if (G != null) {
                strArr[0] = G.getCountry();
                strArr[1] = G.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
        } catch (Throwable e) {
            bl.m341e(f347a, "error in getLocaleInfo", e);
        }
        return strArr;
    }

    private static Locale m252G(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Exception e) {
            bl.m331c(f347a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String m291s(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                bl.m331c(f347a, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable e) {
            bl.m341e(f347a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    public static String m292t(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                String string = applicationInfo.metaData.getString("UMENG_TOKEN");
                if (string != null) {
                    return string.trim();
                }
                bl.m331c(f347a, "getToken failed.");
            }
        } catch (Throwable e) {
            bl.m341e(f347a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e);
        }
        return null;
    }

    public static String m293u(Context context) {
        if (VERSION.SDK_INT < 23) {
            return bj.m253H(context);
        }
        String d = bj.m273d();
        if (d == null) {
            return bj.m253H(context);
        }
        return d;
    }

    private static String m253H(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (bj.m264a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            bl.m343e(f347a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            bl.m343e(f347a, "Could not get mac address." + e.toString());
            return "";
        }
    }

    public static String m294v(Context context) {
        int[] w = bj.m295w(context);
        if (w == null) {
            return "Unknown";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(w[0]);
        stringBuffer.append("*");
        stringBuffer.append(w[1]);
        return stringBuffer.toString();
    }

    public static int[] m295w(Context context) {
        try {
            int a;
            int a2;
            int i;
            Object displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = bj.m256a(displayMetrics, "noncompatWidthPixels");
                a2 = bj.m256a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = -1;
                a = -1;
            }
            if (a == -1 || a2 == -1) {
                i = displayMetrics.widthPixels;
                a = displayMetrics.heightPixels;
            } else {
                i = a;
                a = a2;
            }
            int[] iArr = new int[2];
            if (i > a) {
                iArr[0] = a;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = a;
            return iArr;
        } catch (Throwable e) {
            bl.m341e(f347a, "read resolution fail", e);
            return null;
        }
    }

    private static int m256a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String m296x(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Throwable e) {
            bl.m329c(f347a, "read carrier fail", e);
            return "Unknown";
        }
    }

    public static String m259a(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String m271c() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static Date m262a(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static int m257a(Date date, Date date2) {
        if (!date.after(date2)) {
            Date date3 = date2;
            date2 = date;
            date = date3;
        }
        return (int) ((date.getTime() - date2.getTime()) / 1000);
    }

    public static String m297y(Context context) {
        String str = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                    bl.m319a(f347a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                }
            }
        } catch (Exception e) {
            bl.m319a(f347a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e.printStackTrace();
        }
        return str;
    }

    public static String m298z(Context context) {
        return context.getPackageName();
    }

    public static String m246A(Context context) {
        PackageInfo packageInfo;
        String str = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(bj.m298z(context), 64);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Object obj = str;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            obj = str;
        }
        try {
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e22) {
            e22.printStackTrace();
            obj = str;
        }
        try {
            str = bj.m261a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (CertificateEncodingException e4) {
            e4.printStackTrace();
        }
        return str;
    }

    private static String m261a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = "0" + toHexString;
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    public static String m247B(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean m248C(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String m249D(Context context) {
        String str = null;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable e) {
            bl.m318a(f347a, e);
        }
        return str;
    }

    private static String m254I(Context context) {
        String deviceId;
        Throwable e;
        CharSequence charSequence = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (VERSION.SDK_INT >= 23) {
            if (telephonyManager != null) {
                try {
                    if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
                        deviceId = telephonyManager.getDeviceId();
                        try {
                            bl.m319a(f347a, "getDeviceId, IMEI: " + deviceId);
                        } catch (Exception e2) {
                            e = e2;
                            bl.m335d(f347a, "No IMEI.", e);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            deviceId = bj.m273d();
                            bl.m319a(f347a, "getDeviceId, mc: " + deviceId);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            deviceId = Secure.getString(context.getContentResolver(), "android_id");
                            bl.m319a(f347a, "getDeviceId, android_id: " + deviceId);
                            if (!TextUtils.isEmpty(deviceId)) {
                                return deviceId;
                            }
                            if (VERSION.SDK_INT >= 9) {
                                deviceId = Build.SERIAL;
                            }
                            bl.m319a(f347a, "getDeviceId, serial no: " + deviceId);
                            return deviceId;
                        }
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = bj.m273d();
                        bl.m319a(f347a, "getDeviceId, mc: " + deviceId);
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        deviceId = Secure.getString(context.getContentResolver(), "android_id");
                        bl.m319a(f347a, "getDeviceId, android_id: " + deviceId);
                        if (!TextUtils.isEmpty(deviceId)) {
                            return deviceId;
                        }
                        if (VERSION.SDK_INT >= 9) {
                            deviceId = Build.SERIAL;
                        }
                        bl.m319a(f347a, "getDeviceId, serial no: " + deviceId);
                        return deviceId;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    deviceId = charSequence;
                    e = th;
                    bl.m335d(f347a, "No IMEI.", e);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    deviceId = bj.m273d();
                    bl.m319a(f347a, "getDeviceId, mc: " + deviceId);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    deviceId = Secure.getString(context.getContentResolver(), "android_id");
                    bl.m319a(f347a, "getDeviceId, android_id: " + deviceId);
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    if (VERSION.SDK_INT >= 9) {
                        deviceId = Build.SERIAL;
                    }
                    bl.m319a(f347a, "getDeviceId, serial no: " + deviceId);
                    return deviceId;
                }
            }
            CharSequence charSequence2 = charSequence;
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = bj.m273d();
            bl.m319a(f347a, "getDeviceId, mc: " + deviceId);
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            bl.m319a(f347a, "getDeviceId, android_id: " + deviceId);
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            if (VERSION.SDK_INT >= 9) {
                deviceId = Build.SERIAL;
            }
            bl.m319a(f347a, "getDeviceId, serial no: " + deviceId);
            return deviceId;
        }
        if (telephonyManager != null) {
            try {
                if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
                    charSequence = telephonyManager.getDeviceId();
                }
            } catch (Throwable e32) {
                bl.m335d(f347a, "No IMEI.", e32);
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        bl.m343e(f347a, "No IMEI.");
        deviceId = bj.m293u(context);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        bl.m343e(f347a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
        deviceId = Secure.getString(context.getContentResolver(), "android_id");
        bl.m319a(f347a, "getDeviceId: Secure.ANDROID_ID: " + deviceId);
        return deviceId;
    }

    private static String m255J(Context context) {
        Throwable e;
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String string;
        if (VERSION.SDK_INT >= 23) {
            string = Secure.getString(context.getContentResolver(), "android_id");
            bl.m319a(f347a, "getDeviceId, android_id: " + string);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            string = bj.m273d();
            bl.m319a(f347a, "getDeviceId, mc: " + string);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            if (telephonyManager != null) {
                try {
                    if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
                        str = telephonyManager.getDeviceId();
                        try {
                            bl.m319a(f347a, "getDeviceId, IMEI: " + str);
                        } catch (Exception e2) {
                            e = e2;
                            bl.m335d(f347a, "No IMEI.", e);
                            if (!TextUtils.isEmpty(str)) {
                                return str;
                            }
                            if (VERSION.SDK_INT >= 9) {
                                str = Build.SERIAL;
                            }
                            bl.m319a(f347a, "getDeviceId, serial no: " + str);
                            return str;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            return str;
                        }
                        if (VERSION.SDK_INT >= 9) {
                            str = Build.SERIAL;
                        }
                        bl.m319a(f347a, "getDeviceId, serial no: " + str);
                        return str;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    str = string;
                    e = th;
                    bl.m335d(f347a, "No IMEI.", e);
                    if (!TextUtils.isEmpty(str)) {
                        return str;
                    }
                    if (VERSION.SDK_INT >= 9) {
                        str = Build.SERIAL;
                    }
                    bl.m319a(f347a, "getDeviceId, serial no: " + str);
                    return str;
                }
            }
            str = string;
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (VERSION.SDK_INT >= 9) {
                str = Build.SERIAL;
            }
            bl.m319a(f347a, "getDeviceId, serial no: " + str);
            return str;
        }
        string = Secure.getString(context.getContentResolver(), "android_id");
        bl.m319a(f347a, "getDeviceId: Secure.ANDROID_ID: " + string);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        bl.m343e(f347a, "No IMEI.");
        string = bj.m293u(context);
        if (!TextUtils.isEmpty(string) || telephonyManager == null) {
            return string;
        }
        try {
            if (bj.m264a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            } else {
                str = string;
            }
            return str;
        } catch (Throwable e32) {
            bl.m335d(f347a, "No IMEI.", e32);
            return string;
        }
    }

    public static String m250E(Context context) {
        Properties e = bj.m276e();
        try {
            String property = e.getProperty(f352f);
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (bj.m278f()) {
                return "Flyme";
            }
            if (TextUtils.isEmpty(bj.m260a(e))) {
                return property;
            }
            return "YunOS";
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String m251F(Context context) {
        Properties e = bj.m276e();
        try {
            String property = e.getProperty(f352f);
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (bj.m278f()) {
                try {
                    return bj.m268b(e);
                } catch (Exception e2) {
                    return property;
                }
            }
            try {
                return bj.m260a(e);
            } catch (Exception e3) {
                return property;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private static String m260a(Properties properties) {
        Object property = properties.getProperty("ro.yunos.version");
        return !TextUtils.isEmpty(property) ? property : null;
    }

    private static String m268b(Properties properties) {
        try {
            String toLowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (toLowerCase.contains("flyme os")) {
                return toLowerCase.split(" ")[2];
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static Properties m276e() {
        FileInputStream fileInputStream;
        IOException e;
        Throwable th;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return properties;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileInputStream = null;
            e22.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return properties;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return properties;
    }

    private static boolean m278f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
