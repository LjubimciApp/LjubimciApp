package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

import java.util.Locale;

public class PetListActivity extends ListActivity implements View.OnClickListener {

    static final String[] columns = {"name", "age", "weight", "food", "medicine", "health", "notes", "vet", "owner", "_id"};

    PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        helper = new PetDatabaseHelper(this);

        Button buttonAdd = (Button) findViewById(R.id.add);
        buttonAdd.setOnClickListener(this);

        setListAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                null,
                new String[]{"_id", "name"}, new int[]{android.R.id.text1, android.R.id.text2}, 0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ContentValues vals = new ContentValues();
                vals.put("name", "test");
                vals.put("age", 0);
                vals.put("weight", 0);
                vals.put("food", "");
                vals.put("medicine", "");
                vals.put("health", "");
                vals.put("notes", "");
                vals.put("vet", "");
                vals.put("owner", "");
                long id = helper.getWritableDatabase().insert("pets", null, vals);

                refreshCursor();

                showDetails(id);
                break;
        }
    }

    private void refreshCursor() {
        ((CursorAdapter) getListAdapter()).changeCursor(
                helper.getWritableDatabase().query("pets", columns, null, null, null, null, null));
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshCursor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.wtf("BITNO", String.format(Locale.getDefault(), "%d", id));
        showDetails(id);
    }

    private void showDetails(long id) {

        Intent intent = new Intent(this, PetDetailActivity.class);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}
