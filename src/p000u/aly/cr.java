package p000u.aly;

import p000u.aly.ci.C0650a;

/* compiled from: TProtocolUtil */
public class cr {
    private static int f408a = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    public static void m480a(int i) {
        f408a = i;
    }

    public static void m481a(co coVar, byte b) throws bv {
        cr.m482a(coVar, b, f408a);
    }

    public static void m482a(co coVar, byte b, int i) throws bv {
        int i2 = 0;
        if (i <= 0) {
            throw new bv("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                coVar.mo1796t();
                return;
            case (byte) 3:
                coVar.mo1797u();
                return;
            case (byte) 4:
                coVar.mo1801y();
                return;
            case (byte) 6:
                coVar.mo1798v();
                return;
            case (byte) 8:
                coVar.mo1799w();
                return;
            case (byte) 10:
                coVar.mo1800x();
                return;
            case (byte) 11:
                coVar.mo1762A();
                return;
            case (byte) 12:
                coVar.mo1786j();
                while (true) {
                    cj l = coVar.mo1788l();
                    if (l.f393b == (byte) 0) {
                        coVar.mo1787k();
                        return;
                    } else {
                        cr.m482a(coVar, l.f393b, i - 1);
                        coVar.mo1789m();
                    }
                }
            case (byte) 13:
                cl n = coVar.mo1790n();
                while (i2 < n.f399c) {
                    cr.m482a(coVar, n.f397a, i - 1);
                    cr.m482a(coVar, n.f398b, i - 1);
                    i2++;
                }
                coVar.mo1791o();
                return;
            case (byte) 14:
                cs r = coVar.mo1794r();
                while (i2 < r.f410b) {
                    cr.m482a(coVar, r.f409a, i - 1);
                    i2++;
                }
                coVar.mo1795s();
                return;
            case (byte) 15:
                ck p = coVar.mo1792p();
                while (i2 < p.f396b) {
                    cr.m482a(coVar, p.f395a, i - 1);
                    i2++;
                }
                coVar.mo1793q();
                return;
            default:
                return;
        }
    }

    public static cq m479a(byte[] bArr, cq cqVar) {
        if (bArr[0] > cv.f425n) {
            return new C0650a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return cqVar;
        }
        return new C0650a();
    }
}
