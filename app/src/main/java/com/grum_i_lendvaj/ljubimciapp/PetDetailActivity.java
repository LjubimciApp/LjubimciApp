package com.grum_i_lendvaj.ljubimciapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.grum_i_lendvaj.ljubimciapp.fragment.PetDetailFragment;

public class PetDetailActivity extends AppCompatActivity {

    static {
        Log.wtf("BITNO", "YES1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.wtf("BITNO", "YES");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentById(android.R.id.content)).commit();
            finish();
            return;
        }

        if (savedInstanceState == null) {
            PetDetailFragment fragment = new PetDetailFragment();
            fragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content, fragment, "details")
                    .commit();
        }
    }
}
