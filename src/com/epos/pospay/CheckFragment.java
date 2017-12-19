package com.epos.pospay;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.common.utils.FileUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.cloudpos.scanserver.aidl.AidlController;
import com.cloudpos.scanserver.aidl.IAIDLListener;
import com.cloudpos.scanserver.aidl.IScanCallBack.Stub;
import com.cloudpos.scanserver.aidl.IScanService;
import com.cloudpos.scanserver.aidl.ScanParameter;
import com.cloudpos.scanserver.aidl.ScanResult;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.CommonUtil;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.MainApplication;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.model.RateBean;
import com.epos.pospay.zxing.utils.CalcRate;
import com.google.gson.Gson;
import java.text.DecimalFormat;

public class CheckFragment extends BaseFragment implements OnClickListener, IAIDLListener {
    private RelativeLayout all;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btndel;
    private Button btnpoint;
    private CheckBox checkBox;
    private String custom_fee;
    private TextView fee;
    private Handler handler = new C02346();
    private IAIDLListener listener;
    private Context mContext;
    private Float max_money;
    private EditText r_content;
    private ServiceConnection scanConn;
    private IScanService scanService;
    private TextView tv_cnynumber;
    private TextView tv_money1;
    private TextView tv_money2;
    private TextView tv_moneynumber1;
    private TextView tv_moneynumber2;
    private TextView tv_nzd;
    private TextView tv_nzdnumber;
    private TextView tv_rate;

    class C02301 implements OnClickListener {
        C02301() {
        }

        public void onClick(View v) {
            if (CheckFragment.this.checkBox.isChecked()) {
                CheckFragment.this.gotoCapture(CheckFragment.this.tv_nzdnumber.getText().toString());
            } else {
                CheckFragment.this.gotoCapture(CheckFragment.this.tv_moneynumber1.getText().toString());
            }
        }
    }

    class C02312 implements OnClickListener {
        C02312() {
        }

        public void onClick(View v) {
            CommonUtil.hideKeyBoard(CheckFragment.this.getActivity());
            CheckFragment.this.r_content.setFocusable(false);
            CheckFragment.this.r_content.clearFocus();
            CheckFragment.this.r_content.setFocusableInTouchMode(true);
        }
    }

