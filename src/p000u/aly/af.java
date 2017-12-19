package p000u.aly;

import com.umeng.analytics.AnalyticsConfig;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: CrashHandler */
public class af implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler f147a;
    private an f148b;

    public af() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.f147a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void m159a(an anVar) {
        this.f148b = anVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        m158a(th);
        if (this.f147a != null && this.f147a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.f147a.uncaughtException(thread, th);
        }
    }

    private void m158a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.f148b.mo1734a(th);
        } else {
            this.f148b.mo1734a(null);
        }
    }
}
