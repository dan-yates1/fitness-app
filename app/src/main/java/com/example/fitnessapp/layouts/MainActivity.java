package com.example.fitnessapp.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNav;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpInterface();
        buildNavBar();
    }

    private void setUpInterface() {
        mTextView = findViewById(R.id.textView2);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            Python py = Python.getInstance();
            PyObject pyObj = py.getModule("workout_ai").callAttr("main");;
            PyObject obj = pyObj
            mTextView.setText(obj.toString());
        });
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