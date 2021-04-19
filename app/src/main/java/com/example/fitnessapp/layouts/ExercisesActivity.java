package com.example.fitnessapp.layouts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.models.Exercise;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private Button mBicepsBtn, mTricepsBtn, mChestBtn, mQuadsBtn, mShouldersBtn, mBackBtn, mAllBtn, mHamsBtn;
    private EditText mSearchBar;
    private ArrayList<Exercise> mExerciseList;
    BottomNavigationView mBottomNav;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        buildFilterButtons();
        buildExerciseList();
        buildRecyclerView();
        buildSearchBar();
        buildNavBar();
    }

    private void buildSearchBar() {
        mSearchBar = findViewById(R.id.searchEditText);
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterByName(s.toString());
            }
        });
    }

    private void filterByName(String text) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise item : mExerciseList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void filterByMuscle(String text) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise item : mExerciseList) {
            if (item.getAllMuscles().contains(text)) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void buildFilterButtons() {
        mBicepsBtn = findViewById(R.id.bicepsBtn);
        mTricepsBtn = findViewById(R.id.tricepsBtn);
        mChestBtn = findViewById(R.id.chestBtn);
        mQuadsBtn = findViewById(R.id.quadsBtn);
        mHamsBtn = findViewById(R.id.hamstringsBtn);
        mShouldersBtn = findViewById(R.id.shouldersBtn);
        mBackBtn = findViewById(R.id.backBtn);
        mAllBtn = findViewById(R.id.allBtn);

        mBicepsBtn.setOnClickListener(this);
        mTricepsBtn.setOnClickListener(this);
        mChestBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mShouldersBtn.setOnClickListener(this);
        mQuadsBtn.setOnClickListener(this);
        mHamsBtn.setOnClickListener(this);
        mAllBtn.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void buildExerciseList() {
        // Parse list of exercises from JSON file located in assets folder
        mExerciseList = new ArrayList<>();
        Gson gson = new Gson();
        Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();
        mExerciseList = gson.fromJson(loadJSONFromAsset("exercises.json"), exerciseListType);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loadJSONFromAsset(String fileName) {
        // Read JSON file into string
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExerciseAdapter(mExerciseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allBtn:
                mAdapter.filterList(mExerciseList);
                break;
            case R.id.chestBtn:
                filterByMuscle("Chest");
                break;
            case R.id.quadsBtn:
                filterByMuscle("Quads");
                break;
            case R.id.hamstringsBtn:
                filterByMuscle("Hamstrings");
                break;
            case R.id.bicepsBtn:
                filterByMuscle("Biceps");
                break;
            case R.id.tricepsBtn:
                filterByMuscle("Triceps");
                break;
            case R.id.backBtn:
                filterByMuscle("Back");
                break;
            case R.id.shouldersBtn:
                filterByMuscle("Shoulders");
                break;
        }
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_profile);
        mBottomNav.setSelectedItemId(R.id.nav_exercises);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                case R.id.nav_exercises:
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                case R.id.nav_workout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
            }
            return false;
        });
    }
}