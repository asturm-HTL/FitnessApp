package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CreateProgram extends AppCompatActivity
{
    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;

    //---------------------------MainVariables-End-----------------------------

    private Spinner workoutexperiencespinner;
    private Spinner workoutopportunitiesspinner;
    private Spinner workoutcountspinner;
    private Button createProgramBtn;

    private ArrayList pgBeforeSplit = new ArrayList();

    public String allPrograms;

    public TimeList experienceListClicked;
    public TimeList opportunitiesListClicked;
    public TimeList counterListClicked;

    private ArrayList<TimeList> experienceList = new ArrayList();
    private ArrayList<TimeList> opportunitiesList = new ArrayList();
    private ArrayList<TimeList> counterList = new ArrayList();

    public static String workoutExperience;
    public static String workoutOpportunities;
    public static String workoutCounter;

    private TimeSpinnerAdapter experienceSpinnerAdapter;
    private TimeSpinnerAdapter opportunitiesSpinnerAdapter;
    private TimeSpinnerAdapter counterSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_program);

        initLists();

        //-------DEBUG---------
        System.out.println("MOIS_CP_id_"+id);
        System.out.println("MOIS_CP_firstname_"+firstname);
        System.out.println("MOIS_CP_lastname_"+lastname);
        System.out.println("MOIS_CP_username_"+username);
        System.out.println("MOIS_CP_password_"+password);
        //-----DEBUG-End------

        workoutexperiencespinner = findViewById(R.id.workoutexperienceSpinner);
        workoutopportunitiesspinner = findViewById(R.id.workoutopportunitiesSpinner);
        workoutcountspinner = findViewById(R.id.workoutcountSpinner);

        experienceSpinnerAdapter = new TimeSpinnerAdapter(this, experienceList);
        opportunitiesSpinnerAdapter = new TimeSpinnerAdapter(this, opportunitiesList);
        counterSpinnerAdapter = new TimeSpinnerAdapter(this, counterList);

        workoutexperiencespinner.setAdapter(experienceSpinnerAdapter);
        workoutopportunitiesspinner.setAdapter(opportunitiesSpinnerAdapter);
        workoutcountspinner.setAdapter(counterSpinnerAdapter);

        createProgramBtn = findViewById(R.id.createProgramBtn);

        workoutexperiencespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                experienceListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedExperience = experienceListClicked.getTime();

                if(clickedExperience == "Beginner")
                {
                    workoutExperience = "Beginner";
                }
                else if(clickedExperience == "Standard")
                {
                    workoutExperience = "Standard";
                }
                else if(clickedExperience == "Advanced")
                {
                    workoutExperience = "Advanced";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        workoutopportunitiesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                opportunitiesListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedOpportunity = opportunitiesListClicked.getTime();

                if(clickedOpportunity == "Bodyweight")
                {
                    workoutOpportunities = "Bodyweight";
                }
                else if(clickedOpportunity == "Bench, Barbell and Dumbbells")
                {
                    workoutOpportunities = "BenchBarbellDumbbell";
                }
                else if(clickedOpportunity == "Gym")
                {
                    workoutOpportunities = "Gym";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        workoutcountspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                counterListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedCounter = counterListClicked.getTime();

                if(clickedCounter == "1")
                {
                    workoutCounter = "1";
                }
                else if(clickedCounter == "2")
                {
                    workoutCounter = "2";
                }
                else if(clickedCounter == "3")
                {
                    workoutCounter = "3";
                }
                else if(clickedCounter == "4")
                {
                    workoutCounter = "4";
                }
                else if(clickedCounter == "5")
                {
                    workoutCounter = "5";
                }
                else if(clickedCounter == "6")
                {
                    workoutCounter = "6";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        createProgramBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                searchForProgram(workoutExperience, workoutOpportunities, workoutCounter);
            }
        });

    }

    private void initLists()
    {
        //----------------------------experienceList----------------------------
        experienceList.add(new TimeList("Beginner"));
        experienceList.add(new TimeList("Standard"));
        experienceList.add(new TimeList("Advanced"));
        //----------------------------experienceList-End------------------------

        //----------------------------opportunitiesList-------------------------
        opportunitiesList.add(new TimeList("Bodyweight"));
        opportunitiesList.add(new TimeList("Bench, Barbell and Dumbbells"));
        opportunitiesList.add(new TimeList("Gym"));
        //------------------------opportunitiesList-End-------------------------

        //-----------------------------counterList------------------------------
        counterList.add(new TimeList("1"));
        counterList.add(new TimeList("2"));
        counterList.add(new TimeList("3"));
        counterList.add(new TimeList("4"));
        counterList.add(new TimeList("5"));
        counterList.add(new TimeList("6"));
        //-------------------------counterList-End------------------------------
    }

    public void searchForProgram(String experience, String opportunity, String counter)
    {
        View view = new View(this);
        getAllPrograms(view, experience, opportunity, counter);
    }


    //-----------------------------------------GetExercises-----------------------------------------

    private class GetAllPrograms extends AsyncTask<String, Integer, String>
    {

        private final String TAG = "DieserTag";

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/programs.php";

        @Override
        protected String doInBackground(String... strings)
        {
            String sJson = "";

            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(serverURL).openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    sJson = readResponseStream(reader);
                }

            }
            catch (IOException e)
            {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return sJson;
        }

        private String readResponseStream(BufferedReader reader) throws IOException
        {
            Log.d(TAG, "entered readResponseStrem");

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ( (line=reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s)
        {
            Log.d(TAG, "entered onPostExecute");
            outputExercises(s);
            super.onPostExecute(s);
        }

        private void outputExercises(String s)
        {

            StringBuilder stringBuilder = new StringBuilder();

            try
            {
                JSONArray array = new JSONArray(s);

                for (int i=0 ; i < array.length() ; i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    stringBuilder.append(jsonObject);
                    stringBuilder.append(("||"));
                }
            }
            catch (JSONException e)
            {
                Log.d(TAG, e.getLocalizedMessage());
            }
        }
    }

    public void getAllPrograms(View view, String experience, String opportunity, String counter)
    {

        CreateProgram.GetAllPrograms task = new CreateProgram.GetAllPrograms();
        task.execute();

        try
        {
            allPrograms  = task.get();

            String[] allProgramsSplit = allPrograms.split("\\{");


            for(int k = 0; k < allProgramsSplit.length; k++)
            {
                pgBeforeSplit.add(allProgramsSplit[k]);
            }

            for(int j = 0; j<pgBeforeSplit.size(); j++)
            {
                String[] pgparts2 = pgBeforeSplit.get(j).toString().split(",\"");

                System.out.println("exbeforeSplit_" + pgBeforeSplit.get(j).toString());

                for(int x = 0; x < pgparts2.length; x++)
                {

                    if(x == 1)
                    {

                    //------------------------------------------GYM------------------------------------------

                        //---------------------GYM - ADVANCED---------------------

                            //------------------Advanced, Gym, 6-5-3------------------
                                if((experience == "Advanced" && opportunity == "Gym" && counter == "6") || (experience == "Advanced" && opportunity == "Gym" && counter == "3") || (experience == "Advanced" && opportunity == "Gym" && counter == "5"))
                                {

                                    if((experience == "Advanced" && opportunity == "Gym" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();

                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();

                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();

                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Gym" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Gym" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Advanced, Gym, 4-2------------------
                                if((experience == "Advanced" && opportunity == "Gym" && counter == "4") || (experience == "Advanced" && opportunity == "Gym" && counter == "2"))
                                {

                                    if((experience == "Advanced" && opportunity == "Gym" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Gym" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.counter = "2";
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.counter = "2";
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Advanced, Gym, 1------------------
                                if((experience == "Advanced" && opportunity == "Gym" && counter == "1"))
                                {

                                    if((experience == "Advanced" && opportunity == "Gym" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Advanced") && namestr.contains("Gym") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                        //---------------------GYM - STANDARD---------------------

                            //------------------Standard, Gym, 6-5-3------------------
                                if((experience == "Standard" && opportunity == "Gym" && counter == "6") || (experience == "Standard" && opportunity == "Gym" && counter == "3") || (experience == "Standard" && opportunity == "Gym" && counter == "5"))
                                {

                                    if((experience == "Standard" && opportunity == "Gym" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;

                                    }
                                    else if((experience == "Standard" && opportunity == "Gym" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "Gym" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Standard, Gym, 4-2------------------
                                if((experience == "Standard" && opportunity == "Gym" && counter == "4") || (experience == "Standard" && opportunity == "Gym" && counter == "2"))
                                {
                                    if((experience == "Standard" && opportunity == "Gym" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "Gym" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Standard, Gym, 1------------------
                                if((experience == "Standard" && opportunity == "Gym" && counter == "1"))
                                {
                                    if((experience == "Standard" && opportunity == "Gym" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Standard") && namestr.contains("Gym") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }

                        //---------------------GYM - BEGINNER---------------------

                            //------------------Beginner, Gym, 6-5-3------------------
                                if((experience == "Beginner" && opportunity == "Gym" && counter == "6") || (experience == "Beginner" && opportunity == "Gym" && counter == "3") || (experience == "Beginner" && opportunity == "Gym" && counter == "5"))
                                {
                                    if((experience == "Beginner" && opportunity == "Gym" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Gym" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Gym" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Beginner, Gym, 4-2------------------
                                if((experience == "Beginner" && opportunity == "Gym" && counter == "4") || (experience == "Beginner" && opportunity == "Gym" && counter == "2"))
                                {
                                    if((experience == "Beginner" && opportunity == "Gym" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Gym" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Beginner, Gym, 1------------------
                                if((experience == "Beginner" && opportunity == "Gym" && counter == "1"))
                                {
                                    if((experience == "Beginner" && opportunity == "Gym" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Beginner") && namestr.contains("Gym") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }

                    //------------------------------------------BODYWEIGHT------------------------------------------

                        //---------------------BODYWEIGHT - ADVANCED---------------------

                            //------------------Advanced, Bodyweight, 6-5-3------------------
                                if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "6") || (experience == "Advanced" && opportunity == "Bodyweight" && counter == "3") || (experience == "Advanced" && opportunity == "Bodyweight" && counter == "5"))
                                {
                                    if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        System.out.println("MOIS_"+experience + counter +opportunity);
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Advanced, Bodyweight, 4-2------------------
                                if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "4") || (experience == "Advanced" && opportunity == "Bodyweight" && counter == "2"))
                                {
                                    if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Advanced, Bodyweight, 1------------------
                                if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "1"))
                                {
                                    if((experience == "Advanced" && opportunity == "Bodyweight" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Advanced") && namestr.contains("Bodyweight") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }

                        //---------------------BODYWEIGHT - STANDARD---------------------

                            //------------------Standard, Bodyweight, 6-5-3------------------
                                if((experience == "Standard" && opportunity == "Bodyweight" && counter == "6") || (experience == "Standard" && opportunity == "Bodyweight" && counter == "3") || (experience == "Standard" && opportunity == "Bodyweight" && counter == "5"))
                                {
                                    if((experience == "Standard" && opportunity == "Bodyweight" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;

                                    }
                                    else if((experience == "Standard" && opportunity == "Bodyweight" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "Bodyweight" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.counter = "3";
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.counter = "3";
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.counter = "3";
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Standard, Bodyweight, 4-2------------------
                                if((experience == "Standard" && opportunity == "Bodyweight" && counter == "4") || (experience == "Standard" && opportunity == "Bodyweight" && counter == "2"))
                                {
                                    if((experience == "Standard" && opportunity == "Bodyweight" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "Bodyweight" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Standard, Bodyweight, 1------------------
                                if((experience == "Standard" && opportunity == "Bodyweight" && counter == "1"))
                                {
                                    if((experience == "Standard" && opportunity == "Bodyweight" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Standard") && namestr.contains("Bodyweight") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                        //---------------------BODYWEIGHT - BEGINNER---------------------

                            //------------------Beginner, Bodyweight, 6-5-3------------------
                                if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "6") || (experience == "Beginner" && opportunity == "Bodyweight" && counter == "3") || (experience == "Beginner" && opportunity == "Bodyweight" && counter == "5"))
                                {
                                    if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Beginner, Bodyweight, 4-2------------------
                                if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "4") || (experience == "Beginner" && opportunity == "Bodyweight" && counter == "2"))
                                {
                                    if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Beginner, Bodyweight, 1------------------
                                if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "1"))
                                {
                                    if((experience == "Beginner" && opportunity == "Bodyweight" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Beginner") && namestr.contains("Bodyweight") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }

                    //------------------------------------------BENCH, BARBELL AND DUMBBELLS------------------------------------------

                        //---------------------BENCH, BARBELL AND DUMBBELLS - ADVANCED---------------------

                            //------------------Advanced, Bench, Barbell and Dumbbells, 6-5-3------------------
                                if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "6") || (experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "3") || (experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "5"))
                                {
                                    if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Advanced, Bench, Barbell and Dumbbells, 4-2------------------
                                if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "4") || (experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                {
                                    if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Advanced, Bench, Barbell and Dumbbells, 1------------------
                                if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                {
                                    if((experience == "Advanced" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Advanced") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }

                        //---------------------BENCH, BARBELL AND DUMBBELLS - STANDARD---------------------

                            //------------------Standard, Bench, Barbell and Dumbbells, 6-5-3------------------
                                if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "6") || (experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "3") || (experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "5"))
                                {
                                    if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Standard, Bench, Barbell and Dumbbells, 4-2------------------
                                if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "4") || (experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                {
                                    if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Standard, Bench, Barbell and Dumbbells, 1------------------
                                if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                {
                                    if((experience == "Standard" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Standard") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }

                        //---------------------BENCH, BARBELL AND DUMBBELLS - BEGINNER---------------------

                            //------------------Beginner, Bench, Barbell and Dumbbells, 6-5-3------------------
                                if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "6") || (experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "3") || (experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "5"))
                                {
                                    if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "6"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                            ShowSingleProgramFromCreate.legstwo = splitLegs[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "5") )
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.pulltwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.pushtwo = splitPush[1].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "3"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Pull") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pullone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Push")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.pushone = splitPush[0].toString();
                                        }
                                        if(namestr.contains("Legs")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("6"))
                                        {
                                            String[] splitLegs = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.legsone = splitLegs[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }

                                }
                            //------------------Beginner, Bench, Barbell and Dumbbells, 4-2------------------
                                if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "4") || (experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                {
                                    if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "4"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                            ShowSingleProgramFromCreate.upperbodytwo = splitPull[1].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                            ShowSingleProgramFromCreate.lowerbodytwo = splitPush[1].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                    else if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "2"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Upperbody") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.upperbodyone = splitPull[0].toString();
                                        }
                                        if(namestr.contains("Lowerbody")&& namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("4"))
                                        {
                                            String[] splitPush = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.lowerbodyone = splitPush[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                            //------------------Beginner, Bench, Barbell and Dumbbells, 1------------------
                                if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                {
                                    if((experience == "Beginner" && opportunity == "BenchBarbellDumbbell" && counter == "1"))
                                    {
                                        String namestr = pgparts2[1].toString();
                                        if(namestr.contains("Fullbody") && namestr.contains("Beginner") && namestr.contains("BenchBarbellDumbbell") && namestr.contains("1"))
                                        {
                                            String[] splitPull = pgparts2[2].toString().split("\\|");
                                            ShowSingleProgramFromCreate.fullbodyone = splitPull[0].toString();
                                        }
                                        ShowSingleProgramFromCreate.counter = counter;
                                        ShowSingleProgramFromCreate.experience = experience;
                                        ShowSingleProgramFromCreate.opportunity = opportunity;
                                        startShowSingleProgram();
                                        break;
                                    }
                                }
                    }
                }
            }






        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    //----------------------------------------GetExercises-End---------------------------------------


    public void startShowSingleProgram()
    {
        Intent sspIntent = new Intent(CreateProgram.this, ShowSingleProgramFromCreate.class);
        startActivity(sspIntent);
    }

}
