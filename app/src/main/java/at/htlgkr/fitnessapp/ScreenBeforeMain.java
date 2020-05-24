package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;



public class ScreenBeforeMain extends AppCompatActivity
{

    public static String firstname;
    private static int SCREENBEFOREMAIN = 1500;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_before_main);

        TextView welcomeTV = findViewById(R.id.welcomteTV);

        welcomeTV.setText("Welcome to GAIN,\n" + firstname );

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(ScreenBeforeMain.this, MainActivity.class);
                startActivity(intent);
                finish(); //Closes when 'Back'
            }
        }, SCREENBEFOREMAIN);
    }
    }

