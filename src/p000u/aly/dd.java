package p000u.aly;

/* compiled from: TTransportException */
public class dd extends bv {
    public static final int f977a = 0;
    public static final int f978b = 1;
    public static final int f979c = 2;
    public static final int f980d = 3;
    public static final int f981e = 4;
    private static final long f982g = 1;
    protected int f983f = 0;

    public dd(int i) {
        this.f983f = i;
    }

    public dd(int i, String str) {
        super(str);
        this.f983f = i;
    }

    public dd(String str) {
        super(str);
    }

    public dd(int i, Throwable th) {
        super(th);
        this.f983f = i;
    }

    public dd(Throwable th) {
        super(th);
    }

    public dd(String str, Throwable th) {
        super(str, th);
    }

    public dd(int i, String str, Throwable th) {
        super(str, th);
        this.f983f = i;
    }

    public int m1365a() {
        return this.f983f;
    }
}
