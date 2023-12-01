package com.example.medihelp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class BilirubinOutputActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilirubin_output);


        Button goBackButton = findViewById(R.id.goBackButton);

        String userEmail = getIntent().getStringExtra("user_email");

        Intent intent = getIntent();
        String bilirubinStatus = intent.getStringExtra("BILIRUBIN_STATUS");
        ArrayList<String> bilirubinDiseases = intent.getStringArrayListExtra("BILIRUBIN_DISEASE");
        ArrayList<String> bilirubinSymptoms = intent.getStringArrayListExtra("BILIRUBIN_SYMPTOMS");

        TextView bilirubinStatusTextView = findViewById(R.id.bilirubinStatusView);
        TextView diseasesListTextView = findViewById(R.id.diseasesListTextView);
        TextView symptomsListTextView = findViewById(R.id.symptomsListTextView);

        // Display the Bilirubin status
        bilirubinStatusTextView.setText("Bilirubin Status: " + bilirubinStatus);
        int textColor;
        if ("Normal".equals(bilirubinStatus)) {
            textColor = ContextCompat.getColor(this, R.color.green); // Use ContextCompat
        } else {
            textColor = ContextCompat.getColor(this, R.color.red); // Use ContextCompat
        }

        // Set the text color of the TextView
        bilirubinStatusTextView.setTextColor(textColor);

        if(bilirubinDiseases != null){
            StringBuilder diseasesText = new StringBuilder();
            for (String disease : bilirubinDiseases) {
                diseasesText.append(disease).append("\n");
            }
            diseasesListTextView.setText(diseasesText.toString());

        }
        if(bilirubinSymptoms != null){
            StringBuilder symptomsText = new StringBuilder();

            for (String symptom : bilirubinSymptoms) {
                symptomsText.append(symptom).append("\n");
            }
            symptomsListTextView.setText(symptomsText.toString());
        }

        goBackButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(BilirubinOutputActivity.this, DashboardActivity.class);
            intent1.putExtra("user_email", userEmail); // "user_email" is a key for the email

            startActivity(intent1);
        });


    }
}
