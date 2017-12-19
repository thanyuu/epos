package com.cloudpos.apidemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.android.common.utils.HttpUtils;
import com.android.common.utils.PackageUtils;
import com.android.common.utils.ShellUtils;
import com.cloudpos.apidemo.action.IDCardAction;
import com.cloudpos.apidemo.common.OnClicker;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.function.ActionReflect;
import com.cloudpos.apidemo.function.HandlerImpl;
import com.cloudpos.apidemo.function.ListViewAdapter;
import com.cloudpos.apidemo.manager.AppManager;
import com.cloudpos.mvc.base.ActionManager;
import java.util.HashMap;

public class MainActivity extends ConstantActivity {

    class C02201 implements OnClickListener {
        C02201() {
        }

        public void onClick(View v) {
            MainActivity.this.keyPressed(v.getId());
        }
    }

    class C02212 implements OnItemClickListener {
        C02212() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            MainActivity.this.itemPressed(view.findViewById(C0223R.id.lvw_button).getTag().toString(), position);
        }
    }

    class C02223 implements OnScrollListener {
        C02223() {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == 0 && MainActivity.this.isMain) {
                ConstantActivity.position = MainActivity.this.lvwMain.getFirstVisiblePosition();
            }
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams();
        OnClicker.setOnClickListenerByIds(host, this.btnWidget, new C02201());
        setData(this.lvwMain);
        this.lvwMain.setOnItemClickListener(new C02212());
        this.lvwMain.setOnScrollListener(new C02223());
    }

    public void onResume() {
        super.onResume();
        if (isHand) {
            setRequestedOrientation(1);
        }
    }

    private void initParams() {
        handler = new HandlerImpl(txtResult);
        callback = new ActionCallbackImpl(handler);
        this.arryTag = ActionReflect.getArraysXml(this, AppManager.model);
        this.arryText = ActionReflect.getStringsXml(this, AppManager.model);
        txtVersion.setText(getResources().getString(C0223R.string.version));
        host = this;
        this.context = this;
        this.btnWidget = new int[]{C0223R.id.btn_clean, C0223R.id.btn_exit};
        this.param = new HashMap();
    }

    private void setData(ListView listView) {
        this.adapter = new ListViewAdapter(this.arryText, this.arryTag, host);
        this.lvwMain.setAdapter(this.adapter);
    }

    private void keyPressed(int id) {
        if (id == C0223R.id.btn_clean) {
            txtResult.setText("");
        } else if (id != C0223R.id.btn_exit) {
        } else {
            if (this.isMain) {
                PackageUtils.uninstallNormal(this, getPackageName());
                return;
            }
            this.btnExit.setText(getResources().getString(C0223R.string.uninstall));
            if (this.layoutJianjie != null) {
                this.layoutJianjie.setVisibility(8);
            }
            refreshListView(AppManager.model);
            this.lvwMain.setSelection(position);
            this.isMain = true;
            closeAllDriver();
            ConstantActivity.writeLog(getResources().getString(C0223R.string.testEnd));
        }
    }

    private void itemPressed(String command, int position) {
        Log.e("MainActivity", command);
        if (this.isMain) {
            String commandText = (String) this.arryText.get(position);
            ConstantActivity.writeLog(getResources().getString(C0223R.string.welcome) + "\t" + commandText);
            if (command.equals(getResources().getString(C0223R.string.IDCardAction))) {
                startActivityForResult(new Intent(this, IDCardAction.class), 0);
                return;
            }
            this.isMain = false;
            this.btnExit.setText(getResources().getString(C0223R.string.back));
            mainMenu = command;
            if (this.layoutJianjie != null) {
                this.layoutJianjie.setVisibility(0);
                this.txtJianjie.setText(getResources().getString(C0223R.string.jianjie) + ShellUtils.COMMAND_LINE_END + commandText);
            }
            refreshListView(command);
            return;
        }
        this.param.clear();
        this.param.put("host", host);
        ActionManager.doSubmit(mainMenu + HttpUtils.PATHS_SEPARATOR + command, this.context, this.param, callback);
    }

    private void closeAllDriver() {
    }

    private void refreshListView(String command) {
        this.arryTag.clear();
        this.arryText.clear();
        String priorCommand = command + AppManager.model;
        if (ActionReflect.getStringsXml(host, priorCommand) != null) {
            this.arryText = ActionReflect.getStringsXml(host, priorCommand);
            this.arryTag = ActionReflect.getArraysXml(host, priorCommand);
        } else {
            this.arryText = ActionReflect.getStringsXml(host, command);
            this.arryTag = ActionReflect.getArraysXml(host, command);
        }
        this.adapter.refreshData(this.arryText, this.arryTag);
        this.lvwMain.setAdapter(this.adapter);
    }
}
