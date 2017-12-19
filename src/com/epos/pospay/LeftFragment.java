package com.epos.pospay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.epos.pospay.common.BaseFragment;

public class LeftFragment extends BaseFragment implements OnClickListener {
    protected int getContentViewResId() {
        return C0258R.layout.leftfragment_layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        RelativeLayout re2 = (RelativeLayout) view.findViewById(C0258R.id.re_Order);
        RelativeLayout re3 = (RelativeLayout) view.findViewById(C0258R.id.re_user);
        RelativeLayout re4 = (RelativeLayout) view.findViewById(C0258R.id.re_bill);
        ((RelativeLayout) view.findViewById(C0258R.id.re_checkout)).setOnClickListener(this);
        re2.setOnClickListener(this);
        re3.setOnClickListener(this);
        re4.setOnClickListener(this);
    }

    public void onClick(View v) {
        Fragment newContent = null;
        String title = null;
        switch (v.getId()) {
            case C0258R.id.re_checkout:
                newContent = new CheckFragment();
                title = getString(C0258R.string.left_checkout);
                break;
            case C0258R.id.re_Order:
                newContent = new OrderFragment();
                title = getString(C0258R.string.left_order);
                break;
            case C0258R.id.re_bill:
                newContent = new PrintBillFragment();
                title = getString(C0258R.string.printbills);
                break;
            case C0258R.id.re_user:
                newContent = new UserFragment();
                title = getString(C0258R.string.about);
                break;
        }
        if (newContent != null) {
            switchFragment(newContent, title);
        }
    }

    private void switchFragment(Fragment fragment, String title) {
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).switchConent(fragment, title);
        }
    }
}
