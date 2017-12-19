package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0292b;
import com.umeng.analytics.C0295f;
import com.umeng.analytics.C0296g;
import com.umeng.analytics.C0306h;
import com.umeng.analytics.ReportPolicy.C0290i;
import com.umeng.analytics.ReportPolicy.C0587a;
import com.umeng.analytics.ReportPolicy.C0588b;
import com.umeng.analytics.ReportPolicy.C0589c;
import com.umeng.analytics.ReportPolicy.C0590d;
import com.umeng.analytics.ReportPolicy.C0591e;
import com.umeng.analytics.ReportPolicy.C0593g;
import com.umeng.analytics.ReportPolicy.C0594h;
import com.umeng.analytics.ReportPolicy.C0595j;
import com.umeng.analytics.ReportPolicy.C0596k;
import java.util.List;
import p000u.aly.C0384x.C0383a;
import p000u.aly.av.C0325h;
import p000u.aly.av.C0625o;

/* compiled from: CacheImpl */
public final class ad implements ah, ao {
    private static Context f652o;
    private final long f653a = 28800000;
    private final int f654b = 5000;
    private ak f655c = null;
    private C0306h f656d = null;
    private as f657e = null;
    private ax f658f = null;
    private aw f659g = null;
    private ay f660h = null;
    private C0317a f661i = null;
    private C0383a f662j = null;
    private int f663k = 10;
    private long f664l = 0;
    private int f665m = 0;
    private int f666n = 0;

    /* compiled from: CacheImpl */
    public class C0317a {
        final /* synthetic */ ad f141a;
        private C0290i f142b;
        private int f143c = -1;
        private int f144d = -1;
        private int f145e = -1;
        private int f146f = -1;

        public C0317a(ad adVar) {
            this.f141a = adVar;
            int[] a = adVar.f662j.m678a(-1, -1);
            this.f143c = a[0];
            this.f144d = a[1];
        }

        protected void m156a(boolean z) {
            int i = 1;
            int i2 = 0;
            if (this.f141a.f658f.m874c()) {
                C0290i c0290i;
                if (!((this.f142b instanceof C0588b) && this.f142b.mo1732a())) {
                    i = 0;
                }
                if (i != 0) {
                    c0290i = this.f142b;
                } else {
                    c0290i = new C0588b(this.f141a.f657e, this.f141a.f658f);
                }
                this.f142b = c0290i;
            } else {
                if (!((this.f142b instanceof C0589c) && this.f142b.mo1732a())) {
                    i = 0;
                }
                if (i == 0) {
                    if (z && this.f141a.f660h.m877a()) {
                        this.f142b = new C0589c((int) this.f141a.f660h.m878b());
                        this.f141a.m801b((int) this.f141a.f660h.m878b());
                    } else if (bl.f355a && this.f141a.f662j.m681b()) {
                        bl.m322b("Debug: send log every 15 seconds");
                        this.f142b = new C0587a(this.f141a.f657e);
                    } else if (this.f141a.f659g.m863a()) {
                        bl.m322b("Start A/B Test");
                        if (this.f141a.f659g.m864b() == 6) {
                            if (this.f141a.f662j.m677a()) {
                                i2 = this.f141a.f662j.m684d(90000);
                            } else if (this.f144d > 0) {
                                i2 = this.f144d;
                            } else {
                                i2 = this.f146f;
                            }
                        }
                        this.f142b = m153b(this.f141a.f659g.m864b(), i2);
                    } else {
                        i = this.f145e;
                        i2 = this.f146f;
                        if (this.f143c != -1) {
                            i = this.f143c;
                            i2 = this.f144d;
                        }
                        this.f142b = m153b(i, i2);
                    }
                }
            }
            bl.m322b("Report policy : " + this.f142b.getClass().getSimpleName());
        }

        public C0290i m157b(boolean z) {
            m156a(z);
            return this.f142b;
        }

