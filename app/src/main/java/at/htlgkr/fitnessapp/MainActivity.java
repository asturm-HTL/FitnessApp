package at.htlgkr.fitnessapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawer;

    public String exercisesGetRequest;

    private static int STARTSCREEN = 124;
    Handler handler;

    //-------------Variables which get changed from LoginActivity------------------
        public static int id;
        public static String firstname;
        public static String lastname;
        public static String username;
        public static String password;

    //---------------------------------------------------

    public Button createProgramBtn;
    public  Button showProgramsBtn;
    public  Button exercisesBtn;
    public Button timerBtn;
    Toolbar toolbar;

    public NavigationView navigationView;

    NavigationView navview;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = new View(this);
        getExercises(view);

        //-------Drawer---------
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //-------Drawer-End---------

        //-------DEBUG---------
        System.out.println("MOIS_M_id_"+id);
        System.out.println("MOIS_M_firstname_"+firstname);
        System.out.println("MOIS_M_lastname_"+lastname);
        System.out.println("MOIS_M_username_"+username);
        System.out.println("MOIS_M_password_"+password);
        //-----DEBUG-End------

        //---------------------Buttons---------------------
        createProgramBtn = findViewById(R.id.createProgramBtn);
        showProgramsBtn = findViewById(R.id.showProgramsBtn);
        exercisesBtn = findViewById(R.id.exercisesBtn);
        timerBtn = findViewById(R.id.timerBtn);
        //-------------------Buttons-End--------------------

        //------------------------------onClickListeners----------------------------

            //------------------createProgram-------------------
            createProgramBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    createProgramBtn.setBackgroundResource(R.drawable.createprogramclicked);
                    startCreateProgram(id, firstname, lastname, username, password);
                }
            });

            //------------------showPrograms-------------------
            showProgramsBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    showProgramsBtn.setBackgroundResource(R.drawable.showprogramsclicked);
                    startShowPrograms(id, firstname, lastname, username, password);
                }
            });
            //--------------------exercises---------------------
            exercisesBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    exercisesBtn.setBackgroundResource(R.drawable.exercisesclicked);
                    startExercises(id, firstname, lastname, username, password);
                }
            });
            //----------------------timer-----------------------
            timerBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    timerBtn.setBackgroundResource(R.drawable.timerclicked);
                    startTimer(id, firstname, lastname, username, password);
                }
            });

        //--------------------------onCLickListers-End-----------------------------------


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        /*if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Do some stuff
            Toast.makeText(this, "is Landscape", Toast.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.END))
        {
            drawer.closeDrawer(GravityCompat.END);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //-----------------------setBackgroundRight-----------------------
        createProgramBtn.setBackgroundResource(R.drawable.createprogrambackground);
        showProgramsBtn.setBackgroundResource(R.drawable.showprogramsbackground);
        exercisesBtn.setBackgroundResource(R.drawable.exercisesbackground);
        timerBtn.setBackgroundResource(R.drawable.timerbackground);
        //-----------------------setBackground-End-------------------------

        navigationView.setCheckedItem(R.id.nav_home);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {

        switch (menuItem.getItemId())
        {
            case R.id.nav_stepcounter:
                startStepCounter(id, firstname, lastname, username, password);
                break;

            case R.id.nav_account:
                startAccount(id, firstname, lastname, username, password);
                break;
            case R.id.nav_settings:
                startSettings(id, firstname, lastname, username, password);
                break;
            case R.id.nav_logout:
                startLogin();
                break;

        }

        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    //-----------------------------------------GetExercises-----------------------------------------

    private class GetExercises extends AsyncTask<String, Integer, String>
    {

        private final String TAG = GetExercises.class.getSimpleName();

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

        MainActivity.GetExercises task = new MainActivity.GetExercises();
        task.execute();

        try
        {
            exercisesGetRequest  = task.get();

            System.out.println("MOIS_Exercises_: " + exercisesGetRequest);
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



    //----------------------------startActivityX-------------------------------
    public void startCreateProgram(int id, String firstname, String lastname, String username, String password)
    {
        CreateProgram.id = id;
        CreateProgram.firstname = firstname;
        CreateProgram.lastname = lastname;
        CreateProgram.username = username;
        CreateProgram.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, CreateProgram.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);

    }

    public void startShowPrograms(int id, String firstname, String lastname, String username, String password)
    {
        ShowPrograms.id = id;
        ShowPrograms.firstname = firstname;
        ShowPrograms.lastname = lastname;
        ShowPrograms.username = username;
        ShowPrograms.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, ShowPrograms.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);
    }

    public void startExercises(int id, String firstname, String lastname, String username, String password)
    {
        Exercises.id = id;
        Exercises.firstname = firstname;
        Exercises.lastname = lastname;
        Exercises.username = username;
        Exercises.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, Exercises.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);
    }

    public void startTimer(int id, String firstname, String lastname, String username, String password)
    {
        Timer.id = id;
        Timer.firstname = firstname;
        Timer.lastname = lastname;
        Timer.username = username;
        Timer.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, ActivityBeforeTimer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);
    }

    public void startStepCounter(int id, String firstname, String lastname, String username, String password)
    {
        StepCounter.id = id;
        StepCounter.firstname = firstname;
        StepCounter.lastname = lastname;
        StepCounter.username = username;
        StepCounter.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, StepCounter.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);
    }

    public void startAccount(int id, String firstname, String lastname, String username, String password)
    {
        Account.id = String.valueOf(id);
        Account.firstname = firstname;
        Account.lastname = lastname;
        Account.username = username;
        String pwStr = password.substring(0, 1);
        String pwStrFW = pwStr + "*****";
        Account.password = pwStrFW;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, Account.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        }, STARTSCREEN);
    }

    public void startSettings(int id, String firstname, String lastname, String username, String password)
    {
        StepCounter.id = id;
        StepCounter.firstname = firstname;
        StepCounter.lastname = lastname;
        StepCounter.username = username;
        StepCounter.password = password;

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, STARTSCREEN);
    }

    public void startLogin()
    {
        Intent lintent = new Intent(MainActivity.this, Login.class);
        startActivity(lintent);
    }


}
