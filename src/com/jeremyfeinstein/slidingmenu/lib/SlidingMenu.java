package com.jeremyfeinstein.slidingmenu.lib;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.DefaultRetryPolicy;
import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove.OnPageChangeListener;

public class SlidingMenu extends RelativeLayout {
    public static final int LEFT = 0;
    public static final int LEFT_RIGHT = 2;
    public static final int RIGHT = 1;
    public static final int SLIDING_CONTENT = 1;
    public static final int SLIDING_WINDOW = 0;
    private static final String TAG = SlidingMenu.class.getSimpleName();
    public static final int TOUCHMODE_FULLSCREEN = 1;
    public static final int TOUCHMODE_MARGIN = 0;
    public static final int TOUCHMODE_NONE = 2;
    private boolean mActionbarOverlay;
    private OnCloseListener mCloseListener;
    private OnOpenListener mOpenListener;
    private OnOpenListener mSecondaryOpenListner;
    private CustomViewAbove mViewAbove;
    private CustomViewBehind mViewBehind;

    public interface CanvasTransformer {
        void transformCanvas(Canvas canvas, float f);
    }

    public interface OnCloseListener {
        void onClose();
    }

    public interface OnClosedListener {
        void onClosed();
    }

    public interface OnOpenListener {
        void onOpen();
    }

    public interface OnOpenedListener {
        void onOpened();
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C02861();
        private final int mItem;

        static class C02861 implements Creator<SavedState> {
            C02861() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        public SavedState(Parcelable superState, int item) {
            super(superState);
            this.mItem = item;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mItem = in.readInt();
        }

        public int getItem() {
            return this.mItem;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mItem);
        }
    }

    class C05831 implements OnPageChangeListener {
        public static final int POSITION_CLOSE = 1;
        public static final int POSITION_OPEN = 0;
        public static final int POSITION_SECONDARY_OPEN = 2;

