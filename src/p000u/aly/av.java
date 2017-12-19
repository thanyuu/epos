package p000u.aly;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: UMEntry */
public class av implements Serializable {
    public static long f332c = 0;
    private static final long f333d = -5254997387189944418L;
    public C0329n f334a = new C0329n();
    public C0328m f335b = new C0328m();

    /* compiled from: UMEntry */
    public static class C0318a {
        public static final int f239a = 0;
        public static final int f240b = 1;
        public static final int f241c = 2;
    }

    /* compiled from: UMEntry */
    public static class C0319b implements Serializable {
        private static final long f242b = 395432415169525323L;
        public long f243a = 0;
    }

    /* compiled from: UMEntry */
    public static class C0320c implements Serializable {
        private static final long f244c = -6648526015472635581L;
        public String f245a = null;
        public String f246b = null;
    }

    /* compiled from: UMEntry */
    public static class C0321d implements Serializable {
        private static final long f247c = -4761083466478982295L;
        public Map<String, List<C0322e>> f248a = new HashMap();
        public Map<String, List<C0323f>> f249b = new HashMap();
    }

    /* compiled from: UMEntry */
    public static class C0322e implements Serializable {
        private static final long f250f = 8614138410597604223L;
        public long f251a = 0;
        public long f252b = 0;
        public int f253c = 0;
        public int f254d = 0;
        public List<String> f255e = new ArrayList();
    }

    /* compiled from: UMEntry */
    public static final class C0323f implements Serializable {
        private static final long f256d = -7569163627707250811L;
        public int f257a = 0;
        public long f258b = 0;
        public String f259c = null;
    }

    /* compiled from: UMEntry */
    public static class C0324g implements Serializable {
        private static final long f260d = -1010993116426830703L;
        public Integer f261a = Integer.valueOf(0);
        public long f262b = 0;
        public boolean f263c = false;
    }

    /* compiled from: UMEntry */
    public static class C0325h implements Serializable {
        private static final long f264c = -7833224895044623144L;
        public String f265a = null;
        public List<C0624j> f266b = new ArrayList();
    }

    /* compiled from: UMEntry */
    public static final class C0326k implements Serializable {
        private static final long f267d = -1397960951960451474L;
        public double f268a = 0.0d;
        public double f269b = 0.0d;
        public long f270c = 0;
    }

    /* compiled from: UMEntry */
    public static final class C0327l implements Serializable {
        private static final long f271e = 2506525905874738341L;
        public String f272a = null;
        public long f273b = 0;
        public long f274c = 0;
        public long f275d = 0;
    }

    /* compiled from: UMEntry */
    public static class C0328m implements Serializable {
        private static final long f276k = 5703014667657688269L;
        public List<C0325h> f277a = new ArrayList();
        public List<C0325h> f278b = new ArrayList();
        public List<C0625o> f279c = new ArrayList();
        public C0319b f280d = new C0319b();
        public C0324g f281e = new C0324g();
        public Map<String, Integer> f282f = new HashMap();
        public C0320c f283g = new C0320c();
        public C0321d f284h = new C0321d();
        public List<C0623i> f285i = new ArrayList();
        public String f286j = null;
    }

    /* compiled from: UMEntry */
    public static class C0329n implements Serializable {
        private static final long f287P = 4568484649280698573L;
        public String f288A = Build.DEVICE;
        public String f289B = null;
        public String f290C = null;
        public long f291D = 8;
        public String f292E = null;
        public String f293F = null;
        public String f294G = null;
        public String f295H = null;
        public String f296I = null;
        public String f297J = null;
        public long f298K = 0;
        public long f299L = 0;
        public long f300M = 0;
        public String f301N = null;
        public String f302O = null;
        public String f303a = null;
        public String f304b = null;
        public String f305c = null;
        public String f306d = null;
        public String f307e = null;
        public String f308f = null;
        public String f309g = null;
        public int f310h = 0;
        public String f311i = AnalyticsConfig.mWrapperType;
        public String f312j = AnalyticsConfig.mWrapperVersion;
        public String f313k = "Android";
        public String f314l = null;
        public int f315m = 0;
        public String f316n = null;
        public String f317o = bj.m258a();
        public String f318p = "Android";
        public String f319q = VERSION.RELEASE;
        public String f320r = null;
        public String f321s = null;
        public String f322t = null;
        public String f323u = Build.MODEL;
        public String f324v = Build.BOARD;
        public String f325w = Build.BRAND;
        public long f326x = Build.TIME;
        public String f327y = Build.MANUFACTURER;
        public String f328z = Build.ID;
    }

