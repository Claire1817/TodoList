package com.example.claire.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class ModifActivity extends AppCompatActivity {

    private EditText content;
    private EditText date;
    private TextView name;
    private Button button_modif;
    private Button button_date;
    int Year;
    int Month;
    int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif);

        final Intent intent = getIntent();

        content = (EditText)findViewById(R.id.add_task_content_modif);
        date = (EditText) findViewById(R.id.add_task_date_modif);
        name = (TextView) findViewById(R.id.name);

        final String taskLastName = intent.getStringExtra(ListTaskAdaptater.TASK_SELECTED_NAME);
        final String taskLastContent = intent.getStringExtra(ListTaskAdaptater.TASK_SELECTED_CONTENT);
        final String taskLastDate = intent.getStringExtra(ListTaskAdaptater.TASK_SELECTED_DATE);

        content.setText(taskLastContent);
        date.setText(taskLastDate);
        name.setText(taskLastName);

        button_date = (Button) findViewById(R.id.task_select);
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                Year = c.get(Calendar.YEAR);
                Month = c.get(Calendar.MONTH);
                Day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ModifActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, Year, Month, Day);
                datePickerDialog.show();
            }

        });
        
        button_modif = (Button) findViewById(R.id.button_modif);
        button_modif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(ModifActivity.this, MainActivity.class);

                String taskContent = content.getText().toString();
                String taskDate = date.getText().toString();

                if (taskContent != taskLastContent || taskLastDate != taskDate) {
                    Toast.makeText(getApplicationContext(), "Task Modified", Toast.LENGTH_LONG).show();
                    MainActivity.dataSource.queryUpdate(taskLastName, taskContent, taskDate);
                    startActivity(newIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Task not Modified", Toast.LENGTH_LONG).show();
                    startActivity(newIntent);
                }
            }
        });
    }
}
