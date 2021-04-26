package com.example.fitnessapp.util;


import android.content.Context;

import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Exercise;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.Workout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class WorkoutGenerator {
    private static final int EXERCISES_PER_WORKOUT = 4;
    private Routine mRoutine;
    private Workout mWorkout;
    private int mRoutineId;
    private int mRequiredReps;
    private int mRequiredSets;
    private ArrayList<Exercise> mExerciseList;

    public WorkoutGenerator(Workout workout, int routineId, Context context) {
        mWorkout = workout;
        mRoutineId = routineId;
        getExerciseList(context);
        getRepsSets();
        getSplit(context);
    }

    public void getSplit(Context context) {
        switch (mRoutineId) {
            case 0:
                buildPPL(context);
                break;
            case 1:
                build5Day(context);
                break;
            case 2:
                buildUpperLower(context);
                break;
        }
    }

    public void getRepsSets() {
        switch (mWorkout.getGoal()) {
            case "Weight-loss":
                mRequiredReps = 12;
                mRequiredSets = 4;
                break;
            case "Fitness":
                mRequiredReps = 16;
                mRequiredSets = 4;
                break;
            case "Strength":
                mRequiredReps = 5;
                mRequiredSets = 5;
                break;
            case "Muscle-gain":
                mRequiredReps = 8;
                mRequiredSets = 6;
                break;
        }
    }

    public void getExerciseList(Context context) {
        JsonHandler jsonHandler = new JsonHandler();
        mExerciseList = jsonHandler.getAllExercises(context);
    }

    public Routine getSplit(String jsonString) {
        Gson gson = new Gson();
        Type routineType = new TypeToken<Routine>(){}.getType();
        Routine routine = gson.fromJson(jsonString, routineType);
        return routine;
    }

    public void buildPPL(Context context) {
        JsonHandler jsonHandler = new JsonHandler();
        String ppl = jsonHandler.loadJSONFromAsset("ppl.json", context);
        Routine routine = getSplit(ppl);
        routine.setDays(populateRoutine(routine));
        mRoutine = routine;
    }

    public void build5Day(Context context) {
        JsonHandler jsonHandler = new JsonHandler();
        String fiveDay = jsonHandler.loadJSONFromAsset("5day.json", context);
        Routine routine = getSplit(fiveDay);
        routine.setDays(populateRoutine(routine));
        mRoutine = routine;
    }

    public void buildUpperLower(Context context) {
        JsonHandler jsonHandler = new JsonHandler();
        String upperlower = jsonHandler.loadJSONFromAsset("upperlower.json", context);
        Routine routine = getSplit(upperlower);
        routine.setDays(populateRoutine(routine));
        mRoutine = routine;
    }

    public ArrayList<Day> populateRoutine(Routine routine) {
        Random rand = new Random();
        // Get object of each day
        ArrayList<Day> days = routine.getDays();
        for (int i = 0; i < days.size(); i++) {
            // Set reps and sets for each day
            days.get(i).setReps(mRequiredReps);
            days.get(i).setSets(mRequiredSets);
            // Populate each day with exercises
            ArrayList<Exercise> exercises = new ArrayList<>();
            for (int j = 0; j < EXERCISES_PER_WORKOUT; j++) {
                // Choose random exercise from exercise list
                exercises.add(mExerciseList.get(rand.nextInt(mExerciseList.size())));
            }
            days.get(i).setExercises(exercises);
        }
        return days;
    }

    public Routine getRoutine() {
        return mRoutine;
    }
}
