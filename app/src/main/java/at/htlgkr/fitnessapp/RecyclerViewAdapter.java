package at.htlgkr.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    ArrayList exercises;
    ArrayList sets;
    ArrayList reps;

    private OnExerciseListener mOnExerciseListener;

    Context context;

    public RecyclerViewAdapter(Context ct, ArrayList exercises, ArrayList sets, ArrayList reps, OnExerciseListener onExerciseListener)
    {
        this.context = ct;
        this.exercises = exercises;
        this.sets = sets;
        this.reps = reps;
        this.mOnExerciseListener = onExerciseListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.recylerview_row, parent, false);
        return new MyViewHolder(view, mOnExerciseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        holder.exercisesRV.setText(exercises.get(position).toString());
        holder.setsRV.setText(sets.get(position).toString());
        holder.repsRV.setText(reps.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView exercisesRV, setsRV, repsRV;
        OnExerciseListener onExerciseListener;
        public MyViewHolder(@NonNull View itemView, OnExerciseListener onExerciseListener)
        {
            super(itemView);

            exercisesRV = itemView.findViewById(R.id.exercisesRV);
            setsRV = itemView.findViewById(R.id.setsRV);
            repsRV = itemView.findViewById(R.id.repsRV);
            this.onExerciseListener = onExerciseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            onExerciseListener.onExerciseClick(getAdapterPosition());
        }
    }

    public interface OnExerciseListener
    {
        void onExerciseClick(int position);
    }

}

/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    ArrayList exercises;
    ArrayList sets;
    ArrayList reps;


    Context context;

    public RecyclerViewAdapter(Context ct, ArrayList exercises, ArrayList sets, ArrayList reps)
    {
        this.context = ct;
        this.exercises = exercises;
        this.sets = sets;
        this.reps = reps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.recylerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        holder.exercisesRV.setText(exercises.get(position).toString());
        holder.setsRV.setText(sets.get(position).toString());
        holder.repsRV.setText(reps.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView exercisesRV, setsRV, repsRV;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            exercisesRV = itemView.findViewById(R.id.exercisesRV);
            setsRV = itemView.findViewById(R.id.setsRV);
            repsRV = itemView.findViewById(R.id.repsRV);

        }


    }

}*/
