package com.example.adwitiyasingh.weatherwithdrawer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adwitiyasingh.weatherwithdrawer.db.tables.mycities;

/**
 * Created by adwitiyasingh on 4/15/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

        public static final String DB_NAME = "todo.db";
        public static final int DB_VER = 1;

        public MyDatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(mycities.CMD_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           }

        }


