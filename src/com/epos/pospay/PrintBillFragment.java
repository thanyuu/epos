package com.epos.pospay;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import com.android.common.utils.MapUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.cloudpos.apidemo.common.PrintBean;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.function.HandlerImpl;
import com.cloudpos.mvc.base.ActionCallback;
import com.cloudpos.mvc.base.ActionManager;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.SPUtil;
import com.epos.pospay.common.VolleyErrorHelper;
import com.epos.pospay.model.PrintBill;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PrintBillFragment extends BaseFragment {
    private TextView merchant;
    private List<PrintBean> printBeanList = new ArrayList();
    private PrintBill printBill;
    private TextView purchasemoney;
    private TextView purchasenum;
    private TextView refundmoney;
    private TextView refundnum;
    private TextView text_print;
    private TextView timeend;
    private TextView timestart;
    private TextView totalmoney;
    private TextView totalnum;

    class C02531 implements OnClickListener {
        C02531() {
        }

        public void onClick(View v) {
            PrintBillFragment.this.timePicker(1, PrintBillFragment.this.timestart.getText().toString());
        }
    }

    class C02542 implements OnClickListener {
        C02542() {
        }

        public void onClick(View v) {
            PrintBillFragment.this.timePicker(2, PrintBillFragment.this.timeend.getText().toString());
        }
    }

    class C02553 implements OnClickListener {
        C02553() {
        }

        public void onClick(View v) {
            PrintBillFragment.this.getBill(PrintBillFragment.this.changeTime(PrintBillFragment.this.timestart.getText().toString()), PrintBillFragment.this.changeTime(PrintBillFragment.this.timeend.getText().toString()));
        }
    }

    class C02564 implements OnClickListener {
        C02564() {
        }

        public void onClick(View v) {
            PrintBillFragment.this.printBeanList.clear();
            PrintBillFragment.this.setPrintContent("*-MERCHANT           TOTAL-*", 1);
            PrintBillFragment.this.setPrintContent("MERCHANT             " + SPUtil.get(PrintBillFragment.this.getActivity(), "orgName", ""), 1);
            PrintBillFragment.this.setPrintContent("TIME                 " + PrintBillFragment.this.getTime(), 1);
            PrintBillFragment.this.setPrintContent("  MERCHANT           TOTALS", 1);
            PrintBillFragment.this.setPrintContent("             ----         ", 1);
            PrintBillFragment.this.setPrintContent("PURCHASE", 1);
            PrintBillFragment.this.setPrintContent(PrintBillFragment.this.printBill.getTotal().getSuccess() + "                   " + SPUtil.get(PrintBillFragment.this.getActivity(), "feeType", "").toString() + " $" + PrintBillFragment.this.printBill.getTotal().getSuccessAmount(), 1);
            PrintBillFragment.this.setPrintContent("REFUND", 1);
            PrintBillFragment.this.setPrintContent(PrintBillFragment.this.printBill.getTotal().getRefund() + "                   " + SPUtil.get(PrintBillFragment.this.getActivity(), "feeType", "").toString() + " $" + PrintBillFragment.this.printBill.getTotal().getRefundAmount(), 1);
            PrintBillFragment.this.setPrintContent("TOTAL", 1);
            PrintBillFragment.this.setPrintContent(PrintBillFragment.this.printBill.getTotal().getTotal() + "                   " + SPUtil.get(PrintBillFragment.this.getActivity(), "feeType", "").toString() + " $" + PrintBillFragment.this.printBill.getTotal().getTotalAmount(), 1);
            PrintBillFragment.this.setPrintContent("*------------------------*", 1);
            if (PrintBillFragment.this.printBill != null && PrintBillFragment.this.printBill.isSuccess()) {
                PrintBillFragment.this.print();
            }
        }
    }

    class C05245 implements Listener<String> {
        C05245() {
        }

        public void onResponse(String response) {
            PrintBillFragment.this.stopLoadingProgress();
            PrintBill mprintBill = (PrintBill) new Gson().fromJson(response, PrintBill.class);
            PrintBillFragment.this.printBill = mprintBill;
            if (mprintBill.isSuccess()) {
                PrintBillFragment.this.initUI(mprintBill);
            }
        }
    }

    class C05256 implements ErrorListener {
        C05256() {
        }

        public void onErrorResponse(VolleyError error) {
            PrintBillFragment.this.stopLoadingProgress();
            try {
                VolleyErrorHelper.showErrorMessage(PrintBillFragment.this.getActivity(), error);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C05267 implements Listener<String> {
        C05267() {
        }

        public void onResponse(String response) {
            PrintBillFragment.this.stopLoadingProgress();
            PrintBill mprintBill = (PrintBill) new Gson().fromJson(response, PrintBill.class);
            PrintBillFragment.this.printBill = mprintBill;
            if (mprintBill.isSuccess()) {
                PrintBillFragment.this.initUI(mprintBill);
            }
        }
    }

    class C05278 implements ErrorListener {
        C05278() {
        }

        public void onErrorResponse(VolleyError error) {
            PrintBillFragment.this.stopLoadingProgress();
            try {
                VolleyErrorHelper.showErrorMessage(PrintBillFragment.this.getActivity(), error);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.printbill_layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initData();
    }

    private void init(View view) {
        this.text_print = (TextView) view.findViewById(C0258R.id.text_print);
        this.merchant = (TextView) view.findViewById(C0258R.id.merchant);
        this.timestart = (TextView) view.findViewById(C0258R.id.timestart);
        this.timeend = (TextView) view.findViewById(C0258R.id.timeend);
        this.purchasenum = (TextView) view.findViewById(C0258R.id.purchasenum);
        this.purchasemoney = (TextView) view.findViewById(C0258R.id.purchasemoney);
        this.refundnum = (TextView) view.findViewById(C0258R.id.refundnum);
        this.refundmoney = (TextView) view.findViewById(C0258R.id.refundmoney);
        this.totalnum = (TextView) view.findViewById(C0258R.id.totalnum);
        this.totalmoney = (TextView) view.findViewById(C0258R.id.totalmoney);
        Button query = (Button) view.findViewById(C0258R.id.query);
        Button print = (Button) view.findViewById(C0258R.id.print);
        this.timestart.setOnClickListener(new C02531());
        this.timeend.setOnClickListener(new C02542());
        query.setOnClickListener(new C02553());
        print.setOnClickListener(new C02564());
    }

    private void initData() {
        HttpRequestManager.getInstance().getBills(new C05245(), new C05256(), getActivity()).commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }

    private void getBill(String startDate, String endDate) {
        HttpRequestManager.getInstance().getBills1(new C05267(), new C05278(), getActivity(), startDate, endDate).commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }

    private void initUI(PrintBill printBill) {
        this.merchant.setText(SPUtil.get(getActivity(), "orgName", "").toString());
        if (TextUtils.isEmpty(this.timestart.getText().toString())) {
            this.timestart.setText(getStartTime());
        }
        if (TextUtils.isEmpty(this.timeend.getText().toString())) {
            this.timeend.setText(getTime());
        }
        this.purchasenum.setText(printBill.getTotal().getSuccess() + "");
        this.purchasemoney.setText(SPUtil.get(getActivity(), "feeType", "").toString() + " $" + printBill.getTotal().getSuccessAmount());
        this.refundnum.setText(printBill.getTotal().getRefund() + "");
        this.refundmoney.setText(SPUtil.get(getActivity(), "feeType", "").toString() + " $" + printBill.getTotal().getRefundAmount());
        this.totalnum.setText(printBill.getTotal().getTotal() + "");
        this.totalmoney.setText(SPUtil.get(getActivity(), "feeType", "").toString() + " $" + printBill.getTotal().getTotalAmount());
    }

    private String getStartTime() {
        Date date = new Date();
        return new SimpleDateFormat("ddMMM 00:00", Locale.ENGLISH).format(date) + " " + getString(C0258R.string.sanjiao);
    }

    private String getTime() {
        Date date = new Date();
        return new SimpleDateFormat("ddMMM HH:mm", Locale.ENGLISH).format(date) + " " + getString(C0258R.string.sanjiao);
    }

    private void setPrintContent(String content, int type) {
        PrintBean bean = new PrintBean();
        bean.setContent(content);
        bean.setType(type);
        if (!TextUtils.isEmpty(content)) {
            this.printBeanList.add(bean);
        }
    }

    private void print() {
        ActionCallback callback = new ActionCallbackImpl(new HandlerImpl(this.text_print));
        Map param = new HashMap();
        param.put("host", this);
        ActionManager.doSubmit("PrinterAction/open", getActivity(), param, callback);
        param.clear();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.text_print.getText().toString().contains("失败")) {
            param.put("host", this);
            ActionManager.doSubmit("PrinterAction/open", getActivity(), param, callback);
            param.clear();
        }
        param.put("host", this);
        param.put("content", this.printBeanList);
        ActionManager.doSubmit("PrinterAction/write", getActivity(), param, callback);
        Log.e("===============", "printBeanList: " + this.printBeanList);
    }

    private void timePicker(final int type, final String time) {
        String[] tempTime = time.split(" ");
        new TimePickerDialog(getActivity(), new OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int mHour, int mMinute) {
                StringBuilder append;
                Object obj;
                switch (type) {
                    case 1:
                        append = new StringBuilder().append(mHour < 10 ? "0" + mHour : Integer.valueOf(mHour)).append(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR);
                        if (mMinute < 10) {
                            obj = "0" + mMinute;
                        } else {
                            obj = Integer.valueOf(mMinute);
                        }
                        PrintBillFragment.this.timestart.setText(time.replaceAll(time.split(" ")[1], append.append(obj).toString()));
                        return;
                    case 2:
                        append = new StringBuilder().append(String.valueOf(mHour < 10 ? "0" + mHour : Integer.valueOf(mHour))).append(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR);
                        if (mMinute < 10) {
                            obj = "0" + mMinute;
                        } else {
                            obj = Integer.valueOf(mMinute);
                        }
                        PrintBillFragment.this.timeend.setText(time.replaceAll(time.split(" ")[1], append.append(obj).toString()));
                        return;
                    default:
                        return;
                }
            }
        }, Integer.valueOf(tempTime[1].split(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR)[0]).intValue(), Integer.valueOf(tempTime[1].split(MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR)[1]).intValue(), true).show();
    }

    private String getNowtime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    private String changeTime(String time) {
        String temp = time.split(" ")[1];
        String now = getNowtime();
        return now.replaceAll(now.split(" ")[1], temp) + ":00";
    }
}
