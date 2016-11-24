package com.coderulez.senai.leansurvey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coderulez.senai.leansurvey.model.Model;
import com.coderulez.senai.leansurvey.R;

import java.util.List;

/**
 * Created by SENAI on 03/11/2016.
 */

public class AdapterNocao extends BaseAdapter
{

    List<Model> dataSource;
    LayoutInflater inflater;

    public AdapterNocao(List<Model> dataSource, Context context)
    {
        this.dataSource = dataSource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout;
        if (convertView == null)
        {
            layout = this.inflater.inflate(R.layout.card_cell, parent, false);
        }
        else
        {
            layout = convertView;
        }

      ((TextView)layout.findViewById(R.id.cell_title)).setText(dataSource.get(position).getTitulo());

        return layout;
    }
}
