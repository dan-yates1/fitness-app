package com.example.fitnessapp.models;

import java.util.ArrayList;

public class User {
    // Personal data
    private String mName;
    private String mEmail;
    private String mPassword;
    private Workout mWorkout;
    // Workout generation data
    private String mGender;
    private ArrayList<String> mEquipment;
    private int mAvailability;
    private String mGoal;
    private String mExperience;

    public User(String name, String email, String password) {
        mName = name;
        mEmail = email;
        mPassword = password;
        mWorkout = new Workout();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public Workout getWorkout() {
        return mWorkout;
    }

    public void setWorkout(Workout workout) {
        this.mWorkout = workout;
    }
}
