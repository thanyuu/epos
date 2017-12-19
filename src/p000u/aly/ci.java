package p000u.aly;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TCompactProtocol */
public class ci extends co {
    private static final ct f947d = new ct("");
    private static final cj f948e = new cj("", (byte) 0, (short) 0);
    private static final byte[] f949f = new byte[16];
    private static final byte f950h = (byte) -126;
    private static final byte f951i = (byte) 1;
    private static final byte f952j = (byte) 31;
    private static final byte f953k = (byte) -32;
    private static final int f954l = 5;
    byte[] f955a;
    byte[] f956b;
    byte[] f957c;
    private bn f958m;
    private short f959n;
    private cj f960o;
    private Boolean f961p;
    private final long f962q;
    private byte[] f963r;

    /* compiled from: TCompactProtocol */
    private static class C0349b {
        public static final byte f380a = (byte) 1;
        public static final byte f381b = (byte) 2;
        public static final byte f382c = (byte) 3;
        public static final byte f383d = (byte) 4;
        public static final byte f384e = (byte) 5;
        public static final byte f385f = (byte) 6;
        public static final byte f386g = (byte) 7;
        public static final byte f387h = (byte) 8;
        public static final byte f388i = (byte) 9;
        public static final byte f389j = (byte) 10;
        public static final byte f390k = (byte) 11;
        public static final byte f391l = (byte) 12;

        private C0349b() {
        }
    }

    /* compiled from: TCompactProtocol */
    public static class C0650a implements cq {
        private final long f946a;

        public C0650a() {
            this.f946a = -1;
        }

        public C0650a(int i) {
            this.f946a = (long) i;
        }

        public co mo1761a(dc dcVar) {
            return new ci(dcVar, this.f946a);
        }
    }

    static {
        f949f[0] = (byte) 0;
        f949f[2] = (byte) 1;
        f949f[3] = (byte) 3;
        f949f[6] = (byte) 4;
        f949f[8] = (byte) 5;
        f949f[10] = (byte) 6;
        f949f[4] = (byte) 7;
        f949f[11] = (byte) 8;
        f949f[15] = (byte) 9;
        f949f[14] = (byte) 10;
        f949f[13] = (byte) 11;
        f949f[12] = (byte) 12;
    }

    public ci(dc dcVar, long j) {
        super(dcVar);
        this.f958m = new bn(15);
        this.f959n = (short) 0;
        this.f960o = null;
        this.f961p = null;
        this.f955a = new byte[5];
        this.f956b = new byte[10];
        this.f963r = new byte[1];
        this.f957c = new byte[1];
        this.f962q = j;
    }

    public ci(dc dcVar) {
        this(dcVar, -1);
    }

    public void mo1803B() {
        this.f958m.m366c();
        this.f959n = (short) 0;
    }

    public void mo1773a(cm cmVar) throws bv {
        m1289b((byte) f950h);
        m1297d(((cmVar.f401b << 5) & -32) | 1);
        mo1935b(cmVar.f402c);
        mo1768a(cmVar.f400a);
    }

    public void mo1775a(ct ctVar) throws bv {
        this.f958m.m364a(this.f959n);
        this.f959n = (short) 0;
    }

    public void mo1778b() throws bv {
        this.f959n = this.f958m.m363a();
    }

    public void mo1770a(cj cjVar) throws bv {
        if (cjVar.f393b == (byte) 2) {
            this.f960o = cjVar;
        } else {
            m1287a(cjVar, (byte) -1);
        }
    }

    private void m1287a(cj cjVar, byte b) throws bv {
        if (b == (byte) -1) {
            b = m1298e(cjVar.f393b);
        }
        if (cjVar.f394c <= this.f959n || cjVar.f394c - this.f959n > 15) {
            m1289b(b);
            mo1776a(cjVar.f394c);
        } else {
            m1297d(((cjVar.f394c - this.f959n) << 4) | b);
        }
        this.f959n = cjVar.f394c;
    }

    public void mo1780d() throws bv {
        m1289b((byte) 0);
    }

