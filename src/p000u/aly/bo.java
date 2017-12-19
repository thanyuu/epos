package p000u.aly;

import com.android.common.constant.DbConstants;

/* compiled from: TApplicationException */
public class bo extends bv {
    public static final int f906a = 0;
    public static final int f907b = 1;
    public static final int f908c = 2;
    public static final int f909d = 3;
    public static final int f910e = 4;
    public static final int f911f = 5;
    public static final int f912g = 6;
    public static final int f913h = 7;
    private static final ct f914j = new ct("TApplicationException");
    private static final cj f915k = new cj("message", (byte) 11, (short) 1);
    private static final cj f916l = new cj(DbConstants.HTTP_CACHE_TABLE_TYPE, (byte) 8, (short) 2);
    private static final long f917m = 1;
    protected int f918i = 0;

    public bo(int i) {
        this.f918i = i;
    }

    public bo(int i, String str) {
        super(str);
        this.f918i = i;
    }

    public bo(String str) {
        super(str);
    }

    public int m1206a() {
        return this.f918i;
    }

    public static bo m1205a(co coVar) throws bv {
        coVar.mo1786j();
        String str = null;
        int i = 0;
        while (true) {
            cj l = coVar.mo1788l();
            if (l.f393b == (byte) 0) {
                coVar.mo1787k();
                return new bo(i, str);
            }
            switch (l.f394c) {
                case (short) 1:
                    if (l.f393b != (byte) 11) {
                        cr.m481a(coVar, l.f393b);
                        break;
                    }
                    str = coVar.mo1802z();
                    break;
                case (short) 2:
                    if (l.f393b != (byte) 8) {
                        cr.m481a(coVar, l.f393b);
                        break;
                    }
                    i = coVar.mo1799w();
                    break;
                default:
                    cr.m481a(coVar, l.f393b);
                    break;
            }
            coVar.mo1789m();
        }
    }

    public void m1207b(co coVar) throws bv {
        coVar.mo1775a(f914j);
        if (getMessage() != null) {
            coVar.mo1770a(f915k);
            coVar.mo1768a(getMessage());
            coVar.mo1779c();
        }
        coVar.mo1770a(f916l);
        coVar.mo1766a(this.f918i);
        coVar.mo1779c();
        coVar.mo1780d();
        coVar.mo1778b();
    }
}
