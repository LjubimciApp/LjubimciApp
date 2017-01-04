package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.grum_i_lendvaj.ljubimciapp.R;
import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class PetAddFragment extends Fragment implements View.OnClickListener {
    private PetDatabaseHelper helper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new PetDatabaseHelper(getActivity());

        Button buttonSubmit = (Button) getActivity().findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_add, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                ContentValues vals = new ContentValues();
                vals.put("name", ((EditText) getActivity().findViewById(R.id.editText)).getText().toString());
                helper.getWritableDatabase().insert("pets", null, vals);
                getActivity().finish();
                break;
        }
    }
}