    /* compiled from: UMEntry */
    public static final class C0330p implements Serializable {
        private static final long f329c = -7629272972021970177L;
        public long f330a = 0;
        public long f331b = 0;
    }

    /* compiled from: UMEntry */
    public static class C0623i implements Serializable, ai {
        private static final long f692d = -7911804253674023187L;
        public long f693a = 0;
        public long f694b = 0;
        public String f695c = null;

        public void mo1751a(av avVar) {
            if (avVar.f335b.f285i != null) {
                avVar.f335b.f285i.add(this);
            }
        }
    }

    /* compiled from: UMEntry */
    public static class C0624j implements Serializable, ai {
        private static final long f696g = -1062440179015494286L;
        public int f697a = 0;
        public String f698b = null;
        public String f699c = null;
        public long f700d = 0;
        public long f701e = 0;
        public Map<String, Object> f702f = new HashMap();

        public void mo1751a(av avVar) {
            int i;
            C0325h c0325h;
            int i2 = 0;
            if (this.f698b == null) {
                this.f698b = ar.m211a();
            }
            if (avVar.f335b.f277a != null) {
                try {
                    if (this.f697a == 1) {
                        int size = avVar.f335b.f277a.size();
                        if (size > 0) {
                            i = 0;
                            while (i < size) {
                                c0325h = (C0325h) avVar.f335b.f277a.get(i);
                                if (TextUtils.isEmpty(c0325h.f265a) || !c0325h.f265a.equals(this.f698b)) {
                                    i++;
                                } else {
                                    avVar.f335b.f277a.remove(c0325h);
                                    c0325h.f266b.add(this);
                                    avVar.f335b.f277a.add(c0325h);
                                    return;
                                }
                            }
                            c0325h = new C0325h();
                            c0325h.f265a = this.f698b;
                            c0325h.f266b.add(this);
                            if (!avVar.f335b.f277a.contains(c0325h)) {
                                avVar.f335b.f277a.add(c0325h);
                            }
                        } else {
                            c0325h = new C0325h();
                            c0325h.f265a = this.f698b;
                            c0325h.f266b.add(this);
                            avVar.f335b.f277a.add(c0325h);
                        }
                    }
                } catch (Throwable th) {
                    bl.m344e(th);
                }
            }
            if (avVar.f335b.f278b == null) {
                return;
            }
            if (this.f697a == 2) {
                i = avVar.f335b.f278b.size();
                if (i > 0) {
                    while (i2 < i) {
                        c0325h = (C0325h) avVar.f335b.f278b.get(i2);
                        if (TextUtils.isEmpty(c0325h.f265a) || !c0325h.f265a.equals(this.f698b)) {
                            i2++;
                        } else {
                            avVar.f335b.f278b.remove(c0325h);
                            c0325h.f266b.add(this);
                            avVar.f335b.f278b.add(c0325h);
                            return;
                        }
                    }
                    c0325h = new C0325h();
                    c0325h.f265a = this.f698b;
                    c0325h.f266b.add(this);
                    avVar.f335b.f278b.add(c0325h);
                    return;
                }
                c0325h = new C0325h();
                c0325h.f265a = this.f698b;
                c0325h.f266b.add(this);
                avVar.f335b.f278b.add(c0325h);
            }
        }
    }

    /* compiled from: UMEntry */
    public static class C0625o implements Serializable, ai {
        public static Map<String, C0327l> f703g = new HashMap();
        private static final long f704k = 8683938900576888953L;
        public int f705a = 0;
        public String f706b = null;
        public long f707c = 0;
        public long f708d = 0;
        public long f709e = 0;
        public boolean f710f = false;
        public List<C0327l> f711h = new ArrayList();
        public C0330p f712i = new C0330p();
        public C0326k f713j = new C0326k();

        public void mo1751a(av avVar) {
            if (avVar.f335b.f279c != null) {
                avVar.f335b.f279c.add(this);
            }
        }
    }

    public boolean m227a() {
        if (this.f334a.f322t == null || this.f334a.f321s == null || this.f334a.f320r == null || this.f334a.f303a == null || this.f334a.f304b == null || this.f334a.f308f == null || this.f334a.f307e == null || this.f334a.f306d == null) {
            return false;
        }
        return true;
    }

    public void m228b() {
        this.f334a = new C0329n();
        this.f335b = new C0328m();
        f332c = 0;
    }
}
