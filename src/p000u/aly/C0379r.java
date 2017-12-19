package p000u.aly;

import android.support.v4.os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: AbstractIdTracker */
public abstract class C0379r {
    private final int f515a = 10;
    private final int f516b = 20;
    private final String f517c;
    private List<ba> f518d;
    private bb f519e;

    public abstract String mo1737f();

    public C0379r(String str) {
        this.f517c = str;
    }

    public boolean m635a() {
        return m630g();
    }

    public String m636b() {
        return this.f517c;
    }

    public boolean m637c() {
        if (this.f519e == null || this.f519e.m959i() <= 20) {
            return true;
        }
        return false;
    }

    private boolean m630g() {
        bb bbVar = this.f519e;
        String c = bbVar == null ? null : bbVar.m951c();
        int i = bbVar == null ? 0 : bbVar.m959i();
        String a = m631a(mo1737f());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (bbVar == null) {
            bbVar = new bb();
        }
        bbVar.m944a(a);
        bbVar.m943a(System.currentTimeMillis());
        bbVar.m942a(i + 1);
        ba baVar = new ba();
        baVar.m901a(this.f517c);
        baVar.m910c(a);
        baVar.m904b(c);
        baVar.m900a(bbVar.m956f());
        if (this.f518d == null) {
            this.f518d = new ArrayList(2);
        }
        this.f518d.add(baVar);
        if (this.f518d.size() > 10) {
            this.f518d.remove(0);
        }
        this.f519e = bbVar;
        return true;
    }

    public bb m638d() {
        return this.f519e;
    }

    public void m633a(bb bbVar) {
        this.f519e = bbVar;
    }

    public List<ba> m639e() {
        return this.f518d;
    }

    public void m632a(List<ba> list) {
        this.f518d = list;
    }

    public String m631a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || "0".equals(trim) || EnvironmentCompat.MEDIA_UNKNOWN.equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    public void m634a(bc bcVar) {
        this.f519e = (bb) bcVar.m994d().get(this.f517c);
        List<ba> i = bcVar.m999i();
        if (i != null && i.size() > 0) {
            if (this.f518d == null) {
                this.f518d = new ArrayList();
            }
            for (ba baVar : i) {
                if (this.f517c.equals(baVar.f765a)) {
                    this.f518d.add(baVar);
                }
            }
        }
    }
}
