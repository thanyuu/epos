package p000u.aly;

import android.content.Context;
import com.umeng.analytics.C0295f;
import com.umeng.analytics.C0296g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import p000u.aly.av.C0624j;

/* compiled from: EventTracker */
public class ag {
    private final int f149a = 128;
    private final int f150b = 256;
    private final int f151c = 10;
    private Context f152d;
    private ae f153e;

    public ag(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null, can't track event");
        }
        this.f152d = context.getApplicationContext();
        this.f153e = ae.m823a(this.f152d);
    }

    public void m166a(String str, Map<String, Object> map, long j) {
        try {
            if (m161a(str) && m162a((Map) map)) {
                ai c0624j = new C0624j();
                c0624j.f699c = str;
                c0624j.f700d = System.currentTimeMillis();
                if (j > 0) {
                    c0624j.f701e = j;
                }
                c0624j.f697a = 1;
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Entry entry = (Entry) it.next();
                    c0624j.f702f.put(entry.getKey(), entry.getValue());
                }
                if (c0624j.f698b == null) {
                    c0624j.f698b = ar.m215g(this.f152d);
                }
                this.f153e.mo1739a(c0624j);
            }
        } catch (Throwable e) {
            bl.m342e("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    public void m164a(String str, String str2, long j, int i) {
        if (m161a(str) && m163b(str2)) {
            Object obj;
            Map hashMap = new HashMap();
            if (str2 == null) {
                obj = "";
            } else {
                String str3 = str2;
            }
            hashMap.put(str, obj);
            ai c0624j = new C0624j();
            c0624j.f699c = str;
            c0624j.f700d = System.currentTimeMillis();
            if (j > 0) {
                c0624j.f701e = j;
            }
            c0624j.f697a = 1;
            hashMap = c0624j.f702f;
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put(str, str2);
            if (c0624j.f698b == null) {
                c0624j.f698b = ar.m215g(this.f152d);
            }
            this.f153e.mo1739a(c0624j);
        }
    }

    public void m165a(String str, Map<String, Object> map) {
        try {
            if (m161a(str)) {
                ai c0624j = new C0624j();
                c0624j.f699c = str;
                c0624j.f700d = System.currentTimeMillis();
                c0624j.f701e = 0;
                c0624j.f697a = 2;
                Iterator it = map.entrySet().iterator();
                for (int i = 0; i < 10 && it.hasNext(); i++) {
                    Entry entry = (Entry) it.next();
                    c0624j.f702f.put(entry.getKey(), entry.getValue());
                }
                if (c0624j.f698b == null) {
                    c0624j.f698b = ar.m215g(this.f152d);
                }
                this.f153e.mo1739a(c0624j);
            }
        } catch (Throwable e) {
            bl.m342e("Exception occurred in Mobclick.onEvent(). ", e);
        }
    }

    private boolean m161a(String str) {
        if (str != null) {
            int length = str.trim().getBytes().length;
            if (length > 0 && length <= 128) {
                return true;
            }
        }
        bl.m340e("Event id is empty or too long in tracking Event");
        return false;
    }

    private boolean m163b(String str) {
        if (str == null || str.trim().getBytes().length <= 256) {
            return true;
        }
        bl.m340e("Event label or value is empty or too long in tracking Event");
        return false;
    }

    private boolean m162a(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            bl.m340e("map is null or empty in onEvent");
            return false;
        }
        for (Entry entry : map.entrySet()) {
            if (!m161a((String) entry.getKey())) {
                return false;
            }
            if (entry.getValue() == null) {
                return false;
            }
            if ((entry.getValue() instanceof String) && !m163b(entry.getValue().toString())) {
                return false;
            }
        }
        return true;
    }

    public boolean m167a(List<String> list, int i, String str) {
        C0373n a = C0373n.m593a();
        if (list == null) {
            bl.m340e("cklist is null!");
        } else if (list.size() <= 0) {
            bl.m340e("the KeyList is null!");
            return false;
        } else {
            List arrayList = new ArrayList(list);
            if (a.m598b((String) arrayList.get(0))) {
                String str2;
                String str3;
                if (arrayList.size() > 8) {
                    str3 = (String) arrayList.get(0);
                    arrayList.clear();
                    arrayList.add(str3);
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                } else if (!a.m596a(arrayList)) {
                    str3 = (String) arrayList.get(0);
                    arrayList.clear();
                    arrayList.add(str3);
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                } else if (a.m599b(arrayList)) {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        str3 = (String) arrayList.get(i2);
                        if (str3.length() > 16) {
                            arrayList.remove(i2);
                            arrayList.add(i2, str3.substring(0, 16));
                        }
                    }
                } else {
                    str3 = (String) arrayList.get(0);
                    arrayList.clear();
                    arrayList.add(str3);
                    arrayList.add("__cc");
                    arrayList.add("illegal");
                }
                if (a.m595a(str)) {
                    str2 = str;
                } else {
                    bl.m340e("label  Invalid!");
                    str2 = "__illegal";
                }
                final Map hashMap = new HashMap();
                hashMap.put(arrayList, new C0366l(arrayList, (long) i, str2, System.currentTimeMillis()));
                C0295f.m49b(new C0296g(this) {
                    final /* synthetic */ ag f676b;

                    /* compiled from: EventTracker */
                    class C06831 extends C0652f {
                        final /* synthetic */ C06211 f1000a;

                        C06831(C06211 c06211) {
                            this.f1000a = c06211;
                        }

                        public void mo1814a(Object obj, boolean z) {
                            if (!obj.equals("success")) {
                            }
                        }
                    }

                    public void mo1733a() {
                        C0370m.m556a(this.f676b.f152d).m584a(new C06831(this), hashMap);
                    }
                });
            } else {
                bl.m340e("Primary key Invalid!");
                return false;
            }
        }
        return true;
    }
}
