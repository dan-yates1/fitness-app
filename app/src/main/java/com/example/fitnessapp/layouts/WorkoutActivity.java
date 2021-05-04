package com.example.fitnessapp.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.DayAdapter;
import com.example.fitnessapp.adapters.FirestoreDayAdapter;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.DaysList;
import com.example.fitnessapp.models.Routine;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WorkoutActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNav;
    private ArrayList<Day> mDaysList;
    private RecyclerView mRecyclerView;

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userId = mAuth.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        buildNavBar();
        buildRecyclerView();
        getDaysList(); // Gets days from firestore and builds recycler view
    }

    private void getDaysList() {
        CollectionReference routineRef = mDb.collection("users")
                .document(userId).collection("routine");
        DocumentReference daysIdRef = routineRef.document(userId);
        daysIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                if (doc.exists()) {
                    List<Day> days = doc.toObject(DaysList.class).days;
                    mDaysList = (ArrayList<Day>) days;
                    DayAdapter adapter = new DayAdapter(mDaysList);
                    mRecyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(position -> {
                        startActivity(new Intent(getApplicationContext(), WorkoutDetailsActivity.class)
                                .putExtra("day", mDaysList.get(position)));
                    });
                }
            }
        });
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // Create temporary adapter
        DayAdapter adapter = new DayAdapter(new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_workout);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_exercises:
                    startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    break;
                case R.id.nav_workout:
                    break;
            }
            return false;
        });
    }
}