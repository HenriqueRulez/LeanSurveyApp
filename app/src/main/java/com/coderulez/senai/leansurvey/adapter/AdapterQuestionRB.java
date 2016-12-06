package com.coderulez.senai.leansurvey.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.coderulez.senai.leansurvey.R;
import com.coderulez.senai.leansurvey.model.Option;


//Sobe pelo amor de deus, essa desgraça não sobe pro git eu vou me matar velho ta tudo mudado e aparece clear eu me odeio
/**
 * Created by SENAI on 03/12/2016.
 */

public class AdapterQuestionRB extends BaseAdapter{


    Option[] dataSource;
    LayoutInflater inflater;

    public AdapterQuestionRB(Context context, Option[] dataSource)
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
            result = (ConstraintLayout)inflater.inflate(R.layout.radiobutton_cell, parent, false);
        }
        else
        {
            result = (ConstraintLayout) convertView;
        }

        Option op = dataSource[position];

        ((RadioButton)result.findViewById(R.id.radioButton)).setText(op.getDescription());
        ((RadioButton)result.findViewById(R.id.radioButton)).setTag(op.getOrdem());

        return  result;
    }
}
