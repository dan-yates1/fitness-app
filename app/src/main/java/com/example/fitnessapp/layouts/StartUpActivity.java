package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button mRegisterButton;
    TextView mLoginText;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        setUpInterface();
        //logoutUser();
    }

    private void logoutUser() {
        // If a user is already signed in then sign them out
        if (fAuth.getCurrentUser() != null) {
            fAuth.signOut();
        }
    }

    public void setUpInterface() {
        fAuth = FirebaseAuth.getInstance();

        mLoginText = findViewById(R.id.loginTextView);
        mRegisterButton = findViewById(R.id.registerButton);

        mLoginText.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginTextView:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.registerButton:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }
    }
}