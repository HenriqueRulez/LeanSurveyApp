package com.coderulez.senai.leansurvey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.model.Questionnaire;
import com.coderulez.senai.leansurvey.util.QuestionnaireRest;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Henrique_Rulez on 13/11/2016.
 */

public class AdapterQuestionnaire extends BaseAdapter
{

    List<Questionnaire> dataSource;
    LayoutInflater inflater;


    public AdapterQuestionnaire(Context context)
    {
        this.dataSource = new ArrayList<>();
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
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout;

        if(convertView == null){

            layout = this.inflater.inflate(R.layout.card_cell, parent, false);

        }else{

            layout = convertView;

        }

        ((TextView)layout.findViewById(R.id.cell_title)).setText(dataSource.get((position)).getTitle());


        return layout;
    }

    public void SetItems(Questionnaire[] items)
    {
        dataSource.clear();
        for (Questionnaire item: items)
        {
            dataSource.add(item);
        }
        this.notifyDataSetChanged();
    }

}
