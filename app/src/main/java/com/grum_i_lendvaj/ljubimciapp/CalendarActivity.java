package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

import java.util.Calendar;

public class CalendarActivity extends ListActivity implements View.OnClickListener {

    private static final String[] columns = {"datetime(time, 'unixepoch', 'localtime')", "description", "_id"};
    private static final int[] ids = {android.R.id.text1, android.R.id.text2};
    private static final String orderBy = "time ASC";

    private PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        helper = new PetDatabaseHelper(this);
        findViewById(R.id.add).setOnClickListener(this);

        setListAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                null,
                columns, ids, 0));
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshCursor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ContentValues vals = new ContentValues();
                vals.put("time", 60 * (Calendar.getInstance().getTime().getTime() / 1000 / 60));
                vals.put("description", "");

                long id = helper.getWritableDatabase().insert("events", null, vals);

                refreshCursor();

                showDetails(id);
                break;
        }
    }

    private void refreshCursor() {
        ((CursorAdapter) getListAdapter()).changeCursor(
                helper.getWritableDatabase().query("events", columns, null, null, null, null, orderBy));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        showDetails(id);
    }

    private void showDetails(long id) {

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}