    public void mo1772a(cl clVar) throws bv {
        if (clVar.f399c == 0) {
            m1297d(0);
            return;
        }
        mo1935b(clVar.f399c);
        m1297d((m1298e(clVar.f397a) << 4) | m1298e(clVar.f398b));
    }

    public void mo1771a(ck ckVar) throws bv {
        m1306a(ckVar.f395a, ckVar.f396b);
    }

    public void mo1774a(cs csVar) throws bv {
        m1306a(csVar.f409a, csVar.f410b);
    }

    public void mo1777a(boolean z) throws bv {
        byte b = (byte) 1;
        if (this.f960o != null) {
            cj cjVar = this.f960o;
            if (!z) {
                b = (byte) 2;
            }
            m1287a(cjVar, b);
            this.f960o = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        m1289b(b);
    }

    public void mo1764a(byte b) throws bv {
        m1289b(b);
    }

    public void mo1776a(short s) throws bv {
        mo1935b(m1292c((int) s));
    }

    public void mo1766a(int i) throws bv {
        mo1935b(m1292c(i));
    }

    public void mo1767a(long j) throws bv {
        m1291b(m1293c(j));
    }

    public void mo1765a(double d) throws bv {
        byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        m1286a(Double.doubleToLongBits(d), bArr, 0);
        this.g.m494b(bArr);
    }

    public void mo1768a(String str) throws bv {
        try {
            byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
            m1288a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new bv("UTF-8 not supported!");
        }
    }

    public void mo1769a(ByteBuffer byteBuffer) throws bv {
        m1288a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void m1288a(byte[] bArr, int i, int i2) throws bv {
        mo1935b(i2);
        this.g.mo1807b(bArr, i, i2);
    }

    public void mo1763a() throws bv {
    }

    public void mo1781e() throws bv {
    }

    public void mo1782f() throws bv {
    }

    public void mo1783g() throws bv {
    }

    public void mo1779c() throws bv {
    }

    protected void m1306a(byte b, int i) throws bv {
        if (i <= 14) {
            m1297d((i << 4) | m1298e(b));
            return;
        }
        m1297d(m1298e(b) | 240);
        mo1935b(i);
    }

    private void mo1935b(int i) throws bv {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.f955a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.f955a[i2] = (byte) i;
        this.g.mo1807b(this.f955a, 0, i4);
    }

    private void m1291b(long j) throws bv {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.f956b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.f956b[i] = (byte) ((int) j);
        this.g.mo1807b(this.f956b, 0, i3);
    }

    private long m1293c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private int m1292c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private void m1286a(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    private void m1289b(byte b) throws bv {
        this.f963r[0] = b;
        this.g.m494b(this.f963r);
    }

    private void m1297d(int i) throws bv {
        m1289b((byte) i);
    }

    public cm mo1784h() throws bv {
        byte u = mo1797u();
        if (u != f950h) {
            throw new cp("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
        }
        u = mo1797u();
        byte b = (byte) (u & 31);
        if (b != (byte) 1) {
            throw new cp("Expected version 1 but got " + b);
        }
        return new cm(mo1802z(), (byte) ((u >> 5) & 3), m1283E());
    }

    public ct mo1786j() throws bv {
        this.f958m.m364a(this.f959n);
        this.f959n = (short) 0;
        return f947d;
    }

    public void mo1787k() throws bv {
        this.f959n = this.f958m.m363a();
    }

    public cj mo1788l() throws bv {
        byte u = mo1797u();
        if (u == (byte) 0) {
            return f948e;
        }
        short s = (short) ((u & 240) >> 4);
        if (s == (short) 0) {
            s = mo1798v();
        } else {
            s = (short) (s + this.f959n);
        }
        cj cjVar = new cj("", m1295d((byte) (u & 15)), s);
        if (m1294c(u)) {
            this.f961p = ((byte) (u & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f959n = cjVar.f394c;
        return cjVar;
    }

    public cl mo1790n() throws bv {
        int E = m1283E();
        int u = E == 0 ? 0 : mo1797u();
        return new cl(m1295d((byte) (u >> 4)), m1295d((byte) (u & 15)), E);
    }

    public ck mo1792p() throws bv {
        byte u = mo1797u();
        int i = (u >> 4) & 15;
        if (i == 15) {
            i = m1283E();
        }
        return new ck(m1295d(u), i);
    }

    public cs mo1794r() throws bv {
        return new cs(mo1792p());
    }

    public boolean mo1796t() throws bv {
        if (this.f961p != null) {
            boolean booleanValue = this.f961p.booleanValue();
            this.f961p = null;
            return booleanValue;
        } else if (mo1797u() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    public byte mo1797u() throws bv {
        if (this.g.mo1813h() > 0) {
            byte b = this.g.mo1811f()[this.g.mo1812g()];
            this.g.mo1810a(1);
            return b;
        }
        this.g.m497d(this.f957c, 0, 1);
        return this.f957c[0];
    }

    public short mo1798v() throws bv {
        return (short) m1301g(m1283E());
    }

    public int mo1799w() throws bv {
        return m1301g(m1283E());
    }

    public long mo1800x() throws bv {
        return m1296d(m1284F());
    }

    public double mo1801y() throws bv {
        byte[] bArr = new byte[8];
        this.g.m497d(bArr, 0, 8);
        return Double.longBitsToDouble(m1285a(bArr));
    }

    public String mo1802z() throws bv {
        int E = m1283E();
        m1300f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.g.mo1813h() < E) {
                return new String(m1299e(E), Key.STRING_CHARSET_NAME);
            }
            String str = new String(this.g.mo1811f(), this.g.mo1812g(), E, Key.STRING_CHARSET_NAME);
            this.g.mo1810a(E);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new bv("UTF-8 not supported!");
        }
    }

    public ByteBuffer mo1762A() throws bv {
        int E = m1283E();
        m1300f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.g.m497d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] m1299e(int i) throws bv {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.g.m497d(bArr, 0, i);
        return bArr;
    }

    private void m1300f(int i) throws cp {
        if (i < 0) {
            throw new cp("Negative length: " + i);
        } else if (this.f962q != -1 && ((long) i) > this.f962q) {
            throw new cp("Length exceeded max allowed: " + i);
        }
    }

    public void mo1785i() throws bv {
    }

    public void mo1789m() throws bv {
    }

    public void mo1791o() throws bv {
    }

    public void mo1793q() throws bv {
    }

    public void mo1795s() throws bv {
    }

    private int m1283E() throws bv {
        int i = 0;
        int i2;
        if (this.g.mo1813h() >= 5) {
            byte[] f = this.g.mo1811f();
            int g = this.g.mo1812g();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = f[g + i];
                i3 |= (b & 127) << i2;
                if ((b & 128) != 128) {
                    this.g.mo1810a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte u = mo1797u();
                i2 |= (u & 127) << i;
                if ((u & 128) != 128) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    private long m1284F() throws bv {
        long j = null;
        long j2 = 0;
        if (this.g.mo1813h() >= 10) {
            int i;
            byte[] f = this.g.mo1811f();
            int g = this.g.mo1812g();
            long j3 = 0;
            while (true) {
                byte b = f[g + i];
                j2 |= ((long) (b & 127)) << j3;
                if ((b & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.g.mo1810a(i + 1);
        } else {
            while (true) {
                byte u = mo1797u();
                j2 |= ((long) (u & 127)) << j;
                if ((u & 128) != 128) {
                    break;
                }
                j += 7;
            }
        }
        return j2;
    }

    private int m1301g(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private long m1296d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private long m1285a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private boolean m1294c(byte b) {
        int i = b & 15;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    private byte m1295d(byte b) throws cp {
        switch ((byte) (b & 15)) {
            case (byte) 0:
                return (byte) 0;
            case (byte) 1:
            case (byte) 2:
                return (byte) 2;
            case (byte) 3:
                return (byte) 3;
            case (byte) 4:
                return (byte) 6;
            case (byte) 5:
                return (byte) 8;
            case (byte) 6:
                return (byte) 10;
            case (byte) 7:
                return (byte) 4;
            case (byte) 8:
                return (byte) 11;
            case (byte) 9:
                return cv.f424m;
            case (byte) 10:
                return cv.f423l;
            case (byte) 11:
                return cv.f422k;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new cp("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte m1298e(byte b) {
        return f949f[b];
    }
}
