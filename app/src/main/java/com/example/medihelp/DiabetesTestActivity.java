package com.example.medihelp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class DiabetesTestActivity extends AppCompatActivity {

    private EditText diabetesValue;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diabetes_test);
        String userEmail = getIntent().getStringExtra("user_email");

        diabetesValue = findViewById(R.id.diabetesValue);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(diabetesValue.getText().toString()))
                {
                    showErrorMessageDialog();
                } else{
                    double diabetes = Double.parseDouble(diabetesValue.getText().toString());

                    //Diabetes Calculate

                    String diabetesStatus = ResultsActivity.calculateDiabetesStatus(diabetes);
                    Intent intent = new Intent(DiabetesTestActivity.this, DiabetesOutputActivity.class);

                    ArrayList<String> diabetesSymptoms = new ArrayList<>();
                    diabetesSymptoms.add("Frequent Hunger\n" +
                            "Weight loss\n" +
                            "Blurred Vision\n" +
                            "Extreme Thirst and greater need to urintate\n" +
                            "Fatigue");

                    ArrayList<String> diabetesSymptomsData;

                    ArrayList<String> normalDiabetes = new ArrayList<>();
                    normalDiabetes.add("No Problem");


                    if("Normal".equals(diabetesStatus))
                    {
                        diabetesSymptomsData = normalDiabetes;
                    }
                    else {
                        diabetesSymptomsData = diabetesSymptoms;

                    }

                    intent.putExtra("DIABETES_STATUS", diabetesStatus);
                    intent.putStringArrayListExtra("DIABETES_SYMPTOMS",diabetesSymptomsData);
                    intent.putExtra("user_email", userEmail); // "user_email" is a key for the email

                    startActivity(intent);




                }
            }

            private void showErrorMessageDialog() {
                ConstraintLayout errorConstraintLayout = findViewById(R.id.errorConstraintLayout);
                View v = LayoutInflater.from(DiabetesTestActivity.this).inflate(R.layout.dialog_error, errorConstraintLayout);
                Button error = v.findViewById(R.id.errorButton);
                AlertDialog.Builder builder = new AlertDialog.Builder(DiabetesTestActivity.this);
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
