package com.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wizarpos.utils.R;

public class DropDownListView extends ListView implements OnScrollListener {
    public static final int HEADER_STATUS_CLICK_TO_LOAD = 1;
    public static final int HEADER_STATUS_DROP_DOWN_TO_LOAD = 2;
    public static final int HEADER_STATUS_LOADING = 4;
    public static final int HEADER_STATUS_RELEASE_TO_LOAD = 3;
    private float actionDownPointY;
    private Context context;
    private int currentHeaderStatus;
    private int currentScrollState;
    private RotateAnimation flipAnimation;
    private Button footerButton;
    private String footerDefaultText;
    private RelativeLayout footerLayout;
    private String footerLoadingText;
    private String footerNoMoreText;
    private ProgressBar footerProgressBar;
    private boolean hasMore = true;
    private boolean hasReachedTop = false;
    private String headerDefaultText;
    private ImageView headerImage;
    private RelativeLayout headerLayout;
    private String headerLoadingText;
    private int headerOriginalHeight;
    private int headerOriginalTopPadding;
    private float headerPaddingTopRate = 1.5f;
    private ProgressBar headerProgressBar;
    private String headerPullText;
    private int headerReleaseMinDistance;
    private String headerReleaseText;
    private TextView headerSecondText;
    private TextView headerText;
    private boolean isAutoLoadOnBottom = false;
    private boolean isDropDownStyle = true;
    private boolean isOnBottomLoading = false;
    private boolean isOnBottomStyle = true;
    private boolean isShowFooterProgressBar = true;
    private boolean isShowFooterWhenNoMore = false;
    private OnDropDownListener onDropDownListener;
    private OnScrollListener onScrollListener;
    private RotateAnimation reverseFlipAnimation;

    class C01851 implements OnClickListener {
        C01851() {
        }

        public void onClick(View v) {
            DropDownListView.this.onDropDown();
        }
    }

    public interface OnDropDownListener {
        void onDropDown();
    }

    public DropDownListView(Context context) {
        super(context);
        init(context);
    }

