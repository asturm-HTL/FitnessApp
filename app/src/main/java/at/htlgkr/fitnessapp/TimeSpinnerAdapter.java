package at.htlgkr.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class TimeSpinnerAdapter extends ArrayAdapter<TimeList>
{

    public TimeSpinnerAdapter(Context context, ArrayList<TimeList> timeList)
    {
        super(context, 0, timeList);


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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.timespinner_row, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.times);

        TimeList currentItem = getItem(position);
        if(currentItem != null)
        {
            textViewName.setText(currentItem.getTime());
        }

        return convertView;
    }

}
