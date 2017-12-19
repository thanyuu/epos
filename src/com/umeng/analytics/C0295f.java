package com.umeng.analytics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: QueuedWork */
public class C0295f {
    private static List<WeakReference<ScheduledFuture<?>>> f60a = new ArrayList();
    private static ExecutorService f61b = Executors.newSingleThreadExecutor();
    private static long f62c = 5;
    private static ScheduledExecutorService f63d = Executors.newSingleThreadScheduledExecutor();

    public static void m47a(Runnable runnable) {
        if (f61b.isShutdown()) {
            f61b = Executors.newSingleThreadExecutor();
        }
        f61b.execute(runnable);
    }

    public static void m46a() {
        try {
            for (WeakReference weakReference : f60a) {
                ScheduledFuture scheduledFuture = (ScheduledFuture) weakReference.get();
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
            }
            f60a.clear();
            if (!f61b.isShutdown()) {
                f61b.shutdown();
            }
            if (!f63d.isShutdown()) {
                f63d.shutdown();
            }
            f61b.awaitTermination(f62c, TimeUnit.SECONDS);
            f63d.awaitTermination(f62c, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
    }

    public static synchronized void m49b(Runnable runnable) {
        synchronized (C0295f.class) {
            if (f63d.isShutdown()) {
                f63d = Executors.newSingleThreadScheduledExecutor();
            }
            f63d.execute(runnable);
        }
    }

    public static synchronized void m48a(Runnable runnable, long j) {
        synchronized (C0295f.class) {
            if (f63d.isShutdown()) {
                f63d = Executors.newSingleThreadScheduledExecutor();
            }
            f60a.add(new WeakReference(f63d.schedule(runnable, j, TimeUnit.MILLISECONDS)));
        }
    }

    public static synchronized void m50c(Runnable runnable) {
        synchronized (C0295f.class) {
            if (f63d.isShutdown()) {
                f63d = Executors.newSingleThreadScheduledExecutor();
            }
            try {
                f63d.submit(runnable).get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
            }
        }
    }
}
