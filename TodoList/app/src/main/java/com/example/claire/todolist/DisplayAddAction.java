package com.example.claire.todolist;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;



import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class DisplayAddAction extends AppCompatActivity {

    Button add;
    private Intent intent;
    private EditText task_date_editText;
    private Button buttonDate;
    private int Year;
    private int Month;
    private int Day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add_action);

        task_date_editText = (EditText) findViewById(R.id.add_task_date);
        buttonDate = (Button) findViewById(R.id.task_select);
        buttonDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Get Current Date
                        final Calendar c = Calendar.getInstance();
                        Year = c.get(Calendar.YEAR);
                        Month = c.get(Calendar.MONTH);
                        Day = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(DisplayAddAction.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                task_date_editText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, Year, Month, Day);
                        datePickerDialog.show();
                    }

                });

        add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(DisplayAddAction.this, MainActivity.class);

                EditText task_name_editText = (EditText) findViewById(R.id.add_task_name);
                EditText task_content_editText = (EditText) findViewById(R.id.add_task_content);

                String taskName = task_name_editText.getText().toString();
                String taskContent = task_content_editText.getText().toString();
                String taskDate = task_date_editText.getText().toString();

                if (!taskDate.isEmpty() && !taskName.isEmpty() && !taskContent.isEmpty()) {
                    MainActivity.dataSource.createTaskModel(taskName, taskContent, taskDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "The title, name and date couldn't be empty ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
