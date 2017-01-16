package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grum_i_lendvaj.ljubimciapp.R;
import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class PetDetailFragment extends Fragment {

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

        Cursor cursor = helper.getReadableDatabase().query(
                "pets", columns,
                query, new String[]{Integer.toString(getShownIndex())},
                null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); ++i) switch (cursor.getType(i)) {
            case Cursor.FIELD_TYPE_STRING:
                ((TextView) getActivity().findViewById(ids[i])).setText(cursor.getString(i));
                break;
            case Cursor.FIELD_TYPE_INTEGER:
                ((TextView) getActivity().findViewById(ids[i])).setText(Integer.toString(cursor.getInt(i)));
                break;
            default:
                Log.wtf("AJOJ", "za " + i + " " + cursor.getType(i));
                assert false;
        }
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
}
