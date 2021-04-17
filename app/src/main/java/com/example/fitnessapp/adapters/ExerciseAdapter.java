package com.example.fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> mExerciseList;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private final TextView mExerciseName;
        private final TextView mMuscleGroups;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            mExerciseName = itemView.findViewById(R.id.exerciseName);
            mMuscleGroups = itemView.findViewById(R.id.muscleGroups);
        }
    }

    public ExerciseAdapter(ArrayList<Exercise> exerciseList) {
        this.mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        ExerciseViewHolder evh = new ExerciseViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise currentItem = mExerciseList.get(position);
        holder.mExerciseName.setText(currentItem.getName().toUpperCase());
        // Display all muscles on CardView
        ArrayList<String> allMuscles = currentItem.getAllMuscles();
        String musclesStr = "";
        if (allMuscles.size() > 1) {
            musclesStr = allMuscles.get(0);
            for (int i = 1; i < allMuscles.size(); i++) {
                musclesStr += (" | " + allMuscles.get(i));
            }
        } else if (allMuscles.size() == 1) {
            musclesStr = allMuscles.get(0);
        }else {
        }
        holder.mMuscleGroups.setText(musclesStr);
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    public void filterList(ArrayList<Exercise> filteredList) {
        mExerciseList = filteredList;
        notifyDataSetChanged();
    }
}
