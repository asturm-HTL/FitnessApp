package at.htlgkr.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShowSingleProgramFromShow extends AppCompatActivity implements RecyclerViewAdapter.OnExerciseListener
{
    private static int STARTSCREEN = 124;
    public static final int RQ_WRITE_STORAGE = 12345;

    public static String counterImport = "";
    public static String programNameImport = "";


    public List exBeforeSplit = new ArrayList();
    public String exercisesGetRequest;


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
    public Button deleteProgramButtonS;

    public static String fromStringFromSP;
    public static String deleteStringFromSP;

    private String stringForSDCard;

    RecyclerViewAdapter.OnExerciseListener pull1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesPull1.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener pull2Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesPull2.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener push1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesPush1.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener push2Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesPush2.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener legs1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesLegs1.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener legs2Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesLegs2.get(position).toString());
        }
    };


    RecyclerViewAdapter.OnExerciseListener ub1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesUB1.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener ub2Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesUB2.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener lb1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesLB1.get(position).toString());
        }
    };

    RecyclerViewAdapter.OnExerciseListener lb2Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesLB2.get(position).toString());
        }
    };


    RecyclerViewAdapter.OnExerciseListener fb1Listener = new RecyclerViewAdapter.OnExerciseListener()
    {
        @Override
        public void onExerciseClick(int position)
        {
            View view = new View(ShowSingleProgramFromShow.this);
            getExercises(view, orderdExercisesFB1.get(position).toString());
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_program_from_show);

        initLists();

        deleteProgramButtonS = findViewById(R.id.deleteProgramBtnS);
        deleteProgramButtonS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                deleteProgram(deleteStringFromSP, fromStringFromSP);
            }
        });



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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1,pull1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2,pull2Listener);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1,push1Listener);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush2S = findViewById(R.id.recyclerviewpush2S);
            rvadaptPush2S = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2,push2Listener);
            recyclerViewPush2S.setAdapter(rvadaptPush2S);
            recyclerViewPush2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1,legs1Listener);
            recyclerViewLegs1S.setAdapter(rvadaptLegs1S);
            recyclerViewLegs1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs2S = findViewById(R.id.recyclerviewlegs2S);
            rvadaptLegs2S = new RecyclerViewAdapter(this, orderdExercisesLegs2, orderdSetsLegs2, orderdRepsLegs2,legs2Listener);
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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1,pull1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesPull2, orderdSetsPull2, orderdRepsPull2,pull2Listener);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1,push1Listener);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush2S = findViewById(R.id.recyclerviewpush2S);
            rvadaptPush2S = new RecyclerViewAdapter(this, orderdExercisesPush2, orderdSetsPush2, orderdRepsPush2,push2Listener);
            recyclerViewPush2S.setAdapter(rvadaptPush2S);
            recyclerViewPush2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1,legs1Listener);
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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesUB1, orderdSetsUB1, orderdRepsUB1,ub1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));


            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesLB1, orderdSetsLB1, orderdRepsLB1,lb1Listener);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPull2S = findViewById(R.id.recyclerviewpull2S);
            rvadaptPull2S = new RecyclerViewAdapter(this, orderdExercisesUB2, orderdSetsUB2, orderdRepsUB2,ub2Listener);
            recyclerViewPull2S.setAdapter(rvadaptPull2S);
            recyclerViewPull2S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLB2, orderdSetsLB2, orderdRepsLB2,lb2Listener);
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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesPull1, orderdSetsPull1, orderdRepsPull1,pull1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesPush1, orderdSetsPush1, orderdRepsPush1,push1Listener);
            recyclerViewPush1S.setAdapter(rvadaptPush1S);
            recyclerViewPush1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewLegs1S = findViewById(R.id.recyclerviewlegs1S);
            rvadaptLegs1S = new RecyclerViewAdapter(this, orderdExercisesLegs1, orderdSetsLegs1, orderdRepsLegs1,legs1Listener);
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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesUB1, orderdSetsUB1, orderdRepsUB1,ub1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));

            recyclerViewPush1S = findViewById(R.id.recyclerviewpush1S);
            rvadaptPush1S = new RecyclerViewAdapter(this, orderdExercisesLB1, orderdSetsLB1, orderdRepsLB1,lb1Listener);
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
            rvadaptPull1S = new RecyclerViewAdapter(this, orderdExercisesFB1, orderdSetsFB1, orderdRepsFB1,fb1Listener);
            recyclerViewPull1S.setAdapter(rvadaptPull1S);
            recyclerViewPull1S.setLayoutManager(new LinearLayoutManager(this));
        }

    }


    public void deleteProgram(String toDeleteString, String fromString)
    {
        int indexOfDeleteString = fromString.indexOf(toDeleteString);
        int lengthOfString = toDeleteString.length();
        int startIndexOfSecondString = indexOfDeleteString+lengthOfString+1;
        int endOfString = fromString.length();

        System.out.println("MOIS_"+"iODS:"+indexOfDeleteString + ", lOS:" + lengthOfString + ", sIOSS:"+startIndexOfSecondString +", eOS:"+endOfString);
       // Toast.makeText(this, "iODS:"+indexOfDeleteString + ", lOS:" + lengthOfString + ", sIOSS:"+startIndexOfSecondString +", eOS:"+endOfString, Toast.LENGTH_SHORT).show();

        if(indexOfDeleteString != 0 && startIndexOfSecondString-1 != endOfString) //wenn mittendrin
        {
            String stringForSDC1 = fromString.substring(0, indexOfDeleteString-1);
            String stringForSDC2 = fromString.substring(startIndexOfSecondString, endOfString);
            stringForSDCard = stringForSDC1 + stringForSDC2;
        }
        else if(indexOfDeleteString != 0 && startIndexOfSecondString-1 == endOfString)  //wenn am ende
        {
            String stringForSDC4 = fromString.substring(0, indexOfDeleteString-1);
            stringForSDCard = stringForSDC4;
        }
        else if(indexOfDeleteString == 0 && toDeleteString.length() == endOfString)   //wenn alleine
        {
            String stringForSDC5 = "";
            stringForSDCard = stringForSDC5;
        }
        else  //wenn am anfang
        {
            String stringForSDC3 = fromString.substring(startIndexOfSecondString, endOfString);
            stringForSDCard = stringForSDC3;
        }

        View v = new View(this);
        saveProgram(v);

        startMain();
    }


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
            out.print(stringForSDCard);
            out.flush();
            out.close();
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

    public void startMain()
    {

        Intent mintent = new Intent(this, MainActivity.class);
        startActivity(mintent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onExerciseClick(int position) {

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

    public void getExercises(View view, String exerciseName)
    {

        ShowSingleProgramFromShow.GetExercises task = new ShowSingleProgramFromShow.GetExercises();
        task.execute();

        ArrayList exBeforeSplit = new ArrayList();

        try
        {
            exercisesGetRequest  = task.get();

            String[] exparts1 = exercisesGetRequest.split("\\{");

            for(int i = 0; i < exparts1.length; i++)
            {
                exBeforeSplit.add(exparts1[i]);
            }

            for(int j = 2; j < exBeforeSplit.size(); j++)
            {
                String[] exsplit2 = exBeforeSplit.get(j).toString().split(",");

                for(int x = 0; x < exsplit2.length; x++)
                {
                    if(x == 1)
                    {
                        String equalString = exsplit2[x].toString().replace("name", "").replace("\"","").replace(":", "").trim().toString();
                        if(exerciseName.trim().equals(equalString))
                        {
                            startExerciseDetailed(exBeforeSplit.get(j).toString());
                            System.out.println();
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

    public void startExerciseDetailed(String exerciseString)
    {
        ExerciseInDetail.exerciseStringToSplit = exerciseString;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(ShowSingleProgramFromShow.this, ExerciseInDetail.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);

    }

    @Override
    public void onBackPressed()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(ShowSingleProgramFromShow.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        }, STARTSCREEN);
    }

    //----------------------------------------GetExercises-End---------------------------------------
}
