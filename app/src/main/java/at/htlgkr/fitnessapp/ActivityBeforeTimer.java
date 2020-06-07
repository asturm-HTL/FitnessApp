package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityBeforeTimer extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_timer);


    }


    public void startTimer()
    {
        Intent tintent = new Intent(this, ActivityBeforeTimer.class);
        startActivity(tintent);
    }

    }