        C05831() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            if (position == 0 && SlidingMenu.this.mOpenListener != null) {
                SlidingMenu.this.mOpenListener.onOpen();
            } else if (position == 1 && SlidingMenu.this.mCloseListener != null) {
                SlidingMenu.this.mCloseListener.onClose();
            } else if (position == 2 && SlidingMenu.this.mSecondaryOpenListner != null) {
                SlidingMenu.this.mSecondaryOpenListner.onOpen();
            }
        }
    }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Activity activity, int slideStyle) {
        this((Context) activity, null);
        attachToActivity(activity, slideStyle);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mActionbarOverlay = false;
        LayoutParams behindParams = new LayoutParams(-1, -1);
        this.mViewBehind = new CustomViewBehind(context);
        addView(this.mViewBehind, behindParams);
        LayoutParams aboveParams = new LayoutParams(-1, -1);
        this.mViewAbove = new CustomViewAbove(context);
        addView(this.mViewAbove, aboveParams);
        this.mViewAbove.setCustomViewBehind(this.mViewBehind);
        this.mViewBehind.setCustomViewAbove(this.mViewAbove);
        this.mViewAbove.setOnPageChangeListener(new C05831());
        TypedArray ta = context.obtainStyledAttributes(attrs, C0284R.styleable.SlidingMenu);
        setMode(ta.getInt(0, 0));
        int viewAbove = ta.getResourceId(1, -1);
        if (viewAbove != -1) {
            setContent(viewAbove);
        } else {
            setContent(new FrameLayout(context));
        }
        int viewBehind = ta.getResourceId(2, -1);
        if (viewBehind != -1) {
            setMenu(viewBehind);
        } else {
            setMenu(new FrameLayout(context));
        }
        setTouchModeAbove(ta.getInt(6, 0));
        setTouchModeBehind(ta.getInt(7, 0));
        int offsetBehind = (int) ta.getDimension(3, -1.0f);
        int widthBehind = (int) ta.getDimension(4, -1.0f);
        if (offsetBehind == -1 || widthBehind == -1) {
            if (offsetBehind != -1) {
                setBehindOffset(offsetBehind);
            } else if (widthBehind != -1) {
                setBehindWidth(widthBehind);
            } else {
                setBehindOffset(0);
            }
            setBehindScrollScale(ta.getFloat(5, 0.33f));
            int shadowRes = ta.getResourceId(8, -1);
            if (shadowRes != -1) {
                setShadowDrawable(shadowRes);
            }
            setShadowWidth((int) ta.getDimension(9, 0.0f));
            setFadeEnabled(ta.getBoolean(10, true));
            setFadeDegree(ta.getFloat(11, 0.33f));
            setSelectorEnabled(ta.getBoolean(12, false));
            int selectorRes = ta.getResourceId(13, -1);
            if (selectorRes != -1) {
                setSelectorDrawable(selectorRes);
            }
            ta.recycle();
            return;
        }
        throw new IllegalStateException("Cannot set both behindOffset and behindWidth for a SlidingMenu");
    }

    public void attachToActivity(Activity activity, int slideStyle) {
        attachToActivity(activity, slideStyle, false);
    }

    public void attachToActivity(Activity activity, int slideStyle, boolean actionbarOverlay) {
        if (slideStyle != 0 && slideStyle != 1) {
            throw new IllegalArgumentException("slideStyle must be either SLIDING_WINDOW or SLIDING_CONTENT");
        } else if (getParent() != null) {
            throw new IllegalStateException("This SlidingMenu appears to already be attached");
        } else {
            TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
            int background = a.getResourceId(0, 0);
            a.recycle();
            switch (slideStyle) {
                case 0:
                    this.mActionbarOverlay = false;
                    ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
                    View decorChild = (ViewGroup) decor.getChildAt(0);
                    decorChild.setBackgroundResource(background);
                    decor.removeView(decorChild);
                    decor.addView(this);
                    setContent(decorChild);
                    return;
                case 1:
                    this.mActionbarOverlay = actionbarOverlay;
                    ViewGroup contentParent = (ViewGroup) activity.findViewById(16908290);
                    View content = contentParent.getChildAt(0);
                    contentParent.removeView(content);
                    contentParent.addView(this);
                    setContent(content);
                    if (content.getBackground() == null) {
                        content.setBackgroundResource(background);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void setContent(int res) {
        setContent(LayoutInflater.from(getContext()).inflate(res, null));
    }

    public void setContent(View view) {
        this.mViewAbove.setContent(view);
        showContent();
    }

    public View getContent() {
        return this.mViewAbove.getContent();
    }

    public void setMenu(int res) {
        setMenu(LayoutInflater.from(getContext()).inflate(res, null));
    }

    public void setMenu(View v) {
        this.mViewBehind.setContent(v);
    }

    public View getMenu() {
        return this.mViewBehind.getContent();
    }

    public void setSecondaryMenu(int res) {
        setSecondaryMenu(LayoutInflater.from(getContext()).inflate(res, null));
    }

    public void setSecondaryMenu(View v) {
        this.mViewBehind.setSecondaryContent(v);
    }

    public View getSecondaryMenu() {
        return this.mViewBehind.getSecondaryContent();
    }

    public void setSlidingEnabled(boolean b) {
        this.mViewAbove.setSlidingEnabled(b);
    }

    public boolean isSlidingEnabled() {
        return this.mViewAbove.isSlidingEnabled();
    }

    public void setMode(int mode) {
        if (mode == 0 || mode == 1 || mode == 2) {
            this.mViewBehind.setMode(mode);
            return;
        }
        throw new IllegalStateException("SlidingMenu mode must be LEFT, RIGHT, or LEFT_RIGHT");
    }

    public int getMode() {
        return this.mViewBehind.getMode();
    }

    public void setStatic(boolean b) {
        if (b) {
            setSlidingEnabled(false);
            this.mViewAbove.setCustomViewBehind(null);
            this.mViewAbove.setCurrentItem(1);
            return;
        }
        this.mViewAbove.setCurrentItem(1);
        this.mViewAbove.setCustomViewBehind(this.mViewBehind);
        setSlidingEnabled(true);
    }

    public void showMenu() {
        showMenu(true);
    }

    public void showMenu(boolean animate) {
        this.mViewAbove.setCurrentItem(0, animate);
    }

    public void showSecondaryMenu() {
        showSecondaryMenu(true);
    }

    public void showSecondaryMenu(boolean animate) {
        this.mViewAbove.setCurrentItem(2, animate);
    }

    public void showContent() {
        showContent(true);
    }

    public void showContent(boolean animate) {
        this.mViewAbove.setCurrentItem(1, animate);
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean animate) {
        if (isMenuShowing()) {
            showContent(animate);
        } else {
            showMenu(animate);
        }
    }

    public boolean isMenuShowing() {
        return this.mViewAbove.getCurrentItem() == 0 || this.mViewAbove.getCurrentItem() == 2;
    }

    public boolean isSecondaryMenuShowing() {
        return this.mViewAbove.getCurrentItem() == 2;
    }

    public int getBehindOffset() {
        return ((LayoutParams) this.mViewBehind.getLayoutParams()).rightMargin;
    }

    public void setBehindOffset(int i) {
        this.mViewBehind.setWidthOffset(i);
    }

    public void setBehindOffsetRes(int resID) {
        setBehindOffset((int) getContext().getResources().getDimension(resID));
    }

    public void setAboveOffset(int i) {
        this.mViewAbove.setAboveOffset(i);
    }

    public void setAboveOffsetRes(int resID) {
        setAboveOffset((int) getContext().getResources().getDimension(resID));
    }

    public void setBehindWidth(int i) {
        int width;
        Display display = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        try {
            Class<?>[] parameterTypes = new Class[]{Point.class};
            Point parameter = new Point();
            Display.class.getMethod("getSize", parameterTypes).invoke(display, new Object[]{parameter});
            width = parameter.x;
        } catch (Exception e) {
            width = display.getWidth();
        }
        setBehindOffset(width - i);
    }

    public void setBehindWidthRes(int res) {
        setBehindWidth((int) getContext().getResources().getDimension(res));
    }

    public float getBehindScrollScale() {
        return this.mViewBehind.getScrollScale();
    }

    public int getTouchmodeMarginThreshold() {
        return this.mViewBehind.getMarginThreshold();
    }

    public void setTouchmodeMarginThreshold(int touchmodeMarginThreshold) {
        this.mViewBehind.setMarginThreshold(touchmodeMarginThreshold);
    }

    public void setBehindScrollScale(float f) {
        if (f >= 0.0f || f <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            this.mViewBehind.setScrollScale(f);
            return;
        }
        throw new IllegalStateException("ScrollScale must be between 0 and 1");
    }

    public void setBehindCanvasTransformer(CanvasTransformer t) {
        this.mViewBehind.setCanvasTransformer(t);
    }

    public int getTouchModeAbove() {
        return this.mViewAbove.getTouchMode();
    }

    public void setTouchModeAbove(int i) {
        if (i == 1 || i == 0 || i == 2) {
            this.mViewAbove.setTouchMode(i);
            return;
        }
        throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    }

    public void setTouchModeBehind(int i) {
        if (i == 1 || i == 0 || i == 2) {
            this.mViewBehind.setTouchMode(i);
            return;
        }
        throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    }

    public void setShadowDrawable(int resId) {
        setShadowDrawable(getContext().getResources().getDrawable(resId));
    }

    public void setShadowDrawable(Drawable d) {
        this.mViewBehind.setShadowDrawable(d);
    }

    public void setSecondaryShadowDrawable(int resId) {
        setSecondaryShadowDrawable(getContext().getResources().getDrawable(resId));
    }

    public void setSecondaryShadowDrawable(Drawable d) {
        this.mViewBehind.setSecondaryShadowDrawable(d);
    }

    public void setShadowWidthRes(int resId) {
        setShadowWidth((int) getResources().getDimension(resId));
    }

    public void setShadowWidth(int pixels) {
        this.mViewBehind.setShadowWidth(pixels);
    }

    public void setFadeEnabled(boolean b) {
        this.mViewBehind.setFadeEnabled(b);
    }

    public void setFadeDegree(float f) {
        this.mViewBehind.setFadeDegree(f);
    }

    public void setSelectorEnabled(boolean b) {
        this.mViewBehind.setSelectorEnabled(true);
    }

    public void setSelectedView(View v) {
        this.mViewBehind.setSelectedView(v);
    }

    public void setSelectorDrawable(int res) {
        this.mViewBehind.setSelectorBitmap(BitmapFactory.decodeResource(getResources(), res));
    }

    public void setSelectorBitmap(Bitmap b) {
        this.mViewBehind.setSelectorBitmap(b);
    }

    public void addIgnoredView(View v) {
        this.mViewAbove.addIgnoredView(v);
    }

    public void removeIgnoredView(View v) {
        this.mViewAbove.removeIgnoredView(v);
    }

    public void clearIgnoredViews() {
        this.mViewAbove.clearIgnoredViews();
    }

    public void setOnOpenListener(OnOpenListener listener) {
        this.mOpenListener = listener;
    }

    public void setSecondaryOnOpenListner(OnOpenListener listener) {
        this.mSecondaryOpenListner = listener;
    }

    public void setOnCloseListener(OnCloseListener listener) {
        this.mCloseListener = listener;
    }

    public void setOnOpenedListener(OnOpenedListener listener) {
        this.mViewAbove.setOnOpenedListener(listener);
    }

    public void setOnClosedListener(OnClosedListener listener) {
        this.mViewAbove.setOnClosedListener(listener);
    }

    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mViewAbove.getCurrentItem());
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.mViewAbove.setCurrentItem(ss.getItem());
    }

    @SuppressLint({"NewApi"})
    protected boolean fitSystemWindows(Rect insets) {
        int leftPadding = insets.left;
        int rightPadding = insets.right;
        int topPadding = insets.top;
        int bottomPadding = insets.bottom;
        if (!this.mActionbarOverlay) {
            Log.v(TAG, "setting padding!");
            setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        }
        return true;
    }

    @TargetApi(11)
    public void manageLayers(float percentOpen) {
        int layerType = 0;
        if (VERSION.SDK_INT >= 11) {
            boolean layer;
            if (percentOpen <= 0.0f || percentOpen >= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                layer = false;
            } else {
                layer = true;
            }
            if (layer) {
                layerType = 2;
            }
            if (layerType != getContent().getLayerType()) {
                getHandler().post(new Runnable() {
                    public void run() {
                        Log.v(SlidingMenu.TAG, "changing layerType. hardware? " + (layerType == 2));
                        SlidingMenu.this.getContent().setLayerType(layerType, null);
                        SlidingMenu.this.getMenu().setLayerType(layerType, null);
                        if (SlidingMenu.this.getSecondaryMenu() != null) {
                            SlidingMenu.this.getSecondaryMenu().setLayerType(layerType, null);
                        }
                    }
                });
            }
        }
    }
}
