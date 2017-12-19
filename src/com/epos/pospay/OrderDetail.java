package com.epos.pospay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.epos.pospay.common.BActivity;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.common.VolleyErrorHelper;
import com.epos.pospay.model.CheckSuccessBean;
import com.epos.pospay.model.OrderRefundBean;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetail extends BActivity {
    private EditText beizhu;
    private Button btn;
    private Button btn_print;
    private Button btn_return;
    private CheckSuccessBean checkSuccessBean;
    private EditText ed_pass;
    private EditText et_refund;
    private String flag;
    private String money;
    private TextView order_num;
    private String orderno;
    private ImageView payType;
    private List<PrintBean> printBeanList = new ArrayList();
    private TextView re2_trade;
    private TextView re3_tv_ordernum;
    private TextView text_print;
    private String time;
    private String transactionId;
    private String transactionNumUnion;
    private TextView tv_money;
    private TextView tv_paysuccess;
    private TextView tv_reason;
    private TextView tv_status;
    private TextView tv_time;
    private TextView tv_trade;

    class C02411 implements OnClickListener {
        C02411() {
        }

        public void onClick(View v) {
            if (OrderDetail.this.ver(OrderDetail.this.ed_pass.getText().toString())) {
                OrderDetail.this.initData(OrderDetail.this.transactionId, OrderDetail.this.beizhu.getText().toString(), OrderDetail.this.ed_pass.getText().toString(), OrderDetail.this.et_refund.getText().toString());
            } else {
                Toast.makeText(OrderDetail.this, OrderDetail.this.getString(C0258R.string.enterpass), 0).show();
            }
        }
    }

    class C02422 implements OnClickListener {
        C02422() {
        }

        public void onClick(View v) {
            OrderDetail.this.printContent(OrderDetail.this.checkSuccessBean);
        }
    }

    class C02433 implements OnClickListener {
        C02433() {
        }

        public void onClick(View v) {
            IntentUtil.gotoMainActivity(OrderDetail.this, IntentUtil.ORDERFRAGMENT);
        }
    }

    class C05204 implements Listener<String> {
        C05204() {
        }

        public void onResponse(String response) {
            OrderDetail.this.stopLoadingProgress();
            OrderDetail.this.showBack();
            OrderRefundBean orderRefundBean = (OrderRefundBean) new Gson().fromJson(response, OrderRefundBean.class);
            Log.e("=========", "onResponse: " + response);
            if (orderRefundBean.isSuccess()) {
                OrderDetail.this.hideKeyBoard();
                Toast.makeText(OrderDetail.this, OrderDetail.this.getString(C0258R.string.refundSuccess), 0).show();
                OrderDetail.this.btn.setVisibility(8);
                OrderDetail.this.findViewById(C0258R.id.re2).setVisibility(8);
                OrderDetail.this.tv_status.setText(OrderDetail.this.getString(C0258R.string.refundSuccess));
                OrderDetail.this.tv_paysuccess.setText(OrderDetail.this.getString(C0258R.string.refundSuccess));
                OrderDetail.this.tv_money.setText(SPUtil.get(OrderDetail.this, "feeType", "").toString() + " " + OrderDetail.this.et_refund.getText().toString());
                return;
            }
            OrderDetail.this.hideKeyBoard();
            Toast.makeText(OrderDetail.this, orderRefundBean.getMessage(), 0).show();
        }
    }

    class C05215 implements ErrorListener {
        C05215() {
        }

        public void onErrorResponse(VolleyError error) {
            OrderDetail.this.hideKeyBoard();
            OrderDetail.this.stopLoadingProgress();
            OrderDetail.this.showBack();
            VolleyErrorHelper.showErrorMessage(OrderDetail.this, error);
        }
    }

    class C05226 implements Listener<String> {
        C05226() {
        }

        public void onResponse(String response) {
            try {
                CheckSuccessBean bean = (CheckSuccessBean) new Gson().fromJson(response, CheckSuccessBean.class);
                OrderDetail.this.checkSuccessBean = bean;
                if (bean.getOrderInfo() != null) {
                    OrderDetail.this.et_refund.setText(((bean.getOrderInfo().getPrice() - bean.getOrderInfo().getRefundTotal()) - bean.getOrderInfo().getCouponFee()) + "");
                    OrderDetail.this.setLogo(bean.getOrderInfo().getTransactionType());
                    if (bean.getMessageCode().equals("SUCCESS")) {
                        OrderDetail.this.btn_print.setVisibility(0);
                        OrderDetail.this.btn.setVisibility(0);
                        OrderDetail.this.findViewById(C0258R.id.re2).setVisibility(0);
                        OrderDetail.this.findViewById(C0258R.id.re3).setVisibility(8);
                        OrderDetail.this.tv_status.setVisibility(0);
                        OrderDetail.this.tv_paysuccess.setText(OrderDetail.this.getString(C0258R.string.pay_success));
                        OrderDetail.this.order_num.setText(bean.getOrderInfo().getTransactionNum());
                        OrderDetail.this.re2_trade.setText(bean.getOrderInfo().getTransactionId());
                    } else {
                        OrderDetail.this.btn.setVisibility(8);
                        OrderDetail.this.btn_print.setVisibility(8);
                        OrderDetail.this.findViewById(C0258R.id.re2).setVisibility(8);
                        OrderDetail.this.findViewById(C0258R.id.re3).setVisibility(0);
                        OrderDetail.this.tv_status.setVisibility(8);
                        OrderDetail.this.tv_paysuccess.setText(bean.getMessageCode());
                        OrderDetail.this.re3_tv_ordernum.setText(bean.getOrderInfo().getTransactionNum());
                        OrderDetail.this.tv_trade.setText(bean.getOrderInfo().getTransactionId());
                        OrderDetail.this.tv_reason.setText(bean.getMessage());
                    }
                    OrderDetail.this.stopLoadingProgress();
                }
                OrderDetail.this.tv_status.setVisibility(8);
                OrderDetail.this.stopLoadingProgress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C05237 implements ErrorListener {
        C05237() {
        }

        public void onErrorResponse(VolleyError error) {
            OrderDetail.this.btn.setVisibility(8);
            OrderDetail.this.btn_print.setVisibility(8);
            OrderDetail.this.findViewById(C0258R.id.re2).setVisibility(8);
            OrderDetail.this.tv_status.setVisibility(8);
            VolleyErrorHelper.showErrorMessage(OrderDetail.this, error);
            OrderDetail.this.stopLoadingProgress();
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.orderdetail_layout;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        this.transactionId = getIntent().getStringExtra("id");
        this.transactionNumUnion = getIntent().getStringExtra("transactionNumUnion");
        this.money = getIntent().getStringExtra("money");
        this.time = getIntent().getStringExtra("time");
        this.orderno = getIntent().getStringExtra("orderno");
        this.flag = getIntent().getStringExtra("flag");
        this.tv_money = (TextView) findViewById(C0258R.id.tv_money);
        this.ed_pass = (EditText) findViewById(C0258R.id.ed_pass);
        this.tv_status = (TextView) findViewById(C0258R.id.tv_status);
        this.tv_time = (TextView) findViewById(C0258R.id.tv_time);
        this.beizhu = (EditText) findViewById(C0258R.id.beizhu);
        this.order_num = (TextView) findViewById(C0258R.id.tv_ordernum);
        this.tv_paysuccess = (TextView) findViewById(C0258R.id.tv_paysuccess);
        this.btn = (Button) findViewById(C0258R.id.btn);
        this.btn_print = (Button) findViewById(C0258R.id.btn_print);
        this.btn_return = (Button) findViewById(C0258R.id.btn_return);
        this.re2_trade = (TextView) findViewById(C0258R.id.re2_trade);
        this.re3_tv_ordernum = (TextView) findViewById(C0258R.id.re3_tv_ordernum);
        this.tv_trade = (TextView) findViewById(C0258R.id.tv_trade);
        this.tv_reason = (TextView) findViewById(C0258R.id.tv_reason);
        this.text_print = (TextView) findViewById(C0258R.id.text_print);
        this.et_refund = (EditText) findViewById(C0258R.id.et_refund);
        this.payType = (ImageView) findViewById(C0258R.id.payType);
        this.tv_money.setText(SPUtil.get(this, "feeType", "").toString() + " " + this.money);
        this.tv_time.setText(this.time);
        getOrderDetail();
        this.btn.setOnClickListener(new C02411());
        this.btn_print.setOnClickListener(new C02422());
        this.btn_return.setOnClickListener(new C02433());
    }

    private void initData(String transactionId, String refundInstructions, String refundPassword, String refund_amount) {
        HttpRequestManager.getInstance().refundOrder(new C05204(), new C05215(), this, transactionId, refundInstructions, refundPassword, refund_amount).commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }

    private void showBack() {
        this.btn_return.setVisibility(0);
        this.btn_print.setVisibility(8);
    }

    private boolean ver(String pass) {
        return !TextUtils.isEmpty(pass);
    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void getOrderDetail() {
        HttpRequestManager.getInstance().checkSuccess(new C05226(), new C05237(), this.orderno, this.transactionId, this.transactionNumUnion).commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }

    private void setLogo(String logo) {
        Object obj = -1;
        switch (logo.hashCode()) {
            case -1738440922:
                if (logo.equals("WECHAT")) {
                    obj = 1;
                    break;
                }
                break;
            case 70445326:
                if (logo.equals("JDPAY")) {
                    obj = 3;
                    break;
                }
                break;
            case 500345892:
                if (logo.equals("BESTPAY")) {
                    obj = 2;
                    break;
                }
                break;
            case 1933336138:
                if (logo.equals("ALIPAY")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.payType.setImageResource(C0258R.mipmap.zfb);
                return;
            case 1:
                this.payType.setImageResource(C0258R.mipmap.wx);
                return;
            case 2:
                this.payType.setImageResource(C0258R.mipmap.yzf);
                return;
            case 3:
                this.payType.setImageResource(C0258R.mipmap.jd);
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Bundle bundle = new Bundle();
        bundle.putString("result", "detail");
        startActivity(new Intent(this, MainActivity.class).putExtras(bundle));
        finish();
    }

    public void onback(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("result", "detail");
        startActivity(new Intent(this, MainActivity.class).putExtras(bundle));
        finish();
    }

    private void printContent(final CheckSuccessBean bean) {
        this.printBeanList.clear();
        if (TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdQrcode())) {
            if (!TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdRemark())) {
                setPrintContent(bean.getOrderInfo().getTransactionOrg().getAdRemark(), 2);
            }
            setPrintContent("MerchantName: " + SPUtil.get(this, "orgName", ""), 1);
            setPrintContent("Tel: " + SPUtil.get(this, "tel", ""), 1);
            setPrintContent("Address: " + bean.getOrderInfo().getTransactionOrg().getOrgAddress(), 1);
            setPrintContent("", 3);
            setPrintContent("TransactionNumber: \n" + bean.getOrderInfo().getTransactionId(), 1);
            setPrintContent("SerialNumber: \n" + bean.getOrderInfo().getTransactionNum(), 1);
            setPrintContent("Date" + (TextUtils.isEmpty((CharSequence) SPUtil.get(this, "timeZone", "")) ? "" : "(" + SPUtil.get(this, "timeZone", "") + ")") + ": " + bean.getOrderInfo().getTransactionDateNZ(), 1);
            setPrintContent("FeeType: " + bean.getOrderInfo().getCurrencyType(), 1);
            if (bean.getOrderInfo().getExchangeRate() > 0.0d) {
                setPrintContent("Rate: " + bean.getOrderInfo().getExchangeRate(), 1);
            }
            setPrintContent("Amount: $" + bean.getOrderInfo().getAmountNZD() + "  ￥" + bean.getOrderInfo().getAmountRMB(), 1);
            setPrintContent("State: " + bean.getOrderInfo().getTransactionState(), 1);
            setPrintContent("", 3);
            setPrintContent("     ", 1);
            setPrintContent("     ", 1);
            print();
            return;
        }
        Glide.with((FragmentActivity) this).load(bean.getOrderInfo().getTransactionOrg().getAdQrcode()).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                OrderDetail.this.setPrintBitmap(resource);
                if (!TextUtils.isEmpty(bean.getOrderInfo().getTransactionOrg().getAdRemark())) {
                    OrderDetail.this.setPrintContent(bean.getOrderInfo().getTransactionOrg().getAdRemark(), 2);
                }
                OrderDetail.this.setPrintContent("MerchantName: " + SPUtil.get(OrderDetail.this, "orgName", ""), 1);
                OrderDetail.this.setPrintContent("Tel: " + SPUtil.get(OrderDetail.this, "tel", ""), 1);
                OrderDetail.this.setPrintContent("Address: " + bean.getOrderInfo().getTransactionOrg().getOrgAddress(), 1);
                OrderDetail.this.setPrintContent("", 3);
                OrderDetail.this.setPrintContent("TransactionNumber: \n" + bean.getOrderInfo().getTransactionId(), 1);
                OrderDetail.this.setPrintContent("SerialNumber: \n" + bean.getOrderInfo().getTransactionNum(), 1);
                OrderDetail.this.setPrintContent("Date" + (TextUtils.isEmpty((CharSequence) SPUtil.get(OrderDetail.this, "timeZone", "")) ? "" : "(" + SPUtil.get(OrderDetail.this, "timeZone", "") + ")") + ": " + bean.getOrderInfo().getTransactionDateNZ(), 1);
                OrderDetail.this.setPrintContent("FeeType: " + bean.getOrderInfo().getCurrencyType(), 1);
                if (bean.getOrderInfo().getExchangeRate() > 0.0d) {
                    OrderDetail.this.setPrintContent("Rate: " + bean.getOrderInfo().getExchangeRate(), 1);
                }
                OrderDetail.this.setPrintContent("Amount: $" + bean.getOrderInfo().getAmountNZD() + "  ￥" + bean.getOrderInfo().getAmountRMB(), 1);
                OrderDetail.this.setPrintContent("State: " + bean.getOrderInfo().getTransactionState(), 1);
                OrderDetail.this.setPrintContent("", 3);
                OrderDetail.this.setPrintContent("     ", 1);
                OrderDetail.this.setPrintContent("     ", 1);
                OrderDetail.this.print();
            }
        });
    }

    private void print() {
        Log.e("===============", "print: ");
        ActionCallback callback = new ActionCallbackImpl(new HandlerImpl(this.text_print));
        Map param = new HashMap();
        param.put("host", this);
        ActionManager.doSubmit("PrinterAction/open", (Context) this, param, callback);
        param.clear();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.text_print.getText().toString().contains("失败")) {
            param.put("host", this);
            ActionManager.doSubmit("PrinterAction/open", (Context) this, param, callback);
            param.clear();
        }
        param.put("host", this);
        param.put("content", this.printBeanList);
        ActionManager.doSubmit("PrinterAction/write", (Context) this, param, callback);
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

    private void setPrintBitmap(Bitmap bitmap) {
        PrintBean bean = new PrintBean();
        bean.setBitmap(bitmap);
        bean.setType(4);
        this.printBeanList.add(bean);
    }
}
