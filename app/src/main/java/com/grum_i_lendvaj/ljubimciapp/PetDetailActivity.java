package com.grum_i_lendvaj.ljubimciapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class PetDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] columns = {"name", "age", "weight", "food", "medicine", "health", "notes", "vet", "owner"};
    private static final int[] ids = {R.id.name, R.id.age, R.id.weight, R.id.food, R.id.medicine, R.id.health, R.id.notes, R.id.vet, R.id.owner};
    private static final String query = "_id = ?";

    PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        helper = new PetDatabaseHelper(this);

        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor cursor = helper.getWritableDatabase().query(
                "pets", columns,
                query, new String[]{String.valueOf(getShownIndex())},
                null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); ++i) switch (cursor.getType(i)) {
            case Cursor.FIELD_TYPE_STRING:
                ((EditText) findViewById(ids[i])).setText(cursor.getString(i));
                break;
            case Cursor.FIELD_TYPE_INTEGER:
                ((EditText) findViewById(ids[i])).setText(Integer.toString(cursor.getInt(i)));
                break;
            default:
                Log.wtf("AJOJ", "za " + i + " " + cursor.getType(i));
                assert false;
        }
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

                try {
                    vals.put("name", getStringField(R.id.name));
                    vals.put("age", getIntField(R.id.age));
                    vals.put("weight", getIntField(R.id.weight));
                    vals.put("food", getStringField(R.id.food));
                    vals.put("medicine", getStringField(R.id.medicine));
                    vals.put("health", getStringField(R.id.health));
                    vals.put("notes", getStringField(R.id.notes));
                    vals.put("vet", getStringField(R.id.vet));
                    vals.put("owner", getStringField(R.id.owner));

                    helper.getWritableDatabase().update("pets", vals, query, new String[]{String.valueOf(getShownIndex())});
                } catch (IllegalArgumentException e) {
                    Toast.makeText(this, R.string.illegal, Toast.LENGTH_LONG).show();
                    break;
                }
                finish();
                break;
            case R.id.delete:
                helper.getWritableDatabase().delete("pets", query, new String[]{String.valueOf(getShownIndex())});
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
