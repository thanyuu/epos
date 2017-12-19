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
import java.util.Map.Entry;

/* compiled from: Imprint */
public class bd implements Serializable, Cloneable, bp<bd, C0637e> {
    public static final Map<C0637e, cb> f814d;
    private static final ct f815e = new ct("Imprint");
    private static final cj f816f = new cj("property", cv.f422k, (short) 1);
    private static final cj f817g = new cj("version", (byte) 8, (short) 2);
    private static final cj f818h = new cj("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends cw>, cx> f819i = new HashMap();
    private static final int f820j = 0;
    public Map<String, be> f821a;
    public int f822b;
    public String f823c;
    private byte f824k;

    /* compiled from: Imprint */
    private static class C0635b implements cx {
        private C0635b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1007a();
        }

        public C0690a m1007a() {
            return new C0690a();
        }
    }

    /* compiled from: Imprint */
    private static class C0636d implements cx {
        private C0636d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1009a();
        }

        public C0691c m1009a() {
            return new C0691c();
        }
    }

    /* compiled from: Imprint */
    public enum C0637e implements bw {
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        
        private static final Map<String, C0637e> f810d = null;
        private final short f812e;
        private final String f813f;

        static {
            f810d = new HashMap();
            Iterator it = EnumSet.allOf(C0637e.class).iterator();
            while (it.hasNext()) {
                C0637e c0637e = (C0637e) it.next();
                f810d.put(c0637e.mo1755b(), c0637e);
            }
        }

        public static C0637e m1011a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static C0637e m1013b(int i) {
            C0637e a = C0637e.m1011a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0637e m1012a(String str) {
            return (C0637e) f810d.get(str);
        }

        private C0637e(short s, String str) {
            this.f812e = s;
            this.f813f = str;
        }

        public short mo1754a() {
            return this.f812e;
        }

        public String mo1755b() {
            return this.f813f;
        }
    }

    /* compiled from: Imprint */
    private static class C0690a extends cy<bd> {
        private C0690a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1406b(coVar, (bd) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1404a(coVar, (bd) bpVar);
        }

        public void m1404a(co coVar, bd bdVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (bdVar.m1041i()) {
                        bdVar.m1045m();
                        return;
                    }
                    throw new cp("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != cv.f422k) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        cl n = coVar.mo1790n();
                        bdVar.f821a = new HashMap(n.f399c * 2);
                        for (int i = 0; i < n.f399c; i++) {
                            String z = coVar.mo1802z();
                            be beVar = new be();
                            beVar.mo1756a(coVar);
                            bdVar.f821a.put(z, beVar);
                        }
                        coVar.mo1791o();
                        bdVar.m1028a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bdVar.f822b = coVar.mo1799w();
                        bdVar.m1032b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bdVar.f823c = coVar.mo1802z();
                        bdVar.m1035c(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1406b(co coVar, bd bdVar) throws bv {
            bdVar.m1045m();
            coVar.mo1775a(bd.f815e);
            if (bdVar.f821a != null) {
                coVar.mo1770a(bd.f816f);
                coVar.mo1772a(new cl((byte) 11, (byte) 12, bdVar.f821a.size()));
                for (Entry entry : bdVar.f821a.entrySet()) {
                    coVar.mo1768a((String) entry.getKey());
                    ((be) entry.getValue()).mo1759b(coVar);
                }
                coVar.mo1781e();
                coVar.mo1779c();
            }
            coVar.mo1770a(bd.f817g);
            coVar.mo1766a(bdVar.f822b);
            coVar.mo1779c();
            if (bdVar.f823c != null) {
                coVar.mo1770a(bd.f818h);
                coVar.mo1768a(bdVar.f823c);
                coVar.mo1779c();
            }
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: Imprint */
    private static class C0691c extends cz<bd> {
        private C0691c() {
        }

        public void m1408a(co coVar, bd bdVar) throws bv {
            coVar = (cu) coVar;
            coVar.mo1766a(bdVar.f821a.size());
            for (Entry entry : bdVar.f821a.entrySet()) {
                coVar.mo1768a((String) entry.getKey());
                ((be) entry.getValue()).mo1759b(coVar);
            }
            coVar.mo1766a(bdVar.f822b);
            coVar.mo1768a(bdVar.f823c);
        }

        public void m1410b(co coVar, bd bdVar) throws bv {
            coVar = (cu) coVar;
            cl clVar = new cl((byte) 11, (byte) 12, coVar.mo1799w());
            bdVar.f821a = new HashMap(clVar.f399c * 2);
            for (int i = 0; i < clVar.f399c; i++) {
                String z = coVar.mo1802z();
                be beVar = new be();
                beVar.mo1756a(coVar);
                bdVar.f821a.put(z, beVar);
            }
            bdVar.m1028a(true);
            bdVar.f822b = coVar.mo1799w();
            bdVar.m1032b(true);
            bdVar.f823c = coVar.mo1802z();
            bdVar.m1035c(true);
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m1034c(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m1022a();
    }

    static {
        f819i.put(cy.class, new C0635b());
        f819i.put(cz.class, new C0636d());
        Map enumMap = new EnumMap(C0637e.class);
        enumMap.put(C0637e.PROPERTY, new cb("property", (byte) 1, new ce(cv.f422k, new cc((byte) 11), new cg((byte) 12, be.class))));
        enumMap.put(C0637e.VERSION, new cb("version", (byte) 1, new cc((byte) 8)));
        enumMap.put(C0637e.CHECKSUM, new cb("checksum", (byte) 1, new cc((byte) 11)));
        f814d = Collections.unmodifiableMap(enumMap);
        cb.m426a(bd.class, f814d);
    }

    public bd() {
        this.f824k = (byte) 0;
    }

    public bd(Map<String, be> map, int i, String str) {
        this();
        this.f821a = map;
        this.f822b = i;
        m1032b(true);
        this.f823c = str;
    }

    public bd(bd bdVar) {
        this.f824k = (byte) 0;
        this.f824k = bdVar.f824k;
        if (bdVar.m1038f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bdVar.f821a.entrySet()) {
                hashMap.put((String) entry.getKey(), new be((be) entry.getValue()));
            }
            this.f821a = hashMap;
        }
        this.f822b = bdVar.f822b;
        if (bdVar.m1044l()) {
            this.f823c = bdVar.f823c;
        }
    }

    public bd m1022a() {
        return new bd(this);
    }

    public void mo1758b() {
        this.f821a = null;
        m1032b(false);
        this.f822b = 0;
        this.f823c = null;
    }

    public int m1033c() {
        return this.f821a == null ? 0 : this.f821a.size();
    }

    public void m1026a(String str, be beVar) {
        if (this.f821a == null) {
            this.f821a = new HashMap();
        }
        this.f821a.put(str, beVar);
    }

    public Map<String, be> m1036d() {
        return this.f821a;
    }

    public bd m1025a(Map<String, be> map) {
        this.f821a = map;
        return this;
    }

    public void m1037e() {
        this.f821a = null;
    }

    public boolean m1038f() {
        return this.f821a != null;
    }

    public void m1028a(boolean z) {
        if (!z) {
            this.f821a = null;
        }
    }

    public int m1039g() {
        return this.f822b;
    }

    public bd m1023a(int i) {
        this.f822b = i;
        m1032b(true);
        return this;
    }

    public void m1040h() {
        this.f824k = bm.m358b(this.f824k, 0);
    }

    public boolean m1041i() {
        return bm.m354a(this.f824k, 0);
    }

    public void m1032b(boolean z) {
        this.f824k = bm.m346a(this.f824k, 0, z);
    }

    public String m1042j() {
        return this.f823c;
    }

    public bd m1024a(String str) {
        this.f823c = str;
        return this;
    }

    public void m1043k() {
        this.f823c = null;
    }

    public boolean m1044l() {
        return this.f823c != null;
    }

    public void m1035c(boolean z) {
        if (!z) {
            this.f823c = null;
        }
    }

    public C0637e m1034c(int i) {
        return C0637e.m1011a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f819i.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f819i.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.f821a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f821a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.f822b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.f823c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f823c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m1045m() throws bv {
        if (this.f821a == null) {
            throw new cp("Required field 'property' was not present! Struct: " + toString());
        } else if (this.f823c == null) {
            throw new cp("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    private void m1017a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m1016a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f824k = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
