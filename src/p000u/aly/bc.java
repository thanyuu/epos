package p000u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IdTracking */
public class bc implements Serializable, Cloneable, bp<bc, C0634e> {
    public static final Map<C0634e, cb> f797d;
    private static final ct f798e = new ct("IdTracking");
    private static final cj f799f = new cj("snapshots", cv.f422k, (short) 1);
    private static final cj f800g = new cj("journals", cv.f424m, (short) 2);
    private static final cj f801h = new cj("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends cw>, cx> f802i = new HashMap();
    public Map<String, bb> f803a;
    public List<ba> f804b;
    public String f805c;
    private C0634e[] f806j;

    /* compiled from: IdTracking */
    private static class C0632b implements cx {
        private C0632b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m964a();
        }

        public C0688a m964a() {
            return new C0688a();
        }
    }

    /* compiled from: IdTracking */
    private static class C0633d implements cx {
        private C0633d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m966a();
        }

        public C0689c m966a() {
            return new C0689c();
        }
    }

    /* compiled from: IdTracking */
    public enum C0634e implements bw {
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
        CHECKSUM((short) 3, "checksum");
        
        private static final Map<String, C0634e> f793d = null;
        private final short f795e;
        private final String f796f;

        static {
            f793d = new HashMap();
            Iterator it = EnumSet.allOf(C0634e.class).iterator();
            while (it.hasNext()) {
                C0634e c0634e = (C0634e) it.next();
                f793d.put(c0634e.mo1755b(), c0634e);
            }
        }

        public static C0634e m968a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static C0634e m970b(int i) {
            C0634e a = C0634e.m968a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0634e m969a(String str) {
            return (C0634e) f793d.get(str);
        }

        private C0634e(short s, String str) {
            this.f795e = s;
            this.f796f = str;
        }

        public short mo1754a() {
            return this.f795e;
        }

        public String mo1755b() {
            return this.f796f;
        }
    }

    /* compiled from: IdTracking */
    private static class C0688a extends cy<bc> {
        private C0688a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1398b(coVar, (bc) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1396a(coVar, (bc) bpVar);
        }

        public void m1396a(co coVar, bc bcVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    bcVar.m1005o();
                    return;
                }
                int i;
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != cv.f422k) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        cl n = coVar.mo1790n();
                        bcVar.f803a = new HashMap(n.f399c * 2);
                        for (i = 0; i < n.f399c; i++) {
                            String z = coVar.mo1802z();
                            bb bbVar = new bb();
                            bbVar.mo1756a(coVar);
                            bcVar.f803a.put(z, bbVar);
                        }
                        coVar.mo1791o();
                        bcVar.m987a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != cv.f424m) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        ck p = coVar.mo1792p();
                        bcVar.f804b = new ArrayList(p.f396b);
                        for (i = 0; i < p.f396b; i++) {
                            ba baVar = new ba();
                            baVar.mo1756a(coVar);
                            bcVar.f804b.add(baVar);
                        }
                        coVar.mo1793q();
                        bcVar.m991b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bcVar.f805c = coVar.mo1802z();
                        bcVar.m993c(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1398b(co coVar, bc bcVar) throws bv {
            bcVar.m1005o();
            coVar.mo1775a(bc.f798e);
            if (bcVar.f803a != null) {
                coVar.mo1770a(bc.f799f);
                coVar.mo1772a(new cl((byte) 11, (byte) 12, bcVar.f803a.size()));
                for (Entry entry : bcVar.f803a.entrySet()) {
                    coVar.mo1768a((String) entry.getKey());
                    ((bb) entry.getValue()).mo1759b(coVar);
                }
                coVar.mo1781e();
                coVar.mo1779c();
            }
            if (bcVar.f804b != null && bcVar.m1001k()) {
                coVar.mo1770a(bc.f800g);
                coVar.mo1771a(new ck((byte) 12, bcVar.f804b.size()));
                for (ba b : bcVar.f804b) {
                    b.mo1759b(coVar);
                }
                coVar.mo1782f();
                coVar.mo1779c();
            }
            if (bcVar.f805c != null && bcVar.m1004n()) {
                coVar.mo1770a(bc.f801h);
                coVar.mo1768a(bcVar.f805c);
                coVar.mo1779c();
            }
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: IdTracking */
    private static class C0689c extends cz<bc> {
        private C0689c() {
        }

        public void m1400a(co coVar, bc bcVar) throws bv {
            coVar = (cu) coVar;
            coVar.mo1766a(bcVar.f803a.size());
            for (Entry entry : bcVar.f803a.entrySet()) {
                coVar.mo1768a((String) entry.getKey());
                ((bb) entry.getValue()).mo1759b(coVar);
            }
            BitSet bitSet = new BitSet();
            if (bcVar.m1001k()) {
                bitSet.set(0);
            }
            if (bcVar.m1004n()) {
                bitSet.set(1);
            }
            coVar.m1447a(bitSet, 2);
            if (bcVar.m1001k()) {
                coVar.mo1766a(bcVar.f804b.size());
                for (ba b : bcVar.f804b) {
                    b.mo1759b(coVar);
                }
            }
            if (bcVar.m1004n()) {
                coVar.mo1768a(bcVar.f805c);
            }
        }

        public void m1402b(co coVar, bc bcVar) throws bv {
            int i = 0;
            coVar = (cu) coVar;
            cl clVar = new cl((byte) 11, (byte) 12, coVar.mo1799w());
            bcVar.f803a = new HashMap(clVar.f399c * 2);
            for (int i2 = 0; i2 < clVar.f399c; i2++) {
                String z = coVar.mo1802z();
                bb bbVar = new bb();
                bbVar.mo1756a(coVar);
                bcVar.f803a.put(z, bbVar);
            }
            bcVar.m987a(true);
            BitSet b = coVar.mo1935b(2);
            if (b.get(0)) {
                ck ckVar = new ck((byte) 12, coVar.mo1799w());
                bcVar.f804b = new ArrayList(ckVar.f396b);
                while (i < ckVar.f396b) {
                    ba baVar = new ba();
                    baVar.mo1756a(coVar);
                    bcVar.f804b.add(baVar);
                    i++;
                }
                bcVar.m991b(true);
            }
            if (b.get(1)) {
                bcVar.f805c = coVar.mo1802z();
                bcVar.m993c(true);
            }
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m979a(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m980a();
    }

    static {
        f802i.put(cy.class, new C0632b());
        f802i.put(cz.class, new C0633d());
        Map enumMap = new EnumMap(C0634e.class);
        enumMap.put(C0634e.SNAPSHOTS, new cb("snapshots", (byte) 1, new ce(cv.f422k, new cc((byte) 11), new cg((byte) 12, bb.class))));
        enumMap.put(C0634e.JOURNALS, new cb("journals", (byte) 2, new cd(cv.f424m, new cg((byte) 12, ba.class))));
        enumMap.put(C0634e.CHECKSUM, new cb("checksum", (byte) 2, new cc((byte) 11)));
        f797d = Collections.unmodifiableMap(enumMap);
        cb.m426a(bc.class, f797d);
    }

    public bc() {
        this.f806j = new C0634e[]{C0634e.JOURNALS, C0634e.CHECKSUM};
    }

    public bc(Map<String, bb> map) {
        this();
        this.f803a = map;
    }

    public bc(bc bcVar) {
        this.f806j = new C0634e[]{C0634e.JOURNALS, C0634e.CHECKSUM};
        if (bcVar.m996f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bcVar.f803a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bb((bb) entry.getValue()));
            }
            this.f803a = hashMap;
        }
        if (bcVar.m1001k()) {
            List arrayList = new ArrayList();
            for (ba baVar : bcVar.f804b) {
                arrayList.add(new ba(baVar));
            }
            this.f804b = arrayList;
        }
        if (bcVar.m1004n()) {
            this.f805c = bcVar.f805c;
        }
    }

    public bc m980a() {
        return new bc(this);
    }

    public void mo1758b() {
        this.f803a = null;
        this.f804b = null;
        this.f805c = null;
    }

    public int m992c() {
        return this.f803a == null ? 0 : this.f803a.size();
    }

    public void m984a(String str, bb bbVar) {
        if (this.f803a == null) {
            this.f803a = new HashMap();
        }
        this.f803a.put(str, bbVar);
    }

    public Map<String, bb> m994d() {
        return this.f803a;
    }

    public bc m983a(Map<String, bb> map) {
        this.f803a = map;
        return this;
    }

    public void m995e() {
        this.f803a = null;
    }

    public boolean m996f() {
        return this.f803a != null;
    }

    public void m987a(boolean z) {
        if (!z) {
            this.f803a = null;
        }
    }

    public int m997g() {
        return this.f804b == null ? 0 : this.f804b.size();
    }

    public Iterator<ba> m998h() {
        return this.f804b == null ? null : this.f804b.iterator();
    }

    public void m985a(ba baVar) {
        if (this.f804b == null) {
            this.f804b = new ArrayList();
        }
        this.f804b.add(baVar);
    }

    public List<ba> m999i() {
        return this.f804b;
    }

    public bc m982a(List<ba> list) {
        this.f804b = list;
        return this;
    }

    public void m1000j() {
        this.f804b = null;
    }

    public boolean m1001k() {
        return this.f804b != null;
    }

    public void m991b(boolean z) {
        if (!z) {
            this.f804b = null;
        }
    }

    public String m1002l() {
        return this.f805c;
    }

    public bc m981a(String str) {
        this.f805c = str;
        return this;
    }

    public void m1003m() {
        this.f805c = null;
    }

    public boolean m1004n() {
        return this.f805c != null;
    }

    public void m993c(boolean z) {
        if (!z) {
            this.f805c = null;
        }
    }

    public C0634e m979a(int i) {
        return C0634e.m968a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f802i.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f802i.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.f803a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f803a);
        }
        if (m1001k()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.f804b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f804b);
            }
        }
        if (m1004n()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.f805c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f805c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m1005o() throws bv {
        if (this.f803a == null) {
            throw new cp("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    private void m974a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m973a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
