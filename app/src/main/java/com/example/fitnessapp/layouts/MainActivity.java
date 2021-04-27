package com.example.fitnessapp.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Routine;
import com.example.fitnessapp.models.Workout;
import com.example.fitnessapp.util.WorkoutGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNav;
    private Button mButton;
    private TextView mTextView;
    private ProgressBar mProgressBar;

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
        buildNavBar();
    }

    private void setUpInterface() {
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        mTextView = findViewById(R.id.textView2);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            mProgressBar.setVisibility(View.VISIBLE);
            getRoutine();
            mProgressBar.setVisibility(View.GONE);
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

    public void getRoutine() {
        docRef = db.collection("users").document(userId)
                .collection("workout").document(userId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                Workout workout = documentSnapshot.toObject(Workout.class);
                int routineId = 0; //getRoutineId(workout);
                Routine routine = generateRoutine(workout, routineId);
                addRoutineToFirestore(routine);
            }
        });
    }

    public void convertToObj() {
        Gson gson = new Gson();
        Type workoutType = new TypeToken<Workout>(){}.getType();
        //Workout workout = gson.fromJson(json, workoutType);
        //mWorkout = workout;
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