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
        public static final String Id = "Id";
        public static final String COL_1 = "first_name";
        public static final String COL_2 = "middle_name";
        public static final String COL_3 = "last_name";
        public static final String COL_4 = "gender";
        public static final String COL_5 = "city";
        public static final String COL_6 = "email_id";
        public static final String COL_7 = "phone_no";
    }
}
