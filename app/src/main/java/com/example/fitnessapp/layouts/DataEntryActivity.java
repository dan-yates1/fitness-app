package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.fragments.GenderFragment;

public class DataEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        GenderFragment genderFragment = new GenderFragment();
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.mainLayout, genderFragment).commit();
    }
}