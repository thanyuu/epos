package com.epos.pospay;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.epos.pospay.model.OrderListBean.List1;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<List1> list;

    public static class ViewHolder {
        ImageView iv_status;
        ImageView logo;
        TextView order_id;
        TextView tv_money;
        TextView tv_status;
        TextView tv_time;
    }

    public void setList(List<List1> data) {
        this.list = data;
    }

    public OrderAdapter(Context cx) {
        this.context = cx;
    }

    public int getCount() {
        return this.list != null ? this.list.size() : 0;
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
        r7 = this;
        r4 = 1;
        r2 = 0;
        r3 = -1;
        if (r9 != 0) goto L_0x0112;
    L_0x0005:
        r1 = r7.context;
        r1 = android.view.LayoutInflater.from(r1);
        r5 = 2130968627; // 0x7f040033 float:1.7545913E38 double:1.052838391E-314;
        r6 = 0;
        r9 = r1.inflate(r5, r6);
        r0 = new com.epos.pospay.OrderAdapter$ViewHolder;
        r0.<init>();
        r1 = 2131624175; // 0x7f0e00ef float:1.8875522E38 double:1.0531622747E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.ImageView) r1;
        r0.iv_status = r1;
        r1 = 2131624176; // 0x7f0e00f0 float:1.8875524E38 double:1.053162275E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r0.tv_money = r1;
        r1 = 2131624173; // 0x7f0e00ed float:1.8875518E38 double:1.0531622737E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r0.tv_time = r1;
        r1 = 2131624178; // 0x7f0e00f2 float:1.8875528E38 double:1.053162276E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r0.tv_status = r1;
        r1 = 2131624177; // 0x7f0e00f1 float:1.8875526E38 double:1.0531622757E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r0.order_id = r1;
        r1 = 2131624179; // 0x7f0e00f3 float:1.887553E38 double:1.0531622767E-314;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.ImageView) r1;
        r0.logo = r1;
        r9.setTag(r0);
    L_0x005d:
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getTransactionState();
        r5 = r1.hashCode();
        switch(r5) {
            case -1881484424: goto L_0x0125;
            case -1149187101: goto L_0x011a;
            default: goto L_0x0070;
        };
    L_0x0070:
        r1 = r3;
    L_0x0071:
        switch(r1) {
            case 0: goto L_0x0130;
            case 1: goto L_0x0153;
            default: goto L_0x0074;
        };
    L_0x0074:
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getTransactionType();
        r5 = r1.hashCode();
        switch(r5) {
            case -1738440922: goto L_0x0181;
            case 70445326: goto L_0x0197;
            case 500345892: goto L_0x018c;
            case 1933336138: goto L_0x0176;
            default: goto L_0x0087;
        };
    L_0x0087:
        r1 = r3;
    L_0x0088:
        switch(r1) {
            case 0: goto L_0x01a2;
            case 1: goto L_0x01ac;
            case 2: goto L_0x01b6;
            case 3: goto L_0x01c0;
            default: goto L_0x008b;
        };
    L_0x008b:
        r2 = r0.tv_money;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getCurrencyTypeSymbol();
        r1 = r3.append(r1);
        r3 = " ";
        r3 = r1.append(r3);
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getAmountNZD();
        r1 = r3.append(r1);
        r3 = "";
        r1 = r1.append(r3);
        r1 = r1.toString();
        r2.setText(r1);
        r2 = r0.tv_time;
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getTransactionDateNZ();
        r2.setText(r1);
        r2 = r0.tv_status;
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getTransactionState();
        r2.setText(r1);
        r2 = r0.order_id;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "(";
        r3 = r1.append(r3);
        r1 = r7.list;
        r1 = r1.get(r8);
        r1 = (com.epos.pospay.model.OrderListBean.List1) r1;
        r1 = r1.getTransactionNum();
        r1 = r3.append(r1);
        r3 = ")";
        r1 = r1.append(r3);
        r1 = r1.toString();
        r2.setText(r1);
        return r9;
    L_0x0112:
        r0 = r9.getTag();
        r0 = (com.epos.pospay.OrderAdapter.ViewHolder) r0;
        goto L_0x005d;
    L_0x011a:
        r5 = "SUCCESS";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0070;
    L_0x0122:
        r1 = r2;
        goto L_0x0071;
    L_0x0125:
        r5 = "REFUND";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0070;
    L_0x012d:
        r1 = r4;
        goto L_0x0071;
    L_0x0130:
        r1 = r0.tv_money;
        r5 = "#4EA3C3";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        r1 = r0.tv_time;
        r5 = "#4EA3C3";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        r1 = r0.tv_status;
        r5 = "#4EA3C3";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        goto L_0x0074;
    L_0x0153:
        r1 = r0.tv_money;
        r5 = "#FF0000";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        r1 = r0.tv_time;
        r5 = "#FF0000";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        r1 = r0.tv_status;
        r5 = "#FF0000";
        r5 = android.graphics.Color.parseColor(r5);
        r1.setTextColor(r5);
        goto L_0x0074;
    L_0x0176:
        r4 = "ALIPAY";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x0087;
    L_0x017e:
        r1 = r2;
        goto L_0x0088;
    L_0x0181:
        r2 = "WECHAT";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0087;
    L_0x0189:
        r1 = r4;
        goto L_0x0088;
    L_0x018c:
        r2 = "BESTPAY";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0087;
    L_0x0194:
        r1 = 2;
        goto L_0x0088;
    L_0x0197:
        r2 = "JDPAY";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0087;
    L_0x019f:
        r1 = 3;
        goto L_0x0088;
    L_0x01a2:
        r1 = r0.logo;
        r2 = 2130903065; // 0x7f030019 float:1.7412937E38 double:1.052805999E-314;
        r1.setImageResource(r2);
        goto L_0x008b;
    L_0x01ac:
        r1 = r0.logo;
        r2 = 2130903063; // 0x7f030017 float:1.7412933E38 double:1.052805998E-314;
        r1.setImageResource(r2);
        goto L_0x008b;
    L_0x01b6:
        r1 = r0.logo;
        r2 = 2130903064; // 0x7f030018 float:1.7412935E38 double:1.0528059985E-314;
        r1.setImageResource(r2);
        goto L_0x008b;
    L_0x01c0:
        r1 = r0.logo;
        r2 = 2130903048; // 0x7f030008 float:1.7412903E38 double:1.0528059906E-314;
        r1.setImageResource(r2);
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epos.pospay.OrderAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    private List<List1> makeList(List<List<List1>> data) {
        List<List1> list = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < ((List) data.get(i)).size(); j++) {
                list.add(((List) data.get(i)).get(j));
            }
        }
        return list;
    }
}
