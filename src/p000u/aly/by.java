package p000u.aly;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import p000u.aly.ci.C0650a;

/* compiled from: TSerializer */
public class by {
    private final ByteArrayOutputStream f366a;
    private final da f367b;
    private co f368c;

    public by() {
        this(new C0650a());
    }

    public by(cq cqVar) {
        this.f366a = new ByteArrayOutputStream();
        this.f367b = new da(this.f366a);
        this.f368c = cqVar.mo1761a(this.f367b);
    }

    public byte[] m415a(bp bpVar) throws bv {
        this.f366a.reset();
        bpVar.mo1759b(this.f368c);
        return this.f366a.toByteArray();
    }

    public String m414a(bp bpVar, String str) throws bv {
        try {
            return new String(m415a(bpVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String m416b(bp bpVar) throws bv {
        return new String(m415a(bpVar));
    }
}
