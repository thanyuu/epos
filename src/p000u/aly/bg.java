package p000u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope */
public class bg implements Serializable, Cloneable, bp<bg, C0646e> {
    private static final int f877A = 3;
    public static final Map<C0646e, cb> f878k;
    private static final ct f879l = new ct("UMEnvelope");
    private static final cj f880m = new cj("version", (byte) 11, (short) 1);
    private static final cj f881n = new cj("address", (byte) 11, (short) 2);
    private static final cj f882o = new cj("signature", (byte) 11, (short) 3);
    private static final cj f883p = new cj("serial_num", (byte) 8, (short) 4);
    private static final cj f884q = new cj("ts_secs", (byte) 8, (short) 5);
    private static final cj f885r = new cj("length", (byte) 8, (short) 6);
    private static final cj f886s = new cj("entity", (byte) 11, (short) 7);
    private static final cj f887t = new cj("guid", (byte) 11, (short) 8);
    private static final cj f888u = new cj("checksum", (byte) 11, (short) 9);
    private static final cj f889v = new cj("codex", (byte) 8, (short) 10);
    private static final Map<Class<? extends cw>, cx> f890w = new HashMap();
    private static final int f891x = 0;
    private static final int f892y = 1;
    private static final int f893z = 2;
    private byte f894B;
    private C0646e[] f895C;
    public String f896a;
    public String f897b;
    public String f898c;
    public int f899d;
    public int f900e;
    public int f901f;
    public ByteBuffer f902g;
    public String f903h;
    public String f904i;
    public int f905j;

    /* compiled from: UMEnvelope */
    private static class C0644b implements cx {
        private C0644b() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1123a();
        }

