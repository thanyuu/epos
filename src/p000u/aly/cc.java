package p000u.aly;

import java.io.Serializable;

/* compiled from: FieldValueMetaData */
public class cc implements Serializable {
    private final boolean f376a;
    public final byte f377b;
    private final String f378c;
    private final boolean f379d;

    public cc(byte b, boolean z) {
        this.f377b = b;
        this.f376a = false;
        this.f378c = null;
        this.f379d = z;
    }

    public cc(byte b) {
        this(b, false);
    }

    public cc(byte b, String str) {
        this.f377b = b;
        this.f376a = true;
        this.f378c = str;
        this.f379d = false;
    }

    public boolean m427a() {
        return this.f376a;
    }

    public String m428b() {
        return this.f378c;
    }

    public boolean m429c() {
        return this.f377b == (byte) 12;
    }

    public boolean m430d() {
        return this.f377b == cv.f424m || this.f377b == cv.f422k || this.f377b == cv.f423l;
    }

    public boolean m431e() {
        return this.f379d;
    }
}
