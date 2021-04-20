package com.example.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Workout;

public class GoalsFragment extends Fragment implements View.OnClickListener {
    Workout mWorkout;
    ImageButton mFitness, mStrength, mWeightLoss, mMuscleGain;

    public GoalsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_goals, container, false);

        getWorkoutObj();

        mFitness = v.findViewById(R.id.fitnessButton);
        mStrength = v.findViewById(R.id.strengthButton);
        mWeightLoss = v.findViewById(R.id.weightLossButton);
        mMuscleGain = v.findViewById(R.id.sizeButton);

        mFitness.setOnClickListener(this);
        mStrength.setOnClickListener(this);
        mWeightLoss.setOnClickListener(this);
        mMuscleGain.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fitnessButton:
                mWorkout.setGoal("Fitness");
                startNextActivity();
            case R.id.strengthButton:
                mWorkout.setGoal("Strength");
                startNextActivity();
            case R.id.weightLossButton:
                mWorkout.setGoal("Weight-loss");
                startNextActivity();
            case R.id.sizeButton:
                mWorkout.setGoal("Muscle-gain");
                startNextActivity();
        }
    }

    public void getWorkoutObj() {
        // Get workout object form previous fragment
        Bundle bundle = getArguments();
        mWorkout = (Workout) bundle.getSerializable("workout");
    }

    public void startNextActivity() {
        // Passes workout object to next fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        GoalsFragment goalsFragment = new GoalsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("workout", mWorkout);
        goalsFragment.setArguments(bundle);
        //ft.replace(R.id.mainLayout, goalsFragment);
        ft.commit();
    }
}