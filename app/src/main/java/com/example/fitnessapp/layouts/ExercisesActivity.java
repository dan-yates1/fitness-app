package com.example.fitnessapp.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ExerciseAdapter;
import com.example.fitnessapp.models.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private Button mBicepsBtn, mTricepsBtn, mChestBtn, mQuadsBtn, mShouldersBtn, mBackBtn, mAllBtn, mHamsBtn;
    private EditText mSearchBar;
    private ArrayList<Exercise> mExerciseList;
    private ArrayList<String> mMusclesFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        buildFilterButtons();
        buildExerciseList();
        buildRecyclerView();
        buildSearchBar();
    }

    private void buildSearchBar() {
        mSearchBar = findViewById(R.id.searchEditText);
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterByName(s.toString());
            }
        });
    }

    private void filterByName(String text) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise item : mExerciseList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void filterByMuscle(String text) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise item : mExerciseList) {
            if (item.getAllMuscles().contains(text)) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void buildFilterButtons() {
        mMusclesFilter = new ArrayList<>();
        mBicepsBtn = findViewById(R.id.bicepsBtn);
        mTricepsBtn = findViewById(R.id.tricepsBtn);
        mChestBtn = findViewById(R.id.chestBtn);
        mQuadsBtn = findViewById(R.id.quadsBtn);
        mHamsBtn = findViewById(R.id.hamstringsBtn);
        mShouldersBtn = findViewById(R.id.shouldersBtn);
        mBackBtn = findViewById(R.id.backBtn);
        mAllBtn = findViewById(R.id.allBtn);

        mBicepsBtn.setOnClickListener(v -> {
            filterByMuscle("Biceps");
        });

        mTricepsBtn.setOnClickListener(v -> {
            filterByMuscle("Triceps");
        });

        mChestBtn.setOnClickListener(v -> {
            filterByMuscle("Chest");
        });

        mBackBtn.setOnClickListener(v -> {
            filterByMuscle("Back");
        });

        mShouldersBtn.setOnClickListener(v -> {
            filterByMuscle("Shoulders");
        });

        mQuadsBtn.setOnClickListener(v -> {
            filterByMuscle("Quads");
        });

        mHamsBtn.setOnClickListener(v -> {
            filterByMuscle("Hamstrings");
        });

        mAllBtn.setOnClickListener(v -> {
            mAdapter.filterList(mExerciseList);
        });
    }

    private void buildExerciseList() {
        // Parse list of exercises from JSON file located in assets folder
        mExerciseList = new ArrayList<>();
        Gson gson = new Gson();
        Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();
        mExerciseList = gson.fromJson(loadJSONFromAsset("exercises.json"), exerciseListType);
    }

    public String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExerciseAdapter(mExerciseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}