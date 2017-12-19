package com.epos.pospay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.SPUtil;

public class UserFragment extends BaseFragment {

    class C02601 implements OnClickListener {
        C02601() {
        }

        public void onClick(View v) {
            IntentUtil.gotoLogin(UserFragment.this.getActivity());
            SPUtil.remove(UserFragment.this.getActivity(), "weposId");
            SPUtil.remove(UserFragment.this.getActivity(), "rate");
            SPUtil.remove(UserFragment.this.getActivity(), "authCode");
            SPUtil.remove(UserFragment.this.getActivity(), "orgName");
            SPUtil.remove(UserFragment.this.getActivity(), "tel");
            SPUtil.remove(UserFragment.this.getActivity(), "maxPayment");
            UserFragment.this.getActivity().finish();
        }
    }

    class C02612 extends WebViewClient {
        C02612() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.user_layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        ((Button) view.findViewById(C0258R.id.btn_logout)).setOnClickListener(new C02601());
        TextView tv_tel = (TextView) view.findViewById(C0258R.id.tv_tel);
        ((TextView) view.findViewById(C0258R.id.tv_user)).setText(getString(C0258R.string.about_user) + SPUtil.get(getActivity(), "orgName", ""));
        WebView webView = (WebView) view.findViewById(C0258R.id.webview);
        tv_tel.setText(getString(C0258R.string.about_tel) + SPUtil.get(getActivity(), "tel", ""));
        webView.setWebViewClient(new C02612());
        webView.loadUrl(SPUtil.get(getActivity(), "aboutInfo", "").toString());
    }
}
