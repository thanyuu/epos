package p000u.aly;

import java.io.Serializable;

/* compiled from: UMCCSystemBuffer */
public class C0365k implements Serializable {
    private static final long f478a = 1;
    private String f479b;
    private long f480c;
    private long f481d;
    private String f482e;

    private C0365k() {
        this.f479b = null;
        this.f480c = 0;
        this.f481d = 0;
        this.f482e = null;
    }

    public C0365k(String str, long j, long j2) {
        this(str, j, j2, null);
    }

    public C0365k(String str, long j, long j2, String str2) {
        this.f479b = null;
        this.f480c = 0;
        this.f481d = 0;
        this.f482e = null;
        this.f479b = str;
        this.f480c = j;
        this.f481d = j2;
        this.f482e = str2;
    }

    public C0365k m538a() {
        this.f481d++;
        return this;
    }

    public String m541b() {
        return this.f482e;
    }

    public void m540a(String str) {
        this.f482e = str;
    }

    public String m543c() {
        return this.f479b;
    }

    public void m542b(String str) {
        this.f479b = str;
    }

    public long m544d() {
        return this.f480c;
    }

    public long m545e() {
        return this.f481d;
    }

    public C0365k m539a(C0365k c0365k) {
        this.f481d = c0365k.m545e() + this.f481d;
        this.f480c = c0365k.m544d();
        return this;
    }
}
