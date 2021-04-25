package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.R;

import gr.net.maroulis.library.EasySplashScreen;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(LoadingScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(WorkoutActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#373737"))
                .withLogo(R.drawable.ic_fitness_bot);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}