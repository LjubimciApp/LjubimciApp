package com.grum_i_lendvaj.ljubimciapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInAccount acct = getIntent().getParcelableExtra("account");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(acct.getDisplayName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_list:

                break;
            case R.id.button_expenses:

                break;
            case R.id.button_calendar:

                break;
        }
    }
}
