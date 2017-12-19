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

/* compiled from: Response */
public class bf implements Serializable, Cloneable, bp<bf, C0643e> {
    public static final Map<C0643e, cb> f851d;
    private static final ct f852e = new ct("Response");
    private static final cj f853f = new cj("resp_code", (byte) 8, (short) 1);
    private static final cj f854g = new cj("msg", (byte) 11, (short) 2);
    private static final cj f855h = new cj(au.f200N, (byte) 12, (short) 3);
    private static final Map<Class<? extends cw>, cx> f856i = new HashMap();
    private static final int f857j = 0;
    public int f858a;
    public String f859b;
    public bd f860c;
    private byte f861k;
    private C0643e[] f862l;

    /* compiled from: Response */
    private static class C0641b implements cx {
        private C0641b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1085a();
        }

        public C0694a m1085a() {
            return new C0694a();
        }
    }

    /* compiled from: Response */
    private static class C0642d implements cx {
        private C0642d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1087a();
        }

        public C0695c m1087a() {
            return new C0695c();
        }
    }

    /* compiled from: Response */
    public enum C0643e implements bw {
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, "msg"),
        IMPRINT((short) 3, au.f200N);
        
        private static final Map<String, C0643e> f847d = null;
        private final short f849e;
        private final String f850f;

        static {
            f847d = new HashMap();
            Iterator it = EnumSet.allOf(C0643e.class).iterator();
            while (it.hasNext()) {
                C0643e c0643e = (C0643e) it.next();
                f847d.put(c0643e.mo1755b(), c0643e);
            }
        }

        public static C0643e m1089a(int i) {
            switch (i) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        public static C0643e m1091b(int i) {
            C0643e a = C0643e.m1089a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0643e m1090a(String str) {
            return (C0643e) f847d.get(str);
        }

        private C0643e(short s, String str) {
            this.f849e = s;
            this.f850f = str;
        }

        public short mo1754a() {
            return this.f849e;
        }

        public String mo1755b() {
            return this.f850f;
        }
    }

    /* compiled from: Response */
    private static class C0694a extends cy<bf> {
        private C0694a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1422b(coVar, (bf) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1420a(coVar, (bf) bpVar);
        }

        public void m1420a(co coVar, bf bfVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (bfVar.m1114e()) {
                        bfVar.m1121l();
                        return;
                    }
                    throw new cp("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bfVar.f858a = coVar.mo1799w();
                        bfVar.m1105a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bfVar.f859b = coVar.mo1802z();
                        bfVar.m1109b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 12) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bfVar.f860c = new bd();
                        bfVar.f860c.mo1756a(coVar);
                        bfVar.m1112c(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1422b(co coVar, bf bfVar) throws bv {
            bfVar.m1121l();
            coVar.mo1775a(bf.f852e);
            coVar.mo1770a(bf.f853f);
            coVar.mo1766a(bfVar.f858a);
            coVar.mo1779c();
            if (bfVar.f859b != null && bfVar.m1117h()) {
                coVar.mo1770a(bf.f854g);
                coVar.mo1768a(bfVar.f859b);
                coVar.mo1779c();
            }
            if (bfVar.f860c != null && bfVar.m1120k()) {
                coVar.mo1770a(bf.f855h);
                bfVar.f860c.mo1759b(coVar);
                coVar.mo1779c();
            }
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: Response */
    private static class C0695c extends cz<bf> {
        private C0695c() {
        }

        public void m1424a(co coVar, bf bfVar) throws bv {
            coVar = (cu) coVar;
            coVar.mo1766a(bfVar.f858a);
            BitSet bitSet = new BitSet();
            if (bfVar.m1117h()) {
                bitSet.set(0);
            }
            if (bfVar.m1120k()) {
                bitSet.set(1);
            }
            coVar.m1447a(bitSet, 2);
            if (bfVar.m1117h()) {
                coVar.mo1768a(bfVar.f859b);
            }
            if (bfVar.m1120k()) {
                bfVar.f860c.mo1759b(coVar);
            }
        }

        public void m1426b(co coVar, bf bfVar) throws bv {
            coVar = (cu) coVar;
            bfVar.f858a = coVar.mo1799w();
            bfVar.m1105a(true);
            BitSet b = coVar.mo1935b(2);
            if (b.get(0)) {
                bfVar.f859b = coVar.mo1802z();
                bfVar.m1109b(true);
            }
            if (b.get(1)) {
                bfVar.f860c = new bd();
                bfVar.f860c.mo1756a(coVar);
                bfVar.m1112c(true);
            }
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m1111c(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m1100a();
    }

    static {
        f856i.put(cy.class, new C0641b());
        f856i.put(cz.class, new C0642d());
        Map enumMap = new EnumMap(C0643e.class);
        enumMap.put(C0643e.RESP_CODE, new cb("resp_code", (byte) 1, new cc((byte) 8)));
        enumMap.put(C0643e.MSG, new cb("msg", (byte) 2, new cc((byte) 11)));
        enumMap.put(C0643e.IMPRINT, new cb(au.f200N, (byte) 2, new cg((byte) 12, bd.class)));
        f851d = Collections.unmodifiableMap(enumMap);
        cb.m426a(bf.class, f851d);
    }

    public bf() {
        this.f861k = (byte) 0;
        this.f862l = new C0643e[]{C0643e.MSG, C0643e.IMPRINT};
    }

    public bf(int i) {
        this();
        this.f858a = i;
        m1105a(true);
    }

    public bf(bf bfVar) {
        this.f861k = (byte) 0;
        this.f862l = new C0643e[]{C0643e.MSG, C0643e.IMPRINT};
        this.f861k = bfVar.f861k;
        this.f858a = bfVar.f858a;
        if (bfVar.m1117h()) {
            this.f859b = bfVar.f859b;
        }
        if (bfVar.m1120k()) {
            this.f860c = new bd(bfVar.f860c);
        }
    }

    public bf m1100a() {
        return new bf(this);
    }

    public void mo1758b() {
        m1105a(false);
        this.f858a = 0;
        this.f859b = null;
        this.f860c = null;
    }

    public int m1110c() {
        return this.f858a;
    }

    public bf m1101a(int i) {
        this.f858a = i;
        m1105a(true);
        return this;
    }

    public void m1113d() {
        this.f861k = bm.m358b(this.f861k, 0);
    }

    public boolean m1114e() {
        return bm.m354a(this.f861k, 0);
    }

    public void m1105a(boolean z) {
        this.f861k = bm.m346a(this.f861k, 0, z);
    }

    public String m1115f() {
        return this.f859b;
    }

    public bf m1102a(String str) {
        this.f859b = str;
        return this;
    }

    public void m1116g() {
        this.f859b = null;
    }

    public boolean m1117h() {
        return this.f859b != null;
    }

    public void m1109b(boolean z) {
        if (!z) {
            this.f859b = null;
        }
    }

    public bd m1118i() {
        return this.f860c;
    }

    public bf m1103a(bd bdVar) {
        this.f860c = bdVar;
        return this;
    }

    public void m1119j() {
        this.f860c = null;
    }

    public boolean m1120k() {
        return this.f860c != null;
    }

    public void m1112c(boolean z) {
        if (!z) {
            this.f860c = null;
        }
    }

    public C0643e m1111c(int i) {
        return C0643e.m1089a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f856i.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f856i.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.f858a);
        if (m1117h()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.f859b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f859b);
            }
        }
        if (m1120k()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.f860c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f860c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m1121l() throws bv {
        if (this.f860c != null) {
            this.f860c.m1045m();
        }
    }

    private void m1095a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m1094a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f861k = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
