package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.umeng.analytics.C0291a;
import com.umeng.analytics.C0295f;
import com.umeng.analytics.C0296g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import p000u.aly.C0370m;
import p000u.aly.C0377p;
import p000u.aly.av.C0322e;
import p000u.aly.av.C0323f;

/* compiled from: UMCCAggregatedManager */
public class C0370m {
    private static final int f492i = 48;
    private static final int f493j = 49;
    private static Context f494k;
    private C0362h f495a;
    private C0376o f496b;
    private C0377p f497c;
    private boolean f498d;
    private boolean f499e;
    private long f500f;
    private final String f501g;
    private final String f502h;
    private List<String> f503l;
    private C0368a f504m;
    private final Thread f505n;

    /* compiled from: UMCCAggregatedManager */
    class C03671 implements Runnable {
        final /* synthetic */ C0370m f489a;

        C03671(C0370m c0370m) {
            this.f489a = c0370m;
        }

        public void run() {
            Looper.prepare();
            if (this.f489a.f504m == null) {
                this.f489a.f504m = new C0368a(this.f489a);
            }
            this.f489a.m566f();
        }
    }

    /* compiled from: UMCCAggregatedManager */
    private static class C0368a extends Handler {
        private final WeakReference<C0370m> f490a;

        public C0368a(C0370m c0370m) {
            this.f490a = new WeakReference(c0370m);
        }

