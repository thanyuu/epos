package p000u.aly;

import android.util.Log;
import com.umeng.analytics.C0291a;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: MLog */
public class bl {
    public static boolean f355a = false;
    private static String f356b = C0291a.f35d;

    private bl() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void m321a(Locale locale, String str, Object... objArr) {
        try {
            bl.m329c(f356b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m327b(Locale locale, String str, Object... objArr) {
        try {
            bl.m323b(f356b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m333c(Locale locale, String str, Object... objArr) {
        try {
            bl.m341e(f356b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m339d(Locale locale, String str, Object... objArr) {
        try {
            bl.m317a(f356b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m345e(Locale locale, String str, Object... objArr) {
        try {
            bl.m335d(f356b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m319a(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                bl.m329c(f356b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            bl.m329c(str, str2, null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m325b(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                bl.m323b(f356b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            bl.m323b(str, str2, null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m331c(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                bl.m341e(f356b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            bl.m341e(str, str2, null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m337d(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                bl.m317a(f356b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            bl.m317a(str, str2, null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m343e(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                bl.m335d(f356b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            bl.m335d(str, str2, null);
        } catch (Throwable th) {
            bl.m344e(th);
        }
    }

    public static void m320a(Throwable th) {
        bl.m329c(f356b, null, th);
    }

    public static void m326b(Throwable th) {
        bl.m317a(f356b, null, th);
    }

    public static void m332c(Throwable th) {
        bl.m335d(f356b, null, th);
    }

    public static void m338d(Throwable th) {
        bl.m323b(f356b, null, th);
    }

    public static void m344e(Throwable th) {
        bl.m341e(f356b, null, th);
    }

    public static void m318a(String str, Throwable th) {
        bl.m329c(f356b, str, th);
    }

    public static void m324b(String str, Throwable th) {
        bl.m317a(f356b, str, th);
    }

    public static void m330c(String str, Throwable th) {
        bl.m335d(f356b, str, th);
    }

    public static void m336d(String str, Throwable th) {
        bl.m323b(f356b, str, th);
    }

    public static void m342e(String str, Throwable th) {
        bl.m341e(f356b, str, th);
    }

    public static void m316a(String str) {
        bl.m317a(f356b, str, null);
    }

    public static void m322b(String str) {
        bl.m323b(f356b, str, null);
    }

    public static void m328c(String str) {
        bl.m329c(f356b, str, null);
    }

    public static void m334d(String str) {
        bl.m335d(f356b, str, null);
    }

    public static void m340e(String str) {
        bl.m341e(f356b, str, null);
    }

    public static void m317a(String str, String str2, Throwable th) {
        if (!f355a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.v(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.v(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.v(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.v(str, str2);
        }
    }

    public static void m323b(String str, String str2, Throwable th) {
        if (!f355a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.d(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.d(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.d(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.d(str, str2);
        }
    }

    public static void m329c(String str, String str2, Throwable th) {
        if (!f355a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.i(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.i(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.i(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.i(str, str2);
        }
    }

    public static void m335d(String str, String str2, Throwable th) {
        if (!f355a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.w(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.w(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.w(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.w(str, str2);
        }
    }

    public static void m341e(String str, String str2, Throwable th) {
        if (!f355a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.e(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.e(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.e(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.e(str, str2);
        }
    }
}
