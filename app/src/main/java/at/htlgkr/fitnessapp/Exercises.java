package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

    public TextView standardSearch;
    public TextView advancedSearch;


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

        standardSearch = findViewById(R.id.standardSearch);
        advancedSearch = findViewById(R.id.advancedSearch);

        //------------openStandardSearch------------------------
        standardSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent standardIntent = new Intent(Exercises.this, ExercisesStandardView.class);
                startActivity(standardIntent);
            }
        });
        //------------openStandardSearch-End--------------------

        //------------openAdvnacedSearch------------------------
        advancedSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent advancedIntent = new Intent(Exercises.this, ExercisesAdvancedView.class);
                startActivity(advancedIntent);
            }
        });
        //------------openAdvancedSearch-End--------------------

    }

}
