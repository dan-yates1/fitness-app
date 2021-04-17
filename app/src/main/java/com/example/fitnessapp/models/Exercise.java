package com.example.fitnessapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    private final String name;
    @SerializedName("primary_muscles")
    private final ArrayList<String> primaryMuscles;
    @SerializedName("secondary_muscles")
    private final ArrayList<String> secondaryMuscles;
    private final ArrayList<String> equipment;

    public Exercise(String name, ArrayList<String> primaryMuscles, ArrayList<String> secondaryMuscles,
                    ArrayList<String> allMuscles, ArrayList<String> equipment) {
        this.name = name;
        this.primaryMuscles = primaryMuscles;
        this.secondaryMuscles = secondaryMuscles;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrimaryMuscles() {
        return primaryMuscles;
    }

    public ArrayList<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public ArrayList<String> getAllMuscles() {
        // Create new array of all muscles
        ArrayList<String> allMuscles = new ArrayList<>();
        for (int i = 0; i < primaryMuscles.size(); i++) {
            allMuscles.add(primaryMuscles.get(i));
        }
        for (int i = 0; i < secondaryMuscles.size(); i++) {
            allMuscles.add(secondaryMuscles.get(i));
        }
        return allMuscles;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }
}
