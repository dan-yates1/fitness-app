package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ViewPagerAdapter;

public class StartUpActivity extends AppCompatActivity {
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    private int[] images = {R.drawable.mountain1, R.drawable.mountain2, R.drawable.mountain3};
    Button mLoginButton, mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        buildViewPager();
        buildButtons();
    }

    public void buildButtons() {
        // Open login activity
        mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        });
        // Open registration activity
        mRegisterButton = findViewById(R.id.registerButton);
        mRegisterButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    public void buildViewPager() {
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.bgViewPager);
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(StartUpActivity.this, images);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}