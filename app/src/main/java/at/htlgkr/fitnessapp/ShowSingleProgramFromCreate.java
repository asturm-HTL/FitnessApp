package at.htlgkr.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ShowSingleProgramFromCreate extends AppCompatActivity {

    private static final int RQ_WRITE_STORAGE = 12345;
    public static String counter = null;
    public static String experience = null;
    public static String opportunity = null;

    //----------------6, 5 or 3 times a week-------------------
    public static String pushone = null;
    public static String pushtwo = null;
    public static String pullone = null;
    public static String pulltwo = null;
    public static String legsone = null;
    public static String legstwo = null;


    //----------------4 or 2 times a week-------------------
    public static String upperbodyone = null;
    public static String upperbodytwo = null;
    public static String lowerbodyone = null;
    public static String lowerbodytwo = null;

    //----------------1 time a week-------------------
    public static String fullbodyone;

    //----------------exercises 6,5,3-------------------
    public static ArrayList pullexercises1 = new ArrayList();
    public static ArrayList pullreps1 = new ArrayList();
    public static ArrayList pullsets1 = new ArrayList();

    public static ArrayList pushexercises1 = new ArrayList();
    public static ArrayList pushreps1 = new ArrayList();
    public static ArrayList pushsets1 = new ArrayList();

    public static ArrayList legsexercises1 = new ArrayList();
    public static ArrayList legsreps1 = new ArrayList();
    public static ArrayList legssets1 = new ArrayList();

    public static ArrayList pullexercises2 = new ArrayList();
    public static ArrayList pullreps2 = new ArrayList();
    public static ArrayList pullsets2 = new ArrayList();

    public static ArrayList pushexercises2 = new ArrayList();
    public static ArrayList pushreps2 = new ArrayList();
    public static ArrayList pushsets2 = new ArrayList();

    public static ArrayList legsexercises2 = new ArrayList();
    public static ArrayList legsreps2 = new ArrayList();
    public static ArrayList legssets2 = new ArrayList();


    //----------------exercises 4,2-------------------
    public static String upperbodyexercise1 = null;
    public static String upperbodyexercise1reps = null;
    public static String upperbodyexercise2 = null;
    public static String upperbodyexercise2reps = null;
    public static String lowerbodyexercise1 = null;
    public static String lowerbodyexercise1reps = null;
    public static String lowerbodyexercise2 = null;
    public static String lowerbodyexercise2reps = null;

    //----------------exercises 1-------------------
    public static String fullbodyexercise1 = null;
    public static String fullbodyexercise1reps = null;

    public ArrayList beforeSplitList = new ArrayList();

    public ArrayList orderdExercisesPull1 = new ArrayList();
    public ArrayList orderdSetsPull1 = new ArrayList();
    public ArrayList orderdRepsPull1 = new ArrayList();

    public ArrayList orderdExercisesPull2 = new ArrayList();
    public ArrayList orderdSetsPull2 = new ArrayList();
    public ArrayList orderdRepsPull2 = new ArrayList();

    public ArrayList orderdExercisesPush1 = new ArrayList();
    public ArrayList orderdSetsPush1 = new ArrayList();
    public ArrayList orderdRepsPush1 = new ArrayList();

    public ArrayList orderdExercisesPush2 = new ArrayList();
    public ArrayList orderdSetsPush2 = new ArrayList();
    public ArrayList orderdRepsPush2 = new ArrayList();

    public ArrayList orderdExercisesLegs1 = new ArrayList();
    public ArrayList orderdSetsLegs1 = new ArrayList();
    public ArrayList orderdRepsLegs1 = new ArrayList();

    public ArrayList orderdExercisesLegs2 = new ArrayList();
    public ArrayList orderdSetsLegs2 = new ArrayList();
    public ArrayList orderdRepsLegs2 = new ArrayList();

    public RecyclerView recyclerViewPull1;
    public RecyclerViewAdapter rvadaptPull1;

    public RecyclerView recyclerViewPull2;
    public RecyclerViewAdapter rvadaptPull2;

    public RecyclerView recyclerViewPush1;
    public RecyclerViewAdapter rvadaptPush1;

    public RecyclerView recyclerViewPush2;
    public RecyclerViewAdapter rvadaptPush2;

    public RecyclerView recyclerViewLegs1;
    public RecyclerViewAdapter rvadaptLegs1;

    public RecyclerView recyclerViewLegs2;
    public RecyclerViewAdapter rvadaptLegs2;

    public TextView programNameTV;
    public Button saveProgramButton;

    public TextView pull1tv;
    public TextView pull2tv;
    public TextView push1tv;
    public TextView push2tv;
    public TextView legs1tv;
    public TextView legs2tv;

    public String programName = "";
    public String programSaveString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_program);

        initLists();

    }

    public void initLists()
    {
        if (counter == "6" && opportunity == "Gym" && experience == "Advanced")
        {
            programName = "Push-Pull-Legs";

        //-------------------------------------Pull-------------------------------------
        String pulloneStr = pullone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pullParts1 = pulloneStr.split(";");

        for (int i = 0; i < pullParts1.length; i++)
        {
            String[] pullParts2 = pullParts1[i].split("\\+");
            String[] pullParts3 = pullParts2[1].split("x");
            pullexercises1.add(pullParts2[0].toString());
            pullsets1.add(pullParts3[0].toString());
            pullreps1.add(pullParts3[1].toString());
        }

        String pulltwoStr = pulltwo.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pulltwoParts1 = pulltwoStr.split(";");

        for (int i = 0; i < pulltwoParts1.length; i++)
        {
            String[] pulltwoParts2 = pullParts1[i].split("\\+");
            String[] pulltwoParts3 = pulltwoParts2[1].split("x");
            pullexercises2.add(pulltwoParts2[0].toString());
            pullsets2.add(pulltwoParts3[0].toString());
            pullreps2.add(pulltwoParts3[1].toString());
        }

        //-------------------------------------Push-------------------------------------
        String pushoneStr = pushone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pushParts1 = pushoneStr.split(";");

        for (int i = 0; i < pushParts1.length; i++)
        {
            String[] pushParts2 = pushParts1[i].split("\\+");
            String[] pushParts3 = pushParts2[1].split("x");
            pushexercises1.add(pushParts2[0].toString());
            pushsets1.add(pushParts3[0].toString());
            pushreps1.add(pushParts3[1].toString());
        }

        String pushtwoStr = pushtwo.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pushtwoParts1 = pushtwoStr.split(";");

        for (int i = 0; i < pushtwoParts1.length; i++)
        {
            String[] pushtwoParts2 = pushParts1[i].split("\\+");
            String[] pushtwoParts3 = pushtwoParts2[1].split("x");
            pushexercises2.add(pushtwoParts2[0].toString());
            pushsets2.add(pushtwoParts3[0].toString());
            pushreps2.add(pushtwoParts3[1].toString());
        }

        //-------------------------------------Legs-------------------------------------
        String legsoneStr = legsone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] legsParts1 = legsoneStr.split(";");

        for (int i = 0; i < legsParts1.length; i++)
        {
            String[] legsParts2 = legsParts1[i].split("\\+");
            String[] legsParts3 = legsParts2[1].split("x");
            legsexercises1.add(legsParts2[0].toString());
            legssets1.add(legsParts3[0].toString());
            legsreps1.add(legsParts3[1].toString());
        }

        String legstwoStr = legstwo.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] legstwoParts1 = legstwoStr.split(";");

        for (int i = 0; i < legstwoParts1.length; i++)
        {
            String[] legstwoParts2 = legsParts1[i].split("\\+");
            String[] legstwoParts3 = legstwoParts2[1].split("x");
            legsexercises2.add(legstwoParts2[0].toString());
            legssets2.add(legstwoParts3[0].toString());
            legsreps2.add(legstwoParts3[1].toString());
        }

            pull1tv = findViewById(R.id.pull1tv);
            pull2tv = findViewById(R.id.pull2tv);
            push1tv = findViewById(R.id.push1tv);
            push2tv = findViewById(R.id.push2tv);
            legs1tv = findViewById(R.id.legs1tv);
            legs2tv = findViewById(R.id.legs2tv);

            pull1tv.setText("Pull - Day 1");
            pull2tv.setText("Pull - Day 4");
            push1tv.setText("Push - Day 2");
            push2tv.setText("Push - Day 5");
            legs1tv.setText("Legs - Day 3");
            legs2tv.setText("Legs - Day 6");

            recyclerViewPull1 = findViewById(R.id.recyclerviewpull1);
            rvadaptPull1 = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
            recyclerViewPull1.setAdapter(rvadaptPull1);
            recyclerViewPull1.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2 = findViewById(R.id.recyclerviewpull2);
            rvadaptPull2 = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2);
            recyclerViewPull2.setAdapter(rvadaptPull2);
            recyclerViewPull2.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1 = findViewById(R.id.recyclerviewpush1);
            rvadaptPush1 = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
            recyclerViewPush1.setAdapter(rvadaptPush1);
            recyclerViewPush1.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush2 = findViewById(R.id.recyclerviewpush2);
            rvadaptPush2 = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2);
            recyclerViewPush2.setAdapter(rvadaptPush2);
            recyclerViewPush2.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1 = findViewById(R.id.recyclerviewlegs1);
            rvadaptLegs1 = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
            recyclerViewLegs1.setAdapter(rvadaptLegs1);
            recyclerViewLegs1.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs2 = findViewById(R.id.recyclerviewlegs2);
            rvadaptLegs2 = new RecyclerViewAdapter(this, orderdExercisesLegs2, orderdSetsLegs2, orderdRepsLegs2);
            recyclerViewLegs2.setAdapter(rvadaptLegs2);
            recyclerViewLegs2.setLayoutManager(new LinearLayoutManager(this));

            orderdExercisesPull1.add("EXERCISE");
            orderdSetsPull1.add("SETS");
            orderdRepsPull1.add("REPS");

            orderdExercisesPull2.add("EXERCISE");
            orderdSetsPull2.add("SETS");
            orderdRepsPull2.add("REPS");

            orderdExercisesPush1.add("EXERCISE");
            orderdSetsPush1.add("SETS");
            orderdRepsPush1.add("REPS");

            orderdExercisesPush2.add("EXERCISE");
            orderdSetsPush2.add("SETS");
            orderdRepsPush2.add("REPS");

            orderdExercisesLegs1.add("EXERCISE");
            orderdSetsLegs1.add("SETS");
            orderdRepsLegs1.add("REPS");

            orderdExercisesLegs2.add("EXERCISE");
            orderdSetsLegs2.add("SETS");
            orderdRepsLegs2.add("REPS");

            for(int x = 0; x < 7; x++)
            {
                if(!pullexercises1.get(x).equals(null))
                {
                    orderdExercisesPull1.add(pullexercises1.get(x));
                    orderdSetsPull1.add(pullsets1.get(x));
                    orderdRepsPull1.add(pullreps1.get(x));
                }
            }

            for(int x = 0; x < 7; x++)
            {
                if(!pullexercises2.get(x).equals(null))
                {
                    orderdExercisesPull2.add(pullexercises2.get(x));
                    orderdSetsPull2.add(pullsets2.get(x));
                    orderdRepsPull2.add(pullreps2.get(x));
                }
            }

            for(int x = 0; x < 8; x++)
            {
                if(!pushexercises1.get(x).equals(null))
                {
                    orderdExercisesPush1.add(pushexercises1.get(x));
                    orderdSetsPush1.add(pushsets1.get(x));
                    orderdRepsPush1.add(pushreps1.get(x));
                }
            }

            for(int x = 0; x < 8; x++)
            {
                if(!pushexercises2.get(x).equals(null))
                {
                    orderdExercisesPush2.add(pushexercises2.get(x));
                    orderdSetsPush2.add(pushsets2.get(x));
                    orderdRepsPush2.add(pushreps2.get(x));
                }
            }

            for(int x = 0; x < 8; x++)
            {
                if(!legsexercises1.get(x).equals(null))
                {
                    orderdExercisesLegs1.add(legsexercises1.get(x));
                    orderdSetsLegs1.add(legssets1.get(x));
                    orderdRepsLegs1.add(legsreps1.get(x));
                }
            }

            for(int x = 0; x < 8; x++)
            {
                if(!legsexercises2.get(x).equals(null))
                {
                    orderdExercisesLegs2.add(legsexercises2.get(x));
                    orderdSetsLegs2.add(legssets2.get(x));
                    orderdRepsLegs2.add(legsreps2.get(x));
                }
            }

            programSaveString = "6" + "|" + programName + "|" + orderdExercisesPull1 + "|" + orderdSetsPull1 + "|" + orderdRepsPull1 + "|" + orderdExercisesPull2 + "|" + orderdSetsPull2 + "|" + orderdRepsPull2 + "|" + orderdExercisesPush1 + "|" + orderdSetsPush1 + "|" + orderdRepsPush1 + "|" + orderdExercisesPush2 + "|" + orderdSetsPush2 + "|" + orderdRepsPush2 + "|" + orderdExercisesLegs1 + "|" + orderdSetsLegs1 + "|" + orderdRepsLegs1 + "|" + orderdExercisesLegs2 + "|" + orderdSetsLegs2 + "|" + orderdRepsLegs2;


        }
    else if (counter == "5" && opportunity == "Gym" && experience == "Advanced")
    {
        programName = "Push-Pull-Legs";

        //-------------------------------------Pull-------------------------------------
        String pulloneStr = pullone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pullParts1 = pulloneStr.split(";");

        for (int i = 0; i < pullParts1.length; i++)
        {
            String[] pullParts2 = pullParts1[i].split("\\+");
            String[] pullParts3 = pullParts2[1].split("x");
            pullexercises1.add(pullParts2[0].toString());
            pullsets1.add(pullParts3[0].toString());
            pullreps1.add(pullParts3[1].toString());
        }

        String pulltwoStr = pulltwo.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pulltwoParts1 = pulltwoStr.split(";");

        for (int i = 0; i < pulltwoParts1.length; i++)
        {
            String[] pulltwoParts2 = pullParts1[i].split("\\+");
            String[] pulltwoParts3 = pulltwoParts2[1].split("x");
            pullexercises2.add(pulltwoParts2[0].toString());
            pullsets2.add(pulltwoParts3[0].toString());
            pullreps2.add(pulltwoParts3[1].toString());
        }

        //-------------------------------------Push-------------------------------------
        String pushoneStr = pushone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pushParts1 = pushoneStr.split(";");

        for (int i = 0; i < pushParts1.length; i++)
        {
            String[] pushParts2 = pushParts1[i].split("\\+");
            String[] pushParts3 = pushParts2[1].split("x");
            pushexercises1.add(pushParts2[0].toString());
            pushsets1.add(pushParts3[0].toString());
            pushreps1.add(pushParts3[1].toString());
        }

        String pushtwoStr = pushtwo.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pushtwoParts1 = pushtwoStr.split(";");

        for (int i = 0; i < pushtwoParts1.length; i++)
        {
            String[] pushtwoParts2 = pushParts1[i].split("\\+");
            String[] pushtwoParts3 = pushtwoParts2[1].split("x");
            pushexercises2.add(pushtwoParts2[0].toString());
            pushsets2.add(pushtwoParts3[0].toString());
            pushreps2.add(pushtwoParts3[1].toString());
        }

        //-------------------------------------Legs-------------------------------------
        String legsoneStr = legsone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] legsParts1 = legsoneStr.split(";");

        for (int i = 0; i < legsParts1.length; i++)
        {
            String[] legsParts2 = legsParts1[i].split("\\+");
            String[] legsParts3 = legsParts2[1].split("x");
            legsexercises1.add(legsParts2[0].toString());
            legssets1.add(legsParts3[0].toString());
            legsreps1.add(legsParts3[1].toString());
        }


        pull1tv = findViewById(R.id.pull1tv);
        pull2tv = findViewById(R.id.pull2tv);
        push1tv = findViewById(R.id.push1tv);
        push2tv = findViewById(R.id.push2tv);
        legs1tv = findViewById(R.id.legs1tv);


        pull1tv.setText("Pull - Day 1");
        pull2tv.setText("Pull - Day 4");
        push1tv.setText("Push - Day 2");
        push2tv.setText("Push - Day 5");
        legs1tv.setText("Legs - Day 3");


        recyclerViewPull1 = findViewById(R.id.recyclerviewpull1);
        rvadaptPull1 = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
        recyclerViewPull1.setAdapter(rvadaptPull1);
        recyclerViewPull1.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPull2 = findViewById(R.id.recyclerviewpull2);
        rvadaptPull2 = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2);
        recyclerViewPull2.setAdapter(rvadaptPull2);
        recyclerViewPull2.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPush1 = findViewById(R.id.recyclerviewpush1);
        rvadaptPush1 = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
        recyclerViewPush1.setAdapter(rvadaptPush1);
        recyclerViewPush1.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPush2 = findViewById(R.id.recyclerviewpush2);
        rvadaptPush2 = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2);
        recyclerViewPush2.setAdapter(rvadaptPush2);
        recyclerViewPush2.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewLegs1 = findViewById(R.id.recyclerviewlegs1);
        rvadaptLegs1 = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
        recyclerViewLegs1.setAdapter(rvadaptLegs1);
        recyclerViewLegs1.setLayoutManager(new LinearLayoutManager(this));


        orderdExercisesPull1.add("EXERCISE");
        orderdSetsPull1.add("SETS");
        orderdRepsPull1.add("REPS");

        orderdExercisesPull2.add("EXERCISE");
        orderdSetsPull2.add("SETS");
        orderdRepsPull2.add("REPS");

        orderdExercisesPush1.add("EXERCISE");
        orderdSetsPush1.add("SETS");
        orderdRepsPush1.add("REPS");

        orderdExercisesPush2.add("EXERCISE");
        orderdSetsPush2.add("SETS");
        orderdRepsPush2.add("REPS");

        orderdExercisesLegs1.add("EXERCISE");
        orderdSetsLegs1.add("SETS");
        orderdRepsLegs1.add("REPS");

        for(int x = 0; x < 7; x++)
        {
            if(!pullexercises1.get(x).equals(null))
            {
                orderdExercisesPull1.add(pullexercises1.get(x));
                orderdSetsPull1.add(pullsets1.get(x));
                orderdRepsPull1.add(pullreps1.get(x));
            }
        }

        for(int x = 0; x < 7; x++)
        {
            if(!pullexercises2.get(x).equals(null))
            {
                orderdExercisesPull2.add(pullexercises2.get(x));
                orderdSetsPull2.add(pullsets2.get(x));
                orderdRepsPull2.add(pullreps2.get(x));
            }
        }

        for(int x = 0; x < 8; x++)
        {
            if(!pushexercises1.get(x).equals(null))
            {
                orderdExercisesPush1.add(pushexercises1.get(x));
                orderdSetsPush1.add(pushsets1.get(x));
                orderdRepsPush1.add(pushreps1.get(x));
            }
        }

        for(int x = 0; x < 8; x++)
        {
            if(!pushexercises2.get(x).equals(null))
            {
                orderdExercisesPush2.add(pushexercises2.get(x));
                orderdSetsPush2.add(pushsets2.get(x));
                orderdRepsPush2.add(pushreps2.get(x));
            }
        }

        for(int x = 0; x < 8; x++)
        {
            if(!legsexercises1.get(x).equals(null))
            {
                orderdExercisesLegs1.add(legsexercises1.get(x));
                orderdSetsLegs1.add(legssets1.get(x));
                orderdRepsLegs1.add(legsreps1.get(x));
            }
        }

        programSaveString = "5" + "|" + programName + "|" + orderdExercisesPull1 + "|" + orderdSetsPull1 + "|" + orderdRepsPull1 + "|" + orderdExercisesPull2 + "|" + orderdSetsPull2 + "|" + orderdRepsPull2 + "|" + orderdExercisesPush1 + "|" + orderdSetsPush1 + "|" + orderdRepsPush1 + "|" + orderdExercisesPush2 + "|" + orderdSetsPush2 + "|" + orderdRepsPush2 + "|" + orderdExercisesLegs1 + "|" + orderdSetsLegs1 + "|" + orderdRepsLegs1;


    }
    else if (counter == "3" && opportunity == "Gym" && experience == "Advanced")
    {
        programName = "Push-Pull-Legs";

        //-------------------------------------Pull-------------------------------------
        String pulloneStr = pullone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pullParts1 = pulloneStr.split(";");

        for (int i = 0; i < pullParts1.length; i++)
        {
            String[] pullParts2 = pullParts1[i].split("\\+");
            String[] pullParts3 = pullParts2[1].split("x");
            pullexercises1.add(pullParts2[0].toString());
            pullsets1.add(pullParts3[0].toString());
            pullreps1.add(pullParts3[1].toString());
        }

        //-------------------------------------Push-------------------------------------
        String pushoneStr = pushone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] pushParts1 = pushoneStr.split(";");

        for (int i = 0; i < pushParts1.length; i++)
        {
            String[] pushParts2 = pushParts1[i].split("\\+");
            String[] pushParts3 = pushParts2[1].split("x");
            pushexercises1.add(pushParts2[0].toString());
            pushsets1.add(pushParts3[0].toString());
            pushreps1.add(pushParts3[1].toString());
        }

        //-------------------------------------Legs-------------------------------------
        String legsoneStr = legsone.replace("weekone", "").replace("\"", "").replace(":", "");
        String[] legsParts1 = legsoneStr.split(";");

        for (int i = 0; i < legsParts1.length; i++)
        {
            String[] legsParts2 = legsParts1[i].split("\\+");
            String[] legsParts3 = legsParts2[1].split("x");
            legsexercises1.add(legsParts2[0].toString());
            legssets1.add(legsParts3[0].toString());
            legsreps1.add(legsParts3[1].toString());
        }

        pull1tv = findViewById(R.id.pull1tv);
        push1tv = findViewById(R.id.push1tv);
        legs1tv = findViewById(R.id.legs1tv);

        pull1tv.setText("Pull - Day 1");
        push1tv.setText("Push - Day 2");
        legs1tv.setText("Legs - Day 3");


        recyclerViewPull1 = findViewById(R.id.recyclerviewpull1);
        rvadaptPull1 = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1);
        recyclerViewPull1.setAdapter(rvadaptPull1);
        recyclerViewPull1.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPush1 = findViewById(R.id.recyclerviewpush1);
        rvadaptPush1 = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1);
        recyclerViewPush1.setAdapter(rvadaptPush1);
        recyclerViewPush1.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewLegs1 = findViewById(R.id.recyclerviewlegs1);
        rvadaptLegs1 = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1);
        recyclerViewLegs1.setAdapter(rvadaptLegs1);
        recyclerViewLegs1.setLayoutManager(new LinearLayoutManager(this));

        orderdExercisesPull1.add("EXERCISE");
        orderdSetsPull1.add("SETS");
        orderdRepsPull1.add("REPS");

        orderdExercisesPush1.add("EXERCISE");
        orderdSetsPush1.add("SETS");
        orderdRepsPush1.add("REPS");

        orderdExercisesLegs1.add("EXERCISE");
        orderdSetsLegs1.add("SETS");
        orderdRepsLegs1.add("REPS");

        for(int x = 0; x < 7; x++)
        {
            if(!pullexercises1.get(x).equals(null))
            {
                orderdExercisesPull1.add(pullexercises1.get(x));
                orderdSetsPull1.add(pullsets1.get(x));
                orderdRepsPull1.add(pullreps1.get(x));
            }
        }

        for(int x = 0; x < 8; x++)
        {
            if(!pushexercises1.get(x).equals(null))
            {
                orderdExercisesPush1.add(pushexercises1.get(x));
                orderdSetsPush1.add(pushsets1.get(x));
                orderdRepsPush1.add(pushreps1.get(x));
            }
        }

        for(int x = 0; x < 8; x++)
        {
            if(!legsexercises1.get(x).equals(null))
            {
                orderdExercisesLegs1.add(legsexercises1.get(x));
                orderdSetsLegs1.add(legssets1.get(x));
                orderdRepsLegs1.add(legsreps1.get(x));
            }
        }

        programSaveString = "3" + "|" + programName + "|" + orderdExercisesPull1 + "|" + orderdSetsPull1 + "|" + orderdRepsPull1 + "|" + orderdExercisesPush1 + "|" + orderdSetsPush1 + "|" + orderdRepsPush1 + "|" + orderdExercisesLegs1 + "|" + orderdSetsLegs1 + "|" + orderdRepsLegs1;


    }

        programNameTV = findViewById(R.id.programnameTV);
        programNameTV.setText(programName);

        saveProgramButton = findViewById(R.id.saveProgramBtn);
        saveProgramButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                saveProgram(v);

            }
        });



        //TODO----implement 4 2 and 1 times a week
    }

    private void showSnackbar()
    {
        ScrollView scrollLinearLayout =  findViewById(R.id.scrollLinearLayout);
        Snackbar snackbar = Snackbar.make(scrollLinearLayout, "Workout-Program saved!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //--------------------------------------SD-Card--------------------------------------

    private void writeToFile(String filename)
    {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + filename;
        Log.d("TAG", "filename: " + fullPath);

        try
        {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fullPath)));
            out.print(programSaveString);
            out.flush();
            out.close();
            showSnackbar();
        }
        catch (Exception e)
        {
            Log.e("TAG", e.getLocalizedMessage());
        }
    }

    public void saveProgram(View view)
    {
        Log.d("TAG", "entered printInput");

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, RQ_WRITE_STORAGE);
        }
        else
        {
            writeToFile("SavedPrograms");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==RQ_WRITE_STORAGE)
        {
            if (grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED)
            {
                Log.d("TAG", "SD Card Permission denied");
                Toast.makeText(this, "SD Car Permission wurde verweigert!", Toast.LENGTH_LONG).show();
            }
            else
            {
                writeToFile("SavedPrograms");
            }
        }
    }

}
