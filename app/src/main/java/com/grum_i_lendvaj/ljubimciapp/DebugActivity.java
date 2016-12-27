package com.grum_i_lendvaj.ljubimciapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.AccountDataHelper;

public class DebugActivity extends AppCompatActivity implements View.OnClickListener {

    AccountDataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_query).setOnClickListener(this);

        helper = new AccountDataHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                helper.getWritableDatabase().execSQL(((EditText) findViewById(R.id.editText_update)).getText().toString());
                break;
            case R.id.button_query:
                Cursor cursor = helper.getWritableDatabase().rawQuery(((EditText) findViewById(R.id.editText_query)).getText().toString(), null);
                ListView x = (ListView) findViewById(R.id.listView_query);
                x.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, cursor.getColumnNames(), new int[] {android.R.id.text1, android.R.id.text2}));
                break;
        }
    }
}
