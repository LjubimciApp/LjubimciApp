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

                ((CursorAdapter) getListAdapter()).changeCursor(
                        helper.getWritableDatabase().query("pets", columns, null, null, null, null, null));

                showDetails(id);
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
