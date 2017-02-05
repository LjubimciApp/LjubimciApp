package com.grum_i_lendvaj.ljubimciapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

import java.util.Calendar;

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

        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onResume() {
        super.onResume();

        Cursor cursor = helper.getWritableDatabase().query(
                "events", columns,
                query, new String[]{String.valueOf(getShownIndex())},
                null, null, null);

        cursor.moveToFirst();

        long millis = 1000L * cursor.getInt(0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        EditText description = (EditText) findViewById(R.id.description);

        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

        description.setText(cursor.getString(1));
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
    @SuppressWarnings("deprecation")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit: {
                ContentValues vals = new ContentValues();

                Calendar calendar = Calendar.getInstance();

                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);

                vals.put("time", (int) (calendar.getTime().getTime() / 1000));
                vals.put("description", getStringField(R.id.description));

                helper.getWritableDatabase().update("events", vals, query, new String[]{String.valueOf(getShownIndex())});

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(this, EventReceiver.class)
                        .putExtra("id", (int) getShownIndex())
                        .putExtra("description", getStringField(R.id.description));

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        PendingIntent.getBroadcast(this, (int) getShownIndex(), intent, PendingIntent.FLAG_UPDATE_CURRENT));
                finish();
                break;
            }
            case R.id.delete: {
                helper.getWritableDatabase().delete("events", query, new String[]{String.valueOf(getShownIndex())});

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(this, EventReceiver.class);

                alarmManager.cancel(PendingIntent.getBroadcast(this, (int) getShownIndex(), intent, 0));
                finish();
                break;
            }
        }
    }

    private String getStringField(int id) {
        return ((EditText) findViewById(id)).getText().toString();
    }

    private int getIntField(int id) {
        return Integer.parseInt(getStringField(id));
    }
}
