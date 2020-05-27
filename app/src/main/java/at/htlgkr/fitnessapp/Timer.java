package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Timer extends AppCompatActivity
{

    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;

    //---------------------------MainVariables-End-----------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //-------DEBUG---------
        System.out.println("MOIS_T_id_"+id);
        System.out.println("MOIS_T_firstname_"+firstname);
        System.out.println("MOIS_T_lastname_"+lastname);
        System.out.println("MOIS_T_username_"+username);
        System.out.println("MOIS_T_password_"+password);
        //-----DEBUG-End------

    }
}
