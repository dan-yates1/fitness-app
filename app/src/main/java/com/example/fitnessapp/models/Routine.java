package com.example.fitnessapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Routine implements Serializable {
    private String mName;
    private ArrayList<Exercise> mExercises;
    private ArrayList<String> mTargetMuscles;

}
