package p000u.aly;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol */
public class ch extends co {
    protected static final int f931a = -65536;
    protected static final int f932b = -2147418112;
    private static final ct f933h = new ct();
    protected boolean f934c;
    protected boolean f935d;
    protected int f936e;
    protected boolean f937f;
    private byte[] f938i;
    private byte[] f939j;
    private byte[] f940k;
    private byte[] f941l;
    private byte[] f942m;
    private byte[] f943n;
    private byte[] f944o;
    private byte[] f945p;

    /* compiled from: TBinaryProtocol */
    public static class C0649a implements cq {
        protected boolean f928a;
        protected boolean f929b;
        protected int f930c;

        public C0649a() {
            this(false, true);
        }

        public C0649a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public C0649a(boolean z, boolean z2, int i) {
            this.f928a = false;
            this.f929b = true;
            this.f928a = z;
            this.f929b = z2;
            this.f930c = i;
        }

        public co mo1761a(dc dcVar) {
            co chVar = new ch(dcVar, this.f928a, this.f929b);
            if (this.f930c != 0) {
                chVar.m1257c(this.f930c);
            }
            return chVar;
        }
    }

    public ch(dc dcVar) {
        this(dcVar, false, true);
    }

    public ch(dc dcVar, boolean z, boolean z2) {
        super(dcVar);
        this.f934c = false;
        this.f935d = true;
        this.f937f = false;
        this.f938i = new byte[1];
        this.f939j = new byte[2];
        this.f940k = new byte[4];
        this.f941l = new byte[8];
        this.f942m = new byte[1];
        this.f943n = new byte[2];
        this.f944o = new byte[4];
        this.f945p = new byte[8];
        this.f934c = z;
        this.f935d = z2;
    }

    public void mo1773a(cm cmVar) throws bv {
        if (this.f935d) {
            mo1766a(f932b | cmVar.f401b);
            mo1768a(cmVar.f400a);
            mo1766a(cmVar.f402c);
            return;
        }
        mo1768a(cmVar.f400a);
        mo1764a(cmVar.f401b);
        mo1766a(cmVar.f402c);
    }

    public void mo1763a() {
    }

    public void mo1775a(ct ctVar) {
    }

    public void mo1778b() {
    }

    public void mo1770a(cj cjVar) throws bv {
        mo1764a(cjVar.f393b);
        mo1776a(cjVar.f394c);
    }

    public void mo1779c() {
    }

    public void mo1780d() throws bv {
        mo1764a((byte) 0);
    }

    public void mo1772a(cl clVar) throws bv {
        mo1764a(clVar.f397a);
        mo1764a(clVar.f398b);
        mo1766a(clVar.f399c);
    }

    public void mo1781e() {
    }

    public void mo1771a(ck ckVar) throws bv {
        mo1764a(ckVar.f395a);
        mo1766a(ckVar.f396b);
    }

    public void mo1782f() {
    }

    public void mo1774a(cs csVar) throws bv {
        mo1764a(csVar.f409a);
        mo1766a(csVar.f410b);
    }

    public void mo1783g() {
    }

    public void mo1777a(boolean z) throws bv {
        mo1764a(z ? (byte) 1 : (byte) 0);
    }

    public void mo1764a(byte b) throws bv {
        this.f938i[0] = b;
        this.g.mo1807b(this.f938i, 0, 1);
    }

    public void mo1776a(short s) throws bv {
        this.f939j[0] = (byte) ((s >> 8) & 255);
        this.f939j[1] = (byte) (s & 255);
        this.g.mo1807b(this.f939j, 0, 2);
    }

    public void mo1766a(int i) throws bv {
        this.f940k[0] = (byte) ((i >> 24) & 255);
        this.f940k[1] = (byte) ((i >> 16) & 255);
        this.f940k[2] = (byte) ((i >> 8) & 255);
        this.f940k[3] = (byte) (i & 255);
        this.g.mo1807b(this.f940k, 0, 4);
    }

    public void mo1767a(long j) throws bv {
        this.f941l[0] = (byte) ((int) ((j >> 56) & 255));
        this.f941l[1] = (byte) ((int) ((j >> 48) & 255));
        this.f941l[2] = (byte) ((int) ((j >> 40) & 255));
        this.f941l[3] = (byte) ((int) ((j >> 32) & 255));
        this.f941l[4] = (byte) ((int) ((j >> 24) & 255));
        this.f941l[5] = (byte) ((int) ((j >> 16) & 255));
        this.f941l[6] = (byte) ((int) ((j >> 8) & 255));
        this.f941l[7] = (byte) ((int) (255 & j));
        this.g.mo1807b(this.f941l, 0, 8);
    }

    public void mo1765a(double d) throws bv {
        mo1767a(Double.doubleToLongBits(d));
    }

    public void mo1768a(String str) throws bv {
        try {
            byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
            mo1766a(bytes.length);
            this.g.mo1807b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void mo1769a(ByteBuffer byteBuffer) throws bv {
        int limit = byteBuffer.limit() - byteBuffer.position();
        mo1766a(limit);
        this.g.mo1807b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public cm mo1784h() throws bv {
        int w = mo1799w();
        if (w < 0) {
            if ((-65536 & w) == f932b) {
                return new cm(mo1802z(), (byte) (w & 255), mo1799w());
            }
            throw new cp(4, "Bad version in readMessageBegin");
        } else if (!this.f934c) {
            return new cm(m1254b(w), mo1797u(), mo1799w());
        } else {
            throw new cp(4, "Missing version in readMessageBegin, old client?");
        }
    }

    public void mo1785i() {
    }

    public ct mo1786j() {
        return f933h;
    }

    public void mo1787k() {
    }

    public cj mo1788l() throws bv {
        byte u = mo1797u();
        return new cj("", u, u == (byte) 0 ? (short) 0 : mo1798v());
    }

    public void mo1789m() {
    }

    public cl mo1790n() throws bv {
        return new cl(mo1797u(), mo1797u(), mo1799w());
    }

    public void mo1791o() {
    }

    public ck mo1792p() throws bv {
        return new ck(mo1797u(), mo1799w());
    }

    public void mo1793q() {
    }

    public cs mo1794r() throws bv {
        return new cs(mo1797u(), mo1799w());
    }

    public void mo1795s() {
    }

    public boolean mo1796t() throws bv {
        return mo1797u() == (byte) 1;
    }

    public byte mo1797u() throws bv {
        if (this.g.mo1813h() >= 1) {
            byte b = this.g.mo1811f()[this.g.mo1812g()];
            this.g.mo1810a(1);
            return b;
        }
        m1237a(this.f942m, 0, 1);
        return this.f942m[0];
    }

    public short mo1798v() throws bv {
        int i = 0;
        byte[] bArr = this.f943n;
        if (this.g.mo1813h() >= 2) {
            bArr = this.g.mo1811f();
            i = this.g.mo1812g();
            this.g.mo1810a(2);
        } else {
            m1237a(this.f943n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int mo1799w() throws bv {
        int i = 0;
        byte[] bArr = this.f944o;
        if (this.g.mo1813h() >= 4) {
            bArr = this.g.mo1811f();
            i = this.g.mo1812g();
            this.g.mo1810a(4);
        } else {
            m1237a(this.f944o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public long mo1800x() throws bv {
        int i = 0;
        byte[] bArr = this.f945p;
        if (this.g.mo1813h() >= 8) {
            bArr = this.g.mo1811f();
            i = this.g.mo1812g();
            this.g.mo1810a(8);
        } else {
            m1237a(this.f945p, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public double mo1801y() throws bv {
        return Double.longBitsToDouble(mo1800x());
    }

    public String mo1802z() throws bv {
        int w = mo1799w();
        if (this.g.mo1813h() < w) {
            return m1254b(w);
        }
        try {
            String str = new String(this.g.mo1811f(), this.g.mo1812g(), w, Key.STRING_CHARSET_NAME);
            this.g.mo1810a(w);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String m1254b(int i) throws bv {
        try {
            m1259d(i);
            byte[] bArr = new byte[i];
            this.g.m497d(bArr, 0, i);
            return new String(bArr, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer mo1762A() throws bv {
        int w = mo1799w();
        m1259d(w);
        if (this.g.mo1813h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.mo1811f(), this.g.mo1812g(), w);
            this.g.mo1810a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.m497d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int m1237a(byte[] bArr, int i, int i2) throws bv {
        m1259d(i2);
        return this.g.m497d(bArr, i, i2);
    }

    public void m1257c(int i) {
        this.f936e = i;
        this.f937f = true;
    }

    protected void m1259d(int i) throws bv {
        if (i < 0) {
            throw new cp("Negative length: " + i);
        } else if (this.f937f) {
            this.f936e -= i;
            if (this.f936e < 0) {
                throw new cp("Message length exceeded: " + i);
            }
        }
    }
}
