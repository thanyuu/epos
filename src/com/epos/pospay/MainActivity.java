package com.epos.pospay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.epos.pospay.capture.ZxingProtocol;
import com.epos.pospay.capture.ZxingProtocol.OnResponseListener;
import com.epos.pospay.capture.ZxingProtocol.ResultBean.Root;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.MainApplication;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.model.RateBean;
import com.epos.pospay.view.CustomDialog;
import com.epos.pospay.view.CustomDialog.DialogType;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener {
    private Fragment mContent;
    private CustomDialog mLoadingDialog;
    private ImageView topButton;
    private TextView topTextView;

    class C05171 implements OnResponseListener {
        C05171() {
        }

        public void onResponse(Root bean) {
            String title = MainActivity.this.getString(C0258R.string.paytitle);
            Fragment fragment;
            Bundle mb;
            if (bean.getSuccess()) {
                Toast.makeText(MainActivity.this, bean.getMessage(), 0).show();
                fragment = new CheckSuccessFragment();
                mb = new Bundle();
                mb.putSerializable("param", bean);
                fragment.setArguments(mb);
                MainActivity.this.switchConent(fragment, title);
                MainActivity.this.stopLoadingProgress();
            } else {
                Toast.makeText(MainActivity.this, bean.getMessage(), 0).show();
                fragment = new CheckSuccessFragment();
                mb = new Bundle();
                mb.putSerializable("param", bean);
                fragment.setArguments(mb);
                MainActivity.this.switchConent(fragment, title);
                MainActivity.this.stopLoadingProgress();
            }
            MainApplication.getInstance().setOrderNum(bean.getOrderNum());
            MainApplication.getInstance().setTrans_id(bean.getOrderInfo().getId() + "");
            MainApplication.getInstance().setTransactionNumUnion(bean.getOrderInfo().getTransactionNumUnion());
        }

        public void onErrorResponse(String error) {
            Toast.makeText(MainActivity.this, error, 0).show();
            IntentUtil.gotoCaptureActivity(MainActivity.this);
            MainActivity.this.stopLoadingProgress();
        }
    }

    class C05182 implements Listener<String> {
        C05182() {
        }

        public void onResponse(String response) {
            RateBean rateBean = (RateBean) new Gson().fromJson(response, RateBean.class);
            if (rateBean.isSuccess()) {
                SPUtil.put(MainActivity.this, "rate", rateBean.getRate());
            }
        }
    }

    class C05193 implements ErrorListener {
        C05193() {
        }

        public void onErrorResponse(VolleyError error) {
            Log.e("=========", "onErrorResponse: " + error);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView((int) C0258R.layout.activity_main);
        init();
        initSlidingMenu(savedInstanceState);
    }

    private void init() {
        this.topButton = (ImageView) findViewById(C0258R.id.topButton);
        this.topButton.setOnClickListener(this);
        this.topTextView = (TextView) findViewById(C0258R.id.topTv);
        initData();
    }

    protected void onResume() {
        super.onResume();
        toFragment();
        Log.e("===============", "onResume: ");
        getRate();
    }

    private void toFragment() {
        String flag = getIntent().getStringExtra("flag");
        if (flag != null) {
            Object obj = -1;
            switch (flag.hashCode()) {
                case 106380190:
                    if (flag.equals(IntentUtil.ORDERFRAGMENT)) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    switchConent(new OrderFragment(), getString(C0258R.string.left_order));
                    return;
                default:
                    return;
            }
        }
    }

    protected void onPause() {
        super.onPause();
        getIntent().removeExtra("flag");
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String result = bundle.getString("result");
            if (result == null) {
                return;
            }
            if (result.equals("detail")) {
                switchConent(new OrderFragment(), getString(C0258R.string.left_order));
                return;
            }
            startLoadingProgress(getString(C0258R.string.wait_progress));
            ZxingProtocol.getResult(this, result, MainApplication.getInstance().getPriceNz(), new C05171());
        }
    }

    private void initSlidingMenu(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        if (this.mContent == null) {
            this.mContent = new CheckFragment();
            switchConent(this.mContent, getString(C0258R.string.left_checkout));
        }
        setBehindContentView((int) C0258R.layout.menu_frame_left);
        getSupportFragmentManager().beginTransaction().replace(C0258R.id.menu_frame, new LeftFragment()).commit();
        SlidingMenu sm = getSlidingMenu();
        sm.setMode(0);
        sm.setShadowWidthRes(C0258R.dimen.shadow_width);
        sm.setShadowDrawable(null);
        sm.setBehindOffsetRes(C0258R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(1);
        sm.setBehindScrollScale(0.0f);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            getSupportFragmentManager().putFragment(outState, "mContent", this.mContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchConent(Fragment fragment, String title) {
        this.mContent = fragment;
        getSupportFragmentManager().beginTransaction().replace(C0258R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
        this.topTextView.setText(title);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0258R.id.topButton:
                toggle();
                return;
            default:
                return;
        }
    }

    protected void startLoadingProgress(String hint) {
        this.mLoadingDialog = new CustomDialog(this, DialogType.Loading);
        this.mLoadingDialog.setLoadingText(hint);
        this.mLoadingDialog.show();
    }

    protected void stopLoadingProgress() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mContent instanceof CheckFragment) {
            ((CheckFragment) this.mContent).onKeyDown(keyCode, event);
        }
        Log.e("===================", "onKeyDown: " + keyCode);
        Log.e("===================", "KeyEvent: " + event);
        return super.onKeyDown(keyCode, event);
    }

    private void getRate() {
        HttpRequestManager.getInstance().getRate(new C05182(), new C05193(), SPUtil.get(this, "merchant", "").toString()).commit();
    }
}
