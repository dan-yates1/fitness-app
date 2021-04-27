package com.example.fitnessapp.util;

import com.example.fitnessapp.models.Workout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FirestoreHandler {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private String mUserId;
    private String mJsonWorkout;

    public FirestoreHandler() {
    }

}
