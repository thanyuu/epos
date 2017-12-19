package p000u.aly;

/* compiled from: TProtocolException */
public class cp extends bv {
    public static final int f964a = 0;
    public static final int f965b = 1;
    public static final int f966c = 2;
    public static final int f967d = 3;
    public static final int f968e = 4;
    public static final int f969f = 5;
    private static final long f970h = 1;
    protected int f971g = 0;

    public cp(int i) {
        this.f971g = i;
    }

    public cp(int i, String str) {
        super(str);
        this.f971g = i;
    }

    public cp(String str) {
        super(str);
    }

    public cp(int i, Throwable th) {
        super(th);
        this.f971g = i;
    }

    public cp(Throwable th) {
        super(th);
    }

    public cp(String str, Throwable th) {
        super(str, th);
    }

    public cp(int i, String str, Throwable th) {
        super(str, th);
        this.f971g = i;
    }

    public int m1345a() {
        return this.f971g;
    }
}
