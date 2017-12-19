package com.epos.pospay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import com.android.volley.DefaultRetryPolicy;
import com.epos.pospay.common.BActivity;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.SPUtil;

public class SplashActivity extends BActivity {

    class C02591 implements AnimationListener {
        C02591() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (TextUtils.isEmpty(SPUtil.get(SplashActivity.this, "weposId", "").toString())) {
                IntentUtil.gotoLogin(SplashActivity.this);
                SplashActivity.this.finish();
                return;
            }
            IntentUtil.gotoMainActivity(SplashActivity.this);
            SplashActivity.this.finish();
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.splash_layout;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setDuration(1500);
        ((ImageView) findViewById(C0258R.id.image)).startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new C02591());
    }
}
