package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Timer extends AppCompatActivity
{

    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;

    //---------------------------MainVariables-End-----------------------------

    public static long START_TIME_IN_MILLIS;
    public static int NUMBER_OF_ROUNDS;
    public static int PAUSE_TIMER_IN_MILLIS;
    public int counter = 0;
    private TextView mTextViewCountdown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimePauseLeftInMillis = PAUSE_TIMER_IN_MILLIS;

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

        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);


        updateCountDownText();

        mButtonStartPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    if(!mTimerRunning)
                    {
                        startTimer();
                    }


            }
        });

    }

    private void startTimer()
    {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish()
            {
                    counter++;
                    if(counter!=NUMBER_OF_ROUNDS)
                    {
                        startPauseTimer();
                        mTimeLeftInMillis = START_TIME_IN_MILLIS;
                    }
                    else
                    {
                        mTimerRunning = false;
                        mTimePauseLeftInMillis = PAUSE_TIMER_IN_MILLIS;
                        mTimeLeftInMillis = START_TIME_IN_MILLIS;
                        counter = 0;
                    }
            }
        }.start();

        mTimerRunning = true;

    }

    private void updateCountDownText()
    {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountdown.setText(timeLeftFormatted);
    }

    public void updatePauseText()
    {
        int minutes = (int) (mTimePauseLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimePauseLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountdown.setText(timeLeftFormatted);
    }


    public void startPauseTimer()
    {
        mCountDownTimer = new CountDownTimer(mTimePauseLeftInMillis, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimePauseLeftInMillis = millisUntilFinished;
                updatePauseText();
            }

            @Override
            public void onFinish()
            {
                mTimePauseLeftInMillis = PAUSE_TIMER_IN_MILLIS;
                startTimer();
            }
        }.start();
    }
}

