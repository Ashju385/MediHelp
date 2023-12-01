package com.example.medihelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.content.Intent;
import com.example.medihelp.databinding.ActivitySignupBinding;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etBloodGroup, etPassword;
    RadioGroup radioGroupGender;
    private Button btnSignUp;
    private DbHelper dbHelper;
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DbHelper(this);

        etName = findViewById(R.id.edtName);
        etEmail = findViewById(R.id.edtEmail);
        etBloodGroup = findViewById(R.id.edtBloodGroup);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        etPassword = findViewById(R.id.edtPass1);

        btnSignUp = findViewById(R.id.btnSignup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user data from the input fields
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String bloodGroup = etBloodGroup.getText().toString().trim();
                String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validate input data (you can add more validations as needed)
                if (name.isEmpty() || email.isEmpty() || bloodGroup.isEmpty() || gender.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a User object with the input data
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setBloodGroup(bloodGroup);
                    user.setGender(gender);
                    user.setPassword(password);

                    // Save user data to the SQLite database
                    long userId = dbHelper.insertUser(user);

                    if (userId != -1) {
                        Toast.makeText(SignupActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                        // You can navigate to the login screen or any other screen here
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Sign Up failed.Check Email and try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void navigateToLogin(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish(); // Optional - finish the SignUpActivity to prevent going back to it using the back button
    }
}
