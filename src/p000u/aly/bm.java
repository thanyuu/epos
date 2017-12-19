package p000u.aly;

/* compiled from: EncodingUtils */
public class bm {
    public static final void m352a(int i, byte[] bArr) {
        bm.m353a(i, bArr, 0);
    }

    public static final void m353a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) ((i >> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
    }

    public static final int m348a(byte[] bArr) {
        return bm.m349a(bArr, 0);
    }

    public static final int m349a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8)) | (bArr[i + 3] & 255);
    }

    public static final boolean m354a(byte b, int i) {
        return bm.m355a((int) b, i);
    }

    public static final boolean m357a(short s, int i) {
        return bm.m355a((int) s, i);
    }

    public static final boolean m355a(int i, int i2) {
        return ((1 << i2) & i) != 0;
    }

    public static final boolean m356a(long j, int i) {
        return ((1 << i) & j) != 0;
    }

    public static final byte m358b(byte b, int i) {
        return (byte) bm.m359b((int) b, i);
    }

    public static final short m361b(short s, int i) {
        return (short) bm.m359b((int) s, i);
    }

    public static final int m359b(int i, int i2) {
        return ((1 << i2) ^ -1) & i;
    }

    public static final long m360b(long j, int i) {
        return ((1 << i) ^ -1) & j;
    }

    public static final byte m346a(byte b, int i, boolean z) {
        return (byte) bm.m347a((int) b, i, z);
    }

    public static final short m351a(short s, int i, boolean z) {
        return (short) bm.m347a((int) s, i, z);
    }

    public static final int m347a(int i, int i2, boolean z) {
        if (z) {
            return (1 << i2) | i;
        }
        return bm.m359b(i, i2);
    }

    public static final long m350a(long j, int i, boolean z) {
        if (z) {
            return (1 << i) | j;
        }
        return bm.m360b(j, i);
    }
}
