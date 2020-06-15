package at.htlgkr.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewShowProgramsAdapter extends RecyclerView.Adapter<RecyclerViewShowProgramsAdapter.MyViewHolder>
{

    ArrayList programName;
    Context context;

    public RecyclerViewShowProgramsAdapter(Context ct, ArrayList programName)
    {
        this.context = ct;
        this.programName = programName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.recyclerviewshowprograms_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        holder.programNamesTV.setText(programName.get(position).toString());

    }

    @Override
    public int getItemCount()
    {
        return programName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView programNamesTV;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            programNamesTV = itemView.findViewById(R.id.programNameTV);


        }
    }
}