    class C02323 implements OnCheckedChangeListener {
        C02323() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                CheckFragment.this.setCustomPrice();
                MainApplication.getInstance().setCustomer_poundageFee("CUSTOMER");
            } else {
                CheckFragment.this.tv_nzdnumber.setText(CheckFragment.this.tv_moneynumber1.getText().toString());
                CheckFragment.this.tv_cnynumber.setText(CheckFragment.this.tv_moneynumber2.getText().toString());
                CheckFragment.this.tv_moneynumber2.setText(CheckFragment.this.tv_moneynumber1.getText().toString());
                MainApplication.getInstance().setCustomer_poundageFee("MERCHANT");
            }
            MainApplication.getInstance().setTotal_fee(CheckFragment.this.tv_moneynumber2.getText().toString());
        }
    }

    class C02334 implements TextWatcher {
        C02334() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            Log.e("=========", "afterTextChanged: " + s.toString());
            MainApplication.getInstance().setRemark(s.toString());
        }
    }

    class C02346 extends Handler {
        C02346() {
        }

        public void handleMessage(Message msg) {
            ScanResult obj = msg.obj;
            switch (msg.what) {
                case 0:
                    Toast.makeText(CheckFragment.this.getContext(), "user cancel", 0).show();
                    return;
                case 1:
                    try {
                        CheckFragment.this.scanService.stopScan();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("result", obj.getText());
                    CheckFragment.this.startActivity(new Intent(CheckFragment.this.getContext(), MainActivity.class).putExtras(bundle));
                    return;
                case 2:
                    CheckFragment.this.stopLoadingProgress();
                    return;
                default:
                    CheckFragment.this.stopLoadingProgress();
                    Toast.makeText(CheckFragment.this.getContext(), obj.getText(), 0).show();
                    return;
            }
        }
    }

    class C05137 implements Listener<String> {
        C05137() {
        }

        public void onResponse(String response) {
            Log.e("=======", "getRateonResponse: " + response);
            RateBean rateBean = (RateBean) new Gson().fromJson(response, RateBean.class);
            if (rateBean.isSuccess()) {
                CheckFragment.this.custom_fee = rateBean.getPoundageRate();
                SPUtil.put(CheckFragment.this.getContext(), "rate", rateBean.getRate());
                CheckFragment.this.tv_rate.setText("Rate Info: 1 " + SPUtil.get(CheckFragment.this.getContext(), "feeType", "").toString() + " ≈ " + SPUtil.get(CheckFragment.this.getContext(), "rate", "").toString() + " CNY (Today)");
                Log.e("=======", "onResponse: " + rateBean.getRate());
                if (rateBean.getPoundagePayer().equals("CUSTOMER")) {
                    CheckFragment.this.checkBox.setChecked(true);
                } else {
                    CheckFragment.this.checkBox.setChecked(false);
                }
                CheckFragment.this.fee.setText(rateBean.getPoundageRate());
                CheckFragment.this.setCustomPrice();
            }
        }
    }

    class C05148 implements ErrorListener {
        C05148() {
        }

        public void onErrorResponse(VolleyError error) {
            Log.e("=========", "onErrorResponse: " + error);
        }
    }

    class C06735 extends Stub {
        C06735() {
        }

        public void foundBarcode(ScanResult result) {
            Message message = new Message();
            message.what = result.getResultCode();
            message.obj = result;
            CheckFragment.this.handler.sendMessage(message);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    public void onResume() {
        super.onResume();
        getRate();
    }

    private void initUI(View view) {
        this.mContext = getContext();
        this.listener = this;
        AidlController.getInstance().startScanService(this.mContext, this.listener);
        ((RelativeLayout) view.findViewById(C0258R.id.btn_pay)).setOnClickListener(new C02301());
        this.r_content = (EditText) view.findViewById(C0258R.id.r_content);
        this.tv_moneynumber1 = (TextView) view.findViewById(C0258R.id.tv_moneynumber1);
        this.tv_moneynumber2 = (TextView) view.findViewById(C0258R.id.tv_moneynumber2);
        this.tv_money1 = (TextView) view.findViewById(C0258R.id.tv_money1);
        this.tv_money2 = (TextView) view.findViewById(C0258R.id.tv_money2);
        this.tv_rate = (TextView) view.findViewById(C0258R.id.tv_rate);
        this.all = (RelativeLayout) view.findViewById(C0258R.id.all);
        this.all.setOnClickListener(new C02312());
        this.tv_rate.setText("Rate Info: 1 " + SPUtil.get(getContext(), "feeType", "").toString() + " ≈ " + SPUtil.get(getContext(), "rate", "").toString() + " CNY (Today)");
        this.tv_money2.setText("Total Payment(" + SPUtil.get(getContext(), "feeType", "").toString() + ")");
        this.max_money = Float.valueOf(Float.parseFloat(SPUtil.get(getContext(), "maxPayment", "0").toString()));
        this.tv_money1.setText(SPUtil.get(getContext(), "feeType", "").toString());
        if (this.max_money.floatValue() == 0.0f) {
            this.max_money = Float.valueOf(50000.0f);
        }
        this.checkBox = (CheckBox) view.findViewById(C0258R.id.r_checkbox);
        this.checkBox.setOnCheckedChangeListener(new C02323());
        this.tv_nzd = (TextView) view.findViewById(C0258R.id.tv_nzd);
        this.tv_nzdnumber = (TextView) view.findViewById(C0258R.id.tv_nzdnumber);
        this.tv_cnynumber = (TextView) view.findViewById(C0258R.id.tv_cnynumber);
        this.fee = (TextView) view.findViewById(C0258R.id.fee);
        this.tv_nzd.setText(SPUtil.get(getContext(), "feeType", "").toString());
        this.tv_nzdnumber.setText(this.tv_moneynumber1.getText().toString());
        this.tv_cnynumber.setText(this.tv_moneynumber2.getText().toString());
        this.r_content.addTextChangedListener(new C02334());
        this.btndel = (Button) view.findViewById(C0258R.id.btndel);
        this.btnpoint = (Button) view.findViewById(C0258R.id.btnpoint);
        this.btn0 = (Button) view.findViewById(C0258R.id.btn0);
        this.btn1 = (Button) view.findViewById(C0258R.id.btn1);
        this.btn2 = (Button) view.findViewById(C0258R.id.btn2);
        this.btn3 = (Button) view.findViewById(C0258R.id.btn3);
        this.btn4 = (Button) view.findViewById(C0258R.id.btn4);
        this.btn5 = (Button) view.findViewById(C0258R.id.btn5);
        this.btn6 = (Button) view.findViewById(C0258R.id.btn6);
        this.btn7 = (Button) view.findViewById(C0258R.id.btn7);
        this.btn8 = (Button) view.findViewById(C0258R.id.btn8);
        this.btn9 = (Button) view.findViewById(C0258R.id.btn9);
        this.btndel.setOnClickListener(this);
        this.btnpoint.setOnClickListener(this);
        this.btn0.setOnClickListener(this);
        this.btn1.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
        this.btn4.setOnClickListener(this);
        this.btn5.setOnClickListener(this);
        this.btn6.setOnClickListener(this);
        this.btn7.setOnClickListener(this);
        this.btn8.setOnClickListener(this);
        this.btn9.setOnClickListener(this);
    }

    private void setCustomPrice() {
        String money = new DecimalFormat("0.00").format((double) (Float.valueOf(this.tv_moneynumber1.getText().toString()).floatValue() * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT + CommonUtil.getFee(this.custom_fee).floatValue())));
        if (money.equals("0.00")) {
            this.tv_nzdnumber.setText("0");
        } else {
            this.tv_nzdnumber.setText(money);
        }
        this.tv_cnynumber.setText(CalcRate.calcRate(SPUtil.get(getContext(), "rate", "0").toString(), money));
        setTotalMoney();
    }

    private void setTotalMoney() {
        String money = new DecimalFormat("0.00").format((double) (Float.valueOf(this.tv_moneynumber1.getText().toString()).floatValue() * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT + CommonUtil.getFee(this.custom_fee).floatValue())));
        if (money.equals("0.00")) {
            this.tv_moneynumber2.setText("0");
        } else {
            this.tv_moneynumber2.setText(money);
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.checkout_fragment;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r9, android.view.KeyEvent r10) {
        /*
        r8 = this;
        r7 = 1;
        r4 = "=========";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "onKeyDown: ";
        r5 = r5.append(r6);
        r5 = r5.append(r9);
        r5 = r5.toString();
        android.util.Log.e(r4, r5);
        r4 = r8.tv_moneynumber1;
        r4 = r4.getText();
        r1 = r4.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r4 = 7;
        if (r9 != r4) goto L_0x0087;
    L_0x002e:
        r4 = java.lang.Float.parseFloat(r1);
        r5 = 0;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 != 0) goto L_0x003f;
    L_0x0037:
        r4 = ".";
        r4 = r1.contains(r4);
        if (r4 == 0) goto L_0x0044;
    L_0x003f:
        r4 = "0";
        r2.append(r4);
    L_0x0044:
        r4 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r4 = java.lang.Float.parseFloat(r4);	 Catch:{ Exception -> 0x017a }
        r5 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 < 0) goto L_0x0063;
    L_0x0052:
        r4 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r5 = 0;
        r4 = r4.charAt(r5);	 Catch:{ Exception -> 0x017a }
        r5 = 48;
        if (r4 != r5) goto L_0x0063;
    L_0x005f:
        r4 = 0;
        r2.deleteCharAt(r4);	 Catch:{ Exception -> 0x017a }
    L_0x0063:
        r4 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r5 = ".";
        r4 = r4.contains(r5);	 Catch:{ Exception -> 0x017a }
        if (r4 == 0) goto L_0x0153;
    L_0x006f:
        r4 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r5 = "\\.";
        r3 = r4.split(r5);	 Catch:{ Exception -> 0x017a }
        r4 = r3.length;	 Catch:{ Exception -> 0x017a }
        if (r4 <= r7) goto L_0x0153;
    L_0x007c:
        r4 = 1;
        r4 = r3[r4];	 Catch:{ Exception -> 0x017a }
        r4 = r4.length();	 Catch:{ Exception -> 0x017a }
        r5 = 2;
        if (r4 <= r5) goto L_0x0153;
    L_0x0086:
        return r7;
    L_0x0087:
        r4 = 8;
        if (r9 != r4) goto L_0x0091;
    L_0x008b:
        r4 = "1";
        r2.append(r4);
        goto L_0x0044;
    L_0x0091:
        r4 = 9;
        if (r9 != r4) goto L_0x009b;
    L_0x0095:
        r4 = "2";
        r2.append(r4);
        goto L_0x0044;
    L_0x009b:
        r4 = 10;
        if (r9 != r4) goto L_0x00a5;
    L_0x009f:
        r4 = "3";
        r2.append(r4);
        goto L_0x0044;
    L_0x00a5:
        r4 = 11;
        if (r9 != r4) goto L_0x00af;
    L_0x00a9:
        r4 = "4";
        r2.append(r4);
        goto L_0x0044;
    L_0x00af:
        r4 = 12;
        if (r9 != r4) goto L_0x00b9;
    L_0x00b3:
        r4 = "5";
        r2.append(r4);
        goto L_0x0044;
    L_0x00b9:
        r4 = 13;
        if (r9 != r4) goto L_0x00c3;
    L_0x00bd:
        r4 = "6";
        r2.append(r4);
        goto L_0x0044;
    L_0x00c3:
        r4 = 14;
        if (r9 != r4) goto L_0x00ce;
    L_0x00c7:
        r4 = "7";
        r2.append(r4);
        goto L_0x0044;
    L_0x00ce:
        r4 = 15;
        if (r9 != r4) goto L_0x00d9;
    L_0x00d2:
        r4 = "8";
        r2.append(r4);
        goto L_0x0044;
    L_0x00d9:
        r4 = 16;
        if (r9 != r4) goto L_0x00e4;
    L_0x00dd:
        r4 = "9";
        r2.append(r4);
        goto L_0x0044;
    L_0x00e4:
        r4 = 56;
        if (r9 != r4) goto L_0x00ef;
    L_0x00e8:
        r4 = ".";
        r2.append(r4);
        goto L_0x0044;
    L_0x00ef:
        r4 = 229; // 0xe5 float:3.21E-43 double:1.13E-321;
        if (r9 != r4) goto L_0x00fc;
    L_0x00f3:
        r4 = r2.toString();
        r8.gotoCapture(r4);
        goto L_0x0044;
    L_0x00fc:
        r4 = 230; // 0xe6 float:3.22E-43 double:1.136E-321;
        if (r9 != r4) goto L_0x0109;
    L_0x0100:
        r4 = r2.toString();
        r8.gotoCapture(r4);
        goto L_0x0044;
    L_0x0109:
        r4 = 232; // 0xe8 float:3.25E-43 double:1.146E-321;
        if (r9 != r4) goto L_0x0116;
    L_0x010d:
        r4 = r2.toString();
        r8.gotoCapture(r4);
        goto L_0x0044;
    L_0x0116:
        r4 = 66;
        if (r9 != r4) goto L_0x0123;
    L_0x011a:
        r4 = r2.toString();
        r8.gotoCapture(r4);
        goto L_0x0044;
    L_0x0123:
        r4 = 67;
        if (r9 != r4) goto L_0x0044;
    L_0x0127:
        r4 = r2.length();
        if (r4 != r7) goto L_0x0142;
    L_0x012d:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r8.tv_moneynumber1;
        r5 = "0";
        r4.setText(r5);
        r4 = r8.tv_moneynumber2;
        r5 = "0";
        r4.setText(r5);
        goto L_0x0044;
    L_0x0142:
        r4 = r2.length();	 Catch:{ Exception -> 0x014d }
        r4 = r4 + -1;
        r2.deleteCharAt(r4);	 Catch:{ Exception -> 0x014d }
        goto L_0x0044;
    L_0x014d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0044;
    L_0x0153:
        r4 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r4 = java.lang.Float.parseFloat(r4);	 Catch:{ Exception -> 0x017a }
        r5 = r8.max_money;	 Catch:{ Exception -> 0x017a }
        r5 = r5.floatValue();	 Catch:{ Exception -> 0x017a }
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 <= 0) goto L_0x018e;
    L_0x0165:
        r4 = r8.getContext();	 Catch:{ Exception -> 0x017a }
        r5 = 2131165256; // 0x7f070048 float:1.7944724E38 double:1.0529355386E-314;
        r5 = r8.getString(r5);	 Catch:{ Exception -> 0x017a }
        r6 = 0;
        r4 = android.widget.Toast.makeText(r4, r5, r6);	 Catch:{ Exception -> 0x017a }
        r4.show();	 Catch:{ Exception -> 0x017a }
        goto L_0x0086;
    L_0x017a:
        r0 = move-exception;
        r0.printStackTrace();
        r4 = r8.tv_nzdnumber;
        r5 = "0";
        r4.setText(r5);
        r4 = r8.tv_cnynumber;
        r5 = "0";
        r4.setText(r5);
        goto L_0x0086;
    L_0x018e:
        r4 = r8.tv_moneynumber1;	 Catch:{ Exception -> 0x017a }
        r5 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r4.setText(r5);	 Catch:{ Exception -> 0x017a }
        r4 = r8.checkBox;	 Catch:{ Exception -> 0x017a }
        r4 = r4.isChecked();	 Catch:{ Exception -> 0x017a }
        if (r4 == 0) goto L_0x01c3;
    L_0x019f:
        r8.setCustomPrice();	 Catch:{ Exception -> 0x017a }
        r8.setTotalMoney();	 Catch:{ Exception -> 0x017a }
    L_0x01a5:
        r4 = com.epos.pospay.common.MainApplication.getInstance();	 Catch:{ Exception -> 0x017a }
        r5 = r8.tv_moneynumber2;	 Catch:{ Exception -> 0x017a }
        r5 = r5.getText();	 Catch:{ Exception -> 0x017a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x017a }
        r4.setTotal_fee(r5);	 Catch:{ Exception -> 0x017a }
        r4 = com.epos.pospay.common.MainApplication.getInstance();	 Catch:{ Exception -> 0x017a }
        r5 = r2.toString();	 Catch:{ Exception -> 0x017a }
        r4.setPriceNz(r5);	 Catch:{ Exception -> 0x017a }
        goto L_0x0086;
    L_0x01c3:
        r4 = r8.tv_nzdnumber;	 Catch:{ Exception -> 0x017a }
        r5 = r8.tv_moneynumber1;	 Catch:{ Exception -> 0x017a }
        r5 = r5.getText();	 Catch:{ Exception -> 0x017a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x017a }
        r4.setText(r5);	 Catch:{ Exception -> 0x017a }
        r4 = r8.tv_cnynumber;	 Catch:{ Exception -> 0x017a }
        r5 = r8.tv_moneynumber2;	 Catch:{ Exception -> 0x017a }
        r5 = r5.getText();	 Catch:{ Exception -> 0x017a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x017a }
        r4.setText(r5);	 Catch:{ Exception -> 0x017a }
        r4 = r8.tv_moneynumber2;	 Catch:{ Exception -> 0x017a }
        r5 = r8.tv_moneynumber1;	 Catch:{ Exception -> 0x017a }
        r5 = r5.getText();	 Catch:{ Exception -> 0x017a }
        r5 = r5.toString();	 Catch:{ Exception -> 0x017a }
        r4.setText(r5);	 Catch:{ Exception -> 0x017a }
        goto L_0x01a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epos.pospay.CheckFragment.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    private void gotoCapture(String money) {
        if (Float.parseFloat(money) == 0.0f) {
            Toast.makeText(getContext(), getString(C0258R.string.pay_toast), 0).show();
        } else {
            continuousScan();
        }
    }

    private void continuousScan() {
        startLoadingProgress(getString(C0258R.string.wait_progress));
        ScanParameter scanParameter = new ScanParameter();
        scanParameter.set(ScanParameter.KEY_CAMERA_INDEX, 1);
        try {
            this.scanService.startScan(scanParameter, new C06735());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        String price = this.tv_moneynumber1.getText().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(price);
        switch (v.getId()) {
            case C0258R.id.btn7:
                sb.append("7");
                break;
            case C0258R.id.btn8:
                sb.append("8");
                break;
            case C0258R.id.btn9:
                sb.append("9");
                break;
            case C0258R.id.btn4:
                sb.append("4");
                break;
            case C0258R.id.btn5:
                sb.append("5");
                break;
            case C0258R.id.btn6:
                sb.append("6");
                break;
            case C0258R.id.btn1:
                sb.append("1");
                break;
            case C0258R.id.btn2:
                sb.append("2");
                break;
            case C0258R.id.btn3:
                sb.append("3");
                break;
            case C0258R.id.btn0:
                if (Float.parseFloat(price) != 0.0f || price.contains(FileUtils.FILE_EXTENSION_SEPARATOR)) {
                    sb.append("0");
                    break;
                }
            case C0258R.id.btnpoint:
                sb.append(FileUtils.FILE_EXTENSION_SEPARATOR);
                break;
            case C0258R.id.btndel:
                if (sb.length() != 1) {
                    try {
                        sb.deleteCharAt(sb.length() - 1);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                sb = new StringBuilder();
                this.tv_moneynumber1.setText("0");
                this.tv_moneynumber2.setText("0");
                break;
        }
        try {
            if (Float.parseFloat(sb.toString()) >= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT && sb.toString().charAt(0) == '0') {
                sb.deleteCharAt(0);
            }
            if (sb.toString().contains(FileUtils.FILE_EXTENSION_SEPARATOR)) {
                String[] strpoint = sb.toString().split("\\.");
                if (strpoint.length > 1 && strpoint[1].length() > 2) {
                    return;
                }
            }
            if (Float.parseFloat(sb.toString()) > this.max_money.floatValue()) {
                Toast.makeText(getContext(), getString(C0258R.string.money_over), 0).show();
                return;
            }
            this.tv_moneynumber2.setText(CalcRate.calcRate(SPUtil.get(getContext(), "rate", "").toString(), sb.toString()));
            this.tv_moneynumber1.setText(sb.toString());
            if (this.checkBox.isChecked()) {
                setCustomPrice();
            } else {
                this.tv_nzdnumber.setText(this.tv_moneynumber1.getText().toString());
                this.tv_cnynumber.setText(this.tv_moneynumber2.getText().toString());
            }
            MainApplication.getInstance().setTotal_fee(this.tv_moneynumber2.getText().toString());
            MainApplication.getInstance().setPriceNz(sb.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void getRate() {
        HttpRequestManager.getInstance().getRate(new C05137(), new C05148(), SPUtil.get(getContext(), "merchant", "").toString()).commit();
    }

    public void serviceConnected(Object objService, ServiceConnection connection) {
        if (objService instanceof IScanService) {
            this.scanService = (IScanService) objService;
            this.scanConn = connection;
        }
    }

    public void onDestroy() {
        if (this.scanService != null) {
            getContext().unbindService(this.scanConn);
            this.scanService = null;
            this.scanConn = null;
        }
        super.onDestroy();
    }
}
