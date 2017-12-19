package p000u.aly;

/* compiled from: TTransport */
public abstract class dc {
    public abstract int mo1804a(byte[] bArr, int i, int i2) throws dd;

    public abstract boolean mo1805a();

    public abstract void mo1806b() throws dd;

    public abstract void mo1807b(byte[] bArr, int i, int i2) throws dd;

    public abstract void mo1808c();

    public boolean m502i() {
        return mo1805a();
    }

    public int m497d(byte[] bArr, int i, int i2) throws dd {
        int i3 = 0;
        while (i3 < i2) {
            int a = mo1804a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new dd("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
            i3 += a;
        }
        return i3;
    }

    public void m494b(byte[] bArr) throws dd {
        mo1807b(bArr, 0, bArr.length);
    }

    public void mo1809d() throws dd {
    }

    public byte[] mo1811f() {
        return null;
    }

    public int mo1812g() {
        return 0;
    }

    public int mo1813h() {
        return -1;
    }

    public void mo1810a(int i) {
    }
}
