package p000u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ImprintValue */
public class be implements Serializable, Cloneable, bp<be, C0640e> {
    public static final Map<C0640e, cb> f832d;
    private static final ct f833e = new ct("ImprintValue");
    private static final cj f834f = new cj("value", (byte) 11, (short) 1);
    private static final cj f835g = new cj("ts", (byte) 10, (short) 2);
    private static final cj f836h = new cj("guid", (byte) 11, (short) 3);
    private static final Map<Class<? extends cw>, cx> f837i = new HashMap();
    private static final int f838j = 0;
    public String f839a;
    public long f840b;
    public String f841c;
    private byte f842k;
    private C0640e[] f843l;

    /* compiled from: ImprintValue */
    private static class C0638b implements cx {
        private C0638b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1047a();
        }

        public C0692a m1047a() {
            return new C0692a();
        }
    }

    /* compiled from: ImprintValue */
    private static class C0639d implements cx {
        private C0639d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1049a();
        }

        public C0693c m1049a() {
            return new C0693c();
        }
    }

    /* compiled from: ImprintValue */
    public enum C0640e implements bw {
        VALUE((short) 1, "value"),
        TS((short) 2, "ts"),
        GUID((short) 3, "guid");
        
        private static final Map<String, C0640e> f828d = null;
        private final short f830e;
        private final String f831f;

        static {
            f828d = new HashMap();
            Iterator it = EnumSet.allOf(C0640e.class).iterator();
            while (it.hasNext()) {
                C0640e c0640e = (C0640e) it.next();
                f828d.put(c0640e.mo1755b(), c0640e);
            }
        }

        public static C0640e m1051a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        public static C0640e m1053b(int i) {
            C0640e a = C0640e.m1051a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0640e m1052a(String str) {
            return (C0640e) f828d.get(str);
        }

        private C0640e(short s, String str) {
            this.f830e = s;
            this.f831f = str;
        }

        public short mo1754a() {
            return this.f830e;
        }

        public String mo1755b() {
            return this.f831f;
        }
    }

    /* compiled from: ImprintValue */
    private static class C0692a extends cy<be> {
        private C0692a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1414b(coVar, (be) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1412a(coVar, (be) bpVar);
        }

        public void m1412a(co coVar, be beVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (beVar.m1079h()) {
                        beVar.m1083l();
                        return;
                    }
                    throw new cp("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        beVar.f839a = coVar.mo1802z();
                        beVar.m1067a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 10) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        beVar.f840b = coVar.mo1800x();
                        beVar.m1072b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        beVar.f841c = coVar.mo1802z();
                        beVar.m1074c(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1414b(co coVar, be beVar) throws bv {
            beVar.m1083l();
            coVar.mo1775a(be.f833e);
            if (beVar.f839a != null && beVar.m1076e()) {
                coVar.mo1770a(be.f834f);
                coVar.mo1768a(beVar.f839a);
                coVar.mo1779c();
            }
            coVar.mo1770a(be.f835g);
            coVar.mo1767a(beVar.f840b);
            coVar.mo1779c();
            if (beVar.f841c != null) {
                coVar.mo1770a(be.f836h);
                coVar.mo1768a(beVar.f841c);
                coVar.mo1779c();
            }
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: ImprintValue */
    private static class C0693c extends cz<be> {
        private C0693c() {
        }

        public void m1416a(co coVar, be beVar) throws bv {
            cu cuVar = (cu) coVar;
            cuVar.mo1767a(beVar.f840b);
            cuVar.mo1768a(beVar.f841c);
            BitSet bitSet = new BitSet();
            if (beVar.m1076e()) {
                bitSet.set(0);
            }
            cuVar.m1447a(bitSet, 1);
            if (beVar.m1076e()) {
                cuVar.mo1768a(beVar.f839a);
            }
        }

        public void m1418b(co coVar, be beVar) throws bv {
            cu cuVar = (cu) coVar;
            beVar.f840b = cuVar.mo1800x();
            beVar.m1072b(true);
            beVar.f841c = cuVar.mo1802z();
            beVar.m1074c(true);
            if (cuVar.mo1935b(1).get(0)) {
                beVar.f839a = cuVar.mo1802z();
                beVar.m1067a(true);
            }
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m1062a(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m1063a();
    }

    static {
        f837i.put(cy.class, new C0638b());
        f837i.put(cz.class, new C0639d());
        Map enumMap = new EnumMap(C0640e.class);
        enumMap.put(C0640e.VALUE, new cb("value", (byte) 2, new cc((byte) 11)));
        enumMap.put(C0640e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        enumMap.put(C0640e.GUID, new cb("guid", (byte) 1, new cc((byte) 11)));
        f832d = Collections.unmodifiableMap(enumMap);
        cb.m426a(be.class, f832d);
    }

    public be() {
        this.f842k = (byte) 0;
        this.f843l = new C0640e[]{C0640e.VALUE};
    }

    public be(long j, String str) {
        this();
        this.f840b = j;
        m1072b(true);
        this.f841c = str;
    }

    public be(be beVar) {
        this.f842k = (byte) 0;
        this.f843l = new C0640e[]{C0640e.VALUE};
        this.f842k = beVar.f842k;
        if (beVar.m1076e()) {
            this.f839a = beVar.f839a;
        }
        this.f840b = beVar.f840b;
        if (beVar.m1082k()) {
            this.f841c = beVar.f841c;
        }
    }

    public be m1063a() {
        return new be(this);
    }

    public void mo1758b() {
        this.f839a = null;
        m1072b(false);
        this.f840b = 0;
        this.f841c = null;
    }

    public String m1073c() {
        return this.f839a;
    }

    public be m1065a(String str) {
        this.f839a = str;
        return this;
    }

    public void m1075d() {
        this.f839a = null;
    }

    public boolean m1076e() {
        return this.f839a != null;
    }

    public void m1067a(boolean z) {
        if (!z) {
            this.f839a = null;
        }
    }

    public long m1077f() {
        return this.f840b;
    }

    public be m1064a(long j) {
        this.f840b = j;
        m1072b(true);
        return this;
    }

    public void m1078g() {
        this.f842k = bm.m358b(this.f842k, 0);
    }

    public boolean m1079h() {
        return bm.m354a(this.f842k, 0);
    }

    public void m1072b(boolean z) {
        this.f842k = bm.m346a(this.f842k, 0, z);
    }

    public String m1080i() {
        return this.f841c;
    }

    public be m1068b(String str) {
        this.f841c = str;
        return this;
    }

    public void m1081j() {
        this.f841c = null;
    }

    public boolean m1082k() {
        return this.f841c != null;
    }

    public void m1074c(boolean z) {
        if (!z) {
            this.f841c = null;
        }
    }

    public C0640e m1062a(int i) {
        return C0640e.m1051a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f837i.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f837i.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (m1076e()) {
            stringBuilder.append("value:");
            if (this.f839a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f839a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.f840b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.f841c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f841c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m1083l() throws bv {
        if (this.f841c == null) {
            throw new cp("Required field 'guid' was not present! Struct: " + toString());
        }
    }

    private void m1057a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m1056a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f842k = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
