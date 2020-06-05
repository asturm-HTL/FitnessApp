package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ExercisesAdvancedView extends AppCompatActivity
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
    //public ImageView imageViewTest;

    //-----------------------------------

    private ArrayList<MuscleAnatomy> muscleAnatomyList;
    private MuscleAdapter muscleAdapter;
    public ListView listViewAdvanced;
    public ArrayList exercisesList;

    public ListviewAdapter arradapt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_advanced_view);

        exercisesList = new ArrayList();

        initList();
        listViewAdvanced = findViewById(R.id.listViewAdvanced);
        arradapt = new ListviewAdapter(this, R.layout.listview_row, exercisesList);

        listViewAdvanced.setAdapter(arradapt);

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

        View view = new View(this);
        getExercises(view);
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

        ExercisesAdvancedView.GetExercises task = new ExercisesAdvancedView.GetExercises();
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

            for(int b = 0; b < exercises.size(); b++)
            {

                System.out.println("BEENX_" +b + "_" + exercises.get(b).toString());
                exercisesList.add(exercises.get(b));

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
