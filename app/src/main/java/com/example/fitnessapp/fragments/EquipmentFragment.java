package com.example.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Workout;

import java.util.ArrayList;

public class EquipmentFragment extends Fragment {
    Workout mWorkout;
    CheckBox mDumbbells, mCables, mMachines, mBarbell;
    Button mNextButton;
    ArrayList<String> mEquipment;

    public EquipmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_equipment, container, false);

        getWorkoutObj();

        mEquipment = new ArrayList<>();
        mDumbbells = v.findViewById(R.id.dumbbellCheckBox);
        mCables = v.findViewById(R.id.cablesCheckBox);
        mMachines = v.findViewById(R.id.machinesCheckBox);
        mBarbell = v.findViewById(R.id.barbellCheckBox);

        mNextButton = v.findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEquipment();
                mWorkout.setEquipment(mEquipment);
                startNextActivity();
            }
        });

        return v;
    }

    public void getWorkoutObj() {
        // Get workout object form previous fragment
        Bundle bundle = getArguments();
        mWorkout = (Workout) bundle.getSerializable("workout");
    }

    public void getEquipment() {
        if (mDumbbells.isChecked()) {
            mEquipment.add("Barbell");
        }
        if (mCables.isChecked()) {
            mEquipment.add("Cables");
        }
        if (mMachines.isChecked()) {
            mEquipment.add("Machines");
        }
        if (mBarbell.isChecked()) {
            mEquipment.add("Barbell");
        }
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