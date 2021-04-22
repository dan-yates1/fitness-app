package com.example.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    private String mName;
    @SerializedName("primary_muscles")
    private ArrayList<String> mPrimaryMuscles;
    @SerializedName("secondary_muscles")
    private ArrayList<String> mSecondaryMuscles;
    @SerializedName("equipment")
    private ArrayList<String> mEquipment;
    @SerializedName("description")
    private String mDescription;

    public Exercise(String name, ArrayList<String> primaryMuscles, ArrayList<String> secondaryMuscles,
                    ArrayList<String> equipment, String description) {
        this.mName = name;
        this.mPrimaryMuscles = primaryMuscles;
        mSecondaryMuscles = secondaryMuscles;
        mEquipment = equipment;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getPrimaryMuscles() {
        return mPrimaryMuscles;
    }

    public ArrayList<String> getSecondaryMuscles() {
        return mSecondaryMuscles;
    }

    public ArrayList<String> getAllMuscles() {
        // Create new array of all muscles
        ArrayList<String> allMuscles = new ArrayList<>();
        for (int i = 0; i < mPrimaryMuscles.size(); i++) {
            allMuscles.add(mPrimaryMuscles.get(i));
        }
        for (int i = 0; i < mSecondaryMuscles.size(); i++) {
            allMuscles.add(mSecondaryMuscles.get(i));
        }
        return allMuscles;
    }

    public ArrayList<String> getEquipment() {
        return mEquipment;
    }

    public String getDescription() {
        return mDescription;
    }
}
