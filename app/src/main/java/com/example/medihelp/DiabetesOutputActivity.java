package com.example.medihelp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DiabetesOutputActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diabetes_output);

        String userEmail = getIntent().getStringExtra("user_email");


        Button goBackButton = findViewById(R.id.goBackButton);

        Intent intent = getIntent();
        String diabetesStatus = intent.getStringExtra("DIABETES_STATUS");
        ArrayList<String> diabetesSymptoms = intent.getStringArrayListExtra("DIABETES_SYMPTOMS");

        TextView diabetesStatusTextView = findViewById(R.id.diabetesStatusView);
        TextView symptomsListTextView = findViewById(R.id.symptomsListTextView);

        // Display the Diabetes status
        diabetesStatusTextView.setText("Diabetes Status: " + diabetesStatus);
        int textColor;
        if ("Normal".equals(diabetesStatus)) {
            textColor = ContextCompat.getColor(this, R.color.green); // Use ContextCompat
        } else {
            textColor = ContextCompat.getColor(this, R.color.red); // Use ContextCompat
        }

        // Set the text color of the TextView
        diabetesStatusTextView.setTextColor(textColor);

        if(diabetesSymptoms != null){
            StringBuilder symptomsText = new StringBuilder();

            for (String symptom : diabetesSymptoms) {
                symptomsText.append(symptom).append("\n");
            }
            symptomsListTextView.setText(symptomsText.toString());
        }

        goBackButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(DiabetesOutputActivity.this, DashboardActivity.class);
            intent1.putExtra("user_email", userEmail); // "user_email" is a key for the email
            startActivity(intent1);
            finish();
        });


    }
}
