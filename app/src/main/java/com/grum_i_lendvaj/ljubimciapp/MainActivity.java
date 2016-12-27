package com.grum_i_lendvaj.ljubimciapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        GoogleSignInAccount acct = getIntent().getParcelableExtra("account");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(acct.getDisplayName());

        Button buttonList = (Button) findViewById(R.id.button_list);
        buttonList.setOnClickListener(this);

        Button buttonExpenses = (Button) findViewById(R.id.button_expenses);
        buttonExpenses.setOnClickListener(this);

        Button buttonCalendar = (Button) findViewById(R.id.button_calendar);
        buttonCalendar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_list:
                startActivity(new Intent(this, PetActivity.class));
                break;
            case R.id.button_expenses:
                startActivity(new Intent(this, ExpensesActivity.class));
                break;
            case R.id.button_calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.button_debug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
        }
    }
}
