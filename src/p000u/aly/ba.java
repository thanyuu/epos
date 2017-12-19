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

/* compiled from: IdJournal */
public class ba implements Serializable, Cloneable, bp<ba, C0628e> {
    public static final Map<C0628e, cb> f757e;
    private static final ct f758f = new ct("IdJournal");
    private static final cj f759g = new cj("domain", (byte) 11, (short) 1);
    private static final cj f760h = new cj("old_id", (byte) 11, (short) 2);
    private static final cj f761i = new cj("new_id", (byte) 11, (short) 3);
    private static final cj f762j = new cj("ts", (byte) 10, (short) 4);
    private static final Map<Class<? extends cw>, cx> f763k = new HashMap();
    private static final int f764l = 0;
    public String f765a;
    public String f766b;
    public String f767c;
    public long f768d;
    private byte f769m;
    private C0628e[] f770n;

    /* compiled from: IdJournal */
    private static class C0626b implements cx {
        private C0626b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m882a();
        }

        public C0684a m882a() {
            return new C0684a();
        }
    }

    /* compiled from: IdJournal */
    private static class C0627d implements cx {
        private C0627d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m884a();
        }

        public C0685c m884a() {
            return new C0685c();
        }
    }

    /* compiled from: IdJournal */
    public enum C0628e implements bw {
        DOMAIN((short) 1, "domain"),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, "ts");
        
        private static final Map<String, C0628e> f753e = null;
        private final short f755f;
        private final String f756g;

        static {
            f753e = new HashMap();
            Iterator it = EnumSet.allOf(C0628e.class).iterator();
            while (it.hasNext()) {
                C0628e c0628e = (C0628e) it.next();
                f753e.put(c0628e.mo1755b(), c0628e);
            }
        }

        public static C0628e m886a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
                default:
                    return null;
            }
        }

        public static C0628e m888b(int i) {
            C0628e a = C0628e.m886a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0628e m887a(String str) {
            return (C0628e) f753e.get(str);
        }

        private C0628e(short s, String str) {
            this.f755f = s;
            this.f756g = str;
        }

        public short mo1754a() {
            return this.f755f;
        }

        public String mo1755b() {
            return this.f756g;
        }
    }

    /* compiled from: IdJournal */
    private static class C0684a extends cy<ba> {
        private C0684a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1382b(coVar, (ba) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1380a(coVar, (ba) bpVar);
        }

        public void m1380a(co coVar, ba baVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (baVar.m923n()) {
                        baVar.m924o();
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
                        baVar.f765a = coVar.mo1802z();
                        baVar.m903a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        baVar.f766b = coVar.mo1802z();
                        baVar.m908b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        baVar.f767c = coVar.mo1802z();
                        baVar.m911c(true);
                        break;
                    case (short) 4:
                        if (l.f393b != (byte) 10) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        baVar.f768d = coVar.mo1800x();
                        baVar.m913d(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1382b(co coVar, ba baVar) throws bv {
            baVar.m924o();
            coVar.mo1775a(ba.f758f);
            if (baVar.f765a != null) {
                coVar.mo1770a(ba.f759g);
                coVar.mo1768a(baVar.f765a);
                coVar.mo1779c();
            }
            if (baVar.f766b != null && baVar.m917h()) {
                coVar.mo1770a(ba.f760h);
                coVar.mo1768a(baVar.f766b);
                coVar.mo1779c();
            }
            if (baVar.f767c != null) {
                coVar.mo1770a(ba.f761i);
                coVar.mo1768a(baVar.f767c);
                coVar.mo1779c();
            }
            coVar.mo1770a(ba.f762j);
            coVar.mo1767a(baVar.f768d);
            coVar.mo1779c();
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: IdJournal */
    private static class C0685c extends cz<ba> {
        private C0685c() {
        }

        public void m1384a(co coVar, ba baVar) throws bv {
            cu cuVar = (cu) coVar;
            cuVar.mo1768a(baVar.f765a);
            cuVar.mo1768a(baVar.f767c);
            cuVar.mo1767a(baVar.f768d);
            BitSet bitSet = new BitSet();
            if (baVar.m917h()) {
                bitSet.set(0);
            }
            cuVar.m1447a(bitSet, 1);
            if (baVar.m917h()) {
                cuVar.mo1768a(baVar.f766b);
            }
        }

        public void m1386b(co coVar, ba baVar) throws bv {
            cu cuVar = (cu) coVar;
            baVar.f765a = cuVar.mo1802z();
            baVar.m903a(true);
            baVar.f767c = cuVar.mo1802z();
            baVar.m911c(true);
            baVar.f768d = cuVar.mo1800x();
            baVar.m913d(true);
            if (cuVar.mo1935b(1).get(0)) {
                baVar.f766b = cuVar.mo1802z();
                baVar.m908b(true);
            }
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m898a(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m899a();
    }

    static {
        f763k.put(cy.class, new C0626b());
        f763k.put(cz.class, new C0627d());
        Map enumMap = new EnumMap(C0628e.class);
        enumMap.put(C0628e.DOMAIN, new cb("domain", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0628e.OLD_ID, new cb("old_id", (byte) 2, new cc((byte) 11)));
        enumMap.put(C0628e.NEW_ID, new cb("new_id", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0628e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        f757e = Collections.unmodifiableMap(enumMap);
        cb.m426a(ba.class, f757e);
    }

    public ba() {
        this.f769m = (byte) 0;
        this.f770n = new C0628e[]{C0628e.OLD_ID};
    }

    public ba(String str, String str2, long j) {
        this();
        this.f765a = str;
        this.f767c = str2;
        this.f768d = j;
        m913d(true);
    }

    public ba(ba baVar) {
        this.f769m = (byte) 0;
        this.f770n = new C0628e[]{C0628e.OLD_ID};
        this.f769m = baVar.f769m;
        if (baVar.m914e()) {
            this.f765a = baVar.f765a;
        }
        if (baVar.m917h()) {
            this.f766b = baVar.f766b;
        }
        if (baVar.m920k()) {
            this.f767c = baVar.f767c;
        }
        this.f768d = baVar.f768d;
    }

    public ba m899a() {
        return new ba(this);
    }

    public void mo1758b() {
        this.f765a = null;
        this.f766b = null;
        this.f767c = null;
        m913d(false);
        this.f768d = 0;
    }

    public String m909c() {
        return this.f765a;
    }

    public ba m901a(String str) {
        this.f765a = str;
        return this;
    }

    public void m912d() {
        this.f765a = null;
    }

    public boolean m914e() {
        return this.f765a != null;
    }

    public void m903a(boolean z) {
        if (!z) {
            this.f765a = null;
        }
    }

    public String m915f() {
        return this.f766b;
    }

    public ba m904b(String str) {
        this.f766b = str;
        return this;
    }

    public void m916g() {
        this.f766b = null;
    }

    public boolean m917h() {
        return this.f766b != null;
    }

    public void m908b(boolean z) {
        if (!z) {
            this.f766b = null;
        }
    }

    public String m918i() {
        return this.f767c;
    }

    public ba m910c(String str) {
        this.f767c = str;
        return this;
    }

    public void m919j() {
        this.f767c = null;
    }

    public boolean m920k() {
        return this.f767c != null;
    }

    public void m911c(boolean z) {
        if (!z) {
            this.f767c = null;
        }
    }

    public long m921l() {
        return this.f768d;
    }

    public ba m900a(long j) {
        this.f768d = j;
        m913d(true);
        return this;
    }

    public void m922m() {
        this.f769m = bm.m358b(this.f769m, 0);
    }

    public boolean m923n() {
        return bm.m354a(this.f769m, 0);
    }

    public void m913d(boolean z) {
        this.f769m = bm.m346a(this.f769m, 0, z);
    }

    public C0628e m898a(int i) {
        return C0628e.m886a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f763k.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f763k.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.f765a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f765a);
        }
        if (m917h()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.f766b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f766b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.f767c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f767c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.f768d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m924o() throws bv {
        if (this.f765a == null) {
            throw new cp("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.f767c == null) {
            throw new cp("Required field 'new_id' was not present! Struct: " + toString());
        }
    }

    private void m892a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m891a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f769m = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
