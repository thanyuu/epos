package p000u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdSnapshot */
public class bb implements Serializable, Cloneable, bp<bb, C0631e> {
    public static final Map<C0631e, cb> f778d;
    private static final ct f779e = new ct("IdSnapshot");
    private static final cj f780f = new cj("identity", (byte) 11, (short) 1);
    private static final cj f781g = new cj("ts", (byte) 10, (short) 2);
    private static final cj f782h = new cj("version", (byte) 8, (short) 3);
    private static final Map<Class<? extends cw>, cx> f783i = new HashMap();
    private static final int f784j = 0;
    private static final int f785k = 1;
    public String f786a;
    public long f787b;
    public int f788c;
    private byte f789l;

    /* compiled from: IdSnapshot */
    private static class C0629b implements cx {
        private C0629b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m926a();
        }

        public C0686a m926a() {
            return new C0686a();
        }
    }

    /* compiled from: IdSnapshot */
    private static class C0630d implements cx {
        private C0630d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m928a();
        }

        public C0687c m928a() {
            return new C0687c();
        }
    }

    /* compiled from: IdSnapshot */
    public enum C0631e implements bw {
        IDENTITY((short) 1, "identity"),
        TS((short) 2, "ts"),
        VERSION((short) 3, "version");
        
        private static final Map<String, C0631e> f774d = null;
        private final short f776e;
        private final String f777f;

        static {
            f774d = new HashMap();
            Iterator it = EnumSet.allOf(C0631e.class).iterator();
            while (it.hasNext()) {
                C0631e c0631e = (C0631e) it.next();
                f774d.put(c0631e.mo1755b(), c0631e);
            }
        }

        public static C0631e m930a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        public static C0631e m932b(int i) {
            C0631e a = C0631e.m930a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0631e m931a(String str) {
            return (C0631e) f774d.get(str);
        }

        private C0631e(short s, String str) {
            this.f776e = s;
            this.f777f = str;
        }

        public short mo1754a() {
            return this.f776e;
        }

        public String mo1755b() {
            return this.f777f;
        }
    }

    /* compiled from: IdSnapshot */
    private static class C0686a extends cy<bb> {
        private C0686a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1390b(coVar, (bb) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1388a(coVar, (bb) bpVar);
        }

        public void m1388a(co coVar, bb bbVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (!bbVar.m958h()) {
                        throw new cp("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (bbVar.m961k()) {
                        bbVar.m962l();
                        return;
                    } else {
                        throw new cp("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bbVar.f786a = coVar.mo1802z();
                        bbVar.m946a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 10) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bbVar.f787b = coVar.mo1800x();
                        bbVar.m950b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bbVar.f788c = coVar.mo1799w();
                        bbVar.m953c(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1390b(co coVar, bb bbVar) throws bv {
            bbVar.m962l();
            coVar.mo1775a(bb.f779e);
            if (bbVar.f786a != null) {
                coVar.mo1770a(bb.f780f);
                coVar.mo1768a(bbVar.f786a);
                coVar.mo1779c();
            }
            coVar.mo1770a(bb.f781g);
            coVar.mo1767a(bbVar.f787b);
            coVar.mo1779c();
            coVar.mo1770a(bb.f782h);
            coVar.mo1766a(bbVar.f788c);
            coVar.mo1779c();
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: IdSnapshot */
    private static class C0687c extends cz<bb> {
        private C0687c() {
        }

        public void m1392a(co coVar, bb bbVar) throws bv {
            cu cuVar = (cu) coVar;
            cuVar.mo1768a(bbVar.f786a);
            cuVar.mo1767a(bbVar.f787b);
            cuVar.mo1766a(bbVar.f788c);
        }

        public void m1394b(co coVar, bb bbVar) throws bv {
            cu cuVar = (cu) coVar;
            bbVar.f786a = cuVar.mo1802z();
            bbVar.m946a(true);
            bbVar.f787b = cuVar.mo1800x();
            bbVar.m950b(true);
            bbVar.f788c = cuVar.mo1799w();
            bbVar.m953c(true);
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m952c(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m941a();
    }

    static {
        f783i.put(cy.class, new C0629b());
        f783i.put(cz.class, new C0630d());
        Map enumMap = new EnumMap(C0631e.class);
        enumMap.put(C0631e.IDENTITY, new cb("identity", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0631e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        enumMap.put(C0631e.VERSION, new cb("version", (byte) 1, new cc((byte) 8)));
        f778d = Collections.unmodifiableMap(enumMap);
        cb.m426a(bb.class, f778d);
    }

    public bb() {
        this.f789l = (byte) 0;
    }

    public bb(String str, long j, int i) {
        this();
        this.f786a = str;
        this.f787b = j;
        m950b(true);
        this.f788c = i;
        m953c(true);
    }

    public bb(bb bbVar) {
        this.f789l = (byte) 0;
        this.f789l = bbVar.f789l;
        if (bbVar.m955e()) {
            this.f786a = bbVar.f786a;
        }
        this.f787b = bbVar.f787b;
        this.f788c = bbVar.f788c;
    }

    public bb m941a() {
        return new bb(this);
    }

    public void mo1758b() {
        this.f786a = null;
        m950b(false);
        this.f787b = 0;
        m953c(false);
        this.f788c = 0;
    }

    public String m951c() {
        return this.f786a;
    }

    public bb m944a(String str) {
        this.f786a = str;
        return this;
    }

    public void m954d() {
        this.f786a = null;
    }

    public boolean m955e() {
        return this.f786a != null;
    }

    public void m946a(boolean z) {
        if (!z) {
            this.f786a = null;
        }
    }

    public long m956f() {
        return this.f787b;
    }

    public bb m943a(long j) {
        this.f787b = j;
        m950b(true);
        return this;
    }

    public void m957g() {
        this.f789l = bm.m358b(this.f789l, 0);
    }

    public boolean m958h() {
        return bm.m354a(this.f789l, 0);
    }

    public void m950b(boolean z) {
        this.f789l = bm.m346a(this.f789l, 0, z);
    }

    public int m959i() {
        return this.f788c;
    }

    public bb m942a(int i) {
        this.f788c = i;
        m953c(true);
        return this;
    }

    public void m960j() {
        this.f789l = bm.m358b(this.f789l, 1);
    }

    public boolean m961k() {
        return bm.m354a(this.f789l, 1);
    }

    public void m953c(boolean z) {
        this.f789l = bm.m346a(this.f789l, 1, z);
    }

    public C0631e m952c(int i) {
        return C0631e.m930a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f783i.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f783i.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.f786a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f786a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.f787b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.f788c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m962l() throws bv {
        if (this.f786a == null) {
            throw new cp("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    private void m936a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m935a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f789l = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
