package com.example.medihelp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        // Retrieve data passed from MedicineActivity
        String medicineName = getIntent().getStringExtra("medicine_name");

        // Find and populate TextView with medicine name
        TextView medicineNameTextView = findViewById(R.id.medicineNameTextView);
        medicineNameTextView.setText(medicineName);

        // Add more code to display other details or purchase functionality as needed
    }
}
