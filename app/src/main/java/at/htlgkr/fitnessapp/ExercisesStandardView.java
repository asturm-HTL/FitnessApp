package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExercisesStandardView extends AppCompatActivity
{

    //---------------------------Variables from Exercises---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //---------------------------MainVariables-End-----------------------------

    public String exercisesGetRequest;

    public List imglinks = new ArrayList();
    public List exBeforeSplit = new ArrayList();
    public ArrayList exercises = new ArrayList();
    public ArrayList target = new ArrayList();
    //public ImageView imageViewTest;
    public MuscleAnatomy muscleAnatomyClicked;

    //-----------------------------------

    private ArrayList<MuscleAnatomy> muscleAnatomyList;
    private MuscleAdapter muscleAdapter;
    public ListView listViewStandard;
    public ArrayList exercisesList;

    public ListviewAdapter arradapt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_standard_view);

        exercisesList = new ArrayList();

        initList();
        listViewStandard = findViewById(R.id.listViewStandard);
        arradapt = new ListviewAdapter(this, R.layout.listview_row, exercisesList);

        listViewStandard.setAdapter(arradapt);
        Spinner spinnerMuscles = findViewById(R.id.spinnerAnatomyStandard);

        View view = new View(this);
        getExercises(view);

        muscleAdapter = new MuscleAdapter(this, muscleAnatomyList);
        spinnerMuscles.setAdapter(muscleAdapter);

        while(exercisesList == null)
        {
            System.out.println("null");
        }

        spinnerMuscles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                muscleAnatomyClicked = (MuscleAnatomy) parent.getItemAtPosition(position);
                String clickedMuscleName = muscleAnatomyClicked.getMuscleName();
                Toast.makeText(ExercisesStandardView.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();

                if(clickedMuscleName.equals("Chest"))
                {
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Chest"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Back"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Back") || target.get(b).toString().contains("Neck"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Shoulders"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Shoulder"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Legs"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Quads") || target.get(b).toString().contains("Hamstrings") || target.get(b).toString().contains("Calf") || target.get(b).toString().contains("Booty"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Arms"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Triceps") || target.get(b).toString().contains("Biceps") || target.get(b).toString().contains("Forearm"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Core"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Sixpack") || target.get(b).toString().contains("Obliques"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else
                {
                    exercisesList.clear();
                    arradapt.notifyDataSetChanged();
                }

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



    //-----------------------------------------GetExercises-----------------------------------------

    private class GetExercises extends AsyncTask<String, Integer, String>
    {

        private final String TAG = "DieserTag";

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/exercises.php";

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

    public void getExercises(View view)
    {

        ExercisesStandardView.GetExercises task = new ExercisesStandardView.GetExercises();
        task.execute();

        try
        {
            exercisesGetRequest  = task.get();

            String[] exparts1 = exercisesGetRequest.split("\\{");

            for(int i = 0; i < exparts1.length; i++)
            {
                exBeforeSplit.add(exparts1[i].toString());
            }

            for(int j = 0; j<exBeforeSplit.size(); j++)
            {
                String[] exparts2 = exBeforeSplit.get(j).toString().split(",\"");
                for(int x = 0; x < exparts2.length; x++)
                {

                    if(x == 1)
                    {
                        exercises.add(exparts2[x].toString().replace("name", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                    if(x == 3)
                    {
                        target.add(exparts2[x].toString().replace("target", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                    if(x == 5)
                    {
                        imglinks.add(exparts2[x].toString().replace("imglink", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                }
            }

            for(int f = 0; f < imglinks.size(); f++)
            {

                System.out.println("BEEN_" +f + "_" + imglinks.get(f).toString());

            }




            //pictures get loaded after register
            // Picasso.get().load("http://www.fitnesscenter-mitter.at/img/FitnessApp/" + imglinks.get(191) + ".png").into(imageViewTest);
            //TODO--.jpgs umdoa

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
}
/*
    private ArrayList<MuscleAnatomy>muscleAnatomyList;
    private MuscleAdapter muscleAdapter;

    //---------------------------Variables from Exercises---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //---------------------------MainVariables-End-----------------------------

    public String exercisesGetRequest;

    public List imglinks = new ArrayList();
    public List exBeforeSplit = new ArrayList();
    public ArrayList exercises = new ArrayList();
    public ArrayList target = new ArrayList();
    //public ImageView imageViewTest;
    public MuscleAnatomy muscleAnatomyClicked;
    public ListviewAdapter arradapt;
    public ListView listViewStandard;
    public ArrayList exercisesList;

    //-----------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_standard_view);

        initList();

        Spinner spinnerMuscles = findViewById(R.id.spinnerAnatomy);

        listViewStandard = findViewById(R.id.listViewStandard);
        arradapt = new ListviewAdapter(this, R.layout.listview_row, exercisesList);

        listViewStandard.setAdapter(arradapt);

        muscleAdapter = new MuscleAdapter(this, muscleAnatomyList);
        spinnerMuscles.setAdapter(muscleAdapter);

        View view = new View(this);
        getExercises(view);

        while(exercisesList == null)
        {
            System.out.println("null");
        }

        spinnerMuscles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                MuscleAnatomy muscleAnatomyClicked = (MuscleAnatomy) parent.getItemAtPosition(position);
                String clickedMuscleName = muscleAnatomyClicked.getMuscleName();
                Toast.makeText(ExercisesStandardView.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();

                if(clickedMuscleName.equals("Chest"))
                {
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Chest"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Back"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Back") || target.get(b).toString().contains("Neck"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Shoulders"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Shoulder"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Legs"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Quads") || target.get(b).toString().contains("Hamstrings") || target.get(b).toString().contains("Calf") || target.get(b).toString().contains("Booty"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Arms"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Triceps") || target.get(b).toString().contains("Biceps") || target.get(b).toString().contains("Forearm"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Core"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Sixpack") || target.get(b).toString().contains("Obliques"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
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

    //-----------------------------------------GetExercises-----------------------------------------

    private class GetExercisesA extends AsyncTask<String, Integer, String>
    {

        private final String TAG = "DieserTag";

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/exercises.php";

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

    public void getExercises(View view)
    {

        ExercisesStandardView.GetExercisesA task = new ExercisesStandardView.GetExercisesA();
        task.execute();

        try
        {
            exercisesGetRequest  = task.get();

            String[] exparts1 = exercisesGetRequest.split("\\{");

            for(int i = 0; i < exparts1.length; i++)
            {
                exBeforeSplit.add(exparts1[i].toString());
            }

            for(int j = 0; j<exBeforeSplit.size(); j++)
            {
                String[] exparts2 = exBeforeSplit.get(j).toString().split(",\"");
                for(int x = 0; x < exparts2.length; x++)
                {

                    if(x == 1)
                    {
                        exercises.add(exparts2[x].toString().replace("name", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                    if(x == 3)
                    {
                        target.add(exparts2[x].toString().replace("target", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                    if(x == 5)
                    {
                        imglinks.add(exparts2[x].toString().replace("imglink", "").replace("\"", "").replace(":", "").replace("}", ""));
                    }
                }
            }

            for(int f = 0; f < imglinks.size(); f++)
            {

                System.out.println("BEEN_" +f + "_" + imglinks.get(f).toString());

            }




            //pictures get loaded after register
            // Picasso.get().load("http://www.fitnesscenter-mitter.at/img/FitnessApp/" + imglinks.get(191) + ".png").into(imageViewTest);
            //TODO--.jpgs umdoa

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
}
*/

