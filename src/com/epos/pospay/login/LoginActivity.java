package com.epos.pospay.login;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.epos.pospay.C0258R;
import com.epos.pospay.common.BActivity;
import com.epos.pospay.common.HttpRequest;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.common.VolleyErrorHelper;
import com.epos.pospay.model.LoginBean;
import com.google.gson.Gson;

public class LoginActivity extends BActivity {
    private EditText login_name;
    private EditText login_password;
    private long[] mHits;

    class C02661 implements OnClickListener {

        class C05301 implements Listener<String> {
            C05301() {
            }

            public void onResponse(String response) {
                Log.e("==========", "onResponse: " + response);
                LoginBean loginBean = (LoginBean) new Gson().fromJson(response, LoginBean.class);
                if (loginBean.isSuccess()) {
                    SPUtil.put(LoginActivity.this, "weposId", loginBean.getWeposId());
                    SPUtil.put(LoginActivity.this, "rate", loginBean.getRate());
                    SPUtil.put(LoginActivity.this, "authCode", loginBean.getAuthCode());
                    SPUtil.put(LoginActivity.this, "orgName", loginBean.getMerchant().getOrgName());
                    SPUtil.put(LoginActivity.this, "tel", loginBean.getMerchant().getServiceTel());
                    SPUtil.put(LoginActivity.this, "maxPayment", loginBean.getMerchant().getMaxPayment());
                    SPUtil.put(LoginActivity.this, "aboutInfo", loginBean.getAboutInfo());
                    SPUtil.put(LoginActivity.this, "merchant", loginBean.getMerchant().getMerchant() + "");
                    SPUtil.put(LoginActivity.this, "feeType", loginBean.getFeeType());
                    if (loginBean.getMerchant().getPrintCount() == 0) {
                        SPUtil.put(LoginActivity.this, "printCount", Integer.valueOf(1));
                    } else {
                        SPUtil.put(LoginActivity.this, "printCount", Integer.valueOf(loginBean.getMerchant().getPrintCount()));
                    }
                    if (TextUtils.isEmpty(loginBean.getMerchant().getTimeZone())) {
                        SPUtil.put(LoginActivity.this, "timeZone", "");
                    } else {
                        SPUtil.put(LoginActivity.this, "timeZone", loginBean.getMerchant().getTimeZone());
                    }
                    IntentUtil.gotoMainActivity(LoginActivity.this);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, loginBean.getMessage(), 0).show();
                }
                LoginActivity.this.stopLoadingProgress();
            }
        }

        class C05312 implements ErrorListener {
            C05312() {
            }

            public void onErrorResponse(VolleyError error) {
                LoginActivity.this.stopLoadingProgress();
                Log.e("==========", "error: " + error);
                VolleyErrorHelper.showErrorMessage(LoginActivity.this, error);
            }
        }

        C02661() {
        }

        public void onClick(View v) {
            if (LoginActivity.this.verification(LoginActivity.this.login_name.getText().toString(), LoginActivity.this.login_password.getText().toString())) {
                HttpRequest request = HttpRequestManager.getInstance().login(new C05301(), new C05312(), LoginActivity.this.login_name.getText().toString(), LoginActivity.this.login_password.getText().toString());
                LoginActivity.this.startLoadingProgress(LoginActivity.this.getString(C0258R.string.wait_progress));
                request.commit();
                return;
            }
            Toast.makeText(LoginActivity.this, LoginActivity.this.getResources().getText(C0258R.string.login_toast_not_null), 0).show();
        }
    }

    class C02682 implements OnClickListener {
        C02682() {
        }

        public void onClick(View v) {
            System.arraycopy(LoginActivity.this.mHits, 1, LoginActivity.this.mHits, 0, LoginActivity.this.mHits.length - 1);
            LoginActivity.this.mHits[LoginActivity.this.mHits.length - 1] = SystemClock.uptimeMillis();
            if (LoginActivity.this.mHits[0] >= SystemClock.uptimeMillis() - ((long) (LoginActivity.this.mHits.length * 500))) {
                LoginActivity.this.mHits = new long[5];
                final EditText texta = new EditText(LoginActivity.this);
                texta.setText(HttpRequestManager.getURL());
                new Builder(LoginActivity.this).setTitle(LoginActivity.this.getString(C0258R.string.enterurl)).setIcon(17301659).setView(texta).setPositiveButton(LoginActivity.this.getString(C0258R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtil.put(LoginActivity.this, HttpRequestManager.URL, texta.getText().toString());
                    }
                }).setNegativeButton(LoginActivity.this.getString(C0258R.string.cancle), null).show();
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    protected int getContentViewResId() {
        return C0258R.layout.login_activity;
    }

    private void initUI() {
        this.login_name = (EditText) findViewById(C0258R.id.login_name);
        this.login_password = (EditText) findViewById(C0258R.id.login_password);
        ((Button) findViewById(C0258R.id.login_button)).setOnClickListener(new C02661());
        this.mHits = new long[6];
        findViewById(C0258R.id.iv_logo).setOnClickListener(new C02682());
    }

    private boolean verification(String name, String password) {
        return (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) ? false : true;
    }
}
