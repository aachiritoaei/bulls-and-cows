package com.ach.alex.bullsandcowsreloaded;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by achir on 23-Nov-16.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ArrayList<String>> list;
    private Activity activity;

    private static final int NAME = 0;
    private static final int SCORE = 1;


    public ListViewAdapter(Activity activity, ArrayList<ArrayList<String>> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder;
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_view_row, null);

            TextView textFirst = (TextView) view.findViewById(R.id.name);
            TextView textSecond = (TextView) view.findViewById(R.id.score);

            holder = new Holder(textFirst, textSecond);

            view.setTag(holder);
        }

        holder = (Holder) view.getTag();

        ArrayList<String> rowValues = list.get(i);

        holder.textFirst.setText(rowValues.get(NAME));
        holder.textSecond.setText(rowValues.get(SCORE));

        return view;
    }

    // holder for a row
    private static class Holder {
        TextView textFirst;
        TextView textSecond;

        public Holder(TextView textFirst, TextView textSecond) {
            this.textFirst = textFirst;
            this.textSecond = textSecond;
        }
    }
}
