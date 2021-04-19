package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mNameEditText, mEmailEditText, mPasswordEditText;
    Button mNextButton;
    CheckBox mCheckBox;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpInterface();
    }

    private User createUserObject() {
        String name = mNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        return new User(name, email, password);
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
            mPasswordEditText.setError("Password is required!");
            valid = false;
        }

        if (password.length() < 6) {
            mPasswordEditText.setError("Password must be more than 6 characters!");
            valid = false;
        }

        /* (!mCheckBox.isChecked()) {
            mCheckBox.setError("Please agree to the terms!");
            valid = false;
        }*/

        return valid;
    }

    private void setUpInterface() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mNameEditText = findViewById(R.id.nameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        mEmailEditText = findViewById(R.id.emailEditText);
        mCheckBox = findViewById(R.id.checkBox);

        mNextButton = findViewById(R.id.registerButton);
        mNextButton.setOnClickListener(v -> {
            if (checkCredentials()) {
                User user = createUserObject();
                addUserToFirebase(user);
                startNextActivity(user);
                finish();
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
                        userId = fAuth.getCurrentUser().getUid();
                        // Add user data to firestore database
                        addDataToDatabase(user);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void addDataToDatabase(User user) {
        DocumentReference docRef = fStore.collection("users").document(userId);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("fName", user.getName());
        userMap.put("email", user.getEmail());
        docRef.set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: User profile is created for" + userId);
            }
        });
    }
}