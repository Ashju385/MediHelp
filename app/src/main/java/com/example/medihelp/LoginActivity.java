package com.example.medihelp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DbHelper(this);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validate input data (you can add more validations as needed)
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }



                else {
                    //Perform login process
                    User user = dbHelper.getUserByEmail(email);

                    if (user != null && user.getPassword().equals(password)) {
                        // Login successful, handle next steps
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Pass the user's email to the DashboardActivity
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.putExtra("user_email", email); // "user_email" is a key for the email
                        startActivity(intent);
                        finish(); // Optional - finish the LoginActivity to prevent going back to it using the back button
                    } else {
                        // Login failed
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }











                //
                }
            }
        });
    }
}
