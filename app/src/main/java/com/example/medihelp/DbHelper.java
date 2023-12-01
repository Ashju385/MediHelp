package com.example.medihelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_BLOOD_GROUP = "blood_group";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_BLOOD_GROUP + " TEXT,"
            + COLUMN_GENDER + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Method to add a new user to the database
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Email already exists, don't insert a new user
            cursor.close();
            db.close();
            return -1;
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_BLOOD_GROUP, user.getBloodGroup());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // Insert the row into the table
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    // Method to retrieve a user from the database based on their email
    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int bloodGroupIndex = cursor.getColumnIndex(COLUMN_BLOOD_GROUP);
            int genderIndex = cursor.getColumnIndex(COLUMN_GENDER);
            int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);

            if (idIndex != -1 && nameIndex != -1 && bloodGroupIndex != -1 && genderIndex != -1 && passwordIndex != -1) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String bloodGroup = cursor.getString(bloodGroupIndex);
                String gender = cursor.getString(genderIndex);
                String password = cursor.getString(passwordIndex);

                user = new User(id, name, email, bloodGroup, gender, password);
            }

            cursor.close();
        }

        db.close();
        return user;
        }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
            int bloodGroupIndex = cursor.getColumnIndex(COLUMN_BLOOD_GROUP);
            int genderIndex = cursor.getColumnIndex(COLUMN_GENDER);
            int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String email = cursor.getString(emailIndex);
                String bloodGroup = cursor.getString(bloodGroupIndex);
                String gender = cursor.getString(genderIndex);
                String password = cursor.getString(passwordIndex);

                User user = new User(id, name, email, bloodGroup, gender, password);
                userList.add(user);
            }

            cursor.close();
        }

        db.close();
        return userList;
    }

    // Method to update a user's data in the database
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_BLOOD_GROUP, user.getBloodGroup());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // Update the row in the table
        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        return rowsAffected;
    }

    // Method to delete a user from the database
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
