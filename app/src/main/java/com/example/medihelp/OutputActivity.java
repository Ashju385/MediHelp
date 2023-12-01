package com.example.medihelp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class OutputActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        Button goBackButton = findViewById(R.id.goBackButton);

        String userEmail = getIntent().getStringExtra("user_email");

        // Retrieve the RBC status from the Intent
        Intent intent = getIntent();
        String rbcStatus = intent.getStringExtra("RBC_STATUS");
        ArrayList<String> rbcDiseases = intent.getStringArrayListExtra("RBC_DISEASE");
        ArrayList<String> wbcDiseases = intent.getStringArrayListExtra("WBC_DISEASE");
        ArrayList<String> plateletDiseases = intent.getStringArrayListExtra("PLATELET_DISEASE");

        ArrayList<String> rbcSymptoms = intent.getStringArrayListExtra("RBC_SYMPTOMS");
        ArrayList<String> wbcSymptoms = intent.getStringArrayListExtra("WBC_SYMPTOMS");
        ArrayList<String> plateletSymptoms = intent.getStringArrayListExtra("PLATELET_SYMPTOMS");


        // Find the TextView for displaying the result
        TextView rbcStatusTextView = findViewById(R.id.rbcStatusTextView);
        TextView wbcStatusTextView = findViewById(R.id.wbcStatusTextView);
        TextView plateletStatusTextView = findViewById(R.id.plateletStatusTextView);

        TextView diseasesListTextView = findViewById(R.id.diseasesListTextView);
        TextView symptomsListTextView = findViewById(R.id.symptomsListTextView);



        // Display the RBC status
        rbcStatusTextView.setText("RBC Status: " + rbcStatus);
        int textColor;
        if ("Normal".equals(rbcStatus)) {
            textColor = ContextCompat.getColor(this, R.color.green); // Use ContextCompat
        } else {
            textColor = ContextCompat.getColor(this, R.color.red); // Use ContextCompat
        }

        // Set the text color of the TextView
        rbcStatusTextView.setTextColor(textColor);


        //Display WBC Status
        String wbcStatus = intent.getStringExtra("WBC_STATUS");
        wbcStatusTextView.setText("WBC Status: " + wbcStatus);
        if ("Normal".equals(wbcStatus)) {
            textColor = ContextCompat.getColor(this, R.color.green); // Use ContextCompat
        } else {
            textColor = ContextCompat.getColor(this, R.color.red); // Use ContextCompat
        }
        wbcStatusTextView.setTextColor(textColor);

                                                       //Display Platelet Status
        String plateletStatus = intent.getStringExtra("PLATELET_STATUS");
        plateletStatusTextView.setText("Platelet Status: " + plateletStatus);

        if ("Normal".equals(plateletStatus)){
            textColor = ContextCompat.getColor(this, R.color.green); // Use ContextCompat
        } else {
            textColor = ContextCompat.getColor(this, R.color.red); // Use ContextCompat
        }


        //Display color

        plateletStatusTextView.setTextColor(textColor);

        //Display the diseases
        if (rbcDiseases != null && wbcDiseases != null && plateletDiseases!= null) {
            // Build a formatted string to display the diseases
            StringBuilder diseasesText = new StringBuilder();

            diseasesText.append("RBC Diseases:\n");
            for (String disease : rbcDiseases) {
                diseasesText.append(disease).append("\n");
            }

            diseasesText.append("\nWBC Diseases:\n");
            for (String disease : wbcDiseases) {
                diseasesText.append(disease).append("\n");
            }

            diseasesText.append("\nPlatelet Diseases:\n");
            for(String disease : plateletDiseases){
                diseasesText.append(disease).append("\n");
            }

            // Set the diseases text in the TextView
            diseasesListTextView.setText(diseasesText.toString());
        }

            //Display the Symptoms
        if (rbcSymptoms != null && wbcSymptoms != null && plateletSymptoms != null) {
            // Build a formatted string to display the diseases
            StringBuilder symptomsText = new StringBuilder();

            symptomsText.append("RBC Symptoms:\n");
            for (String symptom : rbcSymptoms) {
                symptomsText.append(symptom).append("\n");
            }

            symptomsText.append("\nWBC Symptoms:\n");
            for (String symptom : wbcSymptoms) {
                symptomsText.append(symptom).append("\n");
            }

            symptomsText.append("\nPlatelet Symptoms: \n");
            for(String symptom : plateletSymptoms){
                symptomsText.append(symptom).append("\n");
            }

            // Set the symptoms text in the TextView
            symptomsListTextView.setText(symptomsText.toString());
        }

            goBackButton.setOnClickListener(v -> {
                Intent intent1 = new Intent(OutputActivity.this, DashboardActivity.class);
                intent1.putExtra("user_email", userEmail); // "user_email" is a key for the email

                startActivity(intent1);
            });


        }


    }


