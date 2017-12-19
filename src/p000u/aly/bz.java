package p000u.aly;

import com.android.common.utils.MapUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: TUnion */
public abstract class bz<T extends bz<?, ?>, F extends bw> implements bp<T, F> {
    private static final Map<Class<? extends cw>, cx> f919c = new HashMap();
    protected Object f920a;
    protected F f921b;

    /* compiled from: TUnion */
    private static class C0647b implements cx {
        private C0647b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1208a();
        }

        public C0698a m1208a() {
            return new C0698a();
        }
    }

    /* compiled from: TUnion */
    private static class C0648d implements cx {
        private C0648d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1210a();
        }

        public C0699c m1210a() {
            return new C0699c();
        }
    }

    /* compiled from: TUnion */
    private static class C0698a extends cy<bz> {
        private C0698a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1439b(coVar, (bz) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1437a(coVar, (bz) bpVar);
        }

        public void m1437a(co coVar, bz bzVar) throws bv {
            bzVar.f921b = null;
            bzVar.f920a = null;
            coVar.mo1786j();
            cj l = coVar.mo1788l();
            bzVar.f920a = bzVar.m1218a(coVar, l);
            if (bzVar.f920a != null) {
                bzVar.f921b = bzVar.m1221a(l.f394c);
            }
            coVar.mo1789m();
            coVar.mo1788l();
            coVar.mo1787k();
        }

        public void m1439b(co coVar, bz bzVar) throws bv {
            if (bzVar.m1220a() == null || bzVar.m1229c() == null) {
                throw new cp("Cannot write a TUnion with no set value!");
            }
            coVar.mo1775a(bzVar.m1235e());
            coVar.mo1770a(bzVar.m1230c(bzVar.f921b));
            bzVar.m1231c(coVar);
            coVar.mo1779c();
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: TUnion */
    private static class C0699c extends cz<bz> {
        private C0699c() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1443b(coVar, (bz) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1441a(coVar, (bz) bpVar);
        }

        public void m1441a(co coVar, bz bzVar) throws bv {
            bzVar.f921b = null;
            bzVar.f920a = null;
            short v = coVar.mo1798v();
            bzVar.f920a = bzVar.m1219a(coVar, v);
            if (bzVar.f920a != null) {
                bzVar.f921b = bzVar.m1221a(v);
            }
        }

        public void m1443b(co coVar, bz bzVar) throws bv {
            if (bzVar.m1220a() == null || bzVar.m1229c() == null) {
                throw new cp("Cannot write a TUnion with no set value!");
            }
            coVar.mo1776a(bzVar.f921b.mo1754a());
            bzVar.m1233d(coVar);
        }
    }

    protected abstract Object m1218a(co coVar, cj cjVar) throws bv;

    protected abstract Object m1219a(co coVar, short s) throws bv;

    protected abstract F m1221a(short s);

    protected abstract void m1226b(F f, Object obj) throws ClassCastException;

    protected abstract cj m1230c(F f);

    protected abstract void m1231c(co coVar) throws bv;

    protected abstract void m1233d(co coVar) throws bv;

    protected abstract ct m1235e();

    protected bz() {
        this.f921b = null;
        this.f920a = null;
    }

    static {
        f919c.put(cy.class, new C0647b());
        f919c.put(cz.class, new C0648d());
    }

    protected bz(F f, Object obj) {
        m1223a((bw) f, obj);
    }

    protected bz(bz<T, F> bzVar) {
        if (bzVar.getClass().equals(getClass())) {
            this.f921b = bzVar.f921b;
            this.f920a = bz.m1212a(bzVar.f920a);
            return;
        }
        throw new ClassCastException();
    }

    private static Object m1212a(Object obj) {
        if (obj instanceof bp) {
            return ((bp) obj).mo1760p();
        }
        if (obj instanceof ByteBuffer) {
            return bq.m392d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return bz.m1213a((List) obj);
        }
        if (obj instanceof Set) {
            return bz.m1215a((Set) obj);
        }
        if (obj instanceof Map) {
            return bz.m1214a((Map) obj);
        }
        return obj;
    }

    private static Map m1214a(Map<Object, Object> map) {
        Map hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(bz.m1212a(entry.getKey()), bz.m1212a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set m1215a(Set set) {
        Set hashSet = new HashSet();
        for (Object a : set) {
            hashSet.add(bz.m1212a(a));
        }
        return hashSet;
    }

    private static List m1213a(List list) {
        List arrayList = new ArrayList(list.size());
        for (Object a : list) {
            arrayList.add(bz.m1212a(a));
        }
        return arrayList;
    }

    public F m1220a() {
        return this.f921b;
    }

    public Object m1229c() {
        return this.f920a;
    }

    public Object m1217a(F f) {
        if (f == this.f921b) {
            return m1229c();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.f921b);
    }

    public Object m1216a(int i) {
        return m1217a(m1221a((short) i));
    }

    public boolean m1234d() {
        return this.f921b != null;
    }

    public boolean m1228b(F f) {
        return this.f921b == f;
    }

    public boolean m1232c(int i) {
        return m1228b(m1221a((short) i));
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f919c.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void m1223a(F f, Object obj) {
        m1226b(f, obj);
        this.f921b = f;
        this.f920a = obj;
    }

    public void m1222a(int i, Object obj) {
        m1223a(m1221a((short) i), obj);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f919c.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        if (m1220a() != null) {
            Object c = m1229c();
            stringBuilder.append(m1230c(m1220a()).f392a);
            stringBuilder.append(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR);
            if (c instanceof ByteBuffer) {
                bq.m387a((ByteBuffer) c, stringBuilder);
            } else {
                stringBuilder.append(c.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public final void mo1758b() {
        this.f921b = null;
        this.f920a = null;
    }
}
