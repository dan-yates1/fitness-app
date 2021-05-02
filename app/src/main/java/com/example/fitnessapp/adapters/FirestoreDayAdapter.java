package com.example.fitnessapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Routine;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FirestoreDayAdapter extends FirestoreRecyclerAdapter<Day, FirestoreDayAdapter.DayHolder> {

    public FirestoreDayAdapter(@NonNull FirestoreRecyclerOptions<Day> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DayHolder holder, int position, @NonNull Day model) {
        holder.dayTitle.setText("DAY " + position);
        holder.workoutTitle.setText(model.getName().toUpperCase());
        holder.numExercises.setText(model.getExercises().size() + " Exercises");
    }

    @NonNull
    @Override
    public DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day,
                parent, false);
        return new DayHolder(v);
    }

    @Override
    public void onError(@NonNull FirebaseFirestoreException e) {
        Log.e("error", e.getMessage());
    }

    class DayHolder extends RecyclerView.ViewHolder {
        TextView dayTitle, workoutTitle, numExercises;

        public DayHolder(View itemView) {
            super(itemView);
            dayTitle = itemView.findViewById(R.id.dayName);
            workoutTitle = itemView.findViewById(R.id.splitName);
            numExercises = itemView.findViewById(R.id.numExercises);
        }
    }
}
