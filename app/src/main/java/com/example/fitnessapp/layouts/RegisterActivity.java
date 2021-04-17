package com.example.fitnessapp.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText mNameEditText, mEmailEditText, mPasswordEditText;
    Button mNextButton;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpInterface();

        // Check if user is already signed in and take them to main page
        /*if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/
    }

    private User createUserObject() {
        User user = new User(mNameEditText.getText().toString().trim(),
                mEmailEditText.getText().toString().trim(),
                mPasswordEditText.getText().toString().trim());
        return user;
    }

    private boolean checkCredentials() {
        Boolean valid = true;
        String name = mNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            mNameEditText.setError("Name is required!");
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

        if (TextUtils.isEmpty(password)) {
            mNameEditText.setError("Password is required!");
            valid = false;
        }

        if (password.length() < 6) {
            mPasswordEditText.setError("Password must be more than 6 characters!");
            valid = false;
        }

        return valid;
    }

    private void setUpInterface() {
        fAuth = FirebaseAuth.getInstance();

        mNameEditText = findViewById(R.id.nameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        mEmailEditText = findViewById(R.id.emailEditText);

        mNextButton = findViewById(R.id.registerButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCredentials()) {
                    User user = createUserObject();
                    addUserToFirebase(user);
                    //startNextActivity(user);
                }
            }
        });
    }

    private void startNextActivity(User user) {
        // TODO Pass user object to MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void addUserToFirebase(User user) {
        fAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User account created successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}