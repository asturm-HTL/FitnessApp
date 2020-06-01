package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Settings extends AppCompatActivity
{

    public static int targetSteps = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }
}
