package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class ExerciseDetailsActivity extends AppCompatActivity {
    private Exercise mExercise;
    private TextView mNameText, mMusclesText, mDescriptionText;
    private ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        setUpInterface();
        getExercise();
    }

    private void setUpInterface() {
        mNameText = findViewById(R.id.exerciseName);
        mMusclesText = findViewById(R.id.targetMuscles);
        mDescriptionText = findViewById(R.id.description);
        mBackButton = findViewById(R.id.backButton);
        mBackButton.setOnClickListener(v -> finish());
    }

    private void getExercise() {
        Exercise exercise = (Exercise) getIntent().getSerializableExtra("exercise");
        if (exercise != null) {
            mExercise = exercise;
            updateInterface();
        }
    }

    private void updateInterface() {
        mNameText.setText(mExercise.getName());
        mDescriptionText.setText(mExercise.getDescription());
        setMuscles();
    }

    private void setMuscles() {
        ArrayList<String> muscles = mExercise.getAllMuscles();
        if (muscles.size() == 1) {
            mMusclesText.setText("Target Muscles: " + muscles.get(0));
        }
        if (muscles.size() > 1) {
            mMusclesText.setText("Target Muscles: " + muscles.get(0));
            for (int i = 1; i < muscles.size(); i++) {
                mMusclesText.append(", " + muscles.get(i));
            }
        }
    }
}