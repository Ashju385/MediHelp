package com.example.medihelp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class BilirubinTestActivity extends AppCompatActivity {

    private EditText bilirubinValue;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilirubin_test);
        bilirubinValue = findViewById(R.id.bilirubinValue);
        submitButton = findViewById(R.id.submitButton);
        String userEmail = getIntent().getStringExtra("user_email");



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bilirubinValue.getText().toString()))
                {
                    showErrorMessageDialog();
                } else{
                    double bilirubin = Double.parseDouble(bilirubinValue.getText().toString());

                              //Bilirubin Calculate

                    String bilirubinStatus = ResultsActivity.calculateBilirubinStatus(bilirubin);
                    Intent intent = new Intent(BilirubinTestActivity.this, BilirubinOutputActivity.class);

                    ArrayList<String> bilirubinDisease = new ArrayList<>();
                    bilirubinDisease.add("Liver Inflammation");

                    ArrayList<String> bilirubinSymptoms = new ArrayList<>();
                    bilirubinSymptoms.add("Fatigue\n" +
                            "Mild fever\n" +
                            "Muscle aches\n" +
                            "Nausea and Vomiting\n" +
                            "Jaundice");

                    ArrayList<String> bilirubinDiseaseData;
                    ArrayList<String> bilirubinSymptomsData;

                    ArrayList<String> normalBilirubin = new ArrayList<>();
                    normalBilirubin.add("No Problem");


                    if("Abnormal".equals(bilirubinStatus))
                    {
                        bilirubinDiseaseData = bilirubinDisease;
                        bilirubinSymptomsData = bilirubinSymptoms;
                    }
                    else {
                        bilirubinDiseaseData = normalBilirubin;
                        bilirubinSymptomsData = normalBilirubin;

                    }

                    intent.putExtra("BILIRUBIN_STATUS", bilirubinStatus);
                    intent.putStringArrayListExtra("BILIRUBIN_DISEASE", bilirubinDiseaseData);
                    intent.putStringArrayListExtra("BILIRUBIN_SYMPTOMS",bilirubinSymptomsData);
                    intent.putExtra("user_email", userEmail); // "user_email" is a key for the email

                    startActivity(intent);




                }
            }

            private void showErrorMessageDialog() {
                ConstraintLayout errorConstraintLayout = findViewById(R.id.errorConstraintLayout);
                View v = LayoutInflater.from(BilirubinTestActivity.this).inflate(R.layout.dialog_error, errorConstraintLayout);
                Button error = v.findViewById(R.id.errorButton);
                AlertDialog.Builder builder = new AlertDialog.Builder(BilirubinTestActivity.this);
                builder.setView(v);
                final AlertDialog alertDialog = builder.create();

                error.findViewById(R.id.errorButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                if(alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

                }
                alertDialog.show();


            }
        });
    }


}
