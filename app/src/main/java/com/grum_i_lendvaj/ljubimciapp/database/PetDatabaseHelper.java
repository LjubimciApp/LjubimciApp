package com.grum_i_lendvaj.ljubimciapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String PET_TABLE_NAME = "pets";
    private static final String PET_TABLE_CREATE =
            "CREATE TABLE " + PET_TABLE_NAME + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "age INTEGER, "
                    + "weight INTEGER, "
                    + "food TEXT, "
                    + "medicine TEXT, "
                    + "health TEXT, "
                    + "notes TEXT, "
                    + "vet TEXT, "
                    + "owner TEXT"
                    + ")";
    private static final String PET_TABLE_DROP =
            "DROP TABLE IF EXISTS " + PET_TABLE_NAME;

    public PetDatabaseHelper(Context context) {
        super(context, PET_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PET_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(PET_TABLE_DROP);
        onCreate(db);
    }
}
