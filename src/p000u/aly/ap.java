package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: PreferenceWrapper */
public class ap {
    private static final String f161a = "umeng_general_config";

    private ap() {
    }

    public static SharedPreferences m198a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static SharedPreferences m197a(Context context) {
        return context.getSharedPreferences(f161a, 0);
    }
}
