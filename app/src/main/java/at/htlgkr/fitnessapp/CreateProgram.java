package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

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

    private ArrayList<TimeList> experienceList = new ArrayList();
    private ArrayList<TimeList> opportunitiesList = new ArrayList();
    private ArrayList<TimeList> counterList = new ArrayList();

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
}
