package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.codepath.simpletodo.com.codepath.simpletodo.ItemsAdapter;
import com.codepath.simpletodo.com.codepath.simpletodo.SerializeObject;
import com.codepath.simpletodo.com.codepath.simpletodo.TodoItem;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = MainActivity.class.getName();

    private static final int ADD_TASK_REQUEST_CODE = 10;
    private static final int EDIT_TASK_REQUEST_CODE = 20;
    private ArrayList<TodoItem> mItemsArray;
    private ItemsAdapter mItemsAdapter = null;
    private ListView lvItems;
    private Intent editIntent;
    private static int POSITION = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mItemsArray = readItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
//        mItemsArray = new ArrayList<TodoItem>();
        mItemsAdapter = new ItemsAdapter(this,R.layout.todo_item ,mItemsArray);
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(mItemsAdapter);
        setupListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            addNewTask();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListViewListener() {
        editIntent = new Intent(this, EditActivity.class);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = mItemsAdapter.getItem(position);
                mItemsAdapter.remove(item);
                Log.d(TAG, "Heidi itemArray " + mItemsArray);
                writeItems(mItemsArray);
                mItemsAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),"Item removed!", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                POSITION = position;
                TodoItem item = mItemsAdapter.getItem(position);
                editIntent.putExtra("taskName", item.getmTaskName());
                editIntent.putExtra("taskNotes", item.getmTaskNotes());
                editIntent.putExtra("taskPriority", item.getmPriority());
                editIntent.putExtra("taskstatus", item.getmStatus());
                editIntent.putExtra("taskDate", item.getmDueDate());
                editIntent.putExtra("taskMilliTime", item.getMilliTime());
                startActivityForResult(editIntent, EDIT_TASK_REQUEST_CODE);
            }
        });
    }

    private void addNewTask() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_TASK_REQUEST_CODE) {
            addTaskOnActivityResult(data);
        }
        if (resultCode == RESULT_OK && requestCode == EDIT_TASK_REQUEST_CODE) {
            TodoItem Item = mItemsAdapter.getItem(POSITION);
            mItemsAdapter.remove(Item);
            addTaskOnActivityResult(data);
            mItemsAdapter.notifyDataSetChanged();
        }
    }

    private void addTaskOnActivityResult(Intent data) {
        String itemName = null;
        String itemNotes = null;
        String itemPriority = null;
        String itemStatus = null;
        String itemDate = null;
        Long itemMilliTime = null;

        if (data.hasExtra("taskName")) {
            itemName = data.getExtras().getString("taskName");
        }
        if (data.hasExtra("taskNotes")) {
            itemNotes = data.getExtras().getString("taskNotes");
        }
        if (data.hasExtra("taskPriority")) {
            itemPriority = data.getExtras().getString("taskPriority");
        }
        if (data.hasExtra("taskstatus")) {
            itemStatus = data.getExtras().getString("taskstatus");
        }
        if (data.hasExtra("taskDate")) {
            itemDate = data.getExtras().getString("taskDate");
        }
        if (data.hasExtra("taskMilliTime")) {
            itemMilliTime = data.getExtras().getLong("taskMilliTime");
        }

        TodoItem item = new TodoItem(itemDate, itemPriority, itemStatus, itemName, itemNotes, itemMilliTime);
        mItemsAdapter.add(item);
        Log.d(TAG, "Heidi itemArray " + mItemsArray);
        writeItems(mItemsArray);
    }

    private ArrayList<TodoItem> readItems() {
        String ser = SerializeObject.ReadSettings(this, "myobject.dat");
        if (ser != null && !ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            if (obj instanceof ArrayList) {
                mItemsArray = new ArrayList<TodoItem>((ArrayList<TodoItem>) obj);
            }
        } else {
            mItemsArray = new ArrayList<TodoItem>();
        }
        return mItemsArray;
    }

    private void writeItems(ArrayList<TodoItem> items) {
        String ser = SerializeObject.objectToString(items);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(this, ser, "myobject.dat");
        } else {
            SerializeObject.WriteSettings(this, "", "myobject.dat");
        }
//        Log.d(TAG, "Heidi " + readItems());

    }

}
