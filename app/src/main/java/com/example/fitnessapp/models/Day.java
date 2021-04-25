package com.example.fitnessapp.models;

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

    public Day(String mName, ArrayList<String> mMuscles, ArrayList<Exercise> mExercises, int mReps, int mSets) {
        this.mName = mName;
        this.mMuscles = mMuscles;
        this.mExercises = mExercises;
        this.mReps = mReps;
        this.mSets = mSets;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getMuscles() {
        return mMuscles;
    }

    public ArrayList<Exercise> getExercises() {
        return mExercises;
    }

    public int getReps() {
        return mReps;
    }

    public int getSets() {
        return mSets;
    }
}