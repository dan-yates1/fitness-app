package com.example.fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.models.Day;
import com.example.fitnessapp.models.Exercise;

import java.util.ArrayList;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.DayViewHolder> {
    private ArrayList<Exercise> mExerciseList;
    private Day mDay;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onHelpClick(int position);
    }

    public void setOnItemClickListener(SetAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView mSetName;
        private CheckBox mCheckBox;
        private ImageButton mHelpButton;

        public DayViewHolder(@NonNull View itemView, SetAdapter.OnItemClickListener listener) {
            super(itemView);
            mSetName = itemView.findViewById(R.id.setName);
            mCheckBox = itemView.findViewById(R.id.checkBox2);
            mHelpButton = itemView.findViewById(R.id.helpButton);

            mHelpButton.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onHelpClick(position);
                    }
                }
            });
        }
    }

    public SetAdapter(ArrayList<Exercise> exerciseList, Day day) {
        mDay = day;
        mExerciseList = day.getExercises();
    }

    @NonNull
    @Override
    public SetAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set, parent, false);
        SetAdapter.DayViewHolder evh = new SetAdapter.DayViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull SetAdapter.DayViewHolder holder, int position) {
        Exercise currentItem = mExerciseList.get(position);
        holder.mSetName.setText(currentItem.getName());
        holder.mCheckBox.setText(mDay.getSets() + " X " + mDay.getReps());
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}
