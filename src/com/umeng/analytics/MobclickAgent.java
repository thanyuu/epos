package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.social.C0314e;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import p000u.aly.bl;

public class MobclickAgent {
    private static final String f22a = "input map is null";
    private static final C0602d f23b = new C0602d();

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(224),
        E_UM_GAME_OEM(225);
        
        private int f21a;

        private EScenarioType(int i) {
            this.f21a = i;
        }

        public int toValue() {
            return this.f21a;
        }
    }

    public static class UMAnalyticsConfig {
        public String mAppkey;
        public String mChannelId;
        public Context mContext;
        public boolean mIsCrashEnable;
        public EScenarioType mType;

        private UMAnalyticsConfig() {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
        }

        public UMAnalyticsConfig(Context context, String str, String str2) {
            this(context, str, str2, null, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType) {
            this(context, str, str2, eScenarioType, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, EScenarioType eScenarioType, boolean z) {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = EScenarioType.E_UM_NORMAL;
            this.mContext = null;
            this.mContext = context;
            this.mAppkey = str;
            this.mChannelId = str2;
            this.mIsCrashEnable = z;
            if (eScenarioType != null) {
                this.mType = eScenarioType;
                return;
            }
            switch (AnalyticsConfig.getVerticalType(context)) {
                case 0:
                    this.mType = EScenarioType.E_UM_NORMAL;
                    return;
                case 1:
                    this.mType = EScenarioType.E_UM_GAME;
                    return;
                case 224:
                    this.mType = EScenarioType.E_UM_ANALYTICS_OEM;
                    return;
                case 225:
                    this.mType = EScenarioType.E_UM_GAME_OEM;
                    return;
                default:
                    return;
            }
        }
    }

    public static void startWithConfigure(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig != null) {
            f23b.m745a(uMAnalyticsConfig);
        }
    }

    public static void setLocation(double d, double d2) {
        f23b.m734a(d, d2);
    }

    public static void setLatencyWindow(long j) {
        f23b.m735a(j);
    }

    public static void enableEncrypt(boolean z) {
        f23b.m764f(z);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        f23b.m751a(z);
    }

    public static void setWrapper(String str, String str2) {
        f23b.m748a(str, str2);
    }

    public static void setSecret(Context context, String str) {
        f23b.m755b(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
        f23b.m738a(context, eScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        f23b.m753b(j);
    }

    public static void setEnableEventBuffer(boolean z) {
        f23b.m758b(z);
    }

    public static C0602d getAgent() {
        return f23b;
    }

    public static void setCheckDevice(boolean z) {
        f23b.m762d(z);
    }

    public static void setOpenGLContext(GL10 gl10) {
        f23b.m750a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        f23b.m760c(z);
    }

    public static void onPageStart(String str) {
        if (TextUtils.isEmpty(str)) {
            bl.m340e("pageName is null or empty");
        } else {
            f23b.m747a(str);
        }
    }

    public static void onPageEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            bl.m340e("pageName is null or empty");
        } else {
            f23b.m756b(str);
        }
    }

    public static void setDebugMode(boolean z) {
        f23b.m763e(z);
    }

    public static void onPause(Context context) {
        f23b.m754b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            bl.m340e("unexpected null context in onResume");
        } else {
            f23b.m736a(context);
        }
    }

    public static void reportError(Context context, String str) {
        f23b.m739a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        f23b.m743a(context, th);
    }

    public static void onEvent(Context context, List<String> list, int i, String str) {
        f23b.m744a(context, (List) list, i, str);
    }

    public static void onEvent(Context context, String str) {
        f23b.m740a(context, str, null, -1, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bl.m328c("label is null or empty");
        } else {
            f23b.m740a(context, str, str2, -1, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            bl.m340e(f22a);
            return;
        }
        f23b.m742a(context, str, new HashMap(map), -1);
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        Map hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        f23b.m742a(context, str, hashMap, -1);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bl.m340e("context is null in onShareEvent");
            return;
        }
        C0314e.f122e = "3";
        UMSocialService.share(context, str, uMPlatformDataArr);
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            bl.m340e("context is null in onShareEvent");
            return;
        }
        C0314e.f122e = "3";
        UMSocialService.share(context, uMPlatformDataArr);
    }

    public static void onKillProcess(Context context) {
        f23b.m761d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            bl.m334d("uid is null");
        } else if (str2.length() > 64) {
            bl.m334d("uid is Illegal(length bigger then  legitimate length).");
        } else if (TextUtils.isEmpty(str)) {
            f23b.m757b("_adhoc", str2);
        } else if (str.length() > 32) {
            bl.m334d("provider is Illegal(length bigger then  legitimate length).");
        } else {
            f23b.m757b(str, str2);
        }
    }

    public static void onProfileSignOff() {
        f23b.m752b();
    }
}
