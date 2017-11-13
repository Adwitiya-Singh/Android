package com.example.adwitiyasingh.weatherwithdrawer.db.tables;

/**
 * Created by adwitiyasingh on 4/15/17.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.adwitiyasingh.weatherwithdrawer.db.DbConsts.*;

public class mycities {

    public static final String TAG = "CITIES";
    public static final String TABLE_NAME = "mycities";

    interface Columns {
        String ID = "id";
        String CITY = "city";


    }
    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_INE
                    + TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI
                    + COMMA
                    + Columns.CITY + TYPE_TEXT
                    + RBR
                    + SEMICOL;

    public static void addCity (SQLiteDatabase db, String city) {
        if (db.isReadOnly()) {
            Log.w(TAG, "addTask: Database is note writeable");
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(Columns.CITY, city);

        db.insert(
                TABLE_NAME,
                null,
                cv
        );
    }
    public static void removeCity (SQLiteDatabase db, String city) {
        if (db.isReadOnly()) {
            Log.w(TAG, "addTask: Database is note writeable");
            return;
        }
        db.delete(TABLE_NAME, Columns.CITY + " = ?", new String[]{city});

    }
    public static ArrayList<String> fetchCities (SQLiteDatabase db) {

        Cursor c = db.query(
                TABLE_NAME,
                new String[] {Columns.CITY},
                null, null, null, null, null
        );
        ArrayList<String> cities = new ArrayList<>();
        if (c.moveToFirst()) {
            int taskColIndex = c.getColumnIndex(Columns.CITY);
            do {
                cities.add(c.getString(taskColIndex));
            } while ((c.moveToNext()));
        }
        c.close();

        return cities;
    }


}
