package com.example.fitnessapp.models;

import java.util.ArrayList;

public class Exercise {
    private String name;
    private ArrayList<String> primaryMuscles;
    private ArrayList<String> secondaryMuscles;
    private ArrayList<String> equipment;

    public Exercise(String name, ArrayList<String> primaryMuscles, ArrayList<String> secondaryMuscles, ArrayList<String> equipment) {
        this.name = name;
        this.primaryMuscles = primaryMuscles;
        this.secondaryMuscles = secondaryMuscles;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPrimaryMuscles() {
        return primaryMuscles;
    }

    public void setPrimaryMuscles(ArrayList<String> primaryMuscles) {
        this.primaryMuscles = primaryMuscles;
    }

    public ArrayList<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(ArrayList<String> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<String> equipment) {
        this.equipment = equipment;
    }
}
