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

public class GenderFragment extends Fragment {
    ImageButton mMale, mFemale;
    private Workout mWorkout;

    public GenderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gender, container, false);

        mWorkout = new Workout();

        mMale = v.findViewById(R.id.maleButton);
        mFemale = v.findViewById(R.id.femaleButton);
        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkout.setGender("Male");
                startNextActivity();
            }
        });

        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkout.setGender("Female");
                startNextActivity();
            }
        });

        return v;
    }

    public void startNextActivity() {
        // Passes workout object to next fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        EquipmentFragment equipmentFragment = new EquipmentFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("workout", mWorkout);
        equipmentFragment.setArguments(bundle);
        ft.replace(R.id.mainLayout, equipmentFragment);
        ft.commit();
    }
}