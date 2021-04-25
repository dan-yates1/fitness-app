package com.example.fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Routine;

import java.util.ArrayList;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>{
    private RoutineAdapter.OnItemClickListener mListener;
    private ArrayList<Day> mRoutineList;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RoutineAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class RoutineViewHolder extends RecyclerView.ViewHolder {
        private TextView mRoutineName, mNumExercises, mDay;

        public RoutineViewHolder(@NonNull View itemView, RoutineAdapter.OnItemClickListener listener) {
            super(itemView);
            mRoutineName = itemView.findViewById(R.id.splitName);
            mNumExercises = itemView.findViewById(R.id.numExercises);
            mDay = itemView.findViewById(R.id.dayName);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public RoutineAdapter(ArrayList<Day> routineList) {
        mRoutineList = routineList;
    }

    @NonNull
    @Override
    public RoutineAdapter.RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        RoutineAdapter.RoutineViewHolder evh = new RoutineAdapter.RoutineViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        Day currentItem = mRoutineList.get(position);
        holder.mRoutineName.setText(currentItem.getName());
        holder.mNumExercises.setText(currentItem.getExercises().size() + " Exercises");
        holder.mDay.setText("DAY " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return mRoutineList.size();
    }
}
