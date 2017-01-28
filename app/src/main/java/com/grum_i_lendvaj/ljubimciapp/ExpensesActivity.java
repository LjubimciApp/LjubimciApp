package com.grum_i_lendvaj.ljubimciapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.database.ExpensesDatabaseHelper;

public class ExpensesActivity extends ListActivity implements View.OnClickListener {

    private static final String[] columns = {"_id", "name", "vet", "etc"};
    private static final int[] ids = {R.id._id, R.id.name, R.id.vet, R.id.etc};

    ExpensesDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        findViewById(R.id.add).setOnClickListener(this);

        getListView().addHeaderView(getLayoutInflater().inflate(R.layout.expenses_header, null));
        helper = new ExpensesDatabaseHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setListAdapter(new SimpleCursorAdapter(
                this,
                R.layout.expenses_item,
                helper.getWritableDatabase().query("expenses", columns, null, null, null, null, null),
                columns, ids,
                0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        showDetails(position, id);
    }

    private void showDetails(int position, long index) {

        Intent intent = new Intent(this, ExpenseActivity.class);
        intent.putExtra("index", index);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ContentValues vals = new ContentValues();
                vals.put("name", "blank");
                vals.put("vet", "");
                vals.put("etc", "");
                helper.getWritableDatabase().insert("expenses", null, vals);

                ((CursorAdapter) getListAdapter()).changeCursor(
                        helper.getWritableDatabase().query("expenses", columns, null, null, null, null, null));

                Log.wtf("cursor", String.valueOf(((CursorAdapter) getListAdapter()).getCursor().getCount()));
                break;
        }
    }
}
