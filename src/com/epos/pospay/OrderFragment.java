package com.epos.pospay;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.common.PosConst;
import com.epos.pospay.common.VolleyErrorHelper;
import com.epos.pospay.model.OrderListBean.List1;
import com.epos.pospay.model.OrderListBean.Root;
import com.epos.pospay.view.XListView;
import com.epos.pospay.view.XListView.IXListViewListener;
import com.epos.pospay.zxing.utils.TimeUitl;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseFragment implements IXListViewListener {
    private int flag1 = 0;
    private int flag2 = 0;
    private XListView listView;
    private List<List1> lists = new ArrayList();
    private int max = 10;
    private String mendDate;
    private String mstartDate;
    private String mstatus;
    private int offset = 0;
    private OrderAdapter orderAdapter;
    private View parentView;
    private PopupWindow pop = null;
    private PopupWindow pop1 = null;
    private TextView title1;
    private TextView title2;

    class C02441 implements OnClickListener {
        C02441() {
        }

        public void onClick(View v) {
            OrderFragment.this.pop.showAsDropDown(OrderFragment.this.parentView, 0, 0);
        }
    }

    class C02452 implements OnClickListener {
        C02452() {
        }

        public void onClick(View v) {
            OrderFragment.this.pop1.showAsDropDown(OrderFragment.this.parentView, 0, 0);
        }
    }

    class C02463 implements OnItemClickListener {
        C02463() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            if (((List1) OrderFragment.this.lists.get(position - 1)).getTransactionState().equals("SUCCESS")) {
                IntentUtil.gotoOrderDetail(OrderFragment.this.getActivity(), ((List1) OrderFragment.this.lists.get(position - 1)).getId() + "", ((List1) OrderFragment.this.lists.get(position - 1)).getAmountNZD() + "", ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionDateNZ(), ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionNum(), "1", ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionNumUnion());
                OrderFragment.this.getActivity().finish();
                return;
            }
            IntentUtil.gotoOrderDetail(OrderFragment.this.getActivity(), ((List1) OrderFragment.this.lists.get(position - 1)).getId() + "", ((List1) OrderFragment.this.lists.get(position - 1)).getAmountNZD() + "", ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionDateNZ(), ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionNum(), "2", ((List1) OrderFragment.this.lists.get(position - 1)).getTransactionNumUnion());
            OrderFragment.this.getActivity().finish();
        }
    }

    class C02474 implements OnClickListener {
        C02474() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag1 = 0;
            OrderFragment.this.title1.setText(OrderFragment.this.getString(C0258R.string.alltime));
            OrderFragment.this.pop.dismiss();
            switch (OrderFragment.this.flag2) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", "");
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[1]);
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[6]);
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[2]);
                    return;
                default:
                    return;
            }
        }
    }

    class C02485 implements OnClickListener {
        C02485() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag1 = 1;
            OrderFragment.this.title1.setText(OrderFragment.this.getString(C0258R.string.daypay));
            OrderFragment.this.pop.dismiss();
            switch (OrderFragment.this.flag2) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", "");
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[1]);
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[6]);
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[2]);
                    return;
                default:
                    return;
            }
        }
    }

    class C02496 implements OnClickListener {
        C02496() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag1 = 2;
            OrderFragment.this.title1.setText(OrderFragment.this.getString(C0258R.string.weekpay));
            OrderFragment.this.pop.dismiss();
            switch (OrderFragment.this.flag2) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", "");
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[1]);
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[6]);
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[2]);
                    return;
                default:
                    return;
            }
        }
    }

    class C02507 implements OnClickListener {
        C02507() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag1 = 3;
            OrderFragment.this.title1.setText(OrderFragment.this.getString(C0258R.string.monthpay));
            OrderFragment.this.pop.dismiss();
            switch (OrderFragment.this.flag2) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", "");
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[1]);
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[6]);
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[2]);
                    return;
                default:
                    return;
            }
        }
    }

    class C02518 implements OnClickListener {
        C02518() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag2 = 0;
            OrderFragment.this.title2.setText(OrderFragment.this.getString(C0258R.string.allresult));
            OrderFragment.this.pop1.dismiss();
            switch (OrderFragment.this.flag1) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", "");
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", "");
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", "");
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", "");
                    return;
                default:
                    return;
            }
        }
    }

    class C02529 implements OnClickListener {
        C02529() {
        }

        public void onClick(View v) {
            OrderFragment.this.flag2 = 1;
            OrderFragment.this.title2.setText(OrderFragment.this.getString(C0258R.string.getsuccess));
            OrderFragment.this.pop1.dismiss();
            switch (OrderFragment.this.flag1) {
                case 0:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[1]);
                    return;
                case 1:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[1]);
                    return;
                case 2:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[1]);
                    return;
                case 3:
                    OrderFragment.this.lists.clear();
                    OrderFragment.this.orderAdapter.notifyDataSetChanged();
                    OrderFragment.this.offset = 0;
                    OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[1]);
                    return;
                default:
                    return;
            }
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.orderfragment_layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initData();
    }

    private void initUI(View view) {
        this.listView = (XListView) view.findViewById(C0258R.id.listview);
        this.listView.setXListViewListener(this);
        this.listView.setPullRefreshEnable(false);
        this.title1 = (TextView) view.findViewById(C0258R.id.title1);
        this.title2 = (TextView) view.findViewById(C0258R.id.title2);
        view.findViewById(C0258R.id.re1).setOnClickListener(new C02441());
        view.findViewById(C0258R.id.re2).setOnClickListener(new C02452());
        showTimePopWindow(view);
        showResultPopWindow(view);
    }

    private void initData() {
        this.orderAdapter = new OrderAdapter(getActivity());
        loadDataByStatus("", "", "");
        this.orderAdapter.setList(this.lists);
        this.listView.setAdapter(this.orderAdapter);
        if (this.lists.size() == 0) {
            this.listView.hideFooter();
        }
        this.listView.setOnItemClickListener(new C02463());
    }

    private void showTimePopWindow(View view1) {
        this.pop = new PopupWindow(getActivity());
        this.parentView = view1.findViewById(C0258R.id.re1);
        View view = getActivity().getLayoutInflater().inflate(C0258R.layout.all_time_layout, null);
        this.pop.setWidth(-1);
        this.pop.setHeight(-2);
        this.pop.setBackgroundDrawable(new PaintDrawable());
        this.pop.setFocusable(true);
        this.pop.setOutsideTouchable(true);
        this.pop.setContentView(view);
        TextView tv2 = (TextView) view.findViewById(C0258R.id.time_tv2);
        TextView tv3 = (TextView) view.findViewById(C0258R.id.time_tv3);
        TextView tv4 = (TextView) view.findViewById(C0258R.id.time_tv4);
        ((TextView) view.findViewById(C0258R.id.time_tv1)).setOnClickListener(new C02474());
        tv2.setOnClickListener(new C02485());
        tv3.setOnClickListener(new C02496());
        tv4.setOnClickListener(new C02507());
    }

    private void showResultPopWindow(View view1) {
        this.pop1 = new PopupWindow(getActivity());
        this.parentView = view1.findViewById(C0258R.id.re1);
        View view = getActivity().getLayoutInflater().inflate(C0258R.layout.all_result_layout, null);
        this.pop1.setWidth(-1);
        this.pop1.setHeight(-2);
        this.pop1.setBackgroundDrawable(new PaintDrawable());
        this.pop1.setFocusable(true);
        this.pop1.setOutsideTouchable(true);
        this.pop1.setContentView(view);
        TextView tv2 = (TextView) view.findViewById(C0258R.id.get_tv2);
        TextView tv3 = (TextView) view.findViewById(C0258R.id.get_tv3);
        TextView tv4 = (TextView) view.findViewById(C0258R.id.get_tv4);
        ((TextView) view.findViewById(C0258R.id.get_tv1)).setOnClickListener(new C02518());
        tv2.setOnClickListener(new C02529());
        tv3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                OrderFragment.this.flag2 = 2;
                OrderFragment.this.title2.setText(OrderFragment.this.getString(C0258R.string.geterror));
                OrderFragment.this.pop1.dismiss();
                switch (OrderFragment.this.flag1) {
                    case 0:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[6]);
                        return;
                    case 1:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[6]);
                        return;
                    case 2:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[6]);
                        return;
                    case 3:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[6]);
                        return;
                    default:
                        return;
                }
            }
        });
        tv4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                OrderFragment.this.flag2 = 3;
                OrderFragment.this.title2.setText(OrderFragment.this.getString(C0258R.string.getcancel));
                OrderFragment.this.pop1.dismiss();
                switch (OrderFragment.this.flag1) {
                    case 0:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus("", "", PosConst.Transaction_State[2]);
                        return;
                    case 1:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesmorning(), "", PosConst.Transaction_State[2]);
                        return;
                    case 2:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesWeekmorning(), "", PosConst.Transaction_State[2]);
                        return;
                    case 3:
                        OrderFragment.this.lists.clear();
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        OrderFragment.this.offset = 0;
                        OrderFragment.this.loadDataByStatus(TimeUitl.getTimesMonthmorning(), "", PosConst.Transaction_State[2]);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void onRefresh() {
    }

    public void onLoadMore() {
        this.offset += 10;
        loadDataByStatus(this.mstartDate, this.mendDate, this.mstatus);
    }

    private void onLoad() {
        this.listView.stopLoadMore();
        try {
            this.listView.setRefreshTime(getString(C0258R.string.now));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataByStatus(String startdate, String enddate, String status) {
        this.mstartDate = startdate;
        this.mendDate = enddate;
        this.mstatus = status;
        HttpRequestManager.getInstance().orderList(new Listener<String>() {
            public void onResponse(String response) {
                OrderFragment.this.stopLoadingProgress();
                OrderFragment.this.onLoad();
                try {
                    Root root = (Root) new Gson().fromJson(response, Root.class);
                    if (root.getSuccess()) {
                        for (int i = 0; i < root.getList().size(); i++) {
                            OrderFragment.this.lists.add(root.getList().get(i));
                        }
                        if (OrderFragment.this.lists.size() > 6) {
                            OrderFragment.this.listView.setPullLoadEnable(true);
                        } else {
                            OrderFragment.this.listView.setPullLoadEnable(false);
                        }
                        OrderFragment.this.orderAdapter.notifyDataSetChanged();
                        return;
                    }
                    Toast.makeText(OrderFragment.this.getActivity(), root.getMessage(), 0).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                OrderFragment.this.stopLoadingProgress();
                OrderFragment.this.onLoad();
                try {
                    VolleyErrorHelper.showErrorMessage(OrderFragment.this.getActivity(), error);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, getActivity(), this.max, this.offset, status, startdate + "", enddate + "").commit();
        startLoadingProgress(getString(C0258R.string.wait_progress));
    }
}
