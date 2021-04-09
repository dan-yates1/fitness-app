package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Bench Press", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        exerciseList.add(new Exercise("Squat", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        exerciseList.add(new Exercise("Deadlift", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        exerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        exerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        exerciseList.add(new Exercise("Barbell Curl", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExerciseAdapter(exerciseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void buildRecyclerView() {
    }
}