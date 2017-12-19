package p000u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0292b;
import com.umeng.analytics.C0306h;
import p000u.aly.C0384x.C0383a;

/* compiled from: ImLatent */
public class ay implements ao {
    private static ay f732l = null;
    private final long f733a = 1296000000;
    private final long f734b = 129600000;
    private final int f735c = 1800000;
    private final int f736d = 10000;
    private C0306h f737e;
    private as f738f;
    private long f739g = 1296000000;
    private int f740h = 10000;
    private long f741i = 0;
    private long f742j = 0;
    private Context f743k;

    public static synchronized ay m875a(Context context, as asVar) {
        ay ayVar;
        synchronized (ay.class) {
            if (f732l == null) {
                f732l = new ay(context, asVar);
                f732l.mo1740a(C0384x.m688a(context).m696b());
            }
            ayVar = f732l;
        }
        return ayVar;
    }

    private ay(Context context, as asVar) {
        this.f743k = context;
        this.f737e = C0306h.m71a(context);
        this.f738f = asVar;
    }

    public boolean m877a() {
        if (this.f737e.m97i() || this.f738f.m842f()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f738f.m851o();
        if (currentTimeMillis > this.f739g) {
            this.f741i = (long) C0292b.m32a(this.f740h, C0380t.m641a(this.f743k));
            this.f742j = currentTimeMillis;
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            this.f741i = 0;
            this.f742j = currentTimeMillis;
            return true;
        }
    }

    public long m878b() {
        return this.f741i;
    }

    public long m879c() {
        return this.f742j;
    }

    public void mo1740a(C0383a c0383a) {
        this.f739g = c0383a.m674a(1296000000);
        int b = c0383a.m679b(0);
        if (b != 0) {
            this.f740h = b;
        } else if (AnalyticsConfig.sLatentWindow <= 0 || AnalyticsConfig.sLatentWindow > 1800000) {
            this.f740h = 10000;
        } else {
            this.f740h = AnalyticsConfig.sLatentWindow;
        }
    }
}
