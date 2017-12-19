package p000u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: UMCCDBUtils */
public class C0359d {
    public static final String f452a = "/data/data/";
    public static final String f453b = "/databases/cc/";
    public static final String f454c = "cc.db";
    public static final int f455d = 1;
    public static final String f456e = "Id";
    public static final String f457f = "INTEGER";

    /* compiled from: UMCCDBUtils */
    public static class C0352a {
        public static final String f438a = "aggregated";
        public static final String f439b = "aggregated_cache";

        /* compiled from: UMCCDBUtils */
        public static class C0350a {
            public static final String f426a = "key";
            public static final String f427b = "totalTimestamp";
            public static final String f428c = "value";
            public static final String f429d = "count";
            public static final String f430e = "label";
            public static final String f431f = "timeWindowNum";
        }

        /* compiled from: UMCCDBUtils */
        public static class C0351b {
            public static final String f432a = "TEXT";
            public static final String f433b = "TEXT";
            public static final String f434c = "INTEGER";
            public static final String f435d = "INTEGER";
            public static final String f436e = "TEXT";
            public static final String f437f = "TEXT";
        }
    }

    /* compiled from: UMCCDBUtils */
    public static class C0355b {
        public static final String f442a = "limitedck";

        /* compiled from: UMCCDBUtils */
        public static class C0353a {
            public static final String f440a = "ck";
        }

        /* compiled from: UMCCDBUtils */
        public static class C0354b {
            public static final String f441a = "TEXT";
        }
    }

    /* compiled from: UMCCDBUtils */
    public static class C0358c {
        public static final String f451a = "system";

        /* compiled from: UMCCDBUtils */
        public static class C0356a {
            public static final String f443a = "key";
            public static final String f444b = "timeStamp";
            public static final String f445c = "count";
            public static final String f446d = "label";
        }

        /* compiled from: UMCCDBUtils */
        public static class C0357b {
            public static final String f447a = "TEXT";
            public static final String f448b = "INTEGER";
            public static final String f449c = "INTEGER";
            public static final String f450d = "TEXT";
        }
    }

    public static String m486a(Context context) {
        return f452a + context.getPackageName() + f453b;
    }

    public static String m487a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> m488a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> m489b(List<String> list) {
        List<String> arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
