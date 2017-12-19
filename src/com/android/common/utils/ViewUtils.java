package com.android.common.utils;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.lang.reflect.Field;

public class ViewUtils {
    private static final String CLASS_NAME_GRID_VIEW = "android.widget.GridView";
    private static final String FIELD_NAME_VERTICAL_SPACING = "mVerticalSpacing";

    public static int getListViewHeightBasedOnChildren(ListView view) {
        int height = getAbsListViewHeightBasedOnChildren(view);
        if (view == null) {
            return height;
        }
        ListAdapter adapter = view.getAdapter();
        if (adapter == null) {
            return height;
        }
        int adapterCount = adapter.getCount();
        if (adapterCount > 0) {
            return height + (view.getDividerHeight() * (adapterCount - 1));
        }
        return height;
    }

    public static int getGridViewVerticalSpacing(GridView view) {
        int verticalSpacing = 0;
        try {
            Field field = Class.forName(CLASS_NAME_GRID_VIEW).getDeclaredField(FIELD_NAME_VERTICAL_SPACING);
            field.setAccessible(true);
            return ((Integer) field.get(view)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return verticalSpacing;
        }
    }

    public static int getAbsListViewHeightBasedOnChildren(AbsListView view) {
        if (view != null) {
            ListAdapter adapter = (ListAdapter) view.getAdapter();
            if (adapter != null) {
                int height = 0;
                for (int i = 0; i < adapter.getCount(); i++) {
                    View item = adapter.getView(i, null, view);
                    if (item instanceof ViewGroup) {
                        item.setLayoutParams(new LayoutParams(-2, -2));
                    }
                    item.measure(0, 0);
                    height += item.getMeasuredHeight();
                }
                return height + (view.getPaddingTop() + view.getPaddingBottom());
            }
        }
        return 0;
    }

    public static void setViewHeight(View view, int height) {
        if (view != null) {
            view.getLayoutParams().height = height;
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView view) {
        setViewHeight(view, getListViewHeightBasedOnChildren(view));
    }

    public static void setAbsListViewHeightBasedOnChildren(AbsListView view) {
        setViewHeight(view, getAbsListViewHeightBasedOnChildren(view));
    }

    public static void setSearchViewOnClickListener(View v, OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if ((child instanceof LinearLayout) || (child instanceof RelativeLayout)) {
                    setSearchViewOnClickListener(child, listener);
                }
                if (child instanceof TextView) {
                    ((TextView) child).setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }
}
