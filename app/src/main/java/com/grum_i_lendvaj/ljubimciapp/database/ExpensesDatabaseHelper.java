package com.grum_i_lendvaj.ljubimciapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpensesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_NAME = "expenses";
    private static final String
            TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT, "
                    + "vet TEXT, "
                    + "etc TEXT"
                    + ")";
    private static final String TABLE_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ExpensesDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }
}
