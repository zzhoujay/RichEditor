package com.zzhoujay.richeditorfunctionbar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by zhou on 2016/11/30.
 */
public class SpinnerAdapter extends BaseAdapter {

    private static final String[] items = {"H", "H1", "H2", "H3"};

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);
        TextView textView = (TextView) view;
        textView.setText(items[position]);
        return view;
    }
}
