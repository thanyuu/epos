package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.C0291a;
import org.json.JSONObject;

public abstract class UMSocialService {

    private static class C0308a extends AsyncTask<Void, Void, C0313d> {
        String f107a;
        String f108b;
        C0309b f109c;
        UMPlatformData[] f110d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m102a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m103a((C0313d) obj);
        }

        public C0308a(String[] strArr, C0309b c0309b, UMPlatformData[] uMPlatformDataArr) {
            this.f107a = strArr[0];
            this.f108b = strArr[1];
            this.f109c = c0309b;
            this.f110d = uMPlatformDataArr;
        }

        protected void onPreExecute() {
            if (this.f109c != null) {
                this.f109c.m104a();
            }
        }

        protected C0313d m102a(Void... voidArr) {
            String a;
            if (TextUtils.isEmpty(this.f108b)) {
                a = C0312c.m119a(this.f107a);
            } else {
                a = C0312c.m120a(this.f107a, this.f108b);
            }
            try {
                JSONObject jSONObject = new JSONObject(a);
                int optInt = jSONObject.optInt("st");
                C0313d c0313d = new C0313d(optInt == 0 ? C0314e.f137t : optInt);
                String optString = jSONObject.optString("msg");
                if (!TextUtils.isEmpty(optString)) {
                    c0313d.m123a(optString);
                }
                Object optString2 = jSONObject.optString("data");
                if (TextUtils.isEmpty(optString2)) {
                    return c0313d;
                }
                c0313d.m125b(optString2);
                return c0313d;
            } catch (Exception e) {
                return new C0313d(-99, e);
            }
        }

        protected void m103a(C0313d c0313d) {
            if (this.f109c != null) {
                this.f109c.m105a(c0313d, this.f110d);
            }
        }
    }

    public interface C0309b {
        void m104a();

        void m105a(C0313d c0313d, UMPlatformData... uMPlatformDataArr);
    }

    private static void m106a(Context context, C0309b c0309b, String str, UMPlatformData... uMPlatformDataArr) {
        int i = 0;
        if (uMPlatformDataArr != null) {
            try {
                int length = uMPlatformDataArr.length;
                while (i < length) {
                    if (uMPlatformDataArr[i].isValid()) {
                        i++;
                    } else {
                        throw new C0310a("parameter is not valid.");
                    }
                }
            } catch (Throwable e) {
                Log.e(C0291a.f35d, "unable send event.", e);
                return;
            } catch (Throwable e2) {
                Log.e(C0291a.f35d, "", e2);
                return;
            }
        }
        new C0308a(C0315f.m132a(context, str, uMPlatformDataArr), c0309b, uMPlatformDataArr).execute(new Void[0]);
    }

    public static void share(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        m106a(context, null, str, uMPlatformDataArr);
    }

    public static void share(Context context, UMPlatformData... uMPlatformDataArr) {
        m106a(context, null, null, uMPlatformDataArr);
    }
}
