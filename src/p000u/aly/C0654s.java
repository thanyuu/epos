package p000u.aly;

import android.content.Context;
import android.provider.Settings.Secure;

/* compiled from: AndroidIdTracker */
public class C0654s extends C0379r {
    private static final String f987a = "android_id";
    private Context f988b;

    public C0654s(Context context) {
        super(f987a);
        this.f988b = context;
    }

    public String mo1737f() {
        String str = null;
        try {
            str = Secure.getString(this.f988b.getContentResolver(), f987a);
        } catch (Exception e) {
        }
        return str;
    }
}
