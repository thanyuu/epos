package p000u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCVerbatimObject */
public class C0366l implements Serializable {
    private static final long f483a = 1;
    private List<String> f484b = new ArrayList();
    private String f485c;
    private long f486d;
    private long f487e;
    private String f488f;

    public C0366l(List<String> list, long j, String str, long j2) {
        this.f484b = list;
        this.f486d = j;
        this.f485c = str;
        this.f487e = j2;
        m546f();
    }

    private void m546f() {
        this.f488f = C0378q.m624a(this.f487e);
    }

    public List<String> m547a() {
        return this.f484b;
    }

    public String m548b() {
        return this.f485c;
    }

    public long m549c() {
        return this.f486d;
    }

    public long m550d() {
        return this.f487e;
    }

    public String m551e() {
        return this.f488f;
    }
}