    public DropDownListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init(context);
    }

    public DropDownListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getAttrs(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        initDropDownStyle();
        initOnBottomStyle();
        super.setOnScrollListener(this);
    }

    private void initDropDownStyle() {
        if (this.headerLayout != null) {
            if (this.isDropDownStyle) {
                addHeaderView(this.headerLayout);
            } else {
                removeHeaderView(this.headerLayout);
            }
        } else if (this.isDropDownStyle) {
            this.headerReleaseMinDistance = this.context.getResources().getDimensionPixelSize(R.dimen.drop_down_list_header_release_min_distance);
            this.flipAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
            this.flipAnimation.setInterpolator(new LinearInterpolator());
            this.flipAnimation.setDuration(250);
            this.flipAnimation.setFillAfter(true);
            this.reverseFlipAnimation = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            this.reverseFlipAnimation.setInterpolator(new LinearInterpolator());
            this.reverseFlipAnimation.setDuration(250);
            this.reverseFlipAnimation.setFillAfter(true);
            this.headerDefaultText = this.context.getString(R.string.drop_down_list_header_default_text);
            this.headerPullText = this.context.getString(R.string.drop_down_list_header_pull_text);
            this.headerReleaseText = this.context.getString(R.string.drop_down_list_header_release_text);
            this.headerLoadingText = this.context.getString(R.string.drop_down_list_header_loading_text);
            this.headerLayout = (RelativeLayout) ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.drop_down_list_header, this, false);
            this.headerText = (TextView) this.headerLayout.findViewById(R.id.drop_down_list_header_default_text);
            this.headerImage = (ImageView) this.headerLayout.findViewById(R.id.drop_down_list_header_image);
            this.headerProgressBar = (ProgressBar) this.headerLayout.findViewById(R.id.drop_down_list_header_progress_bar);
            this.headerSecondText = (TextView) this.headerLayout.findViewById(R.id.drop_down_list_header_second_text);
            this.headerLayout.setClickable(true);
            this.headerLayout.setOnClickListener(new C01851());
            this.headerText.setText(this.headerDefaultText);
            addHeaderView(this.headerLayout);
            measureHeaderLayout(this.headerLayout);
            this.headerOriginalHeight = this.headerLayout.getMeasuredHeight();
            this.headerOriginalTopPadding = this.headerLayout.getPaddingTop();
            this.currentHeaderStatus = 1;
        }
    }

    private void initOnBottomStyle() {
        if (this.footerLayout != null) {
            if (this.isOnBottomStyle) {
                addFooterView(this.footerLayout);
            } else {
                removeFooterView(this.footerLayout);
            }
        } else if (this.isOnBottomStyle) {
            this.footerDefaultText = this.context.getString(R.string.drop_down_list_footer_default_text);
            this.footerLoadingText = this.context.getString(R.string.drop_down_list_footer_loading_text);
            this.footerNoMoreText = this.context.getString(R.string.drop_down_list_footer_no_more_text);
            this.footerLayout = (RelativeLayout) ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.drop_down_list_footer, this, false);
            this.footerButton = (Button) this.footerLayout.findViewById(R.id.drop_down_list_footer_button);
            this.footerButton.setDrawingCacheBackgroundColor(0);
            this.footerButton.setEnabled(true);
            this.footerProgressBar = (ProgressBar) this.footerLayout.findViewById(R.id.drop_down_list_footer_progress_bar);
            addFooterView(this.footerLayout);
        }
    }

    public boolean isDropDownStyle() {
        return this.isDropDownStyle;
    }

    public void setDropDownStyle(boolean isDropDownStyle) {
        if (this.isDropDownStyle != isDropDownStyle) {
            this.isDropDownStyle = isDropDownStyle;
            initDropDownStyle();
        }
    }

    public boolean isOnBottomStyle() {
        return this.isOnBottomStyle;
    }

    public void setOnBottomStyle(boolean isOnBottomStyle) {
        if (this.isOnBottomStyle != isOnBottomStyle) {
            this.isOnBottomStyle = isOnBottomStyle;
            initOnBottomStyle();
        }
    }

    public boolean isAutoLoadOnBottom() {
        return this.isAutoLoadOnBottom;
    }

    public void setAutoLoadOnBottom(boolean isAutoLoadOnBottom) {
        this.isAutoLoadOnBottom = isAutoLoadOnBottom;
    }

    public boolean isShowFooterProgressBar() {
        return this.isShowFooterProgressBar;
    }

    public void setShowFooterProgressBar(boolean isShowFooterProgressBar) {
        this.isShowFooterProgressBar = isShowFooterProgressBar;
    }

    public boolean isShowFooterWhenNoMore() {
        return this.isShowFooterWhenNoMore;
    }

    public void setShowFooterWhenNoMore(boolean isShowFooterWhenNoMore) {
        this.isShowFooterWhenNoMore = isShowFooterWhenNoMore;
    }

    public Button getFooterButton() {
        return this.footerButton;
    }

    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (this.isDropDownStyle) {
            setSecondPositionVisible();
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.onScrollListener = listener;
    }

    public void setOnDropDownListener(OnDropDownListener onDropDownListener) {
        this.onDropDownListener = onDropDownListener;
    }

    public void setOnBottomListener(OnClickListener onBottomListener) {
        this.footerButton.setOnClickListener(onBottomListener);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.isDropDownStyle) {
            return super.onTouchEvent(event);
        }
        this.hasReachedTop = false;
        switch (event.getAction()) {
            case 0:
                this.actionDownPointY = event.getY();
                break;
            case 1:
                if (!isVerticalScrollBarEnabled()) {
                    setVerticalScrollBarEnabled(true);
                }
                if (getFirstVisiblePosition() == 0 && this.currentHeaderStatus != 4) {
                    switch (this.currentHeaderStatus) {
                        case 2:
                            setHeaderStatusClickToLoad();
                            setSecondPositionVisible();
                            break;
                        case 3:
                            onDropDown();
                            break;
                        default:
                            break;
                    }
                }
            case 2:
                adjustHeaderPadding(event);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (this.isDropDownStyle) {
            if (this.currentScrollState != 1 || this.currentHeaderStatus == 4) {
                if (this.currentScrollState == 2 && firstVisibleItem == 0 && this.currentHeaderStatus != 4) {
                    setSecondPositionVisible();
                    this.hasReachedTop = true;
                } else if (this.currentScrollState == 2 && this.hasReachedTop) {
                    setSecondPositionVisible();
                }
            } else if (firstVisibleItem == 0) {
                this.headerImage.setVisibility(0);
                int pointBottom = this.headerOriginalHeight + this.headerReleaseMinDistance;
                if (this.headerLayout.getBottom() >= pointBottom) {
                    setHeaderStatusReleaseToLoad();
                } else if (this.headerLayout.getBottom() < pointBottom) {
                    setHeaderStatusDropDownToLoad();
                }
            } else {
                setHeaderStatusClickToLoad();
            }
        }
        if (this.isOnBottomStyle && this.isAutoLoadOnBottom && this.hasMore && firstVisibleItem > 0 && totalItemCount > 0 && firstVisibleItem + visibleItemCount == totalItemCount) {
            onBottom();
        }
        if (this.onScrollListener != null) {
            this.onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (this.isDropDownStyle) {
            this.currentScrollState = scrollState;
            if (this.currentScrollState == 0) {
                this.hasReachedTop = false;
            }
        }
        if (this.onScrollListener != null) {
            this.onScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    private void onDropDownBegin() {
        if (this.isDropDownStyle) {
            setHeaderStatusLoading();
        }
    }

    public void onDropDown() {
        if (this.currentHeaderStatus != 4 && this.isDropDownStyle && this.onDropDownListener != null) {
            onDropDownBegin();
            this.onDropDownListener.onDropDown();
        }
    }

    public void onDropDownComplete(CharSequence secondText) {
        if (this.isDropDownStyle) {
            setHeaderSecondText(secondText);
            onDropDownComplete();
        }
    }

    public void setHeaderSecondText(CharSequence secondText) {
        if (!this.isDropDownStyle) {
            return;
        }
        if (secondText == null) {
            this.headerSecondText.setVisibility(8);
            return;
        }
        this.headerSecondText.setVisibility(0);
        this.headerSecondText.setText(secondText);
    }

    public void onDropDownComplete() {
        if (this.isDropDownStyle) {
            setHeaderStatusClickToLoad();
            if (this.headerLayout.getBottom() > 0) {
                invalidateViews();
                setSecondPositionVisible();
            }
        }
    }

    private void onBottomBegin() {
        if (this.isOnBottomStyle) {
            if (this.isShowFooterProgressBar) {
                this.footerProgressBar.setVisibility(0);
            }
            this.footerButton.setText(this.footerLoadingText);
            this.footerButton.setEnabled(false);
        }
    }

    public void onBottom() {
        if (this.isOnBottomStyle && !this.isOnBottomLoading) {
            this.isOnBottomLoading = true;
            onBottomBegin();
            this.footerButton.performClick();
        }
    }

    public void onBottomComplete() {
        if (this.isOnBottomStyle) {
            if (this.isShowFooterProgressBar) {
                this.footerProgressBar.setVisibility(8);
            }
            if (this.hasMore) {
                this.footerButton.setText(this.footerDefaultText);
                this.footerButton.setEnabled(true);
            } else {
                this.footerButton.setText(this.footerNoMoreText);
                this.footerButton.setEnabled(false);
                if (!this.isShowFooterWhenNoMore) {
                    removeFooterView(this.footerLayout);
                }
            }
            this.isOnBottomLoading = false;
        }
    }

    public void setSecondPositionVisible() {
        if (getAdapter() != null && getAdapter().getCount() > 0 && getFirstVisiblePosition() == 0) {
            setSelection(1);
        }
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public RelativeLayout getHeaderLayout() {
        return this.headerLayout;
    }

    public RelativeLayout getFooterLayout() {
        return this.footerLayout;
    }

    public float getHeaderPaddingTopRate() {
        return this.headerPaddingTopRate;
    }

    public void setHeaderPaddingTopRate(float headerPaddingTopRate) {
        this.headerPaddingTopRate = headerPaddingTopRate;
    }

    public int getHeaderReleaseMinDistance() {
        return this.headerReleaseMinDistance;
    }

    public void setHeaderReleaseMinDistance(int headerReleaseMinDistance) {
        this.headerReleaseMinDistance = headerReleaseMinDistance;
    }

    public String getHeaderDefaultText() {
        return this.headerDefaultText;
    }

    public void setHeaderDefaultText(String headerDefaultText) {
        this.headerDefaultText = headerDefaultText;
        if (this.headerText != null && this.currentHeaderStatus == 1) {
            this.headerText.setText(headerDefaultText);
        }
    }

    public String getHeaderPullText() {
        return this.headerPullText;
    }

    public void setHeaderPullText(String headerPullText) {
        this.headerPullText = headerPullText;
    }

    public String getHeaderReleaseText() {
        return this.headerReleaseText;
    }

    public void setHeaderReleaseText(String headerReleaseText) {
        this.headerReleaseText = headerReleaseText;
    }

    public String getHeaderLoadingText() {
        return this.headerLoadingText;
    }

    public void setHeaderLoadingText(String headerLoadingText) {
        this.headerLoadingText = headerLoadingText;
    }

    public String getFooterDefaultText() {
        return this.footerDefaultText;
    }

    public void setFooterDefaultText(String footerDefaultText) {
        this.footerDefaultText = footerDefaultText;
        if (this.footerButton != null && this.footerButton.isEnabled()) {
            this.footerButton.setText(footerDefaultText);
        }
    }

    public String getFooterLoadingText() {
        return this.footerLoadingText;
    }

    public void setFooterLoadingText(String footerLoadingText) {
        this.footerLoadingText = footerLoadingText;
    }

    public String getFooterNoMoreText() {
        return this.footerNoMoreText;
    }

    public void setFooterNoMoreText(String footerNoMoreText) {
        this.footerNoMoreText = footerNoMoreText;
    }

    private void setHeaderStatusClickToLoad() {
        if (this.currentHeaderStatus != 1) {
            resetHeaderPadding();
            this.headerImage.clearAnimation();
            this.headerImage.setVisibility(8);
            this.headerProgressBar.setVisibility(8);
            this.headerText.setText(this.headerDefaultText);
            this.currentHeaderStatus = 1;
        }
    }

    private void setHeaderStatusDropDownToLoad() {
        if (this.currentHeaderStatus != 2) {
            this.headerImage.setVisibility(0);
            if (this.currentHeaderStatus != 1) {
                this.headerImage.clearAnimation();
                this.headerImage.startAnimation(this.reverseFlipAnimation);
            }
            this.headerProgressBar.setVisibility(8);
            this.headerText.setText(this.headerPullText);
            if (isVerticalFadingEdgeEnabled()) {
                setVerticalScrollBarEnabled(false);
            }
            this.currentHeaderStatus = 2;
        }
    }

    private void setHeaderStatusReleaseToLoad() {
        if (this.currentHeaderStatus != 3) {
            this.headerImage.setVisibility(0);
            this.headerImage.clearAnimation();
            this.headerImage.startAnimation(this.flipAnimation);
            this.headerProgressBar.setVisibility(8);
            this.headerText.setText(this.headerReleaseText);
            this.currentHeaderStatus = 3;
        }
    }

    private void setHeaderStatusLoading() {
        if (this.currentHeaderStatus != 4) {
            resetHeaderPadding();
            this.headerImage.setVisibility(8);
            this.headerImage.clearAnimation();
            this.headerProgressBar.setVisibility(0);
            this.headerText.setText(this.headerLoadingText);
            this.currentHeaderStatus = 4;
            setSelection(0);
        }
    }

    private void adjustHeaderPadding(MotionEvent ev) {
        int pointerCount = ev.getHistorySize();
        if (isVerticalFadingEdgeEnabled()) {
            setVerticalScrollBarEnabled(false);
        }
        for (int i = 0; i < pointerCount; i++) {
            if (this.currentHeaderStatus == 2 || this.currentHeaderStatus == 3) {
                this.headerLayout.setPadding(this.headerLayout.getPaddingLeft(), (int) (((ev.getHistoricalY(i) - this.actionDownPointY) - ((float) this.headerOriginalHeight)) / this.headerPaddingTopRate), this.headerLayout.getPaddingRight(), this.headerLayout.getPaddingBottom());
            }
        }
    }

    private void resetHeaderPadding() {
        this.headerLayout.setPadding(this.headerLayout.getPaddingLeft(), this.headerOriginalTopPadding, this.headerLayout.getPaddingRight(), this.headerLayout.getPaddingBottom());
    }

    private void measureHeaderLayout(View child) {
        int childHeightSpec;
        LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new LayoutParams(-1, -2);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, 1073741824);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.drop_down_list_attr);
        this.isDropDownStyle = ta.getBoolean(0, false);
        this.isOnBottomStyle = ta.getBoolean(1, false);
        this.isAutoLoadOnBottom = ta.getBoolean(2, false);
        ta.recycle();
    }
}
