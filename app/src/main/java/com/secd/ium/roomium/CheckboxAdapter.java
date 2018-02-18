package com.secd.ium.roomium;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by Tommaso on 12/02/2018.
 */

public class CheckboxAdapter extends ArrayAdapter<Model> {
    Model[] modelItems = null;
    Context context;

    public CheckboxAdapter(Context context, Model[] resource) {
        super(context,R.layout.layout_row,resource);
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.layout_row, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.textView);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);

        name.setText(modelItems[position].getName());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) modelItems[position].setValue(1);
                else modelItems[position].setValue(0);

            }
        });

        if (modelItems[position].getValue() == 1) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }

        return convertView;
    }
}
