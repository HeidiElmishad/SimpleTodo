package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.simpletodo.com.codepath.simpletodo.ItemsAdapter;
import com.codepath.simpletodo.com.codepath.simpletodo.TodoItem;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_TASK_REQUEST_CODE = 10;
    private static final int EDIT_TASK_REQUEST_CODE = 20;
//    private ArrayList<String> items;
//    private ArrayAdapter<String> itemsAdapter;
    private ItemsAdapter mItemsAdapter = null;
    private ListView lvItems;
    private EditText editText;
    private Button btnAddItem;
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

        readItems();
        lvItems = (ListView) findViewById(R.id.lvItems);

        // Construct the data source
        ArrayList<TodoItem> mItemsArray = new ArrayList<TodoItem>();
        // Create the adapter to convert the array to views
        mItemsAdapter = new ItemsAdapter(this,R.layout.todo_item ,mItemsArray);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(mItemsAdapter);



//        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//        lvItems.setAdapter(itemsAdapter);

//        btnAddItem = (Button) findViewById(R.id.btnAddItem);
//        btnAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText = (EditText) findViewById(R.id.etNewItem);
//                String itemText = editText.getText().toString();
//                TodoItem mTodoItem = new TodoItem(null, null, null, itemText,null);
//                mItemsAdapter.add(mTodoItem);
//                editText.setText("");
//                writeItems();
//            }
//        });
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

    private void addNewTask() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE);

    }

    private void setupListViewListener() {
        editIntent = new Intent(this, EditActivity.class);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = mItemsAdapter.getItem(position);
                mItemsAdapter.remove(item);
                writeItems();
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
                startActivityForResult(editIntent, EDIT_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_TASK_REQUEST_CODE) {
            addTaskOnActivityResult(data);
//            mItemsAdapter.notifyDataSetChanged();
            writeItems();
//            if (data.hasExtra("editedText")) {
//                String result = data.getExtras().getString("editedText");
//                if (result != null && result.length() > 0) {
//                    items.remove(POSITION);
//                    items.add(POSITION,result);
//                    writeItems();
//                    itemsAdapter.notifyDataSetChanged();
//                }

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

        TodoItem item = new TodoItem(null, itemPriority, itemStatus, itemName, itemNotes);
        mItemsAdapter.add(item);
    }

    private void readItems() {
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//        try{
//            items = new ArrayList<String>(FileUtils.readLines(todoFile));
//        }
//        catch (IOException e){
//            items = new ArrayList<String>();
//        }
    }

    private void writeItems() {
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//        try {
//            FileUtils.writeLines(todoFile, items);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

    }

}
