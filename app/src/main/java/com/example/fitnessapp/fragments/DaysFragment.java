package com.example.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.layouts.DataEntryActivity;
import com.example.fitnessapp.layouts.WorkoutActivity;
import com.example.fitnessapp.models.Workout;

public class DaysFragment extends Fragment implements View.OnClickListener {
    TextView mCounter;
    ImageButton mAdd, mMinus;
    private int mCount;
    private Workout mWorkout;

    public DaysFragment() {
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
        View v = inflater.inflate(R.layout.fragment_days, container, false);

        mCounter = v.findViewById(R.id.counter);
        mAdd = v.findViewById(R.id.addButton);
        mMinus = v.findViewById(R.id.minusButton);

        mAdd.setOnClickListener(this);
        mMinus.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        mCount = Integer.parseInt(mCounter.getText().toString());
        switch (v.getId()) {
            case R.id.addButton:
                if (mCount < 6) {
                    mCount += 1;
                    mCounter.setText(mCount);
                } else {
                    Toast.makeText(getActivity(), "Enter a number between 2 & 6", Toast.LENGTH_SHORT).show();
                }
            case R.id.minusButton:
                if (mCount > 2) {
                    mCount -= 1;
                    mCounter.setText(mCount);
                } else {
                    Toast.makeText(getActivity(), "Enter a number between 2 & 6", Toast.LENGTH_SHORT).show();
                }
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
        ft.replace(R.id.mainLayout, goalsFragment);
        ft.commit();
    }
}