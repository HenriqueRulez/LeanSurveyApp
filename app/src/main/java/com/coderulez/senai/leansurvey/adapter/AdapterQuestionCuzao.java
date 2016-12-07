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
import android.widget.RadioButton;
import android.widget.CompoundButton;
import java.util.List;
import java.util.ArrayList;
import android.widget.ListView;
/**
 * Created by SENAI on 03/12/2016.
 */

public class AdapterQuestionCuzao extends BaseAdapter implements View.OnClickListener {

    Option[] dataSource;
    LayoutInflater inflater;
    int layout;
    int componentId;
    boolean isMultipleChoice;
    List<CompoundButton> btns = new ArrayList();
    ListView listView;

    public AdapterQuestionCuzao(Context context, Option[] dataSource, boolean isMultipleChoice, ListView listView)
    {
        this.listView = listView;
        inflater = LayoutInflater.from(context);
        this.dataSource = dataSource;
        this.isMultipleChoice = isMultipleChoice;
        layout = isMultipleChoice ? R.layout.checkbox_cell : R.layout.radiobutton_cell;
        componentId = isMultipleChoice ? R.id.cbCheckBoxCell : R.id.radioButton;

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
        if(convertView == null)
        {
            result = (ConstraintLayout) inflater.inflate(layout, parent, false);
        }
        else
        {
            result = (ConstraintLayout)convertView;
        }

        Option op = dataSource[position];

        CompoundButton btn = (CompoundButton) result.findViewById(componentId);
        if (btn.isChecked() != op.isSelected())
        {
            btn.setChecked(op.isSelected());
        }

        btn.setText(op.getDescription());
        btn.setTag(op);
        btn.setOnClickListener(this);



        return  result;
    }

    @Override
    public void onClick(View v)
    {
        Option op = (Option)v.getTag();
        if (!isMultipleChoice)
        {
            for (Option o : this.dataSource)
            {
                o.setSelected(o == op);
            }
        }
        else
        {
            op.setSelected(!op.isSelected());
        }
        this.notifyDataSetChanged();
    }
}
