package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.Workout;
import com.example.fitnessapp.util.WorkoutGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNav;
    private Button mButton;
    private TextView mTextView;
    private Workout mWorkout;
    private Routine mRoutine;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        setUpInterface();
        buildNavBar();
    }

    private void setUpInterface() {
        mTextView = findViewById(R.id.textView2);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
        });
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_home);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_exercises:
                    startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
                case R.id.nav_workout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    break;
            }
            return false;
        });
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
        addRoutineToFirestore(mRoutine);
    }

    private void addRoutineToFirestore(Routine mRoutine) {
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        Gson gson = new Gson();
        String json = gson.toJson(mRoutine);

        DocumentReference docRef = fStore
                .collection("users").document(userId);
        // Populate hash map with data
        Map<String, Object> routineMap = new HashMap<>();
        routineMap.put("routine", json);
        docRef.set(routineMap).addOnSuccessListener(aVoid -> {
            Log.d(TAG, "onSuccess: Routine is created for" + userId);
        });
    }
}