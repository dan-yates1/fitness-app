package com.example.fitnessapp.models;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    @SerializedName("name")
    private String mName;
    @SerializedName("primary_muscles")
    private ArrayList<String> mPrimaryMuscles;
    @SerializedName("secondary_muscles")
    private ArrayList<String> mSecondaryMuscles;
    @SerializedName("equipment")
    private ArrayList<String> mEquipment;
    @SerializedName("description")
    private String mDescription;

    public Exercise() {} // Needed for firebase

    public Exercise(String name, ArrayList<String> primaryMuscles, ArrayList<String> secondaryMuscles,
                    ArrayList<String> equipment, String description) {
        mName = name;
        mPrimaryMuscles = primaryMuscles;
        mSecondaryMuscles = secondaryMuscles;
        mEquipment = equipment;
        mDescription = description;
    }

    @PropertyName("name")
    public String getName() {
        return mName;
    }

    @PropertyName("primaryMuscles")
    public ArrayList<String> getPrimaryMuscles() {
        return mPrimaryMuscles;
    }

    @PropertyName("secondaryMuscles")
    public ArrayList<String> getSecondaryMuscles() {
        return mSecondaryMuscles;
    }

    @PropertyName("all")
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

    @PropertyName("equipment")
    public ArrayList<String> getEquipment() {
        return mEquipment;
    }

    @PropertyName("description")
    public String getDescription() {
        return mDescription;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPrimaryMuscles(ArrayList<String> primaryMuscles) {
        this.mPrimaryMuscles = primaryMuscles;
    }

    public void setSecondaryMuscles(ArrayList<String> secondaryMuscles) {
        this.mSecondaryMuscles = secondaryMuscles;
    }

    public void setEquipment(ArrayList<String> equipment) {
        this.mEquipment = equipment;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
