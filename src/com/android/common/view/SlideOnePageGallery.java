package com.android.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class SlideOnePageGallery extends Gallery {
    public SlideOnePageGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SlideOnePageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideOnePageGallery(Context context) {
        super(context);
    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int kEvent;
        if (isScrollingLeft(e1, e2)) {
            kEvent = 21;
        } else {
            kEvent = 22;
        }
        onKeyDown(kEvent, null);
        return true;
    }
}
