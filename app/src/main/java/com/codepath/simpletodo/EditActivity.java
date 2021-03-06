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
 * Created by snapfish on 1/5/16.
 */
public class EditActivity extends AppCompatActivity {

    private final static String TAG =  EditActivity.class.getName();

    private String mTaskName;
    private String mTaskNotes;
    private String mTaskPriority;
    private String mTaskStatus;
    private String mTaskDate;
    private Long mTaskMilliTime;
    private Spinner mPrioritySpinner;
    private Spinner mStatusSpinner;
    private EditText mNameEditText;
    private EditText mNotesEditText;
    private CalendarView mDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_layout);
        Bundle b = getIntent().getExtras();
        if(b != null) {
            mTaskName = b.getString("taskName");
            mTaskNotes = b.getString("taskNotes");
            mTaskPriority = b.getString("taskPriority");
            mTaskStatus = b.getString("taskstatus");
            mTaskDate = b.getString("taskDate");
            mTaskMilliTime = b.getLong("taskMilliTime");
        }

        mNameEditText = (EditText) findViewById(R.id.task_name_text);
        mNameEditText.setText(mTaskName);
        mNotesEditText = (EditText) findViewById(R.id.task_notes_text);
        mNotesEditText.setText(mTaskNotes);

        mPrioritySpinner = (Spinner) findViewById(R.id.priority_spinner);
        ArrayAdapter<CharSequence> mPriorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_priority_values, android.R.layout.simple_spinner_item);
        mPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPrioritySpinner.setAdapter(mPriorityAdapter);
        mPrioritySpinner.setSelection(getIndex(mPrioritySpinner, mTaskPriority));
        mPrioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTaskPriority = parent.getItemAtPosition(position).toString();
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
        mStatusSpinner.setSelection(getIndex(mStatusSpinner, mTaskStatus));
        mStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTaskStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDateCalendar = (CalendarView) findViewById(R.id.date_calendar);
        Log.d(TAG, "Edit task milliTime " + mTaskMilliTime);
        mDateCalendar.setDate(mTaskMilliTime);
        mDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month+1);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                mTaskMilliTime = calendar.getTimeInMillis();
                mTaskDate= "" + (month+1) + "/" + dayOfMonth;
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
        data.putExtra("taskName", mNameEditText.getText().toString());
        data.putExtra("taskNotes", mNotesEditText.getText().toString());
        data.putExtra("taskPriority", mTaskPriority);
        data.putExtra("taskstatus", mTaskStatus);
        data.putExtra("taskDate", mTaskDate);
        data.putExtra("taskMilliTime", mTaskMilliTime);

        setResult(RESULT_OK, data);
        super.finish();
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }


}
