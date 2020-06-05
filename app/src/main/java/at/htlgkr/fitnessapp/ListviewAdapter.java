package at.htlgkr.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListviewAdapter extends BaseAdapter
{

    private ArrayList exercises = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public ListviewAdapter(Context ctx, int layoutId, ArrayList exercises)
    {
        this.exercises = exercises;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return exercises.size();
    }

    @Override
    public Object getItem(int i)
    {
        return exercises.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        Object exercise = exercises.get(i);
        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;
        ((TextView) listItem.findViewById(R.id.txt_exname)).setText(exercise.toString());
        return listItem;
    }
}


