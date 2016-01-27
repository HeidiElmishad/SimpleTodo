package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
/**
 * Created by snapfish on 1/25/16.
 */
public class AddTaskActivity extends AppCompatActivity {

    private final static String TAG =  AddTaskActivity.class.getName();

    private Spinner mPrioritySpinner;
    private Spinner mStatusSpinner;
    private EditText mTaskName;
    private EditText mtaskNotes;
    private String mStatus;
    private String mPriority;
    private CalendarView mDateCalendar;
    private String mTaskDate;
    private long milliTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_layout);

        mTaskName = (EditText) findViewById(R.id.task_name_text);
        mtaskNotes = (EditText) findViewById(R.id.task_notes_text);


        mPrioritySpinner = (Spinner) findViewById(R.id.priority_spinner);
        ArrayAdapter<CharSequence> mPriorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_priority_values, android.R.layout.simple_spinner_item);
        mPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPrioritySpinner.setAdapter(mPriorityAdapter);
        mPrioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPriority = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mStatusSpinner = (Spinner) findViewById(R.id.status_spinner);
        ArrayAdapter<CharSequence> mStatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_status_values, android.R.layout.simple_spinner_item);
        mStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(mStatusAdapter);
        mStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDateCalendar = (CalendarView) findViewById(R.id.date_calendar);
        mDateCalendar.setDate(Calendar.getInstance().getTimeInMillis());
        mDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                mDate.set(year, month, dayOfMonth);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                milliTime = calendar.getTimeInMillis();
                Log.d(TAG, "Add Task milliTime " + milliTime);
                mTaskDate = "" + (month+1) + "/" + dayOfMonth;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("taskName", mTaskName.getText().toString());
        data.putExtra("taskNotes", mtaskNotes.getText().toString());
        data.putExtra("taskPriority", mPriority);
        data.putExtra("taskstatus", mStatus);
        data.putExtra("taskDate", mTaskDate);
        data.putExtra("taskMilliTime", milliTime);
        setResult(RESULT_OK, data);
        super.finish();
    }

}
