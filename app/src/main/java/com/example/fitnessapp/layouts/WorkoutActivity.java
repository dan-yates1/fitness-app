package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.adapters.RoutineAdapter;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Exercise;
import com.example.fitnessapp.models.Routine;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    private ArrayList<Day> mRoutineList;
    BottomNavigationView mBottomNav;
    private RecyclerView mRecyclerView;
    private RoutineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        buildNavBar();
        buildRoutineList();
        buildRecyclerView();
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RoutineAdapter(mRoutineList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> startActivity(new Intent(getApplicationContext(),
                WorkoutDetailsActivity.class)
                .putExtra("routine", mRoutineList.get(position))));
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_workout);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
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

    private String loadJSONFromAsset(String fileName) {
        // Read JSON file into string
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, Charset.defaultCharset());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void buildRoutineList() {
        // Parse list of exercises from JSON file located in assets folder
        mRoutineList = new ArrayList<>();
        Gson gson = new Gson();
        Type exerciseListType = new TypeToken<Routine>(){}.getType();
        Routine routine = gson.fromJson(loadJSONFromAsset("routines.json"), exerciseListType);
        mRoutineList = routine.getDays();
    }
}