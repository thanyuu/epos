package p000u.aly;

/* compiled from: TMemoryInputTransport */
public final class db extends dc {
    private byte[] f974a;
    private int f975b;
    private int f976c;

    public db(byte[] bArr) {
        m1355a(bArr);
    }

    public db(byte[] bArr, int i, int i2) {
        m1360c(bArr, i, i2);
    }

    public void m1355a(byte[] bArr) {
        m1360c(bArr, 0, bArr.length);
    }

    public void m1360c(byte[] bArr, int i, int i2) {
        this.f974a = bArr;
        this.f975b = i;
        this.f976c = i + i2;
    }

    public void m1361e() {
        this.f974a = null;
    }

    public void mo1808c() {
    }

    public boolean mo1805a() {
        return true;
    }

    public void mo1806b() throws dd {
    }

    public int mo1804a(byte[] bArr, int i, int i2) throws dd {
        int h = mo1813h();
        if (i2 > h) {
            i2 = h;
        }
        if (i2 > 0) {
            System.arraycopy(this.f974a, this.f975b, bArr, i, i2);
            mo1810a(i2);
        }
        return i2;
    }

    public void mo1807b(byte[] bArr, int i, int i2) throws dd {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public byte[] mo1811f() {
        return this.f974a;
    }

    public int mo1812g() {
        return this.f975b;
    }

    public int mo1813h() {
        return this.f976c - this.f975b;
    }

    public void mo1810a(int i) {
        this.f975b += i;
    }
}
