package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    public ArrayList target = new ArrayList();
    //public ImageView imageViewTest;
    public MuscleAnatomy muscleAnatomyClicked;

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
                //Toast.makeText(ExercisesAdvancedView.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();

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
                else if (clickedMuscleName.equals("Lower Chest"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Lower Chest"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Upper Chest"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Upper Chest"))
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
                        if(target.get(b).toString().contains("Back"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Anterior Shoulder"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Anterior Shoulder"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Lateral Shoulder"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Lateral Shoulder"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Posterior Shoulder"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Posterior Shoulder"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Neck"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Neck"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Lower Back"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Lower Back"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Quads"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Quads"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Abductors"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Abductors"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Adductors"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Adductors"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Hamstrings"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Hamstrings"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Booty"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Booty"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Calves"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Calf"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Sixpack"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Sixpack"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Obliques"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Obliques"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Biceps"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Biceps"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Triceps"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Triceps"))
                        {
                            exercisesList.add(exercises.get(b));
                            arradapt.notifyDataSetChanged();
                        }

                    }
                }
                else if (clickedMuscleName.equals("Forearms"))
                {
                    exercisesList.clear();
                    for(int b = 0; b < exercises.size(); b++)
                    {
                        if(target.get(b).toString().contains("Forearm"))
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


                /*for(int b = 0; b < exercises.size(); b++)
                {
                    if(clickedMuscleName.equals(target.get(b).toString()))
                    {
                        System.out.println("BEENX_" + b + "_" + target.get(b).toString());
                        exercisesList.add(exercises.get(b));
                    }


                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        listViewAdvanced.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                for(int n = 0; n < exBeforeSplit.size(); n++)
                {
                    if((exBeforeSplit.get(n).toString().contains(listViewAdvanced.getItemAtPosition(position).toString())))
                    {
                        //Toast.makeText(ExercisesAdvancedView.this, exBeforeSplit.get(n).toString(), Toast.LENGTH_SHORT).show();
                        startExerciseDetailed(exBeforeSplit.get(n).toString());
                    }
                }
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

                System.out.println("exbeforeSplit_" + exBeforeSplit.get(j).toString());

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

    public void startExerciseDetailed(String exerciseString)
    {
        Intent edintent = new Intent(ExercisesAdvancedView.this, ExerciseInDetail.class);
        ExerciseInDetail.exerciseStringToSplit = exerciseString;
        startActivity(edintent);
    }

}
