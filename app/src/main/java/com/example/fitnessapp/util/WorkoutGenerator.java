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
import java.util.Collections;
import java.util.Random;

public class WorkoutGenerator {
    private static final int EXERCISES_PER_WORKOUT = 2;
    private Routine mRoutine;
    private Workout mWorkout;
    private int mRoutineId;
    private int mRequiredReps;
    private int mRequiredSets;
    private ArrayList<Exercise> mExerciseList;

    public WorkoutGenerator(Workout workout, int routineId, Context context) {
        mWorkout = workout;
        mRoutineId = routineId;
        mExerciseList = getExerciseList(context);
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
                mRequiredReps = 8;
                mRequiredSets = 4;
                break;
            case "Fitness":
                mRequiredReps = 20;
                mRequiredSets = 2;
                break;
            case "Strength":
                mRequiredReps = 5;
                mRequiredSets = 3;
                break;
            case "Muscle-gain":
                mRequiredReps = 12;
                mRequiredSets = 4;
                break;
        }
    }

    public ArrayList<Exercise> getExerciseList(Context context) {
        JsonHandler jsonHandler = new JsonHandler();
        return jsonHandler.getAllExercises(context);
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
        // Loop through each day and assign exercises
        ArrayList<Day> days = routine.getDays();// days.size() == mWorkout.getAvailability()
        for (int i = 0; i < days.size(); i++) {
            Day day = getExerciseForDay(days.get(i));
            day.setSets(mRequiredSets);
            day.setReps(mRequiredReps);
            days.set(i, day);
        }
        return days;
    }

    private ArrayList<Day> removeDays(ArrayList<Day> days) {
        ArrayList<Day> dayList = days;
        return dayList;
    }

    private ArrayList<Day> addDays(ArrayList<Day> days) {
        ArrayList<Day> newDayList = new ArrayList<>();
        int daysRequired = days.size() - mWorkout.getAvailability();
        for (int i = 0; i > daysRequired; i++) {
            newDayList.add(days.get(i));
        }
        return newDayList;
    }

    public Routine getRoutine() {
        return mRoutine;
    }

    private boolean isExerciseSuitable(Day day, Exercise exercise) {
        boolean suitable = false;
        // Check if exercise matches target muscles
        ArrayList<String> targetMuscles = day.getMuscles();
        for (int i = 0; i < targetMuscles.size(); i++) {
            if (exercise.getAllMuscles().contains(targetMuscles.get(i))) {
                suitable = true;
                break;
            }
        }
        return suitable;
    }

    private boolean doesMatchEquipment(Exercise exercise) {
        boolean match = false;
        ArrayList<String> equipmentList = mWorkout.getEquipment();
        for (int i = 0; i < equipmentList.size(); i++) {
            if(exercise.getEquipment().contains(equipmentList.get(i))) {
                match = true;
            }
        }
        return match;
    }

    private Day getExerciseForDay(Day day) {
        Day today = day;
        Random rand = new Random();
        ArrayList<Exercise> todayExercises = new ArrayList<>();
        int exerciseCount = 0;
        while (exerciseCount < EXERCISES_PER_WORKOUT) {
            int randInt = rand.nextInt(mExerciseList.size());
            Exercise exercise = mExerciseList.get(randInt);
            if (isExerciseSuitable(today, exercise) && !todayExercises.contains(exercise) && doesMatchEquipment(exercise)) {
                todayExercises.add(exercise);
                exerciseCount++;
            }
        }
        today.setExercises(todayExercises);
        return today;
    }
}
