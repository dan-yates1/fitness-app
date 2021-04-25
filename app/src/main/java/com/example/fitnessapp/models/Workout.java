package com.example.fitnessapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {
    private String mGender;
    private ArrayList<String> mEquipment;
    private int mAvailability;
    private String mGoal;
    private String mExperience;

    public Workout() {
        this.mGender = "";
        this.mEquipment = new ArrayList<>();
        this.mAvailability = 0;
        this.mGoal = "";
        this.mExperience = "";
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public ArrayList<String> getEquipment() {
        return mEquipment;
    }

    public void setEquipment(ArrayList<String> equipment) {
        this.mEquipment = equipment;
    }

    public int getAvailability() {
        return mAvailability;
    }

    public void setAvailability(int availability) {
        this.mAvailability = availability;
    }

    public String getGoal() {
        return mGoal;
    }

    public void setGoal(String goal) {
        this.mGoal = goal;
    }

    public String getExperience() {
        return mExperience;
    }

    public void setExperience(String experience) {
        this.mExperience = experience;
    }

    public int getRoutine() {
        int routine = 0;
        return routine;
    }

    public Routine generateRoutine() {
        Routine routine = new Routine("test");
        return routine;
    }
}
