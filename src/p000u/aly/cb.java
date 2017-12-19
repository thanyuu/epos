package p000u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData */
public class cb implements Serializable {
    private static Map<Class<? extends bp>, Map<? extends bw, cb>> f372d = new HashMap();
    public final String f373a;
    public final byte f374b;
    public final cc f375c;

    public cb(String str, byte b, cc ccVar) {
        this.f373a = str;
        this.f374b = b;
        this.f375c = ccVar;
    }

    public static void m426a(Class<? extends bp> cls, Map<? extends bw, cb> map) {
        f372d.put(cls, map);
    }

    public static Map<? extends bw, cb> m425a(Class<? extends bp> cls) {
        if (!f372d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) f372d.get(cls);
    }
}
