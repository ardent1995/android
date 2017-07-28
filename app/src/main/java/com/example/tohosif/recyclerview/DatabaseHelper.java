package com.example.tohosif.recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.tohosif.recyclerview.TableDataContract.TableInfo;

/**
 * Created by Tohosif on 27-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
                    TableInfo.COL_1 + " TEXT," +
                    TableInfo.COL_2 + " TEXT," +
                    TableInfo.COL_3 + " TEXT," +
                    TableInfo.COL_4 + " TEXT," +
                    TableInfo.COL_5 + " TEXT," +
                    TableInfo.COL_6 + " TEXT," +
                    TableInfo.COL_7 + " TEXT)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE", "Database Created");
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.d("DATABASE", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
