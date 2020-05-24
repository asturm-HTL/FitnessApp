package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ScreenAfterLogin extends AppCompatActivity
{

    public static String username;                  //TODO--- umändern auf firstname
    private static int SCREENAFTERLOGIN = 1500;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_after_login);

        TextView welcomeTVAfterLogin = findViewById(R.id.welcomteTVAfterLogin);

        welcomeTVAfterLogin.setText("Welcome back,\n" + username );        //TODO -- umändern

        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(ScreenAfterLogin.this, MainActivity.class);
                startActivity(intent);
                finish(); //Closes when 'Back'
            }
        }, SCREENAFTERLOGIN);

    }
}
