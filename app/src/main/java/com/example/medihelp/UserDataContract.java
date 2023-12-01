package com.example.medihelp;

import android.provider.BaseColumns;

public final class UserDataContract {
    private UserDataContract() {
    }

    public static class UserDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_BLOOD_GROUP = "blood_group";
        public static final String COLUMN_GENDER = "gender";
    }
}