        public C0696a m1123a() {
            return new C0696a();
        }
    }

    /* compiled from: UMEnvelope */
    private static class C0645d implements cx {
        private C0645d() {
        }

        public /* synthetic */ cw mo1753b() {
            return m1125a();
        }

        public C0697c m1125a() {
            return new C0697c();
        }
    }

    /* compiled from: UMEnvelope */
    public enum C0646e implements bw {
        VERSION((short) 1, "version"),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, "signature"),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, "entity"),
        GUID((short) 8, "guid"),
        CHECKSUM((short) 9, "checksum"),
        CODEX((short) 10, "codex");
        
        private static final Map<String, C0646e> f873k = null;
        private final short f875l;
        private final String f876m;

        static {
            f873k = new HashMap();
            Iterator it = EnumSet.allOf(C0646e.class).iterator();
            while (it.hasNext()) {
                C0646e c0646e = (C0646e) it.next();
                f873k.put(c0646e.mo1755b(), c0646e);
            }
        }

        public static C0646e m1127a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        public static C0646e m1129b(int i) {
            C0646e a = C0646e.m1127a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static C0646e m1128a(String str) {
            return (C0646e) f873k.get(str);
        }

        private C0646e(short s, String str) {
            this.f875l = s;
            this.f876m = str;
        }

        public short mo1754a() {
            return this.f875l;
        }

        public String mo1755b() {
            return this.f876m;
        }
    }

    /* compiled from: UMEnvelope */
    private static class C0696a extends cy<bg> {
        private C0696a() {
        }

        public /* synthetic */ void mo1932a(co coVar, bp bpVar) throws bv {
            m1430b(coVar, (bg) bpVar);
        }

        public /* synthetic */ void mo1933b(co coVar, bp bpVar) throws bv {
            m1428a(coVar, (bg) bpVar);
        }

        public void m1428a(co coVar, bg bgVar) throws bv {
            coVar.mo1786j();
            while (true) {
                cj l = coVar.mo1788l();
                if (l.f393b == (byte) 0) {
                    coVar.mo1787k();
                    if (!bgVar.m1192n()) {
                        throw new cp("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!bgVar.m1196r()) {
                        throw new cp("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (bgVar.m1199u()) {
                        bgVar.m1153I();
                        return;
                    } else {
                        throw new cp("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.f394c) {
                    case (short) 1:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f896a = coVar.mo1802z();
                        bgVar.m1160a(true);
                        break;
                    case (short) 2:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f897b = coVar.mo1802z();
                        bgVar.m1165b(true);
                        break;
                    case (short) 3:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f898c = coVar.mo1802z();
                        bgVar.m1169c(true);
                        break;
                    case (short) 4:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f899d = coVar.mo1799w();
                        bgVar.m1173d(true);
                        break;
                    case (short) 5:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f900e = coVar.mo1799w();
                        bgVar.m1176e(true);
                        break;
                    case (short) 6:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f901f = coVar.mo1799w();
                        bgVar.m1180f(true);
                        break;
                    case (short) 7:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f902g = coVar.mo1762A();
                        bgVar.m1182g(true);
                        break;
                    case (short) 8:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f903h = coVar.mo1802z();
                        bgVar.m1183h(true);
                        break;
                    case (short) 9:
                        if (l.f393b != (byte) 11) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f904i = coVar.mo1802z();
                        bgVar.m1186i(true);
                        break;
                    case (short) 10:
                        if (l.f393b != (byte) 8) {
                            cr.m481a(coVar, l.f393b);
                            break;
                        }
                        bgVar.f905j = coVar.mo1799w();
                        bgVar.m1188j(true);
                        break;
                    default:
                        cr.m481a(coVar, l.f393b);
                        break;
                }
                coVar.mo1789m();
            }
        }

        public void m1430b(co coVar, bg bgVar) throws bv {
            bgVar.m1153I();
            coVar.mo1775a(bg.f879l);
            if (bgVar.f896a != null) {
                coVar.mo1770a(bg.f880m);
                coVar.mo1768a(bgVar.f896a);
                coVar.mo1779c();
            }
            if (bgVar.f897b != null) {
                coVar.mo1770a(bg.f881n);
                coVar.mo1768a(bgVar.f897b);
                coVar.mo1779c();
            }
            if (bgVar.f898c != null) {
                coVar.mo1770a(bg.f882o);
                coVar.mo1768a(bgVar.f898c);
                coVar.mo1779c();
            }
            coVar.mo1770a(bg.f883p);
            coVar.mo1766a(bgVar.f899d);
            coVar.mo1779c();
            coVar.mo1770a(bg.f884q);
            coVar.mo1766a(bgVar.f900e);
            coVar.mo1779c();
            coVar.mo1770a(bg.f885r);
            coVar.mo1766a(bgVar.f901f);
            coVar.mo1779c();
            if (bgVar.f902g != null) {
                coVar.mo1770a(bg.f886s);
                coVar.mo1769a(bgVar.f902g);
                coVar.mo1779c();
            }
            if (bgVar.f903h != null) {
                coVar.mo1770a(bg.f887t);
                coVar.mo1768a(bgVar.f903h);
                coVar.mo1779c();
            }
            if (bgVar.f904i != null) {
                coVar.mo1770a(bg.f888u);
                coVar.mo1768a(bgVar.f904i);
                coVar.mo1779c();
            }
            if (bgVar.m1152H()) {
                coVar.mo1770a(bg.f889v);
                coVar.mo1766a(bgVar.f905j);
                coVar.mo1779c();
            }
            coVar.mo1780d();
            coVar.mo1778b();
        }
    }

    /* compiled from: UMEnvelope */
    private static class C0697c extends cz<bg> {
        private C0697c() {
        }

        public void m1432a(co coVar, bg bgVar) throws bv {
            cu cuVar = (cu) coVar;
            cuVar.mo1768a(bgVar.f896a);
            cuVar.mo1768a(bgVar.f897b);
            cuVar.mo1768a(bgVar.f898c);
            cuVar.mo1766a(bgVar.f899d);
            cuVar.mo1766a(bgVar.f900e);
            cuVar.mo1766a(bgVar.f901f);
            cuVar.mo1769a(bgVar.f902g);
            cuVar.mo1768a(bgVar.f903h);
            cuVar.mo1768a(bgVar.f904i);
            BitSet bitSet = new BitSet();
            if (bgVar.m1152H()) {
                bitSet.set(0);
            }
            cuVar.m1447a(bitSet, 1);
            if (bgVar.m1152H()) {
                cuVar.mo1766a(bgVar.f905j);
            }
        }

        public void m1434b(co coVar, bg bgVar) throws bv {
            cu cuVar = (cu) coVar;
            bgVar.f896a = cuVar.mo1802z();
            bgVar.m1160a(true);
            bgVar.f897b = cuVar.mo1802z();
            bgVar.m1165b(true);
            bgVar.f898c = cuVar.mo1802z();
            bgVar.m1169c(true);
            bgVar.f899d = cuVar.mo1799w();
            bgVar.m1173d(true);
            bgVar.f900e = cuVar.mo1799w();
            bgVar.m1176e(true);
            bgVar.f901f = cuVar.mo1799w();
            bgVar.m1180f(true);
            bgVar.f902g = cuVar.mo1762A();
            bgVar.m1182g(true);
            bgVar.f903h = cuVar.mo1802z();
            bgVar.m1183h(true);
            bgVar.f904i = cuVar.mo1802z();
            bgVar.m1186i(true);
            if (cuVar.mo1935b(1).get(0)) {
                bgVar.f905j = cuVar.mo1799w();
                bgVar.m1188j(true);
            }
        }
    }

    public /* synthetic */ bw mo1757b(int i) {
        return m1179f(i);
    }

    public /* synthetic */ bp mo1760p() {
        return m1154a();
    }

    static {
        f890w.put(cy.class, new C0644b());
        f890w.put(cz.class, new C0645d());
        Map enumMap = new EnumMap(C0646e.class);
        enumMap.put(C0646e.VERSION, new cb("version", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0646e.ADDRESS, new cb("address", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0646e.SIGNATURE, new cb("signature", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0646e.SERIAL_NUM, new cb("serial_num", (byte) 1, new cc((byte) 8)));
        enumMap.put(C0646e.TS_SECS, new cb("ts_secs", (byte) 1, new cc((byte) 8)));
        enumMap.put(C0646e.LENGTH, new cb("length", (byte) 1, new cc((byte) 8)));
        enumMap.put(C0646e.ENTITY, new cb("entity", (byte) 1, new cc((byte) 11, true)));
        enumMap.put(C0646e.GUID, new cb("guid", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0646e.CHECKSUM, new cb("checksum", (byte) 1, new cc((byte) 11)));
        enumMap.put(C0646e.CODEX, new cb("codex", (byte) 2, new cc((byte) 8)));
        f878k = Collections.unmodifiableMap(enumMap);
        cb.m426a(bg.class, f878k);
    }

    public bg() {
        this.f894B = (byte) 0;
        this.f895C = new C0646e[]{C0646e.CODEX};
    }

    public bg(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f896a = str;
        this.f897b = str2;
        this.f898c = str3;
        this.f899d = i;
        m1173d(true);
        this.f900e = i2;
        m1176e(true);
        this.f901f = i3;
        m1180f(true);
        this.f902g = byteBuffer;
        this.f903h = str4;
        this.f904i = str5;
    }

    public bg(bg bgVar) {
        this.f894B = (byte) 0;
        this.f895C = new C0646e[]{C0646e.CODEX};
        this.f894B = bgVar.f894B;
        if (bgVar.m1177e()) {
            this.f896a = bgVar.f896a;
        }
        if (bgVar.m1184h()) {
            this.f897b = bgVar.f897b;
        }
        if (bgVar.m1189k()) {
            this.f898c = bgVar.f898c;
        }
        this.f899d = bgVar.f899d;
        this.f900e = bgVar.f900e;
        this.f901f = bgVar.f901f;
        if (bgVar.m1203y()) {
            this.f902g = bq.m392d(bgVar.f902g);
        }
        if (bgVar.m1146B()) {
            this.f903h = bgVar.f903h;
        }
        if (bgVar.m1149E()) {
            this.f904i = bgVar.f904i;
        }
        this.f905j = bgVar.f905j;
    }

    public bg m1154a() {
        return new bg(this);
    }

    public void mo1758b() {
        this.f896a = null;
        this.f897b = null;
        this.f898c = null;
        m1173d(false);
        this.f899d = 0;
        m1176e(false);
        this.f900e = 0;
        m1180f(false);
        this.f901f = 0;
        this.f902g = null;
        this.f903h = null;
        this.f904i = null;
        m1188j(false);
        this.f905j = 0;
    }

    public String m1166c() {
        return this.f896a;
    }

    public bg m1156a(String str) {
        this.f896a = str;
        return this;
    }

    public void m1172d() {
        this.f896a = null;
    }

    public boolean m1177e() {
        return this.f896a != null;
    }

    public void m1160a(boolean z) {
        if (!z) {
            this.f896a = null;
        }
    }

    public String m1178f() {
        return this.f897b;
    }

    public bg m1161b(String str) {
        this.f897b = str;
        return this;
    }

    public void m1181g() {
        this.f897b = null;
    }

    public boolean m1184h() {
        return this.f897b != null;
    }

    public void m1165b(boolean z) {
        if (!z) {
            this.f897b = null;
        }
    }

    public String m1185i() {
        return this.f898c;
    }

    public bg m1168c(String str) {
        this.f898c = str;
        return this;
    }

    public void m1187j() {
        this.f898c = null;
    }

    public boolean m1189k() {
        return this.f898c != null;
    }

    public void m1169c(boolean z) {
        if (!z) {
            this.f898c = null;
        }
    }

    public int m1190l() {
        return this.f899d;
    }

    public bg m1155a(int i) {
        this.f899d = i;
        m1173d(true);
        return this;
    }

    public void m1191m() {
        this.f894B = bm.m358b(this.f894B, 0);
    }

    public boolean m1192n() {
        return bm.m354a(this.f894B, 0);
    }

    public void m1173d(boolean z) {
        this.f894B = bm.m346a(this.f894B, 0, z);
    }

    public int m1193o() {
        return this.f900e;
    }

    public bg m1167c(int i) {
        this.f900e = i;
        m1176e(true);
        return this;
    }

    public void m1195q() {
        this.f894B = bm.m358b(this.f894B, 1);
    }

    public boolean m1196r() {
        return bm.m354a(this.f894B, 1);
    }

    public void m1176e(boolean z) {
        this.f894B = bm.m346a(this.f894B, 1, z);
    }

    public int m1197s() {
        return this.f901f;
    }

    public bg m1170d(int i) {
        this.f901f = i;
        m1180f(true);
        return this;
    }

    public void m1198t() {
        this.f894B = bm.m358b(this.f894B, 2);
    }

    public boolean m1199u() {
        return bm.m354a(this.f894B, 2);
    }

    public void m1180f(boolean z) {
        this.f894B = bm.m346a(this.f894B, 2, z);
    }

    public byte[] m1200v() {
        m1157a(bq.m391c(this.f902g));
        return this.f902g == null ? null : this.f902g.array();
    }

    public ByteBuffer m1201w() {
        return this.f902g;
    }

    public bg m1158a(byte[] bArr) {
        m1157a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bg m1157a(ByteBuffer byteBuffer) {
        this.f902g = byteBuffer;
        return this;
    }

    public void m1202x() {
        this.f902g = null;
    }

    public boolean m1203y() {
        return this.f902g != null;
    }

    public void m1182g(boolean z) {
        if (!z) {
            this.f902g = null;
        }
    }

    public String m1204z() {
        return this.f903h;
    }

    public bg m1171d(String str) {
        this.f903h = str;
        return this;
    }

    public void m1145A() {
        this.f903h = null;
    }

    public boolean m1146B() {
        return this.f903h != null;
    }

    public void m1183h(boolean z) {
        if (!z) {
            this.f903h = null;
        }
    }

    public String m1147C() {
        return this.f904i;
    }

    public bg m1175e(String str) {
        this.f904i = str;
        return this;
    }

    public void m1148D() {
        this.f904i = null;
    }

    public boolean m1149E() {
        return this.f904i != null;
    }

    public void m1186i(boolean z) {
        if (!z) {
            this.f904i = null;
        }
    }

    public int m1150F() {
        return this.f905j;
    }

    public bg m1174e(int i) {
        this.f905j = i;
        m1188j(true);
        return this;
    }

    public void m1151G() {
        this.f894B = bm.m358b(this.f894B, 3);
    }

    public boolean m1152H() {
        return bm.m354a(this.f894B, 3);
    }

    public void m1188j(boolean z) {
        this.f894B = bm.m346a(this.f894B, 3, z);
    }

    public C0646e m1179f(int i) {
        return C0646e.m1127a(i);
    }

    public void mo1756a(co coVar) throws bv {
        ((cx) f890w.get(coVar.mo1934D())).mo1753b().mo1933b(coVar, this);
    }

    public void mo1759b(co coVar) throws bv {
        ((cx) f890w.get(coVar.mo1934D())).mo1753b().mo1932a(coVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.f896a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f896a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.f897b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f897b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.f898c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f898c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.f899d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.f900e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f901f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.f902g == null) {
            stringBuilder.append("null");
        } else {
            bq.m387a(this.f902g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.f903h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f903h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.f904i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f904i);
        }
        if (m1152H()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.f905j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m1153I() throws bv {
        if (this.f896a == null) {
            throw new cp("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f897b == null) {
            throw new cp("Required field 'address' was not present! Struct: " + toString());
        } else if (this.f898c == null) {
            throw new cp("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.f902g == null) {
            throw new cp("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.f903h == null) {
            throw new cp("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.f904i == null) {
            throw new cp("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    private void m1144a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo1759b(new ci(new da((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    private void m1143a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f894B = (byte) 0;
            mo1756a(new ci(new da((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
