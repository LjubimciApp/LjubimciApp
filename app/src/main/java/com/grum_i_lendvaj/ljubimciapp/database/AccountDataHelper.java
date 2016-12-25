package com.grum_i_lendvaj.ljubimciapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String ACCOUNT_TABLE_NAME = "pets";
    private static final String ACCOUNT_TABLE_CREATE =
            "CREATE TABLE " + ACCOUNT_TABLE_NAME + " (" +
                    "DUMMY" + " TEXT);";

    public AccountDataHelper(Context context) {
        super(context, ACCOUNT_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ACCOUNT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
