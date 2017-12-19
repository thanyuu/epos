package com.epos.pospay;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cloudpos.apidemo.common.PrintBean;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.function.HandlerImpl;
import com.cloudpos.mvc.base.ActionCallback;
import com.cloudpos.mvc.base.ActionManager;
import com.epos.pospay.capture.ZxingProtocol.ResultBean.Root;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.MainApplication;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.model.CheckSuccessBean;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckSuccessFragment extends BaseFragment {
    private Root bean;
    private Button btn_print;
    private int count = 0;
    private boolean flag = false;
    Handler handler = new C02351();
    private boolean isExit;
    private ImageView iv;
    private List<PrintBean> printBeanList = new ArrayList();
    private Button print_again;
    private TextView r_content;
    private TextView text_print;
    private Button tryagain;
    private TextView tv_money1;
    private TextView tv_money2;
    private TextView tv_moneynumber1;
    private TextView tv_moneynumber2;
    private TextView tv_rate;
    private TextView tv_toast;

    class C02351 extends Handler {
        C02351() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.e("========轮询1次", "5s");
                    if (!CheckSuccessFragment.this.isExit && !CheckSuccessFragment.this.flag) {
                        CheckSuccessFragment.this.getOrderDetail();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class C02362 implements OnClickListener {
        C02362() {
        }

        public void onClick(View v) {
            if (CheckSuccessFragment.this.flag) {
                Fragment fragment = new CheckFragment();
                String title = CheckSuccessFragment.this.getString(C0258R.string.left_checkout);
                if (CheckSuccessFragment.this.getActivity() != null && (CheckSuccessFragment.this.getActivity() instanceof MainActivity)) {
                    ((MainActivity) CheckSuccessFragment.this.getActivity()).switchConent(fragment, title);
                    return;
                }
                return;
            }
            CheckSuccessFragment.this.getOrderDetail();
        }
    }

    class C02373 implements OnClickListener {
        C02373() {
        }

        public void onClick(View v) {
            CheckSuccessFragment.this.gotoCapture(CheckSuccessFragment.this.tv_moneynumber1.getText().toString());
        }
    }

    class C02384 implements OnClickListener {
        C02384() {
        }

        public void onClick(View v) {
            CheckSuccessFragment.this.print();
        }
    }

    class C02408 implements Runnable {
        C02408() {
        }

        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 1;
            CheckSuccessFragment.this.handler.sendMessage(message);
        }
    }

    class C05155 implements Listener<String> {
        C05155() {
        }

        public void onResponse(String response) {
            Log.e("============", "onResponse: " + response);
            try {
                CheckSuccessBean bean1 = (CheckSuccessBean) new Gson().fromJson(response, CheckSuccessBean.class);
                CheckSuccessFragment.this.bean.setMessageCode(bean1.getMessageCode());
                if (bean1.getOrderInfo() != null) {
                    if (bean1.getMessageCode().equals("SUCCESS")) {
                        CheckSuccessFragment.this.print_again.setVisibility(0);
                        CheckSuccessFragment.this.flag = true;
                        CheckSuccessFragment.this.iv.setImageResource(C0258R.mipmap.ok_circle);
                        CheckSuccessFragment.this.tv_toast.setTextColor(Color.parseColor("#3C763D"));
                        CheckSuccessFragment.this.tv_toast.setText(CheckSuccessFragment.this.getString(C0258R.string.payment));
                        CheckSuccessFragment.this.printContent(bean1);
                    } else {
                        CheckSuccessFragment.this.flag = false;
                        CheckSuccessFragment.this.iv.setImageResource(C0258R.mipmap.pay_error);
                        CheckSuccessFragment.this.tv_toast.setTextColor(Color.parseColor("#FF0000"));
                        CheckSuccessFragment.this.tv_toast.setText(bean1.getMessage());
                    }
                    CheckSuccessFragment.this.stopLoadingProgress();
                    CheckSuccessFragment.this.setButton();
                }
                CheckSuccessFragment.this.flag = false;
                CheckSuccessFragment.this.iv.setImageResource(C0258R.mipmap.pay_error);
                CheckSuccessFragment.this.tv_toast.setTextColor(Color.parseColor("#FF0000"));
                CheckSuccessFragment.this.tv_toast.setText(bean1.getMessage());
                CheckSuccessFragment.this.stopLoadingProgress();
                CheckSuccessFragment.this.setButton();
            } catch (Throwable e) {
                MobclickAgent.reportError(CheckSuccessFragment.this.getActivity(), e);
                e.printStackTrace();
            }
        }
    }

    class C05166 implements ErrorListener {
        C05166() {
        }

        public void onErrorResponse(VolleyError error) {
            CheckSuccessFragment.this.stopLoadingProgress();
            CheckSuccessFragment.this.flag = false;
            CheckSuccessFragment.this.iv.setImageResource(C0258R.mipmap.pay_error);
            CheckSuccessFragment.this.tv_toast.setText(CheckSuccessFragment.this.getString(C0258R.string.server_error));
            CheckSuccessFragment.this.setButton();
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.checksuccess_layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        this.bean = (Root) getArguments().getSerializable("param");
        this.tryagain = (Button) view.findViewById(C0258R.id.tryagain);
        this.print_again = (Button) view.findViewById(C0258R.id.print_again);
        this.btn_print = (Button) view.findViewById(C0258R.id.btn_print);
        this.tv_moneynumber1 = (TextView) view.findViewById(C0258R.id.tv_moneynumber1);
        this.tv_moneynumber2 = (TextView) view.findViewById(C0258R.id.tv_moneynumber2);
        this.tv_money1 = (TextView) view.findViewById(C0258R.id.tv_money1);
        this.tv_money2 = (TextView) view.findViewById(C0258R.id.tv_money2);
        this.tv_money1.setText(SPUtil.get(getActivity(), "feeType", "").toString());
        this.tv_money2.setText("Total Payment(" + SPUtil.get(getActivity(), "feeType", "").toString() + ")");
        this.tv_moneynumber1.setText(MainApplication.getInstance().getPriceNz());
        this.tv_moneynumber2.setText(MainApplication.getInstance().getTotal_fee());
        this.iv = (ImageView) view.findViewById(C0258R.id.iv);
        this.tv_toast = (TextView) view.findViewById(C0258R.id.tv_toast);
        this.tv_rate = (TextView) view.findViewById(C0258R.id.tv_rate);
        this.tv_rate.setText("Rate:" + SPUtil.get(getActivity(), "rate", "").toString());
        this.text_print = (TextView) view.findViewById(C0258R.id.text_print);
        this.r_content = (TextView) view.findViewById(C0258R.id.r_content);
        this.r_content.setText(MainApplication.getInstance().getRemark());
        initData();
        this.btn_print.setOnClickListener(new C02362());
        this.tryagain.setOnClickListener(new C02373());
        this.print_again.setOnClickListener(new C02384());
    }

    private void getOrderDetail() {
        HttpRequestManager.getInstance().checkSuccess(new C05155(), new C05166(), MainApplication.getInstance().getOrderNum(), MainApplication.getInstance().getTrans_id(), MainApplication.getInstance().getTransactionNumUnion()).commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }

    private void initData() {
        if (this.bean.getMessageCode().equals("SUCCESS")) {
            this.flag = true;
            this.iv.setImageResource(C0258R.mipmap.ok_circle);
            this.tv_toast.setTextColor(Color.parseColor("#3C763D"));
            this.tv_toast.setText(getString(C0258R.string.payment));
            getOrderDetail();
        } else {
            this.flag = false;
            this.iv.setImageResource(C0258R.mipmap.pay_error);
            this.tv_toast.setTextColor(Color.parseColor("#FF0000"));
            this.tv_toast.setText(this.bean.getMessage());
            if (this.bean.getMessageCode().equals("USERPAYING")) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        handler.postDelayed(this, 10000);
                        if (CheckSuccessFragment.this.count >= 4 || CheckSuccessFragment.this.isExit || CheckSuccessFragment.this.flag || !CheckSuccessFragment.this.bean.getMessageCode().equals("USERPAYING")) {
                            CheckSuccessFragment.this.count = 0;
                            handler.removeCallbacks(this);
                        } else {
                            CheckSuccessFragment.this.getOrderDetail();
                        }
                        CheckSuccessFragment.this.count = CheckSuccessFragment.this.count + 1;
                    }
                }, 10000);
            } else if (this.bean.getMessageCode().equals("PAYERROR")) {
                new Thread(new C02408()).start();
            } else if (this.bean.getMessageCode().equals("REVOKED")) {
                this.tryagain.setVisibility(0);
            }
        }
        setButton();
    }

    private void setButton() {
        if (this.flag) {
            this.btn_print.setText(getString(C0258R.string.print_back));
        } else {
            this.btn_print.setText(getString(C0258R.string.retry));
        }
    }

    public void onPause() {
        super.onPause();
        this.isExit = true;
    }

    private void print() {
        Log.e("===============", "print: ");
        ActionCallback callback = new ActionCallbackImpl(new HandlerImpl(this.text_print));
        Map param = new HashMap();
        param.put("host", getActivity());
        ActionManager.doSubmit("PrinterAction/open", getActivity(), param, callback);
        param.clear();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.text_print.getText().toString().contains("失败")) {
            param.put("host", getActivity());
            ActionManager.doSubmit("PrinterAction/open", getActivity(), param, callback);
            param.clear();
        }
        param.put("host", getActivity());
        param.put("content", this.printBeanList);
        ActionManager.doSubmit("PrinterAction/write", getActivity(), param, callback);
        Log.e("===============", "printBeanList: " + this.printBeanList);
    }

    private void setPrintContent(String content, int type) {
        PrintBean bean = new PrintBean();
        bean.setContent(content);
        bean.setType(type);
        if (!TextUtils.isEmpty(content)) {
            this.printBeanList.add(bean);
        }
    }

    private void printContent(final CheckSuccessBean bean) {
        this.printBeanList.clear();
        final int count = ((Integer) SPUtil.get(getActivity(), "printCount", Integer.valueOf(1))).intValue();
        if (TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdQrcode())) {
            String timeZone;
            if (!TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdRemark())) {
                setPrintContent(bean.getOrderInfo().getTransactionOrg().getAdRemark(), 2);
            }
            setPrintContent("MerchantName: " + SPUtil.get(getActivity(), "orgName", ""), 1);
            setPrintContent("Tel: " + SPUtil.get(getActivity(), "tel", ""), 1);
            setPrintContent("Address: " + bean.getOrderInfo().getTransactionOrg().getOrgAddress(), 1);
            setPrintContent("", 3);
            setPrintContent("TransactionNumber: \n" + bean.getOrderInfo().getTransactionId(), 1);
            setPrintContent("SerialNumber: \n" + bean.getOrderInfo().getTransactionNum(), 1);
            if (TextUtils.isEmpty((CharSequence) SPUtil.get(getActivity(), "timeZone", ""))) {
                timeZone = "";
            } else {
                timeZone = "(" + SPUtil.get(getActivity(), "timeZone", "") + ")";
            }
            setPrintContent("Date" + timeZone + ": " + bean.getOrderInfo().getTransactionDateNZ(), 1);
            setPrintContent("FeeType: " + bean.getOrderInfo().getCurrencyType(), 1);
            if (bean.getOrderInfo().getExchangeRate() > 0.0d) {
                setPrintContent("Rate: " + bean.getOrderInfo().getExchangeRate(), 1);
            }
            setPrintContent("Amount: $" + bean.getOrderInfo().getAmountNZD() + "  ￥" + bean.getOrderInfo().getAmountRMB(), 1);
            setPrintContent("State: " + bean.getOrderInfo().getTransactionState(), 1);
            setPrintContent("", 3);
            setPrintContent("     ", 1);
            setPrintContent("     ", 1);
            for (int i = 0; i < count; i++) {
                print();
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        Glide.with((Fragment) this).load(bean.getOrderInfo().getTransactionOrg().getAdQrcode()).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                String timeZone;
                CheckSuccessFragment.this.setPrintBitmap(resource);
                if (!TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdRemark())) {
                    CheckSuccessFragment.this.setPrintContent(bean.getOrderInfo().getTransactionOrg().getAdRemark(), 2);
                }
                CheckSuccessFragment.this.setPrintContent("MerchantName: " + SPUtil.get(CheckSuccessFragment.this.getActivity(), "orgName", ""), 1);
                CheckSuccessFragment.this.setPrintContent("Tel: " + SPUtil.get(CheckSuccessFragment.this.getActivity(), "tel", ""), 1);
                CheckSuccessFragment.this.setPrintContent("Address: " + bean.getOrderInfo().getTransactionOrg().getOrgAddress(), 1);
                CheckSuccessFragment.this.setPrintContent("", 3);
                CheckSuccessFragment.this.setPrintContent("TransactionNumber: \n" + bean.getOrderInfo().getTransactionId(), 1);
                CheckSuccessFragment.this.setPrintContent("SerialNumber: \n" + bean.getOrderInfo().getTransactionNum(), 1);
                if (TextUtils.isEmpty((CharSequence) SPUtil.get(CheckSuccessFragment.this.getActivity(), "timeZone", ""))) {
                    timeZone = "";
                } else {
                    timeZone = "(" + SPUtil.get(CheckSuccessFragment.this.getActivity(), "timeZone", "") + ")";
                }
                CheckSuccessFragment.this.setPrintContent("Date" + timeZone + ": " + bean.getOrderInfo().getTransactionDateNZ(), 1);
                CheckSuccessFragment.this.setPrintContent("FeeType: " + bean.getOrderInfo().getCurrencyType(), 1);
                if (bean.getOrderInfo().getExchangeRate() > 0.0d) {
                    CheckSuccessFragment.this.setPrintContent("Rate: " + bean.getOrderInfo().getExchangeRate(), 1);
                }
                CheckSuccessFragment.this.setPrintContent("Amount: $" + bean.getOrderInfo().getAmountNZD() + "  ￥" + bean.getOrderInfo().getAmountRMB(), 1);
                CheckSuccessFragment.this.setPrintContent("State: " + bean.getOrderInfo().getTransactionState(), 1);
                CheckSuccessFragment.this.setPrintContent("", 3);
                CheckSuccessFragment.this.setPrintContent("     ", 1);
                CheckSuccessFragment.this.setPrintContent("     ", 1);
                for (int i = 0; i < count; i++) {
                    CheckSuccessFragment.this.print();
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setPrintBitmap(Bitmap bitmap) {
        PrintBean bean = new PrintBean();
        bean.setBitmap(bitmap);
        bean.setType(4);
        this.printBeanList.add(bean);
    }

    private void gotoCapture(String money) {
        if (Float.parseFloat(money) == 0.0f) {
            Toast.makeText(getActivity(), getString(C0258R.string.pay_toast), 0).show();
        } else {
            IntentUtil.gotoCaptureActivity(getActivity());
        }
    }
}
