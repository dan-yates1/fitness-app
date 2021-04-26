package com.example.fitnessapp.util;

import android.content.Context;
import android.widget.Toast;

import com.example.fitnessapp.models.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class JsonHandler {

    public JsonHandler() {
    }

    public String loadJSONFromAsset(String fileName, Context context) {
        // Read JSON file into string
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
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

    public ArrayList<Exercise> getAllExercises(Context context) {
        // Cast JSON to exercise object type
        Gson gson = new Gson();
        Type exerciseListType = new TypeToken<ArrayList<Exercise>>(){}.getType();
        ArrayList<Exercise> exercises = gson.fromJson(loadJSONFromAsset("exercises.json", context), exerciseListType);
        return exercises;
    }
}
