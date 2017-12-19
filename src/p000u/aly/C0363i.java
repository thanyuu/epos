package p000u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCAggregatedObject */
public class C0363i implements Serializable {
    private static final long f462a = 1;
    private List<String> f463b = new ArrayList();
    private List<String> f464c = new ArrayList();
    private long f465d = 0;
    private long f466e = 0;
    private long f467f = 0;
    private String f468g = null;

    public C0363i(List<String> list, long j, long j2, long j3, List<String> list2, String str) {
        this.f463b = list;
        this.f464c = list2;
        this.f465d = j;
        this.f466e = j2;
        this.f467f = j3;
        this.f468g = str;
    }

    public void m523a(String str) {
        try {
            if (this.f464c.size() < C0373n.m593a().m597b()) {
                this.f464c.add(str);
            } else {
                this.f464c.remove(this.f464c.get(0));
                this.f464c.add(str);
            }
            if (this.f464c.size() > C0373n.m593a().m597b()) {
                for (int i = 0; i < this.f464c.size() - C0373n.m593a().m597b(); i++) {
                    this.f464c.remove(this.f464c.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m525a(C0652f c0652f, C0366l c0366l) {
        m523a(c0366l.m548b());
        this.f467f++;
        this.f466e += c0366l.m549c();
        this.f465d += c0366l.m550d();
        c0652f.mo1814a(this, false);
    }

    public void m526a(C0366l c0366l) {
        this.f467f = 1;
        this.f463b = c0366l.m547a();
        m523a(c0366l.m548b());
        this.f466e = c0366l.m549c();
        this.f465d = System.currentTimeMillis();
        this.f468g = C0378q.m624a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[key: ").append(this.f463b).append("] [label: ").append(this.f464c).append("][ totalTimeStamp").append(this.f468g).append("][ value").append(this.f466e).append("][ count").append(this.f467f).append("][ timeWindowNum").append(this.f468g).append("]");
        return stringBuffer.toString();
    }

    public String m521a() {
        return C0359d.m487a(this.f463b);
    }

    public List<String> m527b() {
        return this.f463b;
    }

    public String m531c() {
        return C0359d.m487a(this.f464c);
    }

    public List<String> m533d() {
        return this.f464c;
    }

    public long m534e() {
        return this.f465d;
    }

    public long m535f() {
        return this.f466e;
    }

    public long m536g() {
        return this.f467f;
    }

    public String m537h() {
        return this.f468g;
    }

    public void m524a(List<String> list) {
        this.f463b = list;
    }

    public void m530b(List<String> list) {
        this.f464c = list;
    }

    public void m522a(long j) {
        this.f465d = j;
    }

    public void m528b(long j) {
        this.f466e = j;
    }

    public void m532c(long j) {
        this.f467f = j;
    }

    public void m529b(String str) {
        this.f468g = str;
    }
}
