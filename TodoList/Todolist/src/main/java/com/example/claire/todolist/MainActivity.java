package com.example.claire.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity{

    public static TaskDataSource dataSource;
    Button button_add;
    public ListView list;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(this, DisplayAddAction.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add = (Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        dataSource = new TaskDataSource(this);
        dataSource.open();



        list = (ListView)findViewById(R.id.list_item);

        List<TaskModel> values = dataSource.getAllTask();

        ListTaskAdaptater adapter = new ListTaskAdaptater(this, R.layout.row_layout, values);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskModel item = (TaskModel) parent.getItemAtPosition(position);
            }
        });
    }
}
