package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.ExpensesDatabaseHelper;

public class ExpensesActivity extends ListActivity implements View.OnClickListener {

    private static final String[] columns = {"_id", "name", "vet", "etc"};
    private static final int[] ids = {R.id._id, R.id.name, R.id.vet, R.id.etc};
    private static final String query = "_id = ?";

    private long selected = -1;

    ExpensesDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        getListView().addHeaderView(getLayoutInflater().inflate(R.layout.expenses_header, null));
        helper = new ExpensesDatabaseHelper(this);
        findViewById(R.id.add).setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                break;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        selected = id;
    }

    private String getStringField(View view, int id) {
        return ((EditText) view.findViewById(id)).getText().toString();
    }

    private int getIntField(View view, int id) {
        return Integer.parseInt(getStringField(view, id));
    }
}
