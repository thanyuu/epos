package p000u.aly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.android.common.utils.HttpUtils;
import com.bumptech.glide.load.Key;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0291a;
import com.umeng.analytics.C0292b;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;

/* compiled from: NetworkHelper */
public class al {
    private String f156a;
    private String f157b = "10.0.0.172";
    private int f158c = 80;
    private Context f159d;
    private aj f160e;

    public al(Context context) {
        this.f159d = context;
        this.f156a = m185a(context);
    }

    public void m189a(aj ajVar) {
        this.f160e = ajVar;
    }

    private void m186a() {
        bl.m322b("constructURLS");
        String b = C0384x.m688a(this.f159d).m696b().m680b("");
        String a = C0384x.m688a(this.f159d).m696b().m675a("");
        if (!TextUtils.isEmpty(b)) {
            C0291a.f37f = C0292b.m38b(b);
        }
        if (!TextUtils.isEmpty(a)) {
            C0291a.f38g = C0292b.m38b(a);
        }
        C0291a.f40i = new String[]{C0291a.f37f, C0291a.f38g};
        if (bj.m289q(this.f159d)) {
            C0291a.f40i = new String[]{C0291a.f38g, C0291a.f37f};
        } else {
            int b2 = aw.m855a(this.f159d).m864b();
            if (b2 != -1) {
                if (b2 == 0) {
                    C0291a.f40i = new String[]{C0291a.f37f, C0291a.f38g};
                } else if (b2 == 1) {
                    C0291a.f40i = new String[]{C0291a.f38g, C0291a.f37f};
                }
            }
        }
        bl.m322b("constructURLS list size:" + C0291a.f40i.length);
    }

    public byte[] m190a(byte[] bArr) {
        byte[] bArr2 = null;
        for (String a : C0291a.f40i) {
            bArr2 = m187a(bArr, a);
            if (bArr2 != null) {
                if (this.f160e != null) {
                    this.f160e.mo1749c();
                }
                return bArr2;
            }
            if (this.f160e != null) {
                this.f160e.mo1750d();
            }
        }
        return bArr2;
    }

    private boolean m188b() {
        if (this.f159d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f159d.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f159d.getSystemService("connectivity");
            if (!bj.m264a(this.f159d, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] m187a(byte[] bArr, String str) {
        InputStream inputStream;
        Throwable e;
        HttpURLConnection httpURLConnection;
        try {
            if (this.f160e != null) {
                this.f160e.mo1747a();
            }
            if (m188b()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.f157b, this.f158c)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.f156a);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.f160e != null) {
                    this.f160e.mo1748b();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Object headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    headerField = null;
                } else {
                    headerField = 1;
                }
                if (responseCode != 200 || r0 == null) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                bl.m328c("Send message to " + str);
                inputStream = httpURLConnection.getInputStream();
                byte[] b = bk.m311b(inputStream);
                bk.m313c(inputStream);
                if (httpURLConnection == null) {
                    return b;
                }
                httpURLConnection.disconnect();
                return b;
            } catch (Exception e2) {
                e = e2;
                try {
                    bl.m342e("IOException,Failed to send message.", e);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                bk.m313c(inputStream);
            }
        } catch (Exception e3) {
            e = e3;
            httpURLConnection = null;
            bl.m342e("IOException,Failed to send message.", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th3) {
            e = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }

    private String m185a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append(HttpUtils.PATHS_SEPARATOR);
        stringBuffer.append(C0291a.f34c);
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(bj.m247B(context));
            stringBuffer2.append(HttpUtils.PATHS_SEPARATOR);
            stringBuffer2.append(bj.m274d(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append(HttpUtils.PATHS_SEPARATOR);
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(bk.m303a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), Key.STRING_CHARSET_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
