package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import p000u.aly.av.C0327l;
import p000u.aly.av.C0625o;

/* compiled from: ViewPageTracker */
public class at {
    private static final String f184a = "activities";
    private final Map<String, Long> f185b = new HashMap();
    private final ArrayList<C0327l> f186c = new ArrayList();

    public void m225a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.f185b) {
                this.f185b.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void m226b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.f185b) {
                l = (Long) this.f185b.remove(str);
            }
            if (l == null) {
                bl.m343e("please call 'onPageStart(%s)' before onPageEnd", str);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            synchronized (this.f186c) {
                C0327l c0327l = new C0327l();
                c0327l.f272a = str;
                c0327l.f273b = currentTimeMillis;
                this.f186c.add(c0327l);
            }
        }
    }

    public void m223a() {
        String str = null;
        long j = 0;
        synchronized (this.f185b) {
            for (Entry entry : this.f185b.entrySet()) {
                String str2;
                long j2;
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str2 = (String) entry.getKey();
                    j2 = longValue;
                } else {
                    j2 = j;
                    str2 = str;
                }
                str = str2;
                j = j2;
            }
        }
        if (str != null) {
            m226b(str);
        }
    }

    public void m224a(Context context) {
        SharedPreferences a = ap.m197a(context);
        Editor edit = a.edit();
        if (this.f186c.size() > 0) {
            Object string = a.getString(f184a, "");
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(string);
                stringBuilder.append(";");
            }
            synchronized (this.f186c) {
                Iterator it = this.f186c.iterator();
                while (it.hasNext()) {
                    C0327l c0327l = (C0327l) it.next();
                    stringBuilder.append(String.format("[\"%s\",%d]", new Object[]{c0327l.f272a, Long.valueOf(c0327l.f273b)}));
                    stringBuilder.append(";");
                }
                this.f186c.clear();
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            edit.remove(f184a);
            edit.putString(f184a, stringBuilder.toString());
        }
        edit.commit();
    }

    public static void m222a(SharedPreferences sharedPreferences, C0625o c0625o) {
        Object string = sharedPreferences.getString(f184a, "");
        if (!TextUtils.isEmpty(string)) {
            try {
                String[] split = string.split(";");
                for (String jSONArray : split) {
                    JSONArray jSONArray2 = new JSONArray(jSONArray);
                    C0327l c0327l = new C0327l();
                    c0327l.f272a = jSONArray2.getString(0);
                    c0327l.f273b = (long) jSONArray2.getInt(1);
                    c0625o.f711h.add(c0327l);
                }
            } catch (Throwable e) {
                bl.m344e(e);
            }
        }
    }
}
