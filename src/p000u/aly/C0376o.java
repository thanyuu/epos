package p000u.aly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;
import p000u.aly.av.C0322e;
import p000u.aly.av.C0323f;

/* compiled from: UMCCStorageManager */
public class C0376o {
    private static Context f508a;

    /* compiled from: UMCCStorageManager */
    private static final class C0375a {
        private static final C0376o f507a = new C0376o();

        private C0375a() {
        }
    }

    private C0376o() {
        if (f508a == null) {
        }
    }

    public static C0376o m603a(Context context) {
        f508a = context;
        return C0375a.f507a;
    }

    public void m605a(C0652f c0652f) {
        try {
            SQLiteDatabase a = C0331b.m229a(f508a).m231a();
            String a2 = C0316a.m137a(a);
            String a3 = C0378q.m624a(System.currentTimeMillis());
            if (a2.equals("0")) {
                c0652f.mo1814a("faild", false);
                return;
            }
            if (a2.equals(a3)) {
                C0316a.m149b(a, c0652f);
            } else {
                C0316a.m145a(a, c0652f);
            }
            C0331b.m229a(f508a).m233c();
        } catch (Exception e) {
            c0652f.mo1814a(Boolean.valueOf(false), false);
            bl.m340e("load agg data error");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public void m608a(C0652f c0652f, Map<List<String>, C0363i> map) {
        try {
            C0316a.m144a(C0331b.m229a(f508a).m232b(), map.values());
            c0652f.mo1814a("success", false);
        } catch (Exception e) {
            bl.m340e("save agg data error");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public Map<String, List<C0322e>> m604a() {
        Map<String, List<C0322e>> b;
        try {
            b = C0316a.m147b(C0331b.m229a(f508a).m231a());
        } catch (Exception e) {
            bl.m340e("upload agg date error");
            return null;
        } finally {
            C0331b.m229a(f508a).m233c();
        }
        return b;
    }

    public Map<String, List<C0323f>> m611b(C0652f c0652f) {
        Map<String, List<C0323f>> a;
        try {
            a = C0316a.m138a(c0652f, C0331b.m229a(f508a).m231a());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            C0331b.m229a(f508a).m233c();
        }
        return a;
    }

    public void m609a(C0652f c0652f, boolean z) {
        try {
            C0316a.m141a(C0331b.m229a(f508a).m232b(), z, c0652f);
        } catch (Exception e) {
            bl.m340e("notifyUploadSuccess error");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public void m606a(C0652f c0652f, String str, long j, long j2) {
        try {
            C0316a.m139a(C0331b.m229a(f508a).m232b(), str, j, j2);
            c0652f.mo1814a("success", false);
        } catch (Exception e) {
            bl.m340e("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public void m607a(C0652f c0652f, List<String> list) {
        try {
            C0316a.m142a(c0652f, C0331b.m229a(f508a).m232b(), (List) list);
        } catch (Exception e) {
            bl.m340e("saveToLimitCKTable exception");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public void m612b(C0652f c0652f, Map<String, C0365k> map) {
        try {
            C0316a.m140a(C0331b.m229a(f508a).m232b(), (Map) map, c0652f);
        } catch (Exception e) {
            bl.m340e("arrgetated system buffer exception");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }

    public List<String> m610b() {
        List<String> c;
        try {
            c = C0316a.m151c(C0331b.m229a(f508a).m231a());
        } catch (Exception e) {
            bl.m340e("loadCKToMemory exception");
            return null;
        } finally {
            C0331b.m229a(f508a).m233c();
        }
        return c;
    }

    public void m613c(C0652f c0652f, Map<List<String>, C0363i> map) {
        try {
            C0316a.m146a(c0652f, C0331b.m229a(f508a).m232b(), map.values());
        } catch (Exception e) {
            bl.m340e("cacheToData error");
        } finally {
            C0331b.m229a(f508a).m233c();
        }
    }
}
