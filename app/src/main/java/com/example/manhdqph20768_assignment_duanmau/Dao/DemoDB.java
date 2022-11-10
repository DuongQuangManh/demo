package com.example.manhdqph20768_assignment_duanmau.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.manhdqph20768_assignment_duanmau.Database.Database;

public class DemoDB {
    private SQLiteDatabase db;

    public DemoDB(Context context) {
        Database database = new Database(context);
        db = database.getWritableDatabase();
    }
}
