package com.example.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Routine implements Serializable {
    @SerializedName("name")
    private String mRoutineName;
    @SerializedName("routine")
    private ArrayList<Day> mDays;

    public Routine(String routineName) {
        this.mRoutineName = routineName;
        mDays = new ArrayList<Day>();
    }

    public Routine(String mRoutineName, ArrayList<Day> mDays) {
        this.mRoutineName = mRoutineName;
        this.mDays = mDays;
    }

    public String getRoutineName() {
        return mRoutineName;
    }

    public ArrayList<Day> getDays() {
        return mDays;
    }

    public void setDays(ArrayList<Day> days) {
        this.mDays = days;
    }
}
