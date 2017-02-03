package com.grum_i_lendvaj.ljubimciapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(new Intent(this, PetListActivity.class));
                break;
            case R.id.button_expenses:
                startActivity(new Intent(this, ExpensesActivity.class));
                break;
            case R.id.button_calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
        }
    }
}
