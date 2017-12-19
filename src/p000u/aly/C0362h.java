package p000u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UMCCAggregatedListObject */
public class C0362h implements Serializable {
    private static final long f459a = 1;
    private Map<List<String>, C0363i> f460b = new HashMap();
    private long f461c = 0;

    public Map<List<String>, C0363i> m509a() {
        return this.f460b;
    }

    public void m511a(Map<List<String>, C0363i> map) {
        if (this.f460b.size() <= 0) {
            this.f460b = map;
        } else {
            m508b(map);
        }
    }

    private void m508b(Map<List<String>, C0363i> map) {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        Iterator it = this.f460b.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            List list = (List) entry.getKey();
            Iterator it2 = this.f460b.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry2 = (Entry) it2.next();
                List list2 = (List) entry.getKey();
                if (list.equals(list2)) {
                    C0363i c0363i = (C0363i) entry2.getValue();
                    m507a((C0363i) entry.getValue(), c0363i);
                    this.f460b.remove(list);
                    this.f460b.put(list, c0363i);
                } else {
                    this.f460b.put(list2, entry2.getValue());
                }
            }
        }
    }

    private void m507a(C0363i c0363i, C0363i c0363i2) {
        c0363i2.m532c(c0363i2.m536g() + c0363i.m536g());
        c0363i2.m528b(c0363i2.m535f() + c0363i.m535f());
        c0363i2.m522a(c0363i2.m534e() + c0363i.m534e());
        for (int i = 0; i < c0363i.m533d().size(); i++) {
            c0363i2.m523a((String) c0363i.m533d().get(i));
        }
    }

    public long m518b() {
        return this.f461c;
    }

    public void m510a(long j) {
        this.f461c = j;
    }

    public void m514a(final C0652f c0652f, C0366l c0366l) {
        try {
            if (m516a(c0366l.m547a())) {
                C0363i c0363i = (C0363i) this.f460b.get(c0366l.m547a());
                if (c0363i != null) {
                    c0363i.m525a(new C0652f(this) {
                        final /* synthetic */ C0362h f1002b;

                        public void mo1814a(Object obj, boolean z) {
                            C0363i c0363i = (C0363i) obj;
                            this.f1002b.f460b.remove(c0363i.m521a());
                            this.f1002b.f460b.put(c0363i.m527b(), c0363i);
                            c0652f.mo1814a(this, false);
                        }
                    }, c0366l);
                    return;
                } else {
                    m513a(c0652f, c0366l.m547a(), c0366l);
                    return;
                }
            }
            m513a(c0652f, c0366l.m547a(), c0366l);
        } catch (Exception e) {
            bl.m340e("aggregated faild!");
        }
    }

    public void m513a(C0652f c0652f, List<String> list, C0366l c0366l) {
        C0363i c0363i = new C0363i();
        c0363i.m526a(c0366l);
        this.f460b.put(list, c0363i);
        c0652f.mo1814a(this, false);
    }

    public boolean m516a(List<?> list) {
        if (this.f460b == null || !this.f460b.containsKey(list)) {
            return false;
        }
        return true;
    }

    public void m512a(C0652f c0652f) {
        for (List list : this.f460b.keySet()) {
            if (!c0652f.m1368a()) {
                c0652f.mo1814a(this.f460b.get(list), false);
            } else {
                return;
            }
        }
    }

    public int m519c() {
        if (this.f460b != null) {
            return this.f460b.size();
        }
        return 0;
    }

    public void m520d() {
        this.f460b.clear();
    }

    public boolean m517a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return false;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(C0359d.m488a((String) list.get(i)));
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        return arrayList.contains(list2);
    }

    public void m515a(C0652f c0652f, C0366l c0366l, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (m517a((List) list2, (List) list)) {
                        m506a(c0652f, c0366l, (List) list);
                        return;
                    } else {
                        c0652f.mo1814a(Boolean.valueOf(false), false);
                        return;
                    }
                } else if (m517a((List) list2, (List) list)) {
                    m506a(c0652f, c0366l, (List) list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                bl.m340e("overFlowAggregated faild");
                return;
            }
        }
    }

    private void m506a(C0652f c0652f, C0366l c0366l, List<String> list) {
        if (m516a((List) list)) {
            m514a(c0652f, c0366l);
        } else {
            m513a(c0652f, (List) list, c0366l);
        }
    }
}
