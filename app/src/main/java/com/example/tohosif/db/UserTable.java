package com.example.tohosif.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.tohosif.model.UserFromDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tohosif on 08-08-2017.
 */

public class UserTable {
    private DatabaseHelper databaseHelper;

    public UserTable(@NotNull DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public int deleteFromDatabase(@NotNull UserFromDatabase user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String selection = TableInfo.ID + " =?";
        String[] selectionArgs = {Integer.toString(user.getId())};
        int deletedRows = db.delete(TableInfo.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

    public boolean insertData(@NotNull UserFromDatabase user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableInfo.FIRST_NAME, user.getFirstName());
        contentValues.put(TableInfo.MIDDLE_NAME, user.getMiddleName());
        contentValues.put(TableInfo.LAST_NAME, user.getLastName());
        contentValues.put(TableInfo.GENDER, user.getGender());
        contentValues.put(TableInfo.DOB, user.getDob());
        contentValues.put(TableInfo.CITY, user.getCity());
        contentValues.put(TableInfo.EMAIL_ID, user.getEmailId());
        contentValues.put(TableInfo.PHONE_NO, user.getPhoneNo());
        long result = db.insert(TableInfo.TABLE_NAME, TableInfo.MIDDLE_NAME, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(@NotNull UserFromDatabase user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableInfo.ID, user.getId());
        contentValues.put(TableInfo.FIRST_NAME, user.getFirstName());
        contentValues.put(TableInfo.MIDDLE_NAME, user.getMiddleName());
        contentValues.put(TableInfo.LAST_NAME, user.getLastName());
        contentValues.put(TableInfo.GENDER, user.getGender());
        contentValues.put(TableInfo.DOB, user.getDob());
        contentValues.put(TableInfo.CITY, user.getCity());
        contentValues.put(TableInfo.EMAIL_ID, user.getEmailId());
        contentValues.put(TableInfo.PHONE_NO, user.getPhoneNo());
        String selection = TableInfo.ID + " =?";
        String[] selectionArgs = {Integer.toString(user.getId())};
        db.update(TableInfo.TABLE_NAME, contentValues, selection, selectionArgs);
        return true;
    }


    public List<UserFromDatabase> getUserFromDatabaseList() {
        List<UserFromDatabase> list = new ArrayList<>();
        SQLiteDatabase sd = databaseHelper.getReadableDatabase();
        Cursor cursor = sd.query(TableInfo.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            list.add(new UserFromDatabase(cursor));
        }
        return list;
    }


    public static class TableInfo implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String ID = "Id";
        public static final String FIRST_NAME = "First_Name";
        public static final String MIDDLE_NAME = "Middle_Name";
        public static final String LAST_NAME = "Last_Name";
        public static final String GENDER = "Gender";
        public static final String DOB = "DOB";
        public static final String CITY = "City";
        public static final String EMAIL_ID = "Email_id";
        public static final String PHONE_NO = "Phone_No";
    }
}
