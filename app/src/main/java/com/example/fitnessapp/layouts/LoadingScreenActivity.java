package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.Workout;
import com.example.fitnessapp.util.WorkoutGenerator;

public class LoadingScreenActivity extends AppCompatActivity {
    private Workout mWorkout;
    private Routine mRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        getWorkoutObj();

        /*
        EasySplashScreen config = new EasySplashScreen(LoadingScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(WorkoutActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#373737"))
                .withLogo(R.drawable.ic_fitness_bot);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
        */
    }

    private void getWorkoutObj() {
        mWorkout = (Workout) getIntent().getSerializableExtra("MyClass");
        if (mWorkout != null) {
            generateWorkout(getRoutineId());
        }
    }

    private int getRoutineId() {
        // Run python script to see which cluster user falls into
        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("workout_ai");
        PyObject obj = pyObj.callAttr("get_group", mWorkout.getGender(), mWorkout.getExperience(), mWorkout.getGoal());
        int routine = obj.toInt();
        return routine;
    }

    private void generateWorkout(int routineId) {
        WorkoutGenerator workoutGenerator = new WorkoutGenerator(mWorkout, routineId, getApplicationContext());
        mRoutine = workoutGenerator.getRoutine();
    }
}