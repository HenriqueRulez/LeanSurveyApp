package com.coderulez.senai.leansurvey.adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.model.Option;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;

/**
 * Created by SENAI on 03/12/2016.
 */

public class AdapterQuestionCB extends BaseAdapter
{

    Option[] dataSource;
    LayoutInflater inflater;

    public AdapterQuestionCB(Context context, Option[] dataSource)
    {
        inflater = LayoutInflater.from(context);
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.length;
    }

    @Override
    public Object getItem(int position) {
        return dataSource[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ConstraintLayout result;
        if (convertView == null)
        {
            result = (ConstraintLayout)inflater.inflate(R.layout.checkbox_cell, parent, false);
        }
        else
        {
            result = (ConstraintLayout) convertView;
        }

        Option op = dataSource[position];

        ((CheckBox)result.findViewById(R.id.cbCheckBoxCell)).setText(op.getDescription());
        ((CheckBox)result.findViewById(R.id.cbCheckBoxCell)).setTag(op.getOrdem());

        return  result;
    }
}
