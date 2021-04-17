package com.example.fitnessapp.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText mEmailEditText, mPasswordEditText;
    Button mLoginButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpInterface();
    }

    private boolean validateInput() {
       Boolean valid = true;
       String email = mEmailEditText.getText().toString().trim();
       String password = mPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError("Password is required!");
            valid = false;
        }
        if (password.length() < 6) {
            mPasswordEditText.setError("Password must be more than 6 characters!");
            valid = false;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError("Email is required!");
            valid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError("Please enter a valid email!");
            valid = false;
        }

       return valid;
    }

    private void setUpInterface() {
        fAuth = FirebaseAuth.getInstance();
        mEmailEditText = findViewById(R.id.loginEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(v -> {
            if (validateInput()) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        fAuth.signInWithEmailAndPassword(mEmailEditText.getText().toString().trim(),
                mPasswordEditText.getText().toString().trim())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User logged in successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}