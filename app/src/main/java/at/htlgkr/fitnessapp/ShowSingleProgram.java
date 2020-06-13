package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSingleProgram extends AppCompatActivity {

    public static String counter = null;

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
    public ArrayList orderdExercises = new ArrayList();
    public ArrayList orderdSets = new ArrayList();
    public ArrayList orderdReps = new ArrayList();


    /*
    public ListView exercisesLV;
    public ListView setsLV;
    public ListView repsLV;

    public ArrayAdapter exAdapt;
    public ArrayAdapter seAdapt;
    public ArrayAdapter reAdapt;

    public ListView exercisesLVPush;
    public ListView setsLVPush;
    public ListView repsLVPush;

    public ArrayAdapter exAdaptPush;
    public ArrayAdapter seAdaptPush;
    public ArrayAdapter reAdaptPush;
    */

    RecyclerView recyclerViewTest;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_program);

        System.out.println("PENIS_SSP_" + pullone + " " + pulltwo + " " + pushone + " " + pushtwo + " " + legsone + " " + legstwo);

        initLists();

        recyclerViewTest = findViewById(R.id.recyclerviewtest);
        RecyclerViewAdapter rvadapt = new RecyclerViewAdapter(this, orderdExercises, orderdSets, orderdReps);
        recyclerViewTest.setAdapter(rvadapt);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initLists()
    {
        if (counter == "6")
    {
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
    }
    else if (counter == "5")
    {
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
    }
    else if (counter == "3")
    {
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
    }

    /*
        exercisesLV = findViewById(R.id.exercisesSSP);
        setsLV = findViewById(R.id.setsSSP);
        repsLV = findViewById(R.id.repsSSP);
        exAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdExercises);
        seAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdSets);
        reAdapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdReps);

        exercisesLV.setAdapter(exAdapt);
        setsLV.setAdapter(seAdapt);
        repsLV.setAdapter(reAdapt);

        exercisesLVPush = findViewById(R.id.exercisesSSPPush);
        setsLVPush = findViewById(R.id.setsSSPPush);
        repsLVPush = findViewById(R.id.repsSSPPush);
        exAdaptPush = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdExercises);
        seAdaptPush = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdSets);
        reAdaptPush = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orderdReps);

        exercisesLVPush.setAdapter(exAdapt);
        setsLVPush.setAdapter(seAdapt);
        repsLVPush.setAdapter(reAdapt);*/

    //TODO----implement 4 2 and 1 times a week

        orderdExercises.add("EXERCISES");
        orderdSets.add("SETS");
        orderdReps.add("REPS");

        for(int x = 0; x < 2; x++)
        {
            if(!pullexercises1.get(x).equals(null))
            {
                orderdExercises.add(pullexercises1.get(x));
                orderdSets.add(pullsets1.get(x));
                orderdReps.add(pullreps1.get(x));
            }
            if(!pushexercises1.get(x).equals(null))
            {
                orderdExercises.add(pushexercises1.get(x));
                orderdSets.add(pushsets1.get(x));
                orderdReps.add(pushreps1.get(x));
            }
            if(!legsexercises1.get(x).equals(null))
            {
                orderdExercises.add(legsexercises1.get(x));
                orderdSets.add(legssets1.get(x));
                orderdReps.add(legsreps1.get(x));
            }

            //TODO--implement seperate LVs for push pull and Legs

        }

    }
}
