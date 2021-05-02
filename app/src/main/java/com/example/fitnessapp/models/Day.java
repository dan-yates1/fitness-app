package com.example.fitnessapp.models;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {
    @SerializedName("name")
    private String mName;
    @SerializedName("muscles")
    private ArrayList<String> mMuscles;
    @SerializedName("exercises")
    private ArrayList<Exercise> mExercises;
    @SerializedName("reps")
    private int mReps;
    @SerializedName("sets")
    private int mSets;

    public Day() {} // Needed for firebase

    public Day(String name, ArrayList<String> muscles, ArrayList<Exercise> exercises, int reps, int sets) {
        mName = name;
        mMuscles = muscles;
        mExercises = exercises;
        mReps = reps;
        mSets = sets;
    }

    @PropertyName("name")
    public String getName() {
        return mName;
    }

    @PropertyName("muscles")
    public ArrayList<String> getMuscles() {
        return mMuscles;
    }

    @PropertyName("exercises")
    public ArrayList<Exercise> getExercises() {
        return mExercises;
    }

    @PropertyName("reps")
    public int getReps() {
        return mReps;
    }

    @PropertyName("sets")
    public int getSets() {
        return mSets;
    }

    @PropertyName("exercises")
    public void setExercises(ArrayList<Exercise> exercises) {
        this.mExercises = exercises;
    }

    @PropertyName("reps")
    public void setReps(int reps) {
        this.mReps = reps;
    }

    @PropertyName("sets")
    public void setSets(int sets) {
        this.mSets = sets;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setMuscles(ArrayList<String> muscles) {
        this.mMuscles = muscles;
    }
}