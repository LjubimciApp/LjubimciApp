package com.grum_i_lendvaj.ljubimciapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 11;
    private static final String DATABASE_NAME = "pets";

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
    private static final String EXPENSE_TABLE_NAME = "expenses";
    private static final String
            EXPENSE_TABLE_CREATE =
            "CREATE TABLE " + EXPENSE_TABLE_NAME + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT, "
                    + "vet TEXT, "
                    + "food TEXT, "
                    + "etc TEXT"
                    + ")";
    private static final String EXPENSE_TABLE_DROP =
            "DROP TABLE IF EXISTS " + EXPENSE_TABLE_NAME;
    private static final String EVENT_TABLE_NAME = "events";
    private static final String
            EVENT_TABLE_CREATE =
            "CREATE TABLE " + EVENT_TABLE_NAME + " ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "time INTEGER, "
                    + "description TEXT"
                    + ")";
    private static final String EVENT_TABLE_DROP =
            "DROP TABLE IF EXISTS " + EVENT_TABLE_NAME;

    public PetDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PET_TABLE_CREATE);
        db.execSQL(EXPENSE_TABLE_CREATE);
        db.execSQL(EVENT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(PET_TABLE_DROP);
        db.execSQL(EXPENSE_TABLE_DROP);
        db.execSQL(EVENT_TABLE_DROP);
        onCreate(db);
    }
}
