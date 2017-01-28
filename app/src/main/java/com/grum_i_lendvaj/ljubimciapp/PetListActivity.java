package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

import java.util.Locale;

public class PetListActivity extends ListActivity implements View.OnClickListener {

    PetDatabaseHelper helper;

    private int currentPosition = -1;
    private long currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        helper = new PetDatabaseHelper(this);

        Button buttonAdd = (Button) findViewById(R.id.add);
        buttonAdd.setOnClickListener(this);

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            currentPosition = savedInstanceState.getInt("currentPosition", -1);
            currentIndex = savedInstanceState.getLong("currentIndex", -1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, PetAddActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        setListAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                helper.getWritableDatabase().query("pets", new String[]{"_id, name"}, null, null, null, null, null),
                new String[]{"_id", "name"}, new int[] {android.R.id.text1, android.R.id.text2}, 0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", currentPosition);
        outState.putLong("currentIndex", currentIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.wtf("BITNO", String.format(Locale.getDefault(), "%d", id));
        showDetails(position, id);
    }

    private void showDetails(int position, long index) {

        currentPosition = position;
        currentIndex = index;

        Intent intent = new Intent(this, PetDetailActivity.class);
        intent.putExtra("index", index);

        startActivity(intent);
    }
}
