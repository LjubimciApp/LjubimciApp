package com.grum_i_lendvaj.ljubimciapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class PetAddActivity extends AppCompatActivity implements View.OnClickListener {

    private PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pet_add);

        helper = new PetDatabaseHelper(this);

        Button buttonSubmit = (Button) findViewById(R.id.submit);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                ContentValues vals = new ContentValues();
                vals.put("name",        getStringField(R.id.name));
                vals.put("age",         getIntField(R.id.age));
                vals.put("weight",      getIntField(R.id.weight));
                vals.put("food",        getStringField(R.id.food));
                vals.put("medicine",    getStringField(R.id.medicine));
                vals.put("health",      getStringField(R.id.health));
                vals.put("notes",       getStringField(R.id.notes));
                vals.put("vet",         getStringField(R.id.vet));
                vals.put("owner",       getStringField(R.id.owner));

                helper.getWritableDatabase().insert("pets", null, vals);
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
