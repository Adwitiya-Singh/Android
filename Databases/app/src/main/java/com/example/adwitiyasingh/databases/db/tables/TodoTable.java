package com.example.adwitiyasingh.databases.db.tables;

/**
 * Created by adwitiyasingh on 4/15/17.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.adwitiyasingh.databases.db.DbConsts.*;



public class TodoTable {
    public static final String TAG = "TODO";

    public static final String TABLE_NAME = "todos";

    interface Columns {
        String ID = "id";
        String TASK = "task";


    }

    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_INE
                    + TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI
                    + COMMA
                    + Columns.TASK + TYPE_TEXT
                    + RBR
                    + SEMICOL;

    public static void addCity (SQLiteDatabase db, String city) {
        if (db.isReadOnly()) {
            Log.w(TAG, "addTask: Database is note writeable");
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(Columns.TASK, city);

        db.insert(
                TABLE_NAME,
                null,
                cv
        );
    }



    public static ArrayList<String> fetchCities (SQLiteDatabase db) {

        Cursor c = db.query(
                TABLE_NAME,
                new String[] {Columns.TASK},
                null, null, null, null, null
        );
        ArrayList<String> todos = new ArrayList<>();
        if (c.moveToFirst()) {
            int taskColIndex = c.getColumnIndex(Columns.TASK);
            do {
                todos.add(c.getString(taskColIndex));
            } while ((c.moveToNext()));
        }
        c.close();

        return todos;
    }
}
