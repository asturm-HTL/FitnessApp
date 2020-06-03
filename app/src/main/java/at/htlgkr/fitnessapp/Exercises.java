package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

public class Exercises extends AppCompatActivity
{

    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //---------------------------MainVariables-End-----------------------------

    public String exercisesGetRequest;

    public List imglinks = new ArrayList();
    public List exBeforeSplit = new ArrayList();
    //public ImageView imageViewTest;

    //-----------------------------------

    private ArrayList<MuscleAnatomy>muscleAnatomyList;
    private MuscleAdapter muscleAdapter;

    //-----------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        View view = new View(this);

        //imageViewTest = findViewById(R.id.imageViewTest);

        //-------DEBUG---------
        System.out.println("MOIS_C_id_"+id);
        System.out.println("MOIS_C_firstname_"+firstname);
        System.out.println("MOIS_C_lastname_"+lastname);
        System.out.println("MOIS_C_username_"+username);
        System.out.println("MOIS_C_password_"+password);
        //-----DEBUG-End------


        getExercises(view);

        initList();

        Spinner spinnerMuscles = findViewById(R.id.spinnerAnatomy);

        muscleAdapter = new MuscleAdapter(this, muscleAnatomyList);
        spinnerMuscles.setAdapter(muscleAdapter);

        spinnerMuscles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(Exercises.this, "Penis", Toast.LENGTH_SHORT).show();
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
        muscleAnatomyList.add(new MuscleAnatomy("Back", R.drawable.backanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Quads", R.drawable.quadsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Hamstrings", R.drawable.hamstringsanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Booty", R.drawable.bootyanatomy));
        muscleAnatomyList.add(new MuscleAnatomy("Calves", R.drawable.calfanatomy));

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

        Exercises.GetExercises task = new Exercises.GetExercises();
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
