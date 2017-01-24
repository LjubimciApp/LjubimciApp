package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.grum_i_lendvaj.ljubimciapp.R;
import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class PetDetailFragment extends Fragment implements View.OnClickListener {

    static final String[] columns = {"name", "age", "weight", "food", "medicine", "health", "notes", "vet", "owner"};
    static final int[] ids = {R.id.name, R.id.age, R.id.weight, R.id.food, R.id.medicine, R.id.health, R.id.notes, R.id.vet, R.id.owner};
    static final String query = "_id = ?";

    PetDatabaseHelper helper;

    public static PetDetailFragment newInstance(int index) {
        PetDetailFragment f = new PetDetailFragment();

        Log.wtf("BITNO", "c" + index);

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new PetDatabaseHelper(getActivity());

        Log.wtf("BITNO", "a" + getShownIndex());
        Log.wtf("jeli", getActivity().findViewById(R.id.submit).hasOnClickListeners() ? "da" : "ne");

        Cursor cursor = helper.getReadableDatabase().query(
                "pets", columns,
                query, new String[]{Integer.toString(getShownIndex())},
                null, null, null);
        Log.wtf("jeli", getActivity().findViewById(R.id.submit).hasOnClickListeners() ? "da" : "ne");

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); ++i) switch (cursor.getType(i)) {
            case Cursor.FIELD_TYPE_STRING:
                ((EditText) getActivity().findViewById(ids[i])).setText(cursor.getString(i));
                break;
            case Cursor.FIELD_TYPE_INTEGER:
                ((EditText) getActivity().findViewById(ids[i])).setText(Integer.toString(cursor.getInt(i)));
                break;
            default:
                Log.wtf("AJOJ", "za " + i + " " + cursor.getType(i));
                assert false;
        }

        Log.wtf("jeli", getActivity().findViewById(R.id.submit).hasOnClickListeners() ? "da" : "ne");
        getActivity().findViewById(R.id.submit).setOnClickListener(this);
        Log.wtf("jeli", getActivity().findViewById(R.id.submit).hasOnClickListeners() ? "da" : "ne");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.wtf("BITNO", "b" + getShownIndex());
        return inflater.inflate(R.layout.fragment_pet_detail, container, false);
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
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
                Log.wtf("abc", "daj daj daj");

                helper.getWritableDatabase().update("pets", vals, query, new String[]{Integer.toString(getShownIndex())});
                getActivity().finish();
                break;
        }
    }

    private String getStringField(int id) {
        return ((EditText) getActivity().findViewById(id)).getText().toString();
    }

    private int getIntField(int id) {
        return Integer.parseInt(getStringField(id));
    }
}
