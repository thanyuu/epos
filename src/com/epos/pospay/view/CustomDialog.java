package com.epos.pospay.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.epos.pospay.C0258R;

public class CustomDialog extends AlertDialog {
    private Context context;
    private DialogType dialogType;
    private String loadingText;
    private boolean mHasStarted;
    private LinearLayout mLayout_confirm;
    private LinearLayout mLayout_loading;
    private RelativeLayout mLayout_progress;
    private ProgressBar mProgressBar;
    private TextView mTv_LoadingText;
    private TextView mTv_Message;
    private TextView mTv_NegativeButton;
    private TextView mTv_PositiveButton;
    private TextView mTv_ProgressText;
    private TextView mTv_ProgressTitle;
    private String message;
    private OnClickListener negativeButtonClickListener;
    private String negativeButtonText;
    private OnClickListener positiveButtonClickListener;
    private String positiveButtonText;
    private int progress = -1;
    private String progress_title;

    class C02691 implements View.OnClickListener {
        C02691() {
        }

        public void onClick(View v) {
            CustomDialog.this.positiveButtonClickListener.onClick(CustomDialog.this, -1);
        }
    }

    class C02702 implements View.OnClickListener {
        C02702() {
        }

        public void onClick(View v) {
            CustomDialog.this.negativeButtonClickListener.onClick(CustomDialog.this, -2);
        }
    }

    public enum DialogType {
        Confirm,
        Loading,
        Progress
    }

    public CustomDialog(Context context, DialogType dialogType) {
        super(context);
        this.context = context;
        this.dialogType = dialogType;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0258R.layout.dialog_normal_layout);
        this.mTv_PositiveButton = (TextView) findViewById(C0258R.id.positiveButton);
        this.mTv_NegativeButton = (TextView) findViewById(C0258R.id.negativeButton);
        this.mTv_Message = (TextView) findViewById(C0258R.id.message);
        this.mTv_LoadingText = (TextView) findViewById(C0258R.id.loading_text);
        this.mTv_ProgressText = (TextView) findViewById(C0258R.id.progress_text);
        this.mTv_ProgressTitle = (TextView) findViewById(C0258R.id.progress_title);
        this.mProgressBar = (ProgressBar) findViewById(C0258R.id.customDialog_progress);
        this.mLayout_confirm = (LinearLayout) findViewById(C0258R.id.customDialog_message_buttons);
        this.mLayout_loading = (LinearLayout) findViewById(C0258R.id.content);
        this.mLayout_progress = (RelativeLayout) findViewById(C0258R.id.customDialog_progress_layout);
        setLayoutGone();
        if (this.dialogType == DialogType.Confirm) {
            setConfirmLayout();
        } else if (this.dialogType == DialogType.Loading) {
            setLoadingLayout();
        } else if (this.dialogType == DialogType.Progress) {
            setProgressLayout();
        }
    }

    private void setLayoutGone() {
        this.mLayout_confirm.setVisibility(8);
        this.mLayout_loading.setVisibility(8);
        this.mLayout_progress.setVisibility(8);
    }

    private void setProgressLayout() {
        this.mLayout_progress.setVisibility(0);
        if (this.progress != -1) {
            this.mTv_ProgressText.setText(this.progress + "%");
            this.mProgressBar.setProgress(this.progress);
        } else {
            this.mTv_ProgressText.setVisibility(8);
            this.mProgressBar.setVisibility(8);
        }
        if (this.progress_title != null) {
            this.mTv_ProgressTitle.setText(this.progress_title);
        } else {
            this.mTv_ProgressTitle.setVisibility(8);
        }
    }

    private void setLoadingLayout() {
        this.mLayout_loading.setVisibility(0);
        if (this.loadingText != null) {
            this.mTv_LoadingText.setText(this.loadingText);
        } else {
            this.mTv_LoadingText.setVisibility(8);
        }
    }

    private void setConfirmLayout() {
        this.mLayout_confirm.setVisibility(0);
        if (this.positiveButtonText != null) {
            this.mTv_PositiveButton.setText(this.positiveButtonText);
            if (this.positiveButtonClickListener != null) {
                this.mTv_PositiveButton.setOnClickListener(new C02691());
            }
        } else {
            this.mTv_PositiveButton.setVisibility(8);
        }
        if (this.negativeButtonText != null) {
            this.mTv_NegativeButton.setText(this.negativeButtonText);
            if (this.negativeButtonClickListener != null) {
                this.mTv_NegativeButton.setOnClickListener(new C02702());
            }
        } else {
            this.mTv_NegativeButton.setVisibility(8);
        }
        if (this.message != null) {
            this.mTv_Message.setText(this.message);
        }
    }

    public void setConfirmText(String confirmText) {
        this.message = confirmText;
    }

    public void setProgress(int value) {
        if (this.mHasStarted) {
            this.mProgressBar.setProgress(value);
            this.mTv_ProgressText.setText(value + "%");
            return;
        }
        this.progress = value;
    }

    public void setProgressText(String progressText) {
        if (this.mTv_ProgressTitle != null) {
            this.mTv_ProgressTitle.setText(progressText);
        } else {
            this.progress_title = progressText;
        }
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    public void setPositiveButton(int positiveButtonText, OnClickListener listener) {
        this.positiveButtonText = (String) this.context.getText(positiveButtonText);
        this.positiveButtonClickListener = listener;
    }

    public void setPositiveButton(String positiveButtonText, OnClickListener listener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonClickListener = listener;
    }

    public void setNegativeButton(int negativeButtonText, OnClickListener listener) {
        this.negativeButtonText = (String) this.context.getText(negativeButtonText);
        this.negativeButtonClickListener = listener;
    }

    public void setNegativeButton(String negativeButtonText, OnClickListener listener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonClickListener = listener;
    }

    protected void onStart() {
        super.onStart();
        this.mHasStarted = true;
    }

    protected void onStop() {
        super.onStop();
        this.mHasStarted = false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
