package com.example.medihelp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;import android.Manifest;


public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String userEmail = getIntent().getStringExtra("user_email");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        CardView labTestCard = findViewById(R.id.labTestCard);
        CardView buyMedicineCard = findViewById(R.id.buyMedicineCard);
        CardView callCenterCard = findViewById(R.id.callCenterCard);
        CardView profileCard = findViewById(R.id.profileCard);
        CardView settingsCard = findViewById(R.id.settingsCard);
        CardView logoutCard = findViewById(R.id.logoutCard);

        labTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DashboardActivity.this, LabTestActivity.class);
                intent.putExtra("user_email", userEmail); // "user_email" is a key for the email

                startActivity(intent);
                finish();

            }
        });

        buyMedicineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        callCenterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = "+8801858976933"; // Replace with your actual phone number
                showPhoneNumberDialog(phoneNumber);
            }
            private void showPhoneNumberDialog(String phoneNumber) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Call Center Phone Number");
                builder.setMessage(phoneNumber);

                builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Initiate a call when the "Call" button is clicked
                        initiatePhoneCall(phoneNumber);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle cancel action, if needed
                    }
                });

                builder.show();
            }

            private void initiatePhoneCall(String phoneNumber) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));

                if (ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Handle permissions if not granted
                    // You should request the CALL_PHONE permission if not granted
                    return;
                }

                // Start the phone call
                startActivity(callIntent);
            }


        });
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DbHelper dbHelper = new DbHelper(DashboardActivity.this);
                User user = dbHelper.getUserByEmail(userEmail);

                if (user != null) {
                    String name = user.getName();
                    String bloodGroup = user.getBloodGroup();
                    String gender = user.getGender();

                    dbHelper.close();


                    // String name = "User's Name"; // Replace with the actual name
                //String email = "user@email.com"; // Replace with the actual email
                //String bloodGroup = "A+"; // Replace with the actual blood group
                //String gender = "Male"; // Replace with the actual gender

                // Create an Intent to open the user profile page
                Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", userEmail);
                intent.putExtra("bloodGroup", bloodGroup);
                intent.putExtra("gender", gender);

                startActivity(intent);
            }
        }});

        settingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DashboardActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
            }
        });
        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });




    }
}
