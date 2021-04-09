package com.example.fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> mExerciseList;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView mExerciseName, mMuscleGroups;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            mExerciseName = itemView.findViewById(R.id.exerciseName);
            mMuscleGroups = itemView.findViewById(R.id.muscleGroups);
        }
    }

    public ExerciseAdapter(ArrayList<Exercise> exerciseList) {
        mExerciseList = exerciseList;
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
        holder.mExerciseName.setText(currentItem.getName());

        if (currentItem.getPrimaryMuscles().size() > 1) {
            holder.mMuscleGroups.setText(currentItem.getPrimaryMuscles().get(0));
            for (int i = 1; i < currentItem.getPrimaryMuscles().size(); i++) {
                holder.mMuscleGroups.append(", " + currentItem.getPrimaryMuscles().get(i));
            }
        } else if (currentItem.getPrimaryMuscles().size() == 1) {
            holder.mMuscleGroups.setText(currentItem.getPrimaryMuscles().get(0));
        }
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
