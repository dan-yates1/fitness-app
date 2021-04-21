package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitnessapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        buildNavBar();
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_workout);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
                case R.id.nav_exercises:
                    startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
                case R.id.nav_workout:
                    break;
            }
            return false;
        });
    }
}