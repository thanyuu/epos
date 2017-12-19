package p000u.aly;

import android.os.Build;
import android.os.Build.VERSION;

/* compiled from: SerialTracker */
public class C0658z extends C0379r {
    private static final String f995a = "serial";

    public C0658z() {
        super(f995a);
    }

    public String mo1737f() {
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }
}
