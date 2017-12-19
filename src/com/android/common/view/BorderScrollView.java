package com.android.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class BorderScrollView extends ScrollView {
    private View contentView;
    private OnBorderListener onBorderListener;

    public interface OnBorderListener {
        void onBottom();

        void onTop();
    }

    public BorderScrollView(Context context) {
        super(context);
    }

    public BorderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        doOnBorderListener();
    }

    public void setOnBorderListener(OnBorderListener onBorderListener) {
        this.onBorderListener = onBorderListener;
        if (onBorderListener != null && this.contentView == null) {
            this.contentView = getChildAt(0);
        }
    }

    private void doOnBorderListener() {
        if (this.contentView == null || this.contentView.getMeasuredHeight() > getScrollY() + getHeight()) {
            if (getScrollY() == 0 && this.onBorderListener != null) {
                this.onBorderListener.onTop();
            }
        } else if (this.onBorderListener != null) {
            this.onBorderListener.onBottom();
        }
    }
}
