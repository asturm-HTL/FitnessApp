package at.htlgkr.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MuscleAdapter extends ArrayAdapter<MuscleAnatomy>
{

    public MuscleAdapter(Context context, ArrayList<MuscleAnatomy> muscleList)
    {
        super(context, 0, muscleList);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.anatomy_spinner_row, parent, false);
        }

        ImageView imageViewMuscles = convertView.findViewById(R.id.muscleImageView);
        TextView textViewName = convertView.findViewById(R.id.musclenames);

        MuscleAnatomy currentItem = getItem(position);
        if(currentItem != null)
        {
            imageViewMuscles.setImageResource(currentItem.getAnatomyImage());
            textViewName.setText(currentItem.getMuscleName());
        }

        return convertView;
    }
}
