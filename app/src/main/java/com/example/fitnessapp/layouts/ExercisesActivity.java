package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private Button mArmsBtn, mChestBtn, mLegsBtn, mShouldersBtn, mBackBtn;
    private EditText mSearchBar;
    private ArrayList<Exercise> mExerciseList;
    private ArrayList<String> mMusclesFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        buildFilterButtons();
        buildExerciseList();
        buildRecyclerView();
        buildSearchBar();
    }

    private void buildSearchBar() {
        mSearchBar = findViewById(R.id.searchEditText);
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise item : mExerciseList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void buildFilterButtons() {
        mMusclesFilter = new ArrayList<>();
        mArmsBtn = findViewById(R.id.armsBtn);
        mChestBtn = findViewById(R.id.chestBtn);
        mLegsBtn = findViewById(R.id.legsBtn);
        mShouldersBtn = findViewById(R.id.shouldersBtn);
        mBackBtn = findViewById(R.id.backBtn);

        mArmsBtn.setOnClickListener(v -> {
            if (!mMusclesFilter.contains("Arms")) {
                mMusclesFilter.add("Arms");
            }
        });
    }

    private void buildExerciseList() {
        ArrayList<String> primaryMuscles = new ArrayList<>();
        primaryMuscles.add("Chest");
        primaryMuscles.add("Triceps");
        ArrayList<String> secondaryMuscles = new ArrayList<>();
        primaryMuscles.add("Abs");

        mExerciseList = new ArrayList<>();
        mExerciseList.add(new Exercise("Bench Press", primaryMuscles, secondaryMuscles, new ArrayList<>()));
        mExerciseList.add(new Exercise("Squat", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Leg Press", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Bench Press", primaryMuscles, new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Bench Press", primaryMuscles, new ArrayList<>(), new ArrayList<>()));
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExerciseAdapter(mExerciseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}