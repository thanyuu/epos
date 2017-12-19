package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0291a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MemoCache */
public class ak {
    private List<ai> f154a = new ArrayList();
    private Context f155b = null;

    public ak(Context context) {
        this.f155b = context;
    }

    public Context m179a() {
        return this.f155b;
    }

    protected boolean m182a(int i) {
        return true;
    }

    public synchronized int m183b() {
        int size;
        size = this.f154a.size();
        if (av.f332c != 0) {
            size++;
        }
        return size;
    }

    public synchronized void m180a(ai aiVar) {
        this.f154a.add(aiVar);
    }

    public void m181a(av avVar) {
        if (ar.m215g(this.f155b) != null) {
            m184b(avVar);
            m178c(avVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m178c(p000u.aly.av r7) {
        /*
        r6 = this;
        r5 = 1;
        r4 = 0;
        monitor-enter(r6);
        r0 = r6.f154a;	 Catch:{ all -> 0x0019 }
        r1 = r0.iterator();	 Catch:{ all -> 0x0019 }
    L_0x0009:
        r0 = r1.hasNext();	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x001c;
    L_0x000f:
        r0 = r1.next();	 Catch:{ all -> 0x0019 }
        r0 = (p000u.aly.ai) r0;	 Catch:{ all -> 0x0019 }
        r0.mo1751a(r7);	 Catch:{ all -> 0x0019 }
        goto L_0x0009;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        throw r0;
    L_0x001c:
        r0 = r6.f155b;	 Catch:{ all -> 0x0019 }
        r0 = p000u.aly.ap.m197a(r0);	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0026;
    L_0x0024:
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
    L_0x0025:
        return;
    L_0x0026:
        r1 = "userlevel";
        r2 = "";
        r0 = r0.getString(r1, r2);	 Catch:{ all -> 0x0019 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0019 }
        if (r1 != 0) goto L_0x0038;
    L_0x0034:
        r1 = r7.f335b;	 Catch:{ all -> 0x0019 }
        r1.f286j = r0;	 Catch:{ all -> 0x0019 }
    L_0x0038:
        r0 = r6.f154a;	 Catch:{ all -> 0x0019 }
        r0.clear();	 Catch:{ all -> 0x0019 }
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        r0 = p000u.aly.av.f332c;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x004e;
    L_0x0046:
        r0 = r7.f335b;
        r0 = r0.f280d;
        r2 = p000u.aly.av.f332c;
        r0.f243a = r2;
    L_0x004e:
        r0 = r6.f155b;
        r0 = p000u.aly.C0370m.m556a(r0);
        r0.m582a(r7);
        r0 = r6.f155b;
        r0 = com.umeng.analytics.C0294e.m44a(r0);
        if (r0 == 0) goto L_0x007f;
    L_0x005f:
        r1 = r0[r4];
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x007f;
    L_0x0067:
        r1 = r0[r5];
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x007f;
    L_0x006f:
        r1 = r7.f335b;
        r1 = r1.f283g;
        r2 = r0[r4];
        r1.f245a = r2;
        r1 = r7.f335b;
        r1 = r1.f283g;
        r0 = r0[r5];
        r1.f246b = r0;
    L_0x007f:
        r0 = r6.f155b;
        r0 = p000u.aly.aw.m855a(r0);
        r0.m861a(r7);
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.ak.c(u.aly.av):void");
    }

    void m184b(av avVar) {
        String[] m;
        avVar.f334a.f303a = AnalyticsConfig.getAppkey(this.f155b);
        avVar.f334a.f304b = AnalyticsConfig.getChannel(this.f155b);
        avVar.f334a.f305c = bk.m303a(AnalyticsConfig.getSecretKey(this.f155b));
        avVar.f334a.f315m = AnalyticsConfig.getVerticalType(this.f155b);
        avVar.f334a.f314l = AnalyticsConfig.getSDKVersion(this.f155b);
        avVar.f334a.f307e = bj.m298z(this.f155b);
        int parseInt = Integer.parseInt(bj.m272c(this.f155b));
        String d = bj.m274d(this.f155b);
        SharedPreferences a = ap.m197a(this.f155b);
        if (a == null) {
            avVar.f334a.f310h = parseInt;
            avVar.f334a.f306d = d;
        } else {
            avVar.f334a.f310h = a.getInt(C0291a.f56y, 0);
            avVar.f334a.f306d = a.getString(C0291a.f57z, "");
        }
        avVar.f334a.f308f = bj.m246A(this.f155b);
        avVar.f334a.f309g = bj.m249D(this.f155b);
        if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
            avVar.f334a.f311i = AnalyticsConfig.mWrapperType;
            avVar.f334a.f312j = AnalyticsConfig.mWrapperVersion;
        }
        avVar.f334a.f322t = bj.m277f(this.f155b);
        avVar.f334a.f316n = bj.m279g(this.f155b);
        avVar.f334a.f321s = bj.m293u(this.f155b);
        avVar.f334a.f289B = bj.m250E(this.f155b);
        avVar.f334a.f290C = bj.m251F(this.f155b);
        int[] w = bj.m295w(this.f155b);
        if (w != null) {
            avVar.f334a.f320r = w[1] + "*" + w[0];
        }
        if (AnalyticsConfig.GPU_RENDERER == null || AnalyticsConfig.GPU_VENDER != null) {
            m = bj.m285m(this.f155b);
        } else {
            m = bj.m285m(this.f155b);
        }
        if ("Wi-Fi".equals(m[0])) {
            avVar.f334a.f295H = "wifi";
        } else if (bj.f349c.equals(m[0])) {
            avVar.f334a.f295H = bj.f349c;
        } else {
            avVar.f334a.f295H = "unknow";
        }
        if (!"".equals(m[1])) {
            avVar.f334a.f296I = m[1];
        }
        Object h = bj.m280h(this.f155b);
        if (!TextUtils.isEmpty(h)) {
            avVar.f334a.f297J = h;
        }
        avVar.f334a.f294G = bj.m283k(this.f155b);
        m = bj.m290r(this.f155b);
        avVar.f334a.f293F = m[0];
        avVar.f334a.f292E = m[1];
        avVar.f334a.f291D = (long) bj.m288p(this.f155b);
        as.m836a(this.f155b, avVar);
        try {
            bp b = C0382v.m659a(this.f155b).m666b();
            if (b != null) {
                byte[] a2 = new by().m415a(b);
                avVar.f334a.f302O = Base64.encodeToString(a2, 0);
                try {
                    b = C0384x.m688a(this.f155b).m693a();
                    if (b == null) {
                        bl.m340e("trans the imprint is null");
                        return;
                    }
                    a2 = new by().m415a(b);
                    avVar.f334a.f301N = Base64.encodeToString(a2, 0);
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
    }
}
