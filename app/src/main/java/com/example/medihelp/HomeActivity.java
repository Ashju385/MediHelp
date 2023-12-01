package com.example.medihelp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class HomeActivity extends AppCompatActivity {
    private EditText totalRBC, haemoglobinEdtTxt, totalWBC,
            neutrophilsEdtTxt, lymphocytesEdtTxt, monocytesEdtTxt, eosinophilsEdtTxt, basophilsEdtTxt, plateletEdtTxt;
    RadioGroup genderChoice;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String userEmail = getIntent().getStringExtra("user_email");


        // Initialize EditText fields
        totalRBC = findViewById(R.id.totalRBC);
        haemoglobinEdtTxt = findViewById(R.id.haemoglobinEdtTxt);
        totalWBC = findViewById(R.id.totalWBC);
        neutrophilsEdtTxt = findViewById(R.id.neutrophilsEdtTxt);
        lymphocytesEdtTxt = findViewById(R.id.lymphocytesEdtTxt);
        monocytesEdtTxt = findViewById(R.id.monocytesEdtTxt);
        eosinophilsEdtTxt = findViewById(R.id.eosinophilsEdtTxt);
        basophilsEdtTxt = findViewById(R.id.basophilsEdtTxt);
        plateletEdtTxt = findViewById(R.id.plateletEdtTxt);
        genderChoice = findViewById(R.id.genderRadioGroup);
        int selected = genderChoice.getCheckedRadioButtonId();

        String gender;

        if (selected == R.id.maleRadioButton) {
            gender = "Male";
        } else if (selected == R.id.femaleRadioButton) {
            gender = "Female";
        } else {
            gender = "Unknown";
        }

        // Initialize the Submit button
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(totalRBC.getText().toString()) ||
                        TextUtils.isEmpty(haemoglobinEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(totalWBC.getText().toString()) ||
                        TextUtils.isEmpty(neutrophilsEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(lymphocytesEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(monocytesEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(eosinophilsEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(basophilsEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(plateletEdtTxt.getText().toString()) ||
                        TextUtils.isEmpty(gender)) {

                   showErrorMessageDialog();
                }
                else
                {
                    double rbcValue = Double.parseDouble(totalRBC.getText().toString());
                    double haemoglobinValue = Double.parseDouble(haemoglobinEdtTxt.getText().toString());
                    double wbcValue = Double.parseDouble(totalWBC.getText().toString());
                    double neutrophilsValue = Double.parseDouble(neutrophilsEdtTxt.getText().toString());
                    double lymphocytesValue = Double.parseDouble(lymphocytesEdtTxt.getText().toString());
                    double monocytesValue = Double.parseDouble(monocytesEdtTxt.getText().toString());
                    double eosinophilsValue = Double.parseDouble(eosinophilsEdtTxt.getText().toString());
                    double basophilsValue = Double.parseDouble(basophilsEdtTxt.getText().toString());
                    double plateletValue = Double.parseDouble(plateletEdtTxt.getText().toString());
                    String genderValue = gender.toString();



                    //Blood Test Report Calculate


                    // RBC Calculate
                    ArrayList<String> lowRBC = new ArrayList<>();
                    lowRBC.add("1. Anemia");
                    lowRBC.add("2. Thyroid Condition");
                    lowRBC.add("3. Iron Deficiency");
                    lowRBC.add("4. Blood loss");
                    lowRBC.add("5. Polycythemia Vera");

                    ArrayList<String> highRBC = new ArrayList<>();
                    highRBC.add("1. Congenital Heart Disease");
                    highRBC.add("2. COPD");
                    highRBC.add("3. Kidney Cancer");

                    ArrayList<String> normalRBC = new ArrayList<>();
                    normalRBC.add("No Disease");

                    ArrayList<String> lowRBCSymptoms = new ArrayList<>();
                    lowRBCSymptoms.add("Tiredness");
                    lowRBCSymptoms.add("Weakness");
                    lowRBCSymptoms.add("Shortness of breath");
                    lowRBCSymptoms.add("Pale or Yellowish skin");
                    lowRBCSymptoms.add("Irregular Heart bit");
                    lowRBCSymptoms.add("Chest pain");
                    lowRBCSymptoms.add("Cold hands or feet");
                    lowRBCSymptoms.add("Dizziness");

                    ArrayList<String> highRBCSymptoms = new ArrayList<>();
                    highRBCSymptoms.add("Tiredness");
                    highRBCSymptoms.add("Headaches");
                    highRBCSymptoms.add("Shortness of breath");
                    highRBCSymptoms.add("Blurry Vision");
                    highRBCSymptoms.add("Sleep Disorders");
                    highRBCSymptoms.add("Joint pain");
                    highRBCSymptoms.add("Itchy skin");
                    highRBCSymptoms .add("Numbness and tingling");

                    ArrayList<String> normalRBCSymptoms = new ArrayList<>();
                    normalRBCSymptoms.add("No Symptoms");



                    String rbcStatus = ResultsActivity.calculateRBCStatus(rbcValue,genderValue);
                    ArrayList<String> rbcDiseaseData;
                    ArrayList<String> rbcSymptomsData;

                    Intent intent = new Intent(HomeActivity.this, OutputActivity.class);
                    if("Low".equals(rbcStatus)){
                        rbcDiseaseData = lowRBC;
                        rbcSymptomsData = lowRBCSymptoms;
                    } else if ("High".equals(rbcStatus)) {
                        rbcDiseaseData = highRBC;
                        rbcSymptomsData = highRBCSymptoms;
                    }
                    else {
                        rbcDiseaseData =normalRBC;
                        rbcSymptomsData = normalRBCSymptoms;
                    }
                    intent.putExtra("RBC_STATUS", rbcStatus);
                    intent.putStringArrayListExtra("RBC_DISEASE", rbcDiseaseData);
                    intent.putStringArrayListExtra("RBC_SYMPTOMS",rbcSymptomsData);

                                                                                                  //WBC calculate

                    ArrayList<String> lowWBC = new ArrayList<>();
                    lowWBC.add("1. Leukemia");
                    lowWBC.add("2. Neutropenia");
                    ArrayList<String> highWBC = new ArrayList<>();
                    highWBC.add("1. Infection or Inflammation");
                    highWBC.add("2. Bone Marrow Disease");
                    ArrayList<String> normalWBC = new ArrayList<>();
                    normalWBC.add("No Disease");

                    ArrayList<String> lowWBCSymptoms = new ArrayList<>();
                    lowWBCSymptoms.add("High Temperature");
                    lowWBCSymptoms.add("Sore Throat");
                    lowWBCSymptoms.add("Chills & Shivering");
                    lowWBCSymptoms.add("Toothache");
                    lowWBCSymptoms.add("Skin Rashes");
                    lowWBCSymptoms.add("Flu-like Symptoms");

                    ArrayList<String> highWBCSymptoms = new ArrayList<>();
                    highWBCSymptoms.add("Fever");
                    highWBCSymptoms.add("Fatigue");
                    highWBCSymptoms.add("Difficulty Breathing");
                    highWBCSymptoms.add("Wheezing");
                    highWBCSymptoms.add("Night Sweat");
                    highWBCSymptoms.add("Unexpected Weight loss");

                    ArrayList<String> normalWBCSymptoms = new ArrayList<>();
                    normalWBCSymptoms.add("No Symptoms");


                    String wbcStatus = ResultsActivity.calculateWBCStatus(wbcValue);
                    ArrayList<String> wbcDiseaseData;
                    ArrayList<String> wbcSymptomsData;

                    if("Low".equals(wbcStatus)){
                        wbcDiseaseData = lowWBC;
                        wbcSymptomsData = lowWBCSymptoms;
                    } else if ("High".equals(wbcStatus)) {
                        wbcDiseaseData = highWBC;
                        wbcSymptomsData = highWBCSymptoms;
                    }
                    else {
                        wbcDiseaseData = normalWBC;
                        wbcSymptomsData = normalWBCSymptoms;
                    }
                    intent.putExtra("WBC_STATUS", wbcStatus);
                    intent.putStringArrayListExtra("WBC_DISEASE", wbcDiseaseData);
                    intent.putStringArrayListExtra("WBC_SYMPTOMS", wbcSymptomsData);


                                                                                                //Platelet count
                    String plateletStatus = ResultsActivity.calculatePlateletStatus(plateletValue);
                    ArrayList<String> lowPlatelet = new ArrayList<>();
                    lowPlatelet.add("1. Thrombocytopenia");
                    ArrayList<String> highPlatelet = new ArrayList<>();
                    highPlatelet.add("1. Thrombocythemia");
                    ArrayList<String> normalPlatelet = new ArrayList<>();
                    normalPlatelet.add("No Disease");

                    ArrayList<String> lowPlateletSymptoms = new ArrayList<>();
                    lowPlateletSymptoms.add("Prolonged bleeding from cuts");
                    lowPlateletSymptoms.add("Blood in urine or stools");
                    lowPlateletSymptoms.add("Heavy Menstrul flow");

                    ArrayList<String> highPlateletSymptoms = new ArrayList<>();
                    highPlateletSymptoms.add("Burning pain in the hands or feet");
                    highPlateletSymptoms.add("Shortness of breathe and nausea");
                    highPlateletSymptoms.add("Chest pain");

                    ArrayList<String> normalPlateletSymptoms = new ArrayList<>();
                    normalPlateletSymptoms.add("No Symptoms");

                    ArrayList<String> plateletDiseaseData;
                    ArrayList<String> plateletSymptomsData;
                    if("Low".equals(plateletStatus)){
                        plateletDiseaseData = lowPlatelet;
                        plateletSymptomsData = lowPlateletSymptoms;
                    } else if ("High".equals(plateletStatus)) {
                        plateletDiseaseData = highPlatelet;
                        plateletSymptomsData = highPlateletSymptoms;
                    }
                    else {
                        plateletDiseaseData = normalPlatelet;
                        plateletSymptomsData = normalPlateletSymptoms;
                    }
                    intent.putExtra("PLATELET_STATUS", plateletStatus);
                    intent.putStringArrayListExtra("PLATELET_DISEASE", plateletDiseaseData);
                    intent.putStringArrayListExtra("PLATELET_SYMPTOMS", plateletSymptomsData);
                    intent.putExtra("user_email", userEmail); // "user_email" is a key for the email


                    startActivity(intent);
                }



            }
            private void showErrorMessageDialog() {
                ConstraintLayout errorConstraintLayout = findViewById(R.id.errorConstraintLayout);
                View v = LayoutInflater.from(HomeActivity.this).inflate(R.layout.dialog_error, errorConstraintLayout);
                Button error = v.findViewById(R.id.errorButton);
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
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