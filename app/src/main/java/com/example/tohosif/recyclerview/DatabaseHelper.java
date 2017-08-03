package com.example.tohosif.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import static com.example.tohosif.recyclerview.TableDataContract.TableInfo;

/**
 * Created by Tohosif on 27-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_PATH = "";
    private SQLiteDatabase db;
    private Context context;

//    private static final String SQL_CREATE_TABLE =
//            "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
//                    TableInfo.COL_1 + " TEXT," +
//                    TableInfo.COL_2 + " TEXT," +
//                    TableInfo.COL_3 + " TEXT," +
//                    TableInfo.COL_4 + " TEXT," +
//                    TableInfo.COL_5 + " TEXT," +
//                    TableInfo.COL_6 + " TEXT," +
//                    TableInfo.COL_7 + " TEXT)";
//
//    private static final String SQL_DELETE_TABLE =
//            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DATABASE_PATH = context.getDatabasePath(DATABASE_NAME).toString();
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {

        } else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DATABASE_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DATABASE_PATH;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (db != null) {
            db.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_TABLE);
//        Log.d("DATABASE", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_TABLE);
//        onCreate(db);
    }

    public int deleteFromDatabase(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = TableInfo.ID + " =?";
        String[] selectionArgs = {Integer.toString(id)};
        int deletedRows = db.delete(TableInfo.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

    public boolean insertData(String firstName, String middleName, String lastName, String gender, String dob, String city, String emailId, String phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableInfo.FIRST_NAME, firstName);
        contentValues.put(TableInfo.MIDDLE_NAME, middleName);
        contentValues.put(TableInfo.LAST_NAME, lastName);
        contentValues.put(TableInfo.GENDER, gender);
        contentValues.put(TableInfo.DOB, dob);
        contentValues.put(TableInfo.CITY, city);
        contentValues.put(TableInfo.EMAIL_ID, emailId);
        contentValues.put(TableInfo.PHONE_NO, phoneNo);
        long result = db.insert(TableInfo.TABLE_NAME, TableInfo.MIDDLE_NAME, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
