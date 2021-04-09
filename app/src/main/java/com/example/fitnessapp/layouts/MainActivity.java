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
    RecyclerView mRecyclerView;
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildNavBar();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    return true;
                case R.id.nav_exercises:
                    Intent exercisesIntent = new Intent(getApplicationContext(), ExercisesActivity.class);
                    startActivity(exercisesIntent);
            }
            return false;
        });
    }
}