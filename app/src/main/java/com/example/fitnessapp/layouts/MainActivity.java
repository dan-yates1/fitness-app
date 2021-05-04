package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.DaysList;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.Workout;
import com.example.fitnessapp.util.WorkoutGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import gr.net.maroulis.library.EasySplashScreen;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mButton;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String userId = fAuth.getUid();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        setUpInterface();
        getRoutine();

        EasySplashScreen config = new EasySplashScreen(MainActivity.this)
                .withFullScreen()
                .withTargetActivity(WorkoutActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#373737"))
                .withLogo(R.drawable.ic_fitness_bot);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }

    private void setUpInterface() {
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            getRoutine();
        });
    }

    public void getRoutine() {
        docRef = db.collection("users").document(userId)
                .collection("workout").document(userId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                Workout workout = documentSnapshot.toObject(Workout.class);
                int routineId = 1; //getRoutineId(workout);
                Routine routine = generateRoutine(workout, routineId);
                addRoutineToFirestore(routine);
            }
        });
    }

    private int getRoutineId(Workout workout) {
        // Run python script to see which cluster user falls into
        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("workout_ai");
        PyObject obj = pyObj.callAttr("get_group", workout.getGender(), workout.getExperience(), workout.getGoal());
        int routine = obj.toInt();
        return routine;
    }

    private Routine generateRoutine(Workout workout, int routineId) {
        WorkoutGenerator workoutGenerator = new WorkoutGenerator(workout, routineId, getApplicationContext());
        return workoutGenerator.getRoutine();
    }

    private void addRoutineToFirestore(Routine routine) {
        docRef = db.collection("users").document(userId)
                .collection("routine").document(userId);

        docRef.set(routine).addOnSuccessListener(aVoid -> {
            Log.d(TAG, "onSuccess: Routine is created for" + userId);
        });
    }
}