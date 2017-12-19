package p000u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.C0291a;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: IdTracker */
public class C0382v {
    public static C0382v f536a;
    private final String f537b = "umeng_it.cache";
    private File f538c;
    private bc f539d = null;
    private long f540e;
    private long f541f;
    private Set<C0379r> f542g = new HashSet();
    private C0381a f543h = null;

    /* compiled from: IdTracker */
    public static class C0381a {
        private Context f534a;
        private Set<String> f535b = new HashSet();

        public C0381a(Context context) {
            this.f534a = context;
        }

        public boolean m655a(String str) {
            return !this.f535b.contains(str);
        }

        public void m657b(String str) {
            this.f535b.add(str);
        }

        public void m658c(String str) {
            this.f535b.remove(str);
        }

        public void m654a() {
            if (!this.f535b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : this.f535b) {
                    stringBuilder.append(append);
                    stringBuilder.append(',');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                ap.m197a(this.f534a).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        public void m656b() {
            Object string = ap.m197a(this.f534a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    for (CharSequence charSequence : split) {
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.f535b.add(charSequence);
                        }
                    }
                }
            }
        }
    }

    C0382v(Context context) {
        this.f538c = new File(context.getFilesDir(), "umeng_it.cache");
        this.f541f = C0291a.f41j;
        this.f543h = new C0381a(context);
        this.f543h.m656b();
    }

    public static synchronized C0382v m659a(Context context) {
        C0382v c0382v;
        synchronized (C0382v.class) {
            if (f536a == null) {
                f536a = new C0382v(context);
                f536a.m665a(new C0656w(context));
                f536a.m665a(new C0654s(context));
                f536a.m665a(new ab(context));
                f536a.m665a(new aa(context));
                f536a.m665a(new C0655u(context));
                f536a.m665a(new C0657y(context));
                f536a.m665a(new C0658z());
                f536a.m665a(new ac(context));
                f536a.m669e();
            }
            c0382v = f536a;
        }
        return c0382v;
    }

    public boolean m665a(C0379r c0379r) {
        if (this.f543h.m655a(c0379r.m636b())) {
            return this.f542g.add(c0379r);
        }
        return false;
    }

    public void m664a(long j) {
        this.f541f = j;
    }

    public void m663a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f540e >= this.f541f) {
            Object obj = null;
            for (C0379r c0379r : this.f542g) {
                if (c0379r.m637c()) {
                    if (c0379r.m635a()) {
                        obj = 1;
                        if (!c0379r.m637c()) {
                            this.f543h.m657b(c0379r.m636b());
                        }
                    }
                    obj = obj;
                }
            }
            if (obj != null) {
                m661g();
                this.f543h.m654a();
                m670f();
            }
            this.f540e = currentTimeMillis;
        }
    }

    public bc m666b() {
        return this.f539d;
    }

    private void m661g() {
        bc bcVar = new bc();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (C0379r c0379r : this.f542g) {
            if (c0379r.m637c()) {
                if (c0379r.m638d() != null) {
                    hashMap.put(c0379r.m636b(), c0379r.m638d());
                }
                if (!(c0379r.m639e() == null || c0379r.m639e().isEmpty())) {
                    arrayList.addAll(c0379r.m639e());
                }
            }
        }
        bcVar.m982a(arrayList);
        bcVar.m983a(hashMap);
        synchronized (this) {
            this.f539d = bcVar;
        }
    }

    public String m667c() {
        return null;
    }

    public void m668d() {
        boolean z = false;
        for (C0379r c0379r : this.f542g) {
            if (c0379r.m637c()) {
                boolean z2;
                if (c0379r.m639e() == null || c0379r.m639e().isEmpty()) {
                    z2 = z;
                } else {
                    c0379r.m632a(null);
                    z2 = true;
                }
                z = z2;
            }
        }
        if (z) {
            this.f539d.m991b(false);
            m670f();
        }
    }

    public void m669e() {
        bc h = m662h();
        if (h != null) {
            List<C0379r> arrayList = new ArrayList(this.f542g.size());
            synchronized (this) {
                this.f539d = h;
                for (C0379r c0379r : this.f542g) {
                    c0379r.m634a(this.f539d);
                    if (!c0379r.m637c()) {
                        arrayList.add(c0379r);
                    }
                }
                for (C0379r c0379r2 : arrayList) {
                    this.f542g.remove(c0379r2);
                }
            }
            m661g();
        }
    }

    public void m670f() {
        if (this.f539d != null) {
            m660a(this.f539d);
        }
    }

    private bc m662h() {
        Exception e;
        Throwable th;
        if (!this.f538c.exists()) {
            return null;
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.f538c);
            try {
                byte[] b = bk.m311b(fileInputStream);
                bp bcVar = new bc();
                new bs().m400a(bcVar, b);
                bk.m313c(fileInputStream);
                return bcVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    bk.m313c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bk.m313c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            bk.m313c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            bk.m313c(fileInputStream);
            throw th;
        }
    }

    private void m660a(bc bcVar) {
        if (bcVar != null) {
            try {
                byte[] a;
                synchronized (this) {
                    a = new by().m415a(bcVar);
                }
                if (a != null) {
                    bk.m307a(this.f538c, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
