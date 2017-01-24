package com.example.claire.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 22/01/2017.
 */

public class ListTaskAdaptater extends ArrayAdapter<TaskModel> {

    private final Context context;
    public final List<TaskModel> data;
    private final int layoutResourceId;
    public final static String TASK_SELECTED_NAME = "TASK_SELECTED_NAME";
    public final static String TASK_SELECTED_CONTENT = "TASK_SELECTED_CONTENT";
    public final static String TASK_SELECTED_DATE = "TASK_SELECTED_DATE";


    public ListTaskAdaptater(Context context,int layoutResourceId, List<TaskModel> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        ImageButton delete_button;
        ImageButton modif_button;

        if(row == null)
        {
            final Intent intent = new Intent(parent.getContext(), ModifActivity.class);
            final LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.name= (TextView) row.findViewById(R.id.listName);
            holder.content = (TextView) row.findViewById(R.id.ListContent);
            holder.date = (TextView) row.findViewById(R.id.ListDate);

            modif_button = (ImageButton)row.findViewById(R.id.button_modif);
            delete_button = (ImageButton)row.findViewById(R.id.button_delete);

            delete_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   TaskModel taskdelete = data.get(position);
                    data.remove(data.get(position));
                    notifyDataSetChanged();
                    MainActivity.dataSource.deleteTaskModel(taskdelete);
                }
            });
            modif_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    intent.putExtra(TASK_SELECTED_NAME, data.get(position).getName());
                    intent.putExtra(TASK_SELECTED_CONTENT, data.get(position).getContent());
                    intent.putExtra(TASK_SELECTED_DATE, data.get(position).getDate());
                    context.startActivity(intent);
                }
            });
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

     public TaskModel getTask(int position){
         TaskModel dataNew = data.get(position);
         return dataNew;
    }
}
