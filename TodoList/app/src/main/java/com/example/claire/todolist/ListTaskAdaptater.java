package com.example.claire.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 22/01/2017.
 */

public class ListTaskAdaptater extends ArrayAdapter<TaskModel> {

    private final Context context;
    private final List<TaskModel> data;
    private final int layoutResourceId;


    public ListTaskAdaptater(Context context,int layoutResourceId, List<TaskModel> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.name= (TextView) row.findViewById(R.id.listName);
            holder.content = (TextView) row.findViewById(R.id.ListContent);
            holder.date = (TextView) row.findViewById(R.id.ListDate);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        TaskModel tasks = data.get(position);
        holder.name.setText(tasks.getName());
        holder.date.setText(tasks.getDate());
        holder.content.setText(tasks.getContent());
        return row;
    }

    public TaskModel getItem(int position){
        return data.get(position);
    }
}
