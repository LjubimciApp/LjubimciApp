package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.PetDatabaseHelper;

public class ExpensesActivity extends ListActivity implements View.OnClickListener {

    private static final String[] columns = {"name", "vet", "food", "etc", "_id"};
    private static final int[] ids = {R.id.name, R.id.vet, R.id.food, R.id.etc};

    PetDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        findViewById(R.id.add).setOnClickListener(this);

        getListView().addHeaderView(getLayoutInflater().inflate(R.layout.expenses_header, null));
        helper = new PetDatabaseHelper(this);

        setListAdapter(new SimpleCursorAdapter(
                this,
                R.layout.expenses_item,
                null,
                columns, ids,
                0));
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshCursor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (position < l.getHeaderViewsCount())
            return;

        showDetails(id);
    }

    private void showDetails(long id) {

        Intent intent = new Intent(this, ExpenseActivity.class);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ContentValues vals = new ContentValues();
                vals.put("name", "");
                vals.put("vet", "");
                vals.put("food", "");
                vals.put("etc", "");
                long id = helper.getWritableDatabase().insert("expenses", null, vals);

                refreshCursor();

                showDetails(id);
                break;
        }
    }

    private void refreshCursor() {
        ((CursorAdapter) getListAdapter()).changeCursor(
                helper.getWritableDatabase().query("expenses", columns, null, null, null, null, null));
    }
}
