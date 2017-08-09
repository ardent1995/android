package com.example.tohosif.model;

import android.database.Cursor;

import com.example.tohosif.db.UserTable;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Tohosif on 28-07-2017.
 */

public class UserFromDatabase extends User {

    private int id = -1;
    private String dob;

    public UserFromDatabase(@NotNull Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(UserTable.TableInfo.ID)));
        setFirstName(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.FIRST_NAME)));
        setMiddleName(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.MIDDLE_NAME)));
        setLastName(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.LAST_NAME)));
        setGender(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.GENDER)));
        setDob(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.DOB)));
        setCity(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.CITY)));
        setEmailId(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.EMAIL_ID)));
        setPhoneNo(cursor.getString(cursor.getColumnIndex(UserTable.TableInfo.PHONE_NO)));
    }

    public UserFromDatabase() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