        private C0290i m153b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.f142b instanceof C0594h ? this.f142b : new C0594h();
                case 1:
                    return this.f142b instanceof C0590d ? this.f142b : new C0590d();
                case 4:
                    if (this.f142b instanceof C0593g) {
                        return this.f142b;
                    }
                    return new C0593g(this.f141a.f657e);
                case 5:
                    if (this.f142b instanceof C0595j) {
                        return this.f142b;
                    }
                    return new C0595j(ad.f652o);
                case 6:
                    if (!(this.f142b instanceof C0591e)) {
                        return new C0591e(this.f141a.f657e, (long) i2);
                    }
                    C0290i c0290i = this.f142b;
                    ((C0591e) c0290i).m712a((long) i2);
                    return c0290i;
                case 8:
                    if (this.f142b instanceof C0596k) {
                        return this.f142b;
                    }
                    return new C0596k(this.f141a.f657e);
                default:
                    if (this.f142b instanceof C0590d) {
                        return this.f142b;
                    }
                    return new C0590d();
            }
        }

        public void m154a(int i, int i2) {
            this.f145e = i;
            this.f146f = i2;
        }

        public void m155a(C0383a c0383a) {
            int[] a = c0383a.m678a(-1, -1);
            this.f143c = a[0];
            this.f144d = a[1];
        }
    }

    /* compiled from: CacheImpl */
    class C06161 extends C0296g {
        final /* synthetic */ ad f651a;

        C06161(ad adVar) {
            this.f651a = adVar;
        }

        public void mo1733a() {
            this.f651a.mo1738a();
        }
    }

    public ad(Context context) {
        f652o = context;
        this.f655c = new ak(context);
        this.f657e = new as(context);
        this.f656d = C0306h.m71a(context);
        this.f662j = C0384x.m688a(context).m696b();
        this.f661i = new C0317a(this);
        this.f659g = aw.m855a(f652o);
        this.f658f = ax.m867a(f652o);
        this.f660h = ay.m875a(f652o, this.f657e);
        SharedPreferences a = ap.m197a(f652o);
        this.f664l = a.getLong("thtstart", 0);
        this.f665m = a.getInt("gkvc", 0);
        this.f666n = a.getInt("ekvc", 0);
    }

    public void mo1738a() {
        if (bj.m287o(f652o)) {
            m810f();
        } else {
            bl.m322b("network is unavailable");
        }
    }

    public void mo1739a(ai aiVar) {
        if (aiVar != null) {
            this.f655c.m180a(aiVar);
        }
        m799a(aiVar instanceof C0625o);
    }

    public void mo1742b(ai aiVar) {
        this.f655c.m180a(aiVar);
    }

    public void mo1741b() {
        if (this.f655c.m183b() > 0) {
            try {
                this.f656d.m84a(m812a(new int[0]));
            } catch (Throwable th) {
                bl.m344e(th);
                if (th instanceof OutOfMemoryError) {
                    this.f656d.m96h();
                }
                if (th != null) {
                    th.printStackTrace();
                }
            }
        }
        ap.m197a(f652o).edit().putLong("thtstart", this.f664l).putInt("gkvc", this.f665m).putInt("ekvc", this.f666n).commit();
    }

    public void mo1743c() {
        m798a(m812a(new int[0]));
    }

    private void m799a(boolean z) {
        boolean f = this.f657e.m842f();
        if (f) {
            av.f332c = this.f657e.m850n();
        }
        if (m803b(z)) {
            m810f();
        } else if (f || m809e()) {
            mo1741b();
        }
    }

    private void m795a(int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() - this.f657e.m851o());
        m798a(m812a(i, currentTimeMillis));
        C0295f.m48a(new C06161(this), (long) i);
    }

    private void m798a(av avVar) {
        if (avVar != null) {
            try {
                byte[] a;
                C0382v a2 = C0382v.m659a(f652o);
                a2.m663a();
                try {
                    a = new by().m415a(a2.m666b());
                    avVar.f334a.f302O = Base64.encodeToString(a, 0);
                } catch (Exception e) {
                }
                a = C0306h.m71a(f652o).m89b(m805c(avVar));
                if (a != null && !C0292b.m35a(f652o, a)) {
                    C0380t b;
                    if (m811g()) {
                        b = C0380t.m644b(f652o, AnalyticsConfig.getAppkey(f652o), a);
                    } else {
                        b = C0380t.m642a(f652o, AnalyticsConfig.getAppkey(f652o), a);
                    }
                    a = b.m653c();
                    C0306h a3 = C0306h.m71a(f652o);
                    a3.m96h();
                    a3.m85a(a);
                    a2.m668d();
                    av.f332c = 0;
                }
            } catch (Exception e2) {
            }
        }
    }

    protected av m812a(int... iArr) {
        Object obj = null;
        try {
            if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(f652o))) {
                bl.m340e("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            }
            av g = C0306h.m71a(f652o).m95g();
            if (g == null && this.f655c.m183b() == 0) {
                return null;
            }
            if (g == null) {
                g = new av();
            }
            this.f655c.m181a(g);
            if (g.f335b.f279c != null && bl.f355a && g.f335b.f279c.size() > 0) {
                for (C0625o c0625o : g.f335b.f279c) {
                    Object obj2;
                    if (c0625o.f711h.size() > 0) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj == null) {
                    bl.m334d("missing Activities or PageViews");
                }
            }
            this.f658f.m870a(g, f652o);
            if (iArr != null && iArr.length == 2) {
                g.f335b.f281e.f261a = Integer.valueOf(iArr[0] / 1000);
                g.f335b.f281e.f262b = (long) iArr[1];
                g.f335b.f281e.f263c = true;
            }
            return g;
        } catch (Throwable e) {
            bl.m342e("Fail to construct message ...", e);
            C0306h.m71a(f652o).m96h();
            bl.m344e(e);
            return null;
        }
    }

    private boolean m803b(boolean z) {
        if (!bj.m287o(f652o)) {
            bl.m322b("network is unavailable");
            return false;
        } else if (this.f657e.m842f()) {
            return true;
        } else {
            return this.f661i.m157b(z).mo1731a(z);
        }
    }

    private boolean m809e() {
        return this.f655c.m183b() > this.f663k;
    }

    private void m810f() {
        try {
            if (this.f656d.m97i()) {
                aq aqVar = new aq(f652o, this.f657e);
                aqVar.m207a((ao) this);
                if (this.f658f.m874c()) {
                    aqVar.m210b(true);
                }
                aqVar.m206a();
                return;
            }
            av a = m812a(new int[0]);
            if (m802b(a)) {
                aq aqVar2 = new aq(f652o, this.f657e);
                aqVar2.m207a((ao) this);
                if (this.f658f.m874c()) {
                    aqVar2.m210b(true);
                }
                aqVar2.m208a(m805c(a));
                aqVar2.m209a(m811g());
                aqVar2.m206a();
            }
        } catch (Throwable th) {
            if (th instanceof OutOfMemoryError) {
                if (th != null) {
                    th.printStackTrace();
                }
            } else if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private boolean m802b(av avVar) {
        if (avVar != null && avVar.m227a()) {
            return true;
        }
        return false;
    }

    private av m805c(av avVar) {
        int i;
        int i2;
        int i3 = 5000;
        if (avVar.f335b.f277a != null) {
            i = 0;
            for (i2 = 0; i2 < avVar.f335b.f277a.size(); i2++) {
                i += ((C0325h) avVar.f335b.f277a.get(i2)).f266b.size();
            }
        } else {
            i = 0;
        }
        if (avVar.f335b.f278b != null) {
            for (i2 = 0; i2 < avVar.f335b.f278b.size(); i2++) {
                i += ((C0325h) avVar.f335b.f278b.get(i2)).f266b.size();
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f664l > 28800000) {
            int i4 = i - 5000;
            if (i4 > 0) {
                m796a(-5000, i4, avVar);
            }
            this.f665m = 0;
            if (i4 > 0) {
                i = 5000;
            }
            this.f666n = i;
            this.f664l = currentTimeMillis;
        } else {
            int i5 = this.f665m > 5000 ? 0 : (this.f665m + 0) - 5000;
            i2 = this.f666n > 5000 ? i : (this.f666n + i) - 5000;
            if (i5 > 0 || i2 > 0) {
                m796a(i5, i2, avVar);
            }
            this.f665m = i5 > 0 ? 5000 : this.f665m + 0;
            if (i2 <= 0) {
                i3 = this.f666n + i;
            }
            this.f666n = i3;
        }
        return avVar;
    }

    private void m796a(int i, int i2, av avVar) {
        List list;
        int size;
        int size2;
        if (i > 0) {
            list = avVar.f335b.f278b;
            if (list.size() >= i) {
                size = list.size() - i;
                for (size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
            } else {
                size2 = i - list.size();
                list.clear();
            }
        }
        if (i2 > 0) {
            list = avVar.f335b.f277a;
            if (list.size() >= i2) {
                size = list.size() - i2;
                for (size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
                return;
            }
            size2 = i2 - list.size();
            list.clear();
        }
    }

    private boolean m811g() {
        switch (this.f662j.m682c(-1)) {
            case -1:
                return AnalyticsConfig.sEncrypt;
            case 1:
                return true;
            default:
                return false;
        }
    }

    private void m801b(int i) {
        m795a(i);
    }

    public void mo1740a(C0383a c0383a) {
        this.f659g.mo1740a(c0383a);
        this.f658f.mo1740a(c0383a);
        this.f660h.mo1740a(c0383a);
        this.f661i.m155a(c0383a);
    }
}
