package p000u.aly;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UMCCSystemBufferManager */
public class C0377p implements Serializable {
    private static final long f509a = 1;
    private Map<String, C0365k> f510b = new HashMap();

    public void m619a(C0652f c0652f, String str) {
        if (this.f510b.containsKey(str)) {
            m616c(str);
        } else {
            m614b(str);
        }
        c0652f.mo1814a(this, false);
    }

    public Map<String, C0365k> m617a() {
        return this.f510b;
    }

    public void m622b() {
        this.f510b.clear();
    }

    public void m618a(Map<String, C0365k> map) {
        this.f510b = map;
    }

    public boolean m621a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Entry key : this.f510b.entrySet()) {
            if (((String) key.getKey()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void m614b(String str) {
        this.f510b.put(str, new C0365k(str, System.currentTimeMillis(), 1));
    }

    private void m616c(String str) {
        this.f510b.put(str, ((C0365k) this.f510b.get(str)).m538a());
    }

    public void m620a(C0365k c0365k) {
        if (m621a(c0365k.m543c())) {
            m615b(c0365k);
        } else {
            this.f510b.put(c0365k.m543c(), c0365k);
        }
    }

    private void m615b(C0365k c0365k) {
        this.f510b.put(c0365k.m543c(), ((C0365k) this.f510b.get(c0365k.m543c())).m539a(c0365k));
    }
}
