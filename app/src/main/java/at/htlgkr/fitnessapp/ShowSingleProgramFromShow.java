package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowSingleProgramFromShow extends AppCompatActivity {
    public static String counterImport = "";
    public static String programNameImport = "";

    //---------------Exercises---------------
    public static ArrayList orderdExercisesPull1 = new ArrayList();
    public static ArrayList orderdExercisesPull2 = new ArrayList();
    public static ArrayList orderdExercisesPush1 = new ArrayList();
    public static ArrayList orderdExercisesPush2 = new ArrayList();
    public static ArrayList orderdExercisesLegs1 = new ArrayList();
    public static ArrayList orderdExercisesLegs2 = new ArrayList();

    public static ArrayList orderdExercisesUB1 = new ArrayList();
    public static ArrayList orderdExercisesUB2 = new ArrayList();
    public static ArrayList orderdExercisesLB1 = new ArrayList();
    public static ArrayList orderdExercisesLB2 = new ArrayList();

    public static ArrayList orderdExercisesFB1 = new ArrayList();

    //---------------Sets---------------
    public static ArrayList orderdSetsPull1 = new ArrayList();
    public static ArrayList orderdSetsPull2 = new ArrayList();
    public static ArrayList orderdSetsPush1 = new ArrayList();
    public static ArrayList orderdSetsPush2 = new ArrayList();
    public static ArrayList orderdSetsLegs1 = new ArrayList();
    public static ArrayList orderdSetsLegs2 = new ArrayList();

    public static ArrayList orderdSetsUB1 = new ArrayList();
    public static ArrayList orderdSetsUB2 = new ArrayList();
    public static ArrayList orderdSetsLB1 = new ArrayList();
    public static ArrayList orderdSetsLB2 = new ArrayList();

    public static ArrayList orderdSetsFB1 = new ArrayList();

    //---------------Reps---------------
    public static ArrayList orderdRepsPull1 = new ArrayList();
    public static ArrayList orderdRepsPull2 = new ArrayList();
    public static ArrayList orderdRepsPush1 = new ArrayList();
    public static ArrayList orderdRepsPush2 = new ArrayList();
    public static ArrayList orderdRepsLegs1 = new ArrayList();
    public static ArrayList orderdRepsLegs2 = new ArrayList();

    public static ArrayList orderdRepsUB1 = new ArrayList();
    public static ArrayList orderdRepsUB2 = new ArrayList();
    public static ArrayList orderdRepsLB1 = new ArrayList();
    public static ArrayList orderdRepsLB2 = new ArrayList();

    public static ArrayList orderdRepsFB1 = new ArrayList();

    public TextView pull1tvS;
    public TextView pull2tvS;
    public TextView push1tvS;
    public TextView push2tvS;
    public TextView legs1tvS;
    public TextView legs2tvS;

    public RecyclerView recyclerViewPull1S;
    public RecyclerViewAdapter rvadaptPull1S;

    public RecyclerView recyclerViewPull2S;
    public RecyclerViewAdapter rvadaptPull2S;

    public RecyclerView recyclerViewPush1S;
    public RecyclerViewAdapter rvadaptPush1S;

    public RecyclerView recyclerViewPush2S;
    public RecyclerViewAdapter rvadaptPush2S;

    public RecyclerView recyclerViewLegs1S;
    public RecyclerViewAdapter rvadaptLegs1S;

    public RecyclerView recyclerViewLegs2S;
    public RecyclerViewAdapter rvadaptLegs2S;

    public TextView programNameTVS;
    public Button saveProgramButtonS;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_program_from_show);

        initLists();
    }

    public void initLists()
    {
        if (counterImport.toString().equals("6"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);
            pull2tvS = findViewById(R.id.pull2tvS);
            push1tvS = findViewById(R.id.push1tvS);
            push2tvS = findViewById(R.id.push2tvS);
            legs1tvS = findViewById(R.id.legs1tvS);
            legs2tvS = findViewById(R.id.legs2tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Pull - Day 1");
            pull2tvS.setText("Pull - Day 4");
            push1tvS.setText("Push - Day 2");
            push2tvS.setText("Push - Day 5");
            legs1tvS.setText("Legs - Day 3");
            legs2tvS.setText("Legs - Day 6");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush2S = findViewById(R.id.recyclerviewpush2S);
            rvadaptPush2S = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2);
            recyclerViewPush2S.setAdapter(rvadaptPush2S);
            recyclerViewPush2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
            recyclerViewLegs1S.setAdapter(rvadaptLegs1S);
            recyclerViewLegs1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs2S = findViewById(R.id.recyclerviewlegs2S);
            rvadaptLegs2S = new RecyclerViewAdapter(this, orderdExercisesLegs2, orderdSetsLegs2, orderdRepsLegs2);
            recyclerViewLegs2S.setAdapter(rvadaptLegs2S);
            recyclerViewLegs2S.setLayoutManager(new LinearLayoutManager(this));

        }
        else if (counterImport.toString().equals("5"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);
            pull2tvS = findViewById(R.id.pull2tvS);
            push1tvS = findViewById(R.id.push1tvS);
            push2tvS = findViewById(R.id.push2tvS);
            legs1tvS = findViewById(R.id.legs1tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Pull - Day 1");
            pull2tvS.setText("Pull - Day 4");
            push1tvS.setText("Push - Day 2");
            push2tvS.setText("Push - Day 5");
            legs1tvS.setText("Legs - Day 3");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush2S = findViewById(R.id.recyclerviewpush2S);
            rvadaptPush2S = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2);
            recyclerViewPush2S.setAdapter(rvadaptPush2S);
            recyclerViewPush2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
            recyclerViewLegs1S.setAdapter(rvadaptLegs1S);
            recyclerViewLegs1S.setLayoutManager(new LinearLayoutManager(this));
        }
        else if (counterImport.toString().equals("4"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);
            pull2tvS = findViewById(R.id.pull2tvS);
            push1tvS = findViewById(R.id.push1tvS);
            push2tvS = findViewById(R.id.push2tvS);
            legs1tvS = findViewById(R.id.legs1tvS);
            legs2tvS = findViewById(R.id.legs2tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Upperbody - Day 1");
            pull2tvS.setText("Lowerbody - Day 4");
            push1tvS.setText("Upperbody - Day 2");
            legs1tvS.setText("Lowerbody - Day 3");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesUB1, orderdSetsUB1, orderdRepsUB1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));


            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesLB1, orderdSetsLB1, orderdRepsLB1);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesUB2, orderdSetsUB2, orderdRepsUB2);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLB2, orderdSetsLB2, orderdRepsLB2);
            recyclerViewLegs1S.setAdapter(rvadaptLegs1S);
            recyclerViewLegs1S.setLayoutManager(new LinearLayoutManager(this));

        }
        else if (counterImport.toString().equals("3"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);
            push1tvS = findViewById(R.id.push1tvS);
            legs1tvS = findViewById(R.id.legs1tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Pull - Day 1");
            push1tvS.setText("Push - Day 2");
            legs1tvS.setText("Legs - Day 3");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
            recyclerViewLegs1S.setAdapter(rvadaptLegs1S);
            recyclerViewLegs1S.setLayoutManager(new LinearLayoutManager(this));
        }
        else if (counterImport.toString().equals("2"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);
            pull2tvS = findViewById(R.id.pull2tvS);
            push1tvS = findViewById(R.id.push1tvS);
            push2tvS = findViewById(R.id.push2tvS);
            legs1tvS = findViewById(R.id.legs1tvS);
            legs2tvS = findViewById(R.id.legs2tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Upperbody - Day 1");
            push1tvS.setText("Lowerbody - Day 2");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesUB1, orderdSetsUB1, orderdRepsUB1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesLB1, orderdSetsLB1, orderdRepsLB1);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));



        }
        else if (counterImport.toString().equals("1"))
        {
            programNameTVS = findViewById(R.id.programnameTVS);
            pull1tvS = findViewById(R.id.pull1tvS);

            programNameTVS.setText(programNameImport);
            pull1tvS.setText("Fullbody - Day 1");

            recyclerViewPull1S = findViewById(R.id.recyclerviewpull1S);
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesFB1, orderdSetsFB1, orderdRepsFB1);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
