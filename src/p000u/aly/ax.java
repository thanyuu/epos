package p000u.aly;

import android.content.Context;
import p000u.aly.C0384x.C0383a;
import p000u.aly.av.C0625o;

/* compiled from: Defcon */
public class ax implements ao {
    private static final int f722a = 0;
    private static final int f723b = 1;
    private static final int f724c = 2;
    private static final int f725d = 3;
    private static final long f726e = 14400000;
    private static final long f727f = 28800000;
    private static final long f728g = 86400000;
    private static ax f729j = null;
    private int f730h = 0;
    private final long f731i = 60000;

    public static synchronized ax m867a(Context context) {
        ax axVar;
        synchronized (ax.class) {
            if (f729j == null) {
                f729j = new ax();
                f729j.m869a(C0384x.m688a(context).m696b().m673a(0));
            }
            axVar = f729j;
        }
        return axVar;
    }

    private ax() {
    }

    public void m870a(av avVar, Context context) {
        if (this.f730h == 1) {
            avVar.f335b.f285i = null;
            avVar.f335b.f277a = null;
            avVar.f335b.f278b = null;
            avVar.f335b.f284h = null;
        } else if (this.f730h == 2) {
            avVar.f335b.f279c.clear();
            avVar.f335b.f279c.add(m873b(context));
            avVar.f335b.f285i = null;
            avVar.f335b.f277a = null;
            avVar.f335b.f278b = null;
            avVar.f335b.f284h = null;
        } else if (this.f730h == 3) {
            avVar.f335b.f279c = null;
            avVar.f335b.f285i = null;
            avVar.f335b.f277a = null;
            avVar.f335b.f278b = null;
            avVar.f335b.f284h = null;
        }
    }

    public C0625o m873b(Context context) {
        C0625o c0625o = new C0625o();
        c0625o.f706b = ar.m215g(context);
        long currentTimeMillis = System.currentTimeMillis();
        c0625o.f707c = currentTimeMillis;
        c0625o.f708d = currentTimeMillis + 60000;
        c0625o.f709e = 60000;
        return c0625o;
    }

    public long m868a() {
        switch (this.f730h) {
            case 1:
                return f726e;
            case 2:
                return f727f;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    public long m872b() {
        return this.f730h == 0 ? 0 : 300000;
    }

    public void m869a(int i) {
        if (i >= 0 && i <= 3) {
            this.f730h = i;
        }
    }

    public boolean m874c() {
        return this.f730h != 0;
    }

    public void mo1740a(C0383a c0383a) {
        m869a(c0383a.m673a(0));
    }
}
