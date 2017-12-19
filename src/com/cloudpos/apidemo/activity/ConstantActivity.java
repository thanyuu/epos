package com.cloudpos.apidemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.common.utils.ShellUtils;
import com.cloudpos.apidemo.function.ListViewAdapter;
import com.cloudpos.mvc.base.ActionCallback;
import java.util.List;
import java.util.Map;

public abstract class ConstantActivity extends Activity {
    protected static final String TAG = "MainActivity";
    public static ActionCallback callback;
    public static Handler handler;
    public static Activity host;
    public static boolean isHand = false;
    public static boolean isQ1 = false;
    public static String mainMenu = "Main";
    public static int position;
    public static TextView txtResult;
    public static TextView txtVersion;
    protected ListViewAdapter adapter;
    protected List<String> arryTag;
    protected List<String> arryText;
    protected Button btnClean;
    protected Button btnExit;
    protected int[] btnWidget;
    protected Context context;
    protected boolean isMain = true;
    protected LinearLayout layoutJianjie;
    protected ListView lvwMain;
    protected Map<String, Object> param;
    protected TextView txtJianjie;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0223R.layout.activity_main1);
        initView();
    }

    private void initView() {
        this.layoutJianjie = (LinearLayout) findViewById(C0223R.id.layout_jianjie);
        this.txtJianjie = (TextView) findViewById(C0223R.id.txt_jianjie);
        txtVersion = (TextView) findViewById(C0223R.id.txt_version);
        txtResult = (TextView) findViewById(C0223R.id.txt_result);
        txtResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.lvwMain = (ListView) findViewById(C0223R.id.lvw_main);
        this.btnExit = (Button) findViewById(C0223R.id.btn_exit);
        this.btnClean = (Button) findViewById(C0223R.id.btn_clean);
    }

    public static void writeLog(String obj) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = obj + ShellUtils.COMMAND_LINE_END;
        handler.sendMessage(msg);
    }
}
