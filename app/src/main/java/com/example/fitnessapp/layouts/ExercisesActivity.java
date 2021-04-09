package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button mArmsBtn, mChestBtn, mLegsBtn, mShouldersBtn, mBackBtn;
    private ArrayList<Exercise> mExerciseList;
    private ArrayList<Exercise> mFilteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        buildExerciseList();
        buildRecyclerView();
    }

    private void buildExerciseList() {
        ArrayList<String> muscles = new ArrayList<>();
        muscles.add("Chest");
        muscles.add("Abs");
        mExerciseList = new ArrayList<>();
        mExerciseList.add(new Exercise("Bench Press", muscles, new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Squat", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Leg Press", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        mExerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExerciseAdapter(mExerciseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}