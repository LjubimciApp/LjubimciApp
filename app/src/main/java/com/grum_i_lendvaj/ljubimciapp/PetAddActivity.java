package com.grum_i_lendvaj.ljubimciapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grum_i_lendvaj.ljubimciapp.fragment.PetAddFragment;

public class PetAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PetAddFragment fragment = new PetAddFragment();
        fragment.setArguments(getIntent().getExtras());

        getFragmentManager().beginTransaction().add(android.R.id.content, fragment, "content")
                .commit();

    }

}
