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
    private int[] images = {R.drawable.bg_img, R.drawable.bg_img2};
    Button mLoginButton, mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        buildViewPager();
        buildButtons();
    }

    private void buildButtons() {
        mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void buildViewPager() {
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.bgViewPager);
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(StartUpActivity.this, images);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}