package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShowPrograms extends AppCompatActivity
{

    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //---------------------------MainVariables-End-----------------------------

    public String stringReadFromSDCard;

    public ArrayList listFromSplit = new ArrayList();

    public RecyclerView recyclerViewProgramNames;
    public RecyclerViewShowProgramsAdapter rvadaptProgramNames;


    //if any list is not needed just ignore it
    public ArrayList programNames = new ArrayList();

    public String counterExport ="";
    public String programNameExport = "";

    //---------------Exercises---------------
    public ArrayList orderdExercisesPull1 = new ArrayList();
    public ArrayList orderdExercisesPull2 = new ArrayList();
    public ArrayList orderdExercisesPush1 = new ArrayList();
    public ArrayList orderdExercisesPush2 = new ArrayList();
    public ArrayList orderdExercisesLegs1 = new ArrayList();
    public ArrayList orderdExercisesLegs2 = new ArrayList();

    public ArrayList orderdExercisesUB1 = new ArrayList();
    public ArrayList orderdExercisesUB2 = new ArrayList();
    public ArrayList orderdExercisesLB1 = new ArrayList();
    public ArrayList orderdExercisesLB2 = new ArrayList();

    public ArrayList orderdExercisesFB1 = new ArrayList();

    //---------------Sets---------------
    public ArrayList orderdSetsPull1 = new ArrayList();
    public ArrayList orderdSetsPull2 = new ArrayList();
    public ArrayList orderdSetsPush1 = new ArrayList();
    public ArrayList orderdSetsPush2 = new ArrayList();
    public ArrayList orderdSetsLegs1 = new ArrayList();
    public ArrayList orderdSetsLegs2 = new ArrayList();

    public ArrayList orderdSetsUB1 = new ArrayList();
    public ArrayList orderdSetsUB2 = new ArrayList();
    public ArrayList orderdSetsLB1 = new ArrayList();
    public ArrayList orderdSetsLB2 = new ArrayList();

    public ArrayList orderdSetsFB1 = new ArrayList();

    //---------------Reps---------------
    public ArrayList orderdRepsPull1 = new ArrayList();
    public ArrayList orderdRepsPull2 = new ArrayList();
    public ArrayList orderdRepsPush1 = new ArrayList();
    public ArrayList orderdRepsPush2 = new ArrayList();
    public ArrayList orderdRepsLegs1 = new ArrayList();
    public ArrayList orderdRepsLegs2 = new ArrayList();

    public ArrayList orderdRepsUB1 = new ArrayList();
    public ArrayList orderdRepsUB2 = new ArrayList();
    public ArrayList orderdRepsLB1 = new ArrayList();
    public ArrayList orderdRepsLB2 = new ArrayList();

    public ArrayList orderdRepsFB1 = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_programs);

        //-------DEBUG---------
        System.out.println("MOIS_SP_id_"+id);
        System.out.println("MOIS_SP_firstname_"+firstname);
        System.out.println("MOIS_SP_lastname_"+lastname);
        System.out.println("MOIS_SP_username_"+username);
        System.out.println("MOIS_SP_password_"+password);
        //-----DEBUG-End------


        readFromSDCard();

        recyclerViewProgramNames = findViewById(R.id.programsRV);
        rvadaptProgramNames = new RecyclerViewShowProgramsAdapter(this, programNames);
        recyclerViewProgramNames.setAdapter(rvadaptProgramNames);
        recyclerViewProgramNames.setLayoutManager(new LinearLayoutManager(this));


        recyclerViewProgramNames.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewProgramNames, new RecyclerTouchListener.ClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {

                String programName = programNames.get(position).toString();

                for(int x = 0; x < listFromSplit.size(); x++)
                {
                    if(listFromSplit.get(x).toString().contains(programName))
                    {
                        String[] listfromsplitsplit1 = listFromSplit.get(x).toString().split("\\|");


                        ShowSingleProgramFromShow.deleteStringFromSP = listFromSplit.get(x).toString();


                        if(listfromsplitsplit1[0].toString().equals("6"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------Pull1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for (int y = 0; y < strforsplit1split.length; y++) {
                                orderdExercisesPull1.add(strforsplit1split[y]);
                            }

                            //---------Pull1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for (int y = 0; y < strforsplit2split.length; y++) {
                                orderdSetsPull1.add(strforsplit2split[y]);
                            }

                            //---------Pull1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for (int q = 0; q < strforsplit3split.length; q++) {
                                orderdRepsPull1.add(strforsplit3split[q]);
                            }

                            //---------Push1EX---------
                            String strforsplit4 = listfromsplitsplit1[5].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit4split = strforsplit4.split(",");

                            for (int q = 0; q < strforsplit4split.length; q++) {
                                orderdExercisesPush1.add(strforsplit4split[q]);
                            }

                            //---------Push1SE---------
                            String strforsplit5 = listfromsplitsplit1[6].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit5split = strforsplit5.split(",");

                            for (int y = 0; y < strforsplit5split.length; y++) {
                                orderdSetsPush1.add(strforsplit5split[y]);
                            }

                            //---------Push1RE---------
                            String strforsplit6 = listfromsplitsplit1[7].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit6split = strforsplit6.split(",");

                            for (int q = 0; q < strforsplit6split.length; q++) {
                                orderdRepsPush1.add(strforsplit6split[q]);
                            }

                            //---------Legs1EX---------
                            String strforsplit7 = listfromsplitsplit1[8].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit7split = strforsplit7.split(",");

                            for (int q = 0; q < strforsplit7split.length; q++) {
                                orderdExercisesLegs1.add(strforsplit7split[q]);
                            }

                            //---------Legs1SE---------
                            String strforsplit8 = listfromsplitsplit1[9].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit8split = strforsplit8.split(",");

                            for (int y = 0; y < strforsplit8split.length; y++) {
                                orderdSetsLegs1.add(strforsplit8split[y]);
                            }

                            //---------Legs1RE---------
                            String strforsplit9 = listfromsplitsplit1[10].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit9split = strforsplit9.split(",");

                            for (int q = 0; q < strforsplit9split.length; q++) {
                                orderdRepsLegs1.add(strforsplit9split[q]);
                            }

                            //----------------------------------------------------------------------

                            //---------Pull2EX---------
                            String strforsplit10 = listfromsplitsplit1[11].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit10split = strforsplit10.split(",");

                            for (int y = 0; y < strforsplit10split.length; y++) {
                                orderdExercisesPull2.add(strforsplit10split[y]);
                            }

                            //---------Pull2SE---------
                            String strforsplit11 = listfromsplitsplit1[12].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit11split = strforsplit11.split(",");

                            for (int y = 0; y < strforsplit11split.length; y++) {
                                orderdSetsPull2.add(strforsplit11split[y]);
                            }

                            //---------Pull2RE---------
                            String strforsplit12 = listfromsplitsplit1[13].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit12split = strforsplit12.split(",");

                            for (int q = 0; q < strforsplit12split.length; q++) {
                                orderdRepsPull2.add(strforsplit12split[q]);
                            }

                            //---------Push2EX---------
                            String strforsplit13 = listfromsplitsplit1[14].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit13split = strforsplit13.split(",");

                            for (int q = 0; q < strforsplit13split.length; q++) {
                                orderdExercisesPush2.add(strforsplit13split[q]);
                            }

                            //---------Push2SE---------
                            String strforsplit14 = listfromsplitsplit1[15].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit14split = strforsplit14.split(",");

                            for (int y = 0; y < strforsplit14split.length; y++) {
                                orderdSetsPush2.add(strforsplit14split[y]);
                            }

                            //---------Push2RE---------
                            String strforsplit15 = listfromsplitsplit1[16].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit15split = strforsplit15.split(",");

                            for (int q = 0; q < strforsplit15split.length; q++) {
                                orderdRepsPush2.add(strforsplit15split[q]);
                            }

                            //---------Legs2EX---------
                            String strforsplit16 = listfromsplitsplit1[17].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit16split = strforsplit16.split(",");

                            for (int q = 0; q < strforsplit16split.length; q++) {
                                orderdExercisesLegs2.add(strforsplit16split[q]);
                            }

                            //---------Legs2SE---------
                            String strforsplit17 = listfromsplitsplit1[18].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit17split = strforsplit17.split(",");

                            for (int y = 0; y < strforsplit17split.length; y++) {
                                orderdSetsLegs2.add(strforsplit17split[y]);
                            }

                            //---------Legs2RE---------
                            String strforsplit18 = listfromsplitsplit1[19].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit18split = strforsplit18.split(",");

                            for (int q = 0; q < strforsplit18split.length; q++) {
                                orderdRepsLegs2.add(strforsplit18split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesPull1 = orderdExercisesPull1;
                            ShowSingleProgramFromShow.orderdExercisesPull2 = orderdExercisesPull2;
                            ShowSingleProgramFromShow.orderdExercisesPush1 = orderdExercisesPush1;
                            ShowSingleProgramFromShow.orderdExercisesPush2 = orderdExercisesPush2;
                            ShowSingleProgramFromShow.orderdExercisesLegs1 = orderdExercisesLegs1;
                            ShowSingleProgramFromShow.orderdExercisesLegs2 = orderdExercisesLegs2;
                            ShowSingleProgramFromShow.orderdSetsPull1 = orderdSetsPull1;
                            ShowSingleProgramFromShow.orderdSetsPull2 = orderdSetsPull2;
                            ShowSingleProgramFromShow.orderdSetsPush1 = orderdSetsPush1;
                            ShowSingleProgramFromShow.orderdSetsPush2 = orderdSetsPush2;
                            ShowSingleProgramFromShow.orderdSetsLegs1 = orderdSetsLegs1;
                            ShowSingleProgramFromShow.orderdSetsLegs2 = orderdSetsLegs2;
                            ShowSingleProgramFromShow.orderdRepsPull1 = orderdRepsPull1;
                            ShowSingleProgramFromShow.orderdRepsPull2 = orderdRepsPull2;
                            ShowSingleProgramFromShow.orderdRepsPush1 = orderdRepsPush1;
                            ShowSingleProgramFromShow.orderdRepsPush2 = orderdRepsPush2;
                            ShowSingleProgramFromShow.orderdRepsLegs1 = orderdRepsLegs1;
                            ShowSingleProgramFromShow.orderdRepsLegs2 = orderdRepsLegs2;
                            startShowSingleProgramFromShow();
                            break;
                        }
                        else if(listfromsplitsplit1[0].toString().equals("5"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------Pull1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for(int y = 0; y < strforsplit1split.length; y++)
                            {
                                orderdExercisesPull1.add(strforsplit1split[y]);
                            }

                            //---------Pull1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for(int y = 0; y < strforsplit2split.length; y++)
                            {
                                orderdSetsPull1.add(strforsplit2split[y]);
                            }

                            //---------Pull1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for(int q = 0; q < strforsplit3split.length; q++)
                            {
                                orderdRepsPull1.add(strforsplit3split[q]);
                            }

                            //---------Push1EX---------
                            String strforsplit4 = listfromsplitsplit1[5].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit4split = strforsplit4.split(",");

                            for(int q = 0; q < strforsplit4split.length; q++)
                            {
                                orderdExercisesPush1.add(strforsplit4split[q]);
                            }

                            //---------Push1SE---------
                            String strforsplit5 = listfromsplitsplit1[6].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit5split = strforsplit5.split(",");

                            for(int y = 0; y < strforsplit5split.length; y++)
                            {
                                orderdSetsPush1.add(strforsplit5split[y]);
                            }

                            //---------Push1RE---------
                            String strforsplit6 = listfromsplitsplit1[7].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit6split = strforsplit6.split(",");

                            for(int q = 0; q < strforsplit6split.length; q++)
                            {
                                orderdRepsPush1.add(strforsplit6split[q]);
                            }

                            //---------Legs1EX---------
                            String strforsplit7 = listfromsplitsplit1[8].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit7split = strforsplit7.split(",");

                            for(int q = 0; q < strforsplit7split.length; q++)
                            {
                                orderdExercisesLegs1.add(strforsplit7split[q]);
                            }

                            //---------Legs1SE---------
                            String strforsplit8 = listfromsplitsplit1[9].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit8split = strforsplit8.split(",");

                            for(int y = 0; y < strforsplit8split.length; y++)
                            {
                                orderdSetsLegs1.add(strforsplit8split[y]);
                            }

                            //---------Legs1RE---------
                            String strforsplit9 = listfromsplitsplit1[10].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit9split = strforsplit9.split(",");

                            for(int q = 0; q < strforsplit9split.length; q++)
                            {
                                orderdRepsLegs1.add(strforsplit9split[q]);
                            }

                            //----------------------------------------------------------------------

                            //---------Pull2EX---------
                            String strforsplit10 = listfromsplitsplit1[11].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit10split = strforsplit10.split(",");

                            for(int y = 0; y < strforsplit10split.length; y++)
                            {
                                orderdExercisesPull2.add(strforsplit10split[y]);
                            }

                            //---------Pull2SE---------
                            String strforsplit11 = listfromsplitsplit1[12].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit11split = strforsplit11.split(",");

                            for(int y = 0; y < strforsplit11split.length; y++)
                            {
                                orderdSetsPull2.add(strforsplit11split[y]);
                            }

                            //---------Pull2RE---------
                            String strforsplit12 = listfromsplitsplit1[13].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit12split = strforsplit12.split(",");

                            for(int q = 0; q < strforsplit12split.length; q++)
                            {
                                orderdRepsPull2.add(strforsplit12split[q]);
                            }

                            //---------Push2EX---------
                            String strforsplit13 = listfromsplitsplit1[14].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit13split = strforsplit13.split(",");

                            for(int q = 0; q < strforsplit13split.length; q++)
                            {
                                orderdExercisesPush2.add(strforsplit13split[q]);
                            }

                            //---------Push2SE---------
                            String strforsplit14 = listfromsplitsplit1[15].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit14split = strforsplit14.split(",");

                            for(int y = 0; y < strforsplit14split.length; y++)
                            {
                                orderdSetsPush2.add(strforsplit14split[y]);
                            }

                            //---------Push2RE---------
                            String strforsplit15 = listfromsplitsplit1[16].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit15split = strforsplit15.split(",");

                            for(int q = 0; q < strforsplit15split.length; q++)
                            {
                                orderdRepsPush2.add(strforsplit15split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesPull1 = orderdExercisesPull1;
                            ShowSingleProgramFromShow.orderdExercisesPull2 = orderdExercisesPull2;
                            ShowSingleProgramFromShow.orderdExercisesPush1 = orderdExercisesPush1;
                            ShowSingleProgramFromShow.orderdExercisesPush2 = orderdExercisesPush2;
                            ShowSingleProgramFromShow.orderdExercisesLegs1 = orderdExercisesLegs1;
                            ShowSingleProgramFromShow.orderdSetsPull1 = orderdSetsPull1;
                            ShowSingleProgramFromShow.orderdSetsPull2 = orderdSetsPull2;
                            ShowSingleProgramFromShow.orderdSetsPush1 = orderdSetsPush1;
                            ShowSingleProgramFromShow.orderdSetsPush2 = orderdSetsPush2;
                            ShowSingleProgramFromShow.orderdSetsLegs1 = orderdSetsLegs1;
                            ShowSingleProgramFromShow.orderdRepsPull1 = orderdRepsPull1;
                            ShowSingleProgramFromShow.orderdRepsPull2 = orderdRepsPull2;
                            ShowSingleProgramFromShow.orderdRepsPush1 = orderdRepsPush1;
                            ShowSingleProgramFromShow.orderdRepsPush2 = orderdRepsPush2;
                            ShowSingleProgramFromShow.orderdRepsLegs1 = orderdRepsLegs1;
                            startShowSingleProgramFromShow();
                            break;

                        }
                        else if(listfromsplitsplit1[0].toString().equals("4"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------UB1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for(int y = 0; y < strforsplit1split.length; y++)
                            {
                                orderdExercisesUB1.add(strforsplit1split[y]);
                            }

                            //---------UB1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for(int y = 0; y < strforsplit2split.length; y++)
                            {
                                orderdSetsUB1.add(strforsplit2split[y]);
                            }

                            //---------UB1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for(int q = 0; q < strforsplit3split.length; q++)
                            {
                                orderdRepsUB1.add(strforsplit3split[q]);
                            }

                            //---------LB1EX---------
                            String strforsplit4 = listfromsplitsplit1[5].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit4split = strforsplit4.split(",");

                            for(int q = 0; q < strforsplit4split.length; q++)
                            {
                                orderdExercisesLB1.add(strforsplit4split[q]);
                            }

                            //---------LB1SE---------
                            String strforsplit5 = listfromsplitsplit1[6].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit5split = strforsplit5.split(",");

                            for(int y = 0; y < strforsplit5split.length; y++)
                            {
                                orderdSetsLB1.add(strforsplit5split[y]);
                            }

                            //---------LB1RE---------
                            String strforsplit6 = listfromsplitsplit1[7].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit6split = strforsplit6.split(",");

                            for(int q = 0; q < strforsplit6split.length; q++)
                            {
                                orderdRepsLB1.add(strforsplit6split[q]);
                            }

                            //---------UB2EX---------
                            String strforsplit7 = listfromsplitsplit1[8].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit7split = strforsplit7.split(",");

                            for(int q = 0; q < strforsplit7split.length; q++)
                            {
                                orderdExercisesUB2.add(strforsplit7split[q]);
                            }

                            //---------UB1SE---------
                            String strforsplit8 = listfromsplitsplit1[9].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit8split = strforsplit8.split(",");

                            for(int y = 0; y < strforsplit8split.length; y++)
                            {
                                orderdSetsUB2.add(strforsplit8split[y]);
                            }

                            //---------UB1RE---------
                            String strforsplit9 = listfromsplitsplit1[10].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit9split = strforsplit9.split(",");

                            for(int q = 0; q < strforsplit9split.length; q++)
                            {
                                orderdRepsUB2.add(strforsplit9split[q]);
                            }

                            //---------LB2EX---------
                            String strforsplit10 = listfromsplitsplit1[11].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit10split = strforsplit10.split(",");

                            for(int y = 0; y < strforsplit10split.length; y++)
                            {
                                orderdExercisesLB2.add(strforsplit10split[y]);
                            }

                            //---------LB2SE---------
                            String strforsplit11 = listfromsplitsplit1[12].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit11split = strforsplit11.split(",");

                            for(int y = 0; y < strforsplit11split.length; y++)
                            {
                                orderdSetsLB2.add(strforsplit11split[y]);
                            }

                            //---------LB2RE---------
                            String strforsplit12 = listfromsplitsplit1[13].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit12split = strforsplit12.split(",");

                            for(int q = 0; q < strforsplit12split.length; q++)
                            {
                                orderdRepsLB2.add(strforsplit12split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesUB1 = orderdExercisesUB1;
                            ShowSingleProgramFromShow.orderdExercisesUB2 = orderdExercisesUB2;
                            ShowSingleProgramFromShow.orderdExercisesLB1 = orderdExercisesLB1;
                            ShowSingleProgramFromShow.orderdExercisesLB2 = orderdExercisesLB2;
                            ShowSingleProgramFromShow.orderdSetsUB1 = orderdSetsUB1;
                            ShowSingleProgramFromShow.orderdSetsUB2 = orderdSetsUB2;
                            ShowSingleProgramFromShow.orderdSetsLB1 = orderdSetsLB1;
                            ShowSingleProgramFromShow.orderdSetsLB2 = orderdSetsLB2;
                            ShowSingleProgramFromShow.orderdRepsUB1 = orderdRepsUB1;
                            ShowSingleProgramFromShow.orderdRepsUB2 = orderdRepsUB2;
                            ShowSingleProgramFromShow.orderdRepsLB1 = orderdRepsLB1;
                            ShowSingleProgramFromShow.orderdRepsLB2 = orderdRepsLB2;
                            startShowSingleProgramFromShow();
                            break;
                        }
                        else if(listfromsplitsplit1[0].toString().equals("3"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------Pull1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for(int y = 0; y < strforsplit1split.length; y++)
                            {
                                orderdExercisesPull1.add(strforsplit1split[y]);
                            }

                            //---------Pull1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for(int y = 0; y < strforsplit2split.length; y++)
                            {
                                orderdSetsPull1.add(strforsplit2split[y]);
                            }

                            //---------Pull1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for(int q = 0; q < strforsplit3split.length; q++)
                            {
                                orderdRepsPull1.add(strforsplit3split[q]);
                            }

                            //---------Push1EX---------
                            String strforsplit4 = listfromsplitsplit1[5].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit4split = strforsplit4.split(",");

                            for(int q = 0; q < strforsplit4split.length; q++)
                            {
                                orderdExercisesPush1.add(strforsplit4split[q]);
                            }

                            //---------Push1SE---------
                            String strforsplit5 = listfromsplitsplit1[6].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit5split = strforsplit5.split(",");

                            for(int y = 0; y < strforsplit5split.length; y++)
                            {
                                orderdSetsPush1.add(strforsplit5split[y]);
                            }

                            //---------Push1RE---------
                            String strforsplit6 = listfromsplitsplit1[7].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit6split = strforsplit6.split(",");

                            for(int q = 0; q < strforsplit6split.length; q++)
                            {
                                orderdRepsPush1.add(strforsplit6split[q]);
                            }

                            //---------Legs1EX---------
                            String strforsplit7 = listfromsplitsplit1[8].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit7split = strforsplit7.split(",");

                            for(int q = 0; q < strforsplit7split.length; q++)
                            {
                                orderdExercisesLegs1.add(strforsplit7split[q]);
                            }

                            //---------Legs1SE---------
                            String strforsplit8 = listfromsplitsplit1[9].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit8split = strforsplit8.split(",");

                            for(int y = 0; y < strforsplit8split.length; y++)
                            {
                                orderdSetsLegs1.add(strforsplit8split[y]);
                            }

                            //---------Legs1RE---------
                            String strforsplit9 = listfromsplitsplit1[10].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit9split = strforsplit9.split(",");

                            for(int q = 0; q < strforsplit9split.length; q++)
                            {
                                orderdRepsLegs1.add(strforsplit9split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesPull1 = orderdExercisesPull1;
                            ShowSingleProgramFromShow.orderdExercisesPush1 = orderdExercisesPush1;
                            ShowSingleProgramFromShow.orderdExercisesLegs1 = orderdExercisesLegs1;
                            ShowSingleProgramFromShow.orderdSetsPull1 = orderdSetsPull1;
                            ShowSingleProgramFromShow.orderdSetsPush1 = orderdSetsPush1;
                            ShowSingleProgramFromShow.orderdSetsLegs1 = orderdSetsLegs1;
                            ShowSingleProgramFromShow.orderdRepsPull1 = orderdRepsPull1;
                            ShowSingleProgramFromShow.orderdRepsPush1 = orderdRepsPush1;
                            ShowSingleProgramFromShow.orderdRepsLegs1 = orderdRepsLegs1;
                            startShowSingleProgramFromShow();
                            break;
                        }
                        else if(listfromsplitsplit1[0].toString().equals("2"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------UB1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for(int y = 0; y < strforsplit1split.length; y++)
                            {
                                orderdExercisesUB1.add(strforsplit1split[y]);
                            }

                            //---------UB1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for(int y = 0; y < strforsplit2split.length; y++)
                            {
                                orderdSetsUB1.add(strforsplit2split[y]);
                            }

                            //---------UB1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for(int q = 0; q < strforsplit3split.length; q++)
                            {
                                orderdRepsUB1.add(strforsplit3split[q]);
                            }

                            //---------LB1EX---------
                            String strforsplit4 = listfromsplitsplit1[5].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit4split = strforsplit4.split(",");

                            for(int q = 0; q < strforsplit4split.length; q++)
                            {
                                orderdExercisesLB1.add(strforsplit4split[q]);
                            }

                            //---------LB1SE---------
                            String strforsplit5 = listfromsplitsplit1[6].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit5split = strforsplit5.split(",");

                            for(int y = 0; y < strforsplit5split.length; y++)
                            {
                                orderdSetsLB1.add(strforsplit5split[y]);
                            }

                            //---------LB1RE---------
                            String strforsplit6 = listfromsplitsplit1[7].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit6split = strforsplit6.split(",");

                            for(int q = 0; q < strforsplit6split.length; q++)
                            {
                                orderdRepsLB1.add(strforsplit6split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesUB1 = orderdExercisesUB1;
                            ShowSingleProgramFromShow.orderdExercisesLB1 = orderdExercisesLB1;
                            ShowSingleProgramFromShow.orderdSetsUB1 = orderdSetsUB1;
                            ShowSingleProgramFromShow.orderdSetsLB1 = orderdSetsLB1;
                            ShowSingleProgramFromShow.orderdRepsUB1 = orderdRepsUB1;
                            ShowSingleProgramFromShow.orderdRepsLB1 = orderdRepsLB1;
                            startShowSingleProgramFromShow();
                            break;

                        }
                        else if(listfromsplitsplit1[0].toString().equals("1"))
                        {
                            counterExport = listfromsplitsplit1[0].toString();
                            programNameExport = listfromsplitsplit1[1].toString();

                            //---------FB1EX---------
                            String strforsplit1 = listfromsplitsplit1[2].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit1split = strforsplit1.split(",");

                            for(int y = 0; y < strforsplit1split.length; y++)
                            {
                                orderdExercisesFB1.add(strforsplit1split[y]);
                            }

                            //---------FB1SE---------
                            String strforsplit2 = listfromsplitsplit1[3].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit2split = strforsplit2.split(",");

                            for(int y = 0; y < strforsplit2split.length; y++)
                            {
                                orderdSetsFB1.add(strforsplit2split[y]);
                            }

                            //---------FB1RE---------
                            String strforsplit3 = listfromsplitsplit1[4].toString().replace("[", "").replace("]", "").trim();
                            String[] strforsplit3split = strforsplit3.split(",");

                            for(int q = 0; q < strforsplit3split.length; q++)
                            {
                                orderdRepsFB1.add(strforsplit3split[q]);
                            }

                            ShowSingleProgramFromShow.counterImport = counterExport;
                            ShowSingleProgramFromShow.programNameImport = programNameExport;
                            ShowSingleProgramFromShow.orderdExercisesFB1 = orderdExercisesFB1;
                            ShowSingleProgramFromShow.orderdSetsFB1 = orderdSetsFB1;
                            ShowSingleProgramFromShow.orderdRepsFB1 = orderdRepsFB1;
                            startShowSingleProgramFromShow();
                            break;
                        }
                    }
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void readFromSDCard()
    {
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, "SavedPrograms");

        if(file.exists())
        {
            StringBuilder text = new StringBuilder();

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while((line = br.readLine()) != null)
                {
                    text.append(line);
                    text.append('\n');
                }
            }
            catch(IOException e)
            {

            }

            stringReadFromSDCard = text.toString();
            ShowSingleProgramFromShow.fromStringFromSP = stringReadFromSDCard;

            if(stringReadFromSDCard != "")
            {
                splitSDCardString(stringReadFromSDCard);
            }
        }
        else
        {
            System.out.println("MOIS_File doesn't exist");
        }

    }

    public void splitSDCardString(String sdcardstring)
    {
        String[] sdcardstringsplit1 = sdcardstring.split(";");
        ArrayList listFromFirstArray = new ArrayList();

        for(int i = 0; i < sdcardstringsplit1.length; i++)
        {
            listFromFirstArray.add(sdcardstringsplit1[i]);
        }

        listFromSplit = listFromFirstArray;

        for(int j = 0; j < listFromFirstArray.size(); j++)
        {
            String[] listfromfirstarraysplit1 = listFromFirstArray.get(j).toString().split("\\|");
            programNames.add(listfromfirstarraysplit1[1]);
        }

    }

    public void startShowSingleProgramFromShow()
    {
        Intent spintent = new Intent(ShowPrograms.this, ShowSingleProgramFromShow.class);
        startActivity(spintent);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
