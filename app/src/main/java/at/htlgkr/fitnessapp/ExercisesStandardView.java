package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ExercisesStandardView extends AppCompatActivity
{

    private ArrayList<MuscleAnatomy>muscleAnatomyList;
    private MuscleAdapter muscleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_standard_view);

        initList();

        Spinner spinnerMuscles = findViewById(R.id.spinnerAnatomy);

        muscleAdapter = new MuscleAdapter(this, muscleAnatomyList);
        spinnerMuscles.setAdapter(muscleAdapter);

        spinnerMuscles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                MuscleAnatomy muscleAnatomyClicked = (MuscleAnatomy) parent.getItemAtPosition(position);
                String clickedMuscleName = muscleAnatomyClicked.getMuscleName();
                Toast.makeText(ExercisesStandardView.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private  void initList()
    {
        muscleAnatomyList = new ArrayList<>();
        muscleAnatomyList.add(new MuscleAnatomy("Chest", R.drawable.chestanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Back", R.drawable.fullbackanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Core", R.drawable.coreanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Legs", R.drawable.legsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Arms", R.drawable.armsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Shoulders", R.drawable.shoulderanatomy));
    }
}
