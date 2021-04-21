package com.example.fitnessapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitnessapp.R;
import com.example.fitnessapp.layouts.DataEntryActivity;
import com.example.fitnessapp.models.Workout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExperienceFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "TAG";
    Workout mWorkout;
    Button mBeginner, mIntermediate, mExperienced;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;

    public ExperienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_experience, container, false);

        getWorkoutObj();

        mBeginner = v.findViewById(R.id.beginnerButton);
        mIntermediate = v.findViewById(R.id.intermediateButton);
        mExperienced = v.findViewById(R.id.experiencedButton);

        mBeginner.setOnClickListener(this);
        mIntermediate.setOnClickListener(this);
        mExperienced.setOnClickListener(this);

        return v;
    }

    public void getWorkoutObj() {
        // Get workout object form previous fragment
        Bundle bundle = getArguments();
        mWorkout = (Workout) bundle.getSerializable("workout");
    }

    public void startNextActivity() {
        // Passes workout object to next fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DaysFragment daysFragment = new DaysFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("workout", mWorkout);
        daysFragment.setArguments(bundle);
        ft.replace(R.id.mainLayout, daysFragment);
        ft.commit();
    }

    public void addWorkoutToDb() {
        // Add users workout to fire store database
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference docRef = fStore
                .collection("users").document(userId)
                .collection("workout").document(userId);
        // Populate hash map with data
        Map<String, Object> workoutMap = new HashMap<>();
        workoutMap.put("gender", mWorkout.getGender());
        Object[] array = mWorkout.getEquipment().toArray();
        workoutMap.put("equipment", Arrays.asList(array));
        workoutMap.put("goal", mWorkout.getGoal());
        workoutMap.put("experience", mWorkout.getExperience());
        workoutMap.put("days", mWorkout.getAvailability());
        docRef.set(workoutMap).addOnSuccessListener(aVoid -> {
            Log.d(TAG, "onSuccess: Workout is created for" + userId);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beginnerButton:
                mWorkout.setExperience("Beginner");
                addWorkoutToDb();
                break;
            case R.id.intermediateButton:
                mWorkout.setExperience("Intermediate");
                addWorkoutToDb();
                break;
            case R.id.experiencedButton:
                mWorkout.setExperience("Experienced");
                addWorkoutToDb();
                break;
        }
        //startNextActivity();
    }
}