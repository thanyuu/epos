package com.umeng.analytics;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.android.common.constant.DbConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import p000u.aly.au;

public class MobclickAgentJSInterface {
    private Context f28a;

    final class C0289a extends WebChromeClient {
        WebChromeClient f24a = null;
        final /* synthetic */ MobclickAgentJSInterface f25b;
        private final String f26c = au.aE;
        private final String f27d = "event";

        public C0289a(MobclickAgentJSInterface mobclickAgentJSInterface, WebChromeClient webChromeClient) {
            this.f25b = mobclickAgentJSInterface;
            if (webChromeClient == null) {
                this.f24a = new WebChromeClient();
            } else {
                this.f24a = webChromeClient;
            }
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if (au.aE.equals(str2)) {
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    Map hashMap = new HashMap();
                    String str4 = (String) jSONObject.remove("id");
                    int intValue = jSONObject.isNull("duration") ? 0 : ((Integer) jSONObject.remove("duration")).intValue();
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str5 = (String) keys.next();
                        hashMap.put(str5, jSONObject.getString(str5));
                    }
                    MobclickAgent.getAgent().m742a(this.f25b.f28a, str4, hashMap, (long) intValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!"event".equals(str2)) {
                return this.f24a.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            } else {
                try {
                    JSONObject jSONObject2 = new JSONObject(str3);
                    String optString = jSONObject2.optString("label");
                    if ("".equals(optString)) {
                        optString = null;
                    }
                    MobclickAgent.getAgent().m740a(this.f25b.f28a, jSONObject2.getString(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG), optString, (long) jSONObject2.optInt("duration"), 1);
                } catch (Exception e2) {
                }
            }
            jsPromptResult.confirm();
            return true;
        }

        public void onCloseWindow(WebView webView) {
            this.f24a.onCloseWindow(webView);
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            return this.f24a.onCreateWindow(webView, z, z2, message);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return this.f24a.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            return this.f24a.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return this.f24a.onJsConfirm(webView, str, str2, jsResult);
        }

        public void onProgressChanged(WebView webView, int i) {
            this.f24a.onProgressChanged(webView, i);
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            this.f24a.onReceivedIcon(webView, bitmap);
        }

        public void onReceivedTitle(WebView webView, String str) {
            this.f24a.onReceivedTitle(webView, str);
        }

        public void onRequestFocus(WebView webView) {
            this.f24a.onRequestFocus(webView);
        }
    }

    public MobclickAgentJSInterface(Context context, WebView webView) {
        this.f28a = context;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new C0289a(this, null));
    }

    public MobclickAgentJSInterface(Context context, WebView webView, WebChromeClient webChromeClient) {
        this.f28a = context;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new C0289a(this, webChromeClient));
    }
}
