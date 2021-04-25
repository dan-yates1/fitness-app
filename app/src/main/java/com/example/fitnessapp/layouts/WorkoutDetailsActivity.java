package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.DayAdapter;
import com.example.fitnessapp.adapters.RoutineAdapter;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Exercise;
import com.example.fitnessapp.models.Routine;

import java.util.ArrayList;

public class WorkoutDetailsActivity extends AppCompatActivity {
    private ImageButton mBackButton;
    private RecyclerView mRecyclerView;
    private DayAdapter mAdapter;
    private Day mDay;
    private ArrayList<Exercise> mExercises;
    private TextView mWorkoutTitle, mTargetMuscles, mExerciseCounter;
    private int mCompletedExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        buildExerciseList();
        setUpInterface();
        buildRecyclerView();
    }

    private void buildExerciseList() {
        // Get routine object from workout activity
        mExercises = new ArrayList<>();
        Day day = (Day) getIntent().getSerializableExtra("routine");
        if (day != null) {
            mDay = day;
            mExercises = day.getExercises();
        }
    }

    private void buildRecyclerView () {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DayAdapter(mExercises);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpInterface () {
        mCompletedExercises = 0;
        mWorkoutTitle = findViewById(R.id.workoutTitle);
        mTargetMuscles = findViewById(R.id.targetMuscles);
        mExerciseCounter = findViewById(R.id.exerciseCounter);
        mBackButton = findViewById(R.id.backButton);

        mBackButton.setOnClickListener(v -> finish());
        mWorkoutTitle.setText(mDay.getName());
        mExerciseCounter.setText(mCompletedExercises + "/" + mDay.getExercises().size());

        ArrayList<String> muscles = getAllMuscles();
        if (muscles.size() > 0) {
            mTargetMuscles.setText(muscles.get(0));
        }
        if (muscles.size() > 1) {
            for (int i = 1; i < muscles.size(); i++) {
                mTargetMuscles.append(", " + muscles.get(i));
            }
        }
    }

    private ArrayList<String> getAllMuscles() {
        // Gets all of the muscles targeted in the workout
        ArrayList<String> targetMuscles = new ArrayList<>();
        for (int i = 0; i < mDay.getExercises().size(); i++) {
            Exercise exercise = mDay.getExercises().get(i);
            for (int j = 0; j < exercise.getAllMuscles().size(); j++) {
                String muscle = exercise.getAllMuscles().get(j);
                if (!targetMuscles.contains(muscle)) {
                    targetMuscles.add(muscle);
                }
            }
        }
        return targetMuscles;
    }
}