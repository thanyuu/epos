package com.umeng.analytics;

/* compiled from: SafeRunnable */
public abstract class C0296g implements Runnable {
    public abstract void mo1733a();

    public void run() {
        try {
            mo1733a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
