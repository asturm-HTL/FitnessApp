package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdvancedView extends AppCompatActivity
{

    private ArrayList<MuscleAnatomy> muscleAnatomyList;
    private MuscleAdapter muscleAdapter;
    public ListView listViewAdvanced;
    public ArrayList exercisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_advanced_view);

        exercisesList = new ArrayList();

        initList();
        listViewAdvanced = findViewById(R.id.listViewAdvanced);
        ListviewAdapter arradapt = new ListviewAdapter(this, R.layout.listview_row, exercisesList);

        listViewAdvanced.setAdapter(arradapt);

                exercisesList.add("Test");
                exercisesList.add("Test2");
                exercisesList.add("Test3");
                exercisesList.add("Test4");
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
                Toast.makeText(ExercisesAdvancedView.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();
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
        muscleAnatomyList.add(new MuscleAnatomy("Lower Chest", R.drawable.lowerchestanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Upper Chest", R.drawable.upperchestanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Anterior Shoulder", R.drawable.anteriorshoulderanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Lateral Shoulder", R.drawable.lateralshoulderanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Posterior Shoulder", R.drawable.posteriorshoulderanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Neck", R.drawable.neckanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Back", R.drawable.backanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Lower Back", R.drawable.lowerbackanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Quads", R.drawable.quadsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Adductors", R.drawable.adductorsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Abductors", R.drawable.abductorsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Hamstrings", R.drawable.hamstringsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Booty", R.drawable.bootyanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Calves", R.drawable.calfanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Sixpack", R.drawable.sixpackanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Obliques", R.drawable.obliquesanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Biceps", R.drawable.bicepsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Triceps", R.drawable.tricepsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Forearms", R.drawable.forearmanatomy));



    }

}
