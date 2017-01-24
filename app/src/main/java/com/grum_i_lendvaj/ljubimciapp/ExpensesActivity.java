package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.ExpensesDatabaseHelper;

public class ExpensesActivity extends ListActivity {

    private static final String[] columns = {"name", "vet", "_id"};
    private static final int[] ids = {R.id.name, R.id.vet, R.id.etc};

    ExpensesDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        getListView().addHeaderView(getLayoutInflater().inflate(R.layout.expenses_header, null));
        helper = new ExpensesDatabaseHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getListView().setAdapter(new SimpleCursorAdapter(
                this,
                R.layout.expenses_item,
                helper.getReadableDatabase().query("expenses", columns, null, null, null, null, null),
                columns, ids,
                0));
    }
}
