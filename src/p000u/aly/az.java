package p000u.aly;

/* compiled from: Gender */
public enum az implements bt {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);
    
    private final int f748d;

    private az(int i) {
        this.f748d = i;
    }

    public int mo1752a() {
        return this.f748d;
    }

    public static az m880a(int i) {
        switch (i) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            case 2:
                return UNKNOWN;
            default:
                return null;
        }
    }
}
