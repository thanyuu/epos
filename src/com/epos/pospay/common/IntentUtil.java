package com.epos.pospay.common;

import android.app.Activity;
import android.content.Intent;
import com.epos.pospay.MainActivity;
import com.epos.pospay.OrderDetail;
import com.epos.pospay.capture.CaptureActivity;
import com.epos.pospay.login.LoginActivity;

public class IntentUtil {
    public static final String ORDERFRAGMENT = "orderfragment";

    public static void gotoMainActivity(Activity act) {
        act.startActivity(new Intent(act, MainActivity.class));
    }

    public static void gotoMainActivity(Activity act, String flag) {
        Intent intent = new Intent(act, MainActivity.class);
        intent.putExtra("flag", flag);
        act.startActivity(intent);
    }

    public static void gotoLogin(Activity act) {
        act.startActivity(new Intent(act, LoginActivity.class));
    }

    public static void gotoCaptureActivity(Activity act) {
        act.startActivity(new Intent(act, CaptureActivity.class));
        act.finish();
    }

    public static void gotoOrderDetail(Activity act, String id, String money, String time, String orderno, String flag, String transactionNumUnion) {
        Intent intent = new Intent(act, OrderDetail.class);
        intent.putExtra("id", id);
        intent.putExtra("money", money);
        intent.putExtra("time", time);
        intent.putExtra("orderno", orderno);
        intent.putExtra("flag", flag);
        intent.putExtra("transactionNumUnion", transactionNumUnion);
        act.startActivity(intent);
    }
}
