package com.cloudpos.apidemo.function;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cloudpos.apidemo.activity.C0223R;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<String> arryTag;
    private List<String> arryText;
    private LayoutInflater inflater;
    TextView txtListBtn;

    public static class holder {
        public static TextView listbtn;
    }

    public ListViewAdapter(List<String> arryText, List<String> arryTag, Activity mainActivity) {
        this.arryText = arryText;
        this.arryTag = arryTag;
        this.inflater = LayoutInflater.from(mainActivity);
    }

    public int getCount() {
        if (this.arryText == null) {
            return 0;
        }
        return this.arryText.size();
    }

    public Object getItem(int position) {
        if (this.arryText == null) {
            return null;
        }
        return this.arryText.get(position);
    }

    public long getItemId(int position) {
        if (this.arryText == null) {
            return 0;
        }
        return (long) position;
    }

    public View getView(int position, View contentview, ViewGroup arg2) {
        View view = contentview;
        if (contentview == null) {
            view = this.inflater.inflate(C0223R.layout.item_main, null);
        }
        this.txtListBtn = (TextView) view.findViewById(C0223R.id.lvw_button);
        this.txtListBtn.setTag(this.arryTag.get(position));
        this.txtListBtn.setText((CharSequence) this.arryText.get(position));
        return view;
    }

    public void refreshData(List<String> arryText, List<String> arryTag) {
        this.arryText.clear();
        this.arryText = arryText;
        this.arryTag.clear();
        this.arryTag = arryTag;
        notifyDataSetChanged();
    }

    public List<String> getListTag() {
        List<String> lsTag = new ArrayList();
        for (int i = 0; i < this.arryText.size(); i++) {
            lsTag.add(this.arryTag.get(i));
        }
        return lsTag;
    }
}
