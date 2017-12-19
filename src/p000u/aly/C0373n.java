package p000u.aly;

import android.text.TextUtils;
import java.util.List;

/* compiled from: UMCCAggregatedRestrictionManager */
public class C0373n {

    /* compiled from: UMCCAggregatedRestrictionManager */
    private static class C0372a {
        private static final C0373n f506a = new C0373n();

        private C0372a() {
        }
    }

    private C0373n() {
    }

    public static C0373n m593a() {
        return C0372a.f506a;
    }

    public boolean m595a(String str) {
        if ("".equals(str)) {
            return true;
        }
        if (str == null) {
            return false;
        }
        if (str.getBytes().length >= C0364j.f470b || !m594a(str, 48)) {
            return false;
        }
        return true;
    }

    public boolean m598b(String str) {
        if (!TextUtils.isEmpty(str) && str.length() < 16 && m594a(str, 48)) {
            return true;
        }
        return false;
    }

    public boolean m596a(List<String> list) {
        if (list == null) {
            return false;
        }
        if (list.size() <= 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (TextUtils.isEmpty((CharSequence) list.get(i))) {
                return false;
            }
            if (!m594a((String) list.get(i), 48)) {
                return false;
            }
        }
        return true;
    }

    private boolean m594a(String str, int i) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) < i) {
                return false;
            }
        }
        return true;
    }

    public int m597b() {
        return 8;
    }

    public int m600c() {
        return 128;
    }

    public int m601d() {
        return 512;
    }

    public boolean m599b(List<String> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        int i = 0;
        for (String bytes : list) {
            i = bytes.getBytes().length + i;
        }
        if (i < 256) {
            return true;
        }
        return false;
    }
}
