package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by snapfish on 1/5/16.
 */
public class EditActivity extends AppCompatActivity {

    private EditText editText2;
    private Button btnSave;
    private String itemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        itemText = b.getString("itemText");

        editText2 = (EditText) findViewById(R.id.editText2);
        if(itemText != null) {
            editText2.setText(itemText);
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    itemText = editText2.getText().toString();
                    finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("editedText", itemText);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
