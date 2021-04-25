package com.example.fitnessapp.util;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.models.Workout;

public class WorkoutGenerator implements Runnable {
    private Python mPy;
    private Workout mWorkout;

    public WorkoutGenerator(Context context, Workout workout) {
        initPython(context);
        mPy = Python.getInstance();
        mWorkout = workout;
    }

    @Override
    public void run() {

    }

    private void initPython(Context context) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    }

    private int getWorkoutGroup(int gender, int experience, int goal) {
        PyObject pyObj = mPy.getModule("workout_ai");
        PyObject obj = pyObj.callAttr("get_group", mW, experience, goal);
        return obj.toInt();
    }
}
