package p000u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;

/* compiled from: ImeiTracker */
public class C0656w extends C0379r {
    private static final String f991a = "imei";
    private Context f992b;

    public C0656w(Context context) {
        super(f991a);
        this.f992b = context;
    }

    public String mo1737f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f992b.getSystemService("phone");
        if (telephonyManager == null) {
        }
        try {
            if (bj.m264a(this.f992b, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
