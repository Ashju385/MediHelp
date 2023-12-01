package com.example.medihelp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LabTestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest);
        String userEmail = getIntent().getStringExtra("user_email");

        // Find references to the CardView elements
        CardView bloodTest = findViewById(R.id.bloodTest);
        CardView bilirubinTest = findViewById(R.id.bilirubinTest);
        CardView diabetesTest = findViewById(R.id.diabetesTest);
        Button goBackButton = findViewById(R.id.goBackButton);




        bloodTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this, HomeActivity.class);
                intent.putExtra("user_email", userEmail); // "user_email" is a key for the email
                startActivity(intent);
            }
        });

        bilirubinTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this, BilirubinTestActivity.class);
                intent.putExtra("user_email", userEmail); // "user_email" is a key for the email

                startActivity(intent);
            }
        });

        diabetesTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this,DiabetesTestActivity.class);
                intent.putExtra("user_email", userEmail); // "user_email" is a key for the email

                startActivity(intent);
            }
        });

        goBackButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(LabTestActivity.this, DashboardActivity.class);
            intent1.putExtra("user_email", userEmail); // "user_email" is a key for the email

            startActivity(intent1);
        });

    }
}
