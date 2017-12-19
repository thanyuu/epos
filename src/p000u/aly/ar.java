package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0291a;
import java.lang.reflect.Method;
import p000u.aly.av.C0625o;

/* compiled from: SessionTracker */
public class ar {
    private static final String f174a = "session_start_time";
    private static final String f175b = "session_end_time";
    private static final String f176c = "session_id";
    private static final String f177f = "activities";
    private static final String f178g = "uptr";
    private static final String f179h = "dntr";
    private static String f180i = null;
    private static Context f181j = null;
    private final String f182d = "a_start_time";
    private final String f183e = "a_end_time";

    public C0625o m216a(Context context) {
        SharedPreferences a = ap.m197a(context);
        String string = a.getString(f176c, null);
        if (string == null) {
            return null;
        }
        long j = a.getLong(f174a, 0);
        long j2 = a.getLong(f175b, 0);
        long j3 = 0;
        if (j2 != 0) {
            j3 = j2 - j;
            if (Math.abs(j3) > C0291a.f41j) {
                j3 = 0;
            }
        }
        C0625o c0625o = new C0625o();
        c0625o.f706b = string;
        c0625o.f707c = j;
        c0625o.f708d = j2;
        c0625o.f709e = j3;
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            c0625o.f713j.f268a = location[0];
            c0625o.f713j.f269b = location[1];
            c0625o.f713j.f270c = System.currentTimeMillis();
        }
        try {
            Class cls = Class.forName("android.net.TrafficStats");
            Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
            Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
            if (context.getApplicationInfo().uid == -1) {
                return null;
            }
            j = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
            j3 = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r5)})).longValue();
            if (j > 0 && j3 > 0) {
                long j4 = a.getLong(f178g, -1);
                j2 = a.getLong(f179h, -1);
                a.edit().putLong(f178g, j3).putLong(f179h, j).commit();
                if (j4 > 0 && j2 > 0) {
                    j -= j2;
                    j3 -= j4;
                    if (j > 0 && j3 > 0) {
                        c0625o.f712i.f330a = j;
                        c0625o.f712i.f331b = j3;
                    }
                }
            }
            at.m222a(a, c0625o);
            m213a(a);
            return c0625o;
        } catch (Throwable th) {
        }
    }

    private void m213a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove(f174a);
        edit.remove(f175b);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.putString(f177f, "");
        edit.commit();
    }

    public String m217b(Context context) {
        String f = bj.m277f(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(appkey).append(f);
        f180i = bk.m303a(stringBuilder.toString());
        return f180i;
    }

    public void m218c(Context context) {
        f181j = context;
        SharedPreferences a = ap.m197a(context);
        if (a != null) {
            Editor edit = a.edit();
            int i = a.getInt(C0291a.f56y, 0);
            int parseInt = Integer.parseInt(bj.m272c(f181j));
            if (i != 0 && parseInt != i) {
                if (ar.m215g(context) == null) {
                    m212a(context, a);
                }
                m220e(f181j);
                ae.m823a(f181j).mo1743c();
                m221f(f181j);
            } else if (m214b(a)) {
                bl.m328c("Start new session: " + m212a(context, a));
            } else {
                String string = a.getString(f176c, null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                bl.m328c("Extend current session: " + string);
            }
        }
    }

    public void m219d(Context context) {
        SharedPreferences a = ap.m197a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                bl.m340e("onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = a.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong(f175b, currentTimeMillis);
            edit.commit();
        }
    }

    private boolean m214b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < AnalyticsConfig.kContinueSessionMillis) {
            bl.m340e("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 > AnalyticsConfig.kContinueSessionMillis) {
            return true;
        } else {
            return false;
        }
    }

    private String m212a(Context context, SharedPreferences sharedPreferences) {
        ae a = ae.m823a(context);
        String b = m217b(context);
        ai a2 = m216a(context);
        Editor edit = sharedPreferences.edit();
        edit.putString(f176c, b);
        edit.putLong(f174a, System.currentTimeMillis());
        edit.putLong(f175b, 0);
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0);
        edit.putInt(C0291a.f56y, Integer.parseInt(bj.m272c(context)));
        edit.putString(C0291a.f57z, bj.m274d(context));
        edit.commit();
        if (a2 != null) {
            a.mo1739a(a2);
        } else {
            a.mo1739a((C0625o) null);
        }
        return b;
    }

    public boolean m220e(Context context) {
        boolean z = false;
        SharedPreferences a = ap.m197a(context);
        if (!(a == null || a.getString(f176c, null) == null)) {
            long j = a.getLong("a_start_time", 0);
            long j2 = a.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                z = true;
                m219d(context);
            }
            ae a2 = ae.m823a(context);
            ai a3 = m216a(context);
            if (a3 != null) {
                a2.mo1742b(a3);
            }
        }
        return z;
    }

    public void m221f(Context context) {
        SharedPreferences a = ap.m197a(context);
        if (a != null) {
            String b = m217b(context);
            Editor edit = a.edit();
            edit.putString(f176c, b);
            edit.putLong(f174a, System.currentTimeMillis());
            edit.putLong(f175b, 0);
            edit.putLong("a_start_time", System.currentTimeMillis());
            edit.putLong("a_end_time", 0);
            edit.putInt(C0291a.f56y, Integer.parseInt(bj.m272c(context)));
            edit.putString(C0291a.f57z, bj.m274d(context));
            edit.commit();
            bl.m328c("Restart session: " + b);
        }
    }

    public static String m215g(Context context) {
        if (f180i == null) {
            f180i = ap.m197a(context).getString(f176c, null);
        }
        return f180i;
    }

    public static String m211a() {
        try {
            if (f180i == null) {
                f180i = ap.m197a(f181j).getString(f176c, null);
            }
        } catch (Exception e) {
        }
        return f180i;
    }
}
