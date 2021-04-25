package com.example.fitnessapp.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fitnessapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildNavBar();
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_home);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_exercises:
                    startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
                case R.id.nav_workout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    break;
            }
            return false;
        });
    }
}