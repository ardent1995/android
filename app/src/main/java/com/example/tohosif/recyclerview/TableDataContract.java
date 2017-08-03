package com.example.tohosif.recyclerview;

import android.provider.BaseColumns;

/**
 * Created by Tohosif on 27-07-2017.
 */

public final class TableDataContract {
    public TableDataContract() {
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
