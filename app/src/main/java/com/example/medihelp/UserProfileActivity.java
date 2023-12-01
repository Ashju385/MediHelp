package com.example.medihelp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        String userEmail = "Email: " + getIntent().getStringExtra("email");
        String name = "Name: "+getIntent().getStringExtra("name");
        String bloodGroup ="BloodGroup: "+getIntent().getStringExtra("bloodGroup");
        String gender="Gender: "+ getIntent().getStringExtra("gender");
        TextView tv1= findViewById(R.id.emailTextView);
        TextView tv2= findViewById(R.id.nameTextView);
        TextView tv3= findViewById(R.id.bloodGroupTextView);
        TextView tv4= findViewById(R.id.genderTextView);
        tv1.setText(userEmail);
        tv2.setText(name);
        tv3.setText(bloodGroup);
        tv4.setText(gender);





    }
}
