package com.grum_i_lendvaj.ljubimciapp;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

import java.text.DateFormat;
import java.util.Calendar;

public class CalendarActivity extends ListActivity implements View.OnClickListener {

    private static final String[] columns = {"time", "description", "_id"};
    private static final int[] ids = {android.R.id.text1, android.R.id.text2};

    private PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        helper = new PetDatabaseHelper(this);
        findViewById(R.id.add).setOnClickListener(this);

        setListAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                helper.getWritableDatabase().query("events", columns, null, null, null, null, null),
                columns, ids, 0));
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
                Log.wtf("Current time", DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                vals.put("time", DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                vals.put("description", "");
                long id = helper.getWritableDatabase().insert("events", null, vals);

                ((CursorAdapter) getListAdapter()).changeCursor(
                        helper.getWritableDatabase().query("events", columns, null, null, null, null, null));

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(this, EventReceiver.class).putExtra("id", (int) id);

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000,
                                PendingIntent.getBroadcast(this, 0, intent, 0));
                break;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //TODO
    }
}
