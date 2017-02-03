package com.grum_i_lendvaj.ljubimciapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] columns = {"time", "description"};
    private static final int[] ids = {R.id.name, R.id.age};
    private static final String query = "_id = ?";

    PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        helper = new PetDatabaseHelper(this);

//        findViewById(R.id.submit).setOnClickListener(this);
//        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        Cursor cursor = helper.getWritableDatabase().query(
//                "events", columns,
//                query, new String[]{String.valueOf(getShownIndex())},
//                null, null, null);
//
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getColumnCount(); ++i) switch (cursor.getType(i)) {
//            case Cursor.FIELD_TYPE_STRING:
//                ((EditText) findViewById(ids[i])).setText(cursor.getString(i));
//                break;
//            case Cursor.FIELD_TYPE_INTEGER:
//                ((EditText) findViewById(ids[i])).setText(Integer.toString(cursor.getInt(i)));
//                break;
//            default:
//                Log.wtf("AJOJ", "za " + i + " " + cursor.getType(i));
//                assert false;
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    public long getShownIndex() {
        return getIntent().getLongExtra("id", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                ContentValues vals = new ContentValues();
                vals.put("time", getStringField(R.id.time));
                vals.put("description", getIntField(R.id.description));

                helper.getWritableDatabase().update("events", vals, query, new String[]{String.valueOf(getShownIndex())});
                finish();
                break;
            case R.id.delete:
                helper.getWritableDatabase().delete("events", query, new String[]{String.valueOf(getShownIndex())});
                finish();
                break;
        }
    }

    private String getStringField(int id) {
        return ((EditText) findViewById(id)).getText().toString();
    }

    private int getIntField(int id) {
        return Integer.parseInt(getStringField(id));
    }
}