        public void handleMessage(Message message) {
            if (this.f490a != null) {
                switch (message.what) {
                    case 48:
                        sendEmptyMessageDelayed(48, C0378q.m626b(System.currentTimeMillis()));
                        C0370m.m556a(C0370m.f494k).m579n();
                        return;
                    case 49:
                        sendEmptyMessageDelayed(49, C0378q.m627c(System.currentTimeMillis()));
                        C0370m.m556a(C0370m.f494k).m578m();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    private static class C0369b {
        private static final C0370m f491a = new C0370m();

        private C0369b() {
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07012 extends C0652f {
        final /* synthetic */ C0370m f1007a;

        C07012(C0370m c0370m) {
            this.f1007a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f1007a.f495a.m520d();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07023 extends C0652f {
        final /* synthetic */ C0370m f1008a;

        C07023(C0370m c0370m) {
            this.f1008a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f1008a.f497c.m622b();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07034 extends C0652f {
        final /* synthetic */ C0370m f1009a;

        C07034(C0370m c0370m) {
            this.f1009a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07045 extends C0652f {
        final /* synthetic */ C0370m f1010a;

        C07045(C0370m c0370m) {
            this.f1010a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f1010a.f497c.m622b();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07078 extends C0652f {
        final /* synthetic */ C0370m f1014a;

        C07078(C0370m c0370m) {
            this.f1014a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
            this.f1014a.f495a = (C0362h) obj;
        }
    }

    /* compiled from: UMCCAggregatedManager */
    class C07089 extends C0652f {
        final /* synthetic */ C0370m f1015a;

        C07089(C0370m c0370m) {
            this.f1015a = c0370m;
        }

        public void mo1814a(Object obj, boolean z) {
            if (obj instanceof C0362h) {
                this.f1015a.f495a = (C0362h) obj;
            } else if (obj instanceof Boolean) {
                this.f1015a.m577l();
            }
        }
    }

    public boolean m585a() {
        return this.f498d;
    }

    private C0370m() {
        this.f495a = null;
        this.f496b = null;
        this.f497c = null;
        this.f498d = false;
        this.f499e = false;
        this.f500f = 0;
        this.f501g = "main_fest_mode";
        this.f502h = "main_fest_timestamp";
        this.f503l = new ArrayList();
        this.f504m = null;
        this.f505n = new Thread(new C03671(this));
        if (f494k != null) {
            if (this.f495a == null) {
                this.f495a = new C0362h();
            }
            if (this.f496b == null) {
                this.f496b = C0376o.m603a(f494k);
            }
            if (this.f497c == null) {
                this.f497c = new C0377p();
            }
        }
        this.f505n.start();
    }

    private void m566f() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f504m.sendEmptyMessageDelayed(48, C0378q.m626b(currentTimeMillis));
        this.f504m.sendEmptyMessageDelayed(49, C0378q.m627c(currentTimeMillis));
    }

    public static final C0370m m556a(Context context) {
        f494k = context;
        return C0369b.f491a;
    }

    public void m583a(final C0652f c0652f) {
        if (!this.f498d) {
            C0295f.m49b(new C0296g(this) {
                final /* synthetic */ C0370m f986b;

                /* compiled from: UMCCAggregatedManager */
                class C07051 extends C0652f {
                    final /* synthetic */ C06536 f1011a;

                    C07051(C06536 c06536) {
                        this.f1011a = c06536;
                    }

                    public void mo1814a(Object obj, boolean z) {
                        if (obj instanceof Map) {
                            this.f1011a.f986b.f495a.m511a((Map) obj);
                        } else if (!(obj instanceof String) && (obj instanceof Boolean)) {
                        }
                        this.f1011a.f986b.f498d = true;
                    }
                }

                public void mo1733a() {
                    try {
                        this.f986b.f496b.m605a(new C07051(this));
                        this.f986b.m574j();
                        this.f986b.m580o();
                        c0652f.mo1814a("success", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void m584a(final C0652f c0652f, Map<List<String>, C0366l> map) {
        C0366l c0366l = (C0366l) map.values().toArray()[0];
        List a = c0366l.m547a();
        if (this.f503l.size() > 0 && this.f503l.contains(C0359d.m487a(a))) {
            this.f495a.m514a(new C0652f(this) {
                final /* synthetic */ C0370m f1013b;

                public void mo1814a(Object obj, boolean z) {
                    if (obj instanceof C0362h) {
                        this.f1013b.f495a = (C0362h) obj;
                    }
                    c0652f.mo1814a("success", false);
                }
            }, c0366l);
        } else if (this.f499e) {
            m558a(c0366l, a);
        } else if (m568g()) {
            String a2 = C0359d.m487a(a);
            if (!this.f503l.contains(a2)) {
                this.f503l.add(a2);
            }
            this.f495a.m513a(new C07078(this), a, c0366l);
        } else {
            m558a(c0366l, a);
            m569h();
        }
    }

    private void m558a(C0366l c0366l, List<String> list) {
        this.f495a.m515a(new C07089(this), c0366l, list, this.f503l);
    }

    private boolean m568g() {
        if (this.f503l.size() < C0373n.m593a().m601d()) {
            return true;
        }
        return false;
    }

    private void m569h() {
        SharedPreferences a = ap.m197a(f494k);
        if (!a.getBoolean("main_fest_mode", false)) {
            this.f499e = true;
            Editor edit = a.edit();
            edit.putBoolean("main_fest_mode", true);
            edit.putLong("main_fest_timestamp", System.currentTimeMillis());
            edit.commit();
        }
    }

    private void m571i() {
        Editor edit = ap.m197a(f494k).edit();
        edit.putBoolean("main_fest_mode", false);
        edit.putLong("main_fest_timestamp", 0);
        edit.commit();
        this.f499e = false;
    }

    private void m574j() {
        SharedPreferences a = ap.m197a(f494k);
        this.f499e = a.getBoolean("main_fest_mode", false);
        this.f500f = a.getLong("main_fest_timestamp", 0);
    }

    public void m582a(av avVar) {
        if (avVar.f335b.f284h != null) {
            avVar.f335b.f284h.f248a = m586b(new C0652f());
            avVar.f335b.f284h.f249b = m588c(new C0652f());
        }
    }

    public Map<String, List<C0322e>> m586b(C0652f c0652f) {
        Map a = this.f496b.m604a();
        Map<String, List<C0322e>> hashMap = new HashMap();
        if (a == null || a.size() <= 0) {
            return null;
        }
        for (String str : this.f503l) {
            if (a.containsKey(str)) {
                hashMap.put(str, a.get(str));
            }
        }
        return hashMap;
    }

    public Map<String, List<C0323f>> m588c(C0652f c0652f) {
        if (this.f497c.m617a().size() > 0) {
            this.f496b.m612b(new C0652f(this) {
                final /* synthetic */ C0370m f1003a;

                {
                    this.f1003a = r1;
                }

                public void mo1814a(Object obj, boolean z) {
                    if (obj instanceof String) {
                        this.f1003a.f497c.m622b();
                    }
                }
            }, this.f497c.m617a());
        }
        return this.f496b.m611b(new C0652f());
    }

    public void m591d(C0652f c0652f) {
        boolean z = false;
        if (this.f499e) {
            if (this.f500f == 0) {
                m574j();
            }
            z = C0378q.m625a(System.currentTimeMillis(), this.f500f);
        }
        if (!z) {
            m571i();
            this.f503l.clear();
        }
        this.f497c.m622b();
        this.f496b.m609a(new C0652f(this) {
            final /* synthetic */ C0370m f1004a;

            {
                this.f1004a = r1;
            }

            public void mo1814a(Object obj, boolean z) {
                if (obj.equals("success")) {
                    this.f1004a.m575k();
                }
            }
        }, z);
    }

    private void m575k() {
        for (Entry key : this.f495a.m509a().entrySet()) {
            List list = (List) key.getKey();
            if (!this.f503l.contains(list)) {
                this.f503l.add(C0359d.m487a(list));
            }
        }
        if (this.f503l.size() > 0) {
            this.f496b.m607a(new C0652f(), this.f503l);
        }
    }

    private void m577l() {
        this.f497c.m619a(new C0652f(this) {
            final /* synthetic */ C0370m f1005a;

            {
                this.f1005a = r1;
            }

            public void mo1814a(Object obj, boolean z) {
                this.f1005a.f497c = (C0377p) obj;
            }
        }, C0291a.f49r);
    }

    public void m581a(long j, long j2, String str) {
        this.f496b.m606a(new C0652f(this) {
            final /* synthetic */ C0370m f1006a;

            {
                this.f1006a = r1;
            }

            public void mo1814a(Object obj, boolean z) {
                if (!obj.equals("success")) {
                }
            }
        }, str, j, j2);
    }

    private void m578m() {
        try {
            if (this.f495a.m509a().size() > 0) {
                this.f496b.m613c(new C07012(this), this.f495a.m509a());
            }
            if (this.f497c.m617a().size() > 0) {
                this.f496b.m612b(new C07023(this), this.f497c.m617a());
            }
            if (this.f503l.size() > 0) {
                this.f496b.m607a(new C0652f(), this.f503l);
            }
        } catch (Throwable th) {
            bl.m322b("converyMemoryToDataTable happen error: " + th.toString());
        }
    }

    private void m579n() {
        try {
            if (this.f495a.m509a().size() > 0) {
                this.f496b.m608a(new C07034(this), this.f495a.m509a());
            }
            if (this.f497c.m617a().size() > 0) {
                this.f496b.m612b(new C07045(this), this.f497c.m617a());
            }
            if (this.f503l.size() > 0) {
                this.f496b.m607a(new C0652f(), this.f503l);
            }
        } catch (Throwable th) {
            bl.m322b("convertMemoryToCacheTable happen error: " + th.toString());
        }
    }

    private void m580o() {
        List b = this.f496b.m610b();
        if (b != null) {
            this.f503l = b;
        }
    }

    public void m587b() {
        m579n();
    }

    public void m589c() {
        m579n();
    }

    public void m590d() {
        m579n();
    }
}
