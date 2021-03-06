package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Timer extends AppCompatActivity
{

    public static final String CHANNEL_ID = "timerServiceChannel";

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

    private TextView actualStatus;

    ProgressBar progressBar;

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

        actualStatus = findViewById(R.id.actualStatusTV);

        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);

        progressBar = findViewById(R.id.workpauseprogress);

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
                    else
                    {
                        mCountDownTimer.cancel();
                        mTimerRunning = false;
                        mTimePauseLeftInMillis = PAUSE_TIMER_IN_MILLIS;
                        mTimeLeftInMillis = START_TIME_IN_MILLIS;
                        counter = 0;
                        actualStatus.setText("Stopped");
                        mButtonStartPause.setText("Start");
                        progressBar.setProgress(0);
                        updateCountDownText();
                    }

            }
        });

        progressBar.setMax((int) mTimeLeftInMillis);

        createNotificationChannel();

    }
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Timer Channel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    public void startService(View v)
    {
        String name = "Timer running!";

        Intent serviceIntent = new Intent(this, TimerService.class);
        serviceIntent.putExtra("inputExtra", name);

        startService(serviceIntent);
    }

    public void stopService(View v)
    {
        Intent serviceIntent = new Intent(this, TimerService.class);
        stopService(serviceIntent);
    }


    private void startTimer()
    {
        if(Settings.switchOnOff == true)
        {
            View v = new View(Timer.this);
            startService(v);
        }

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimeLeftInMillis = millisUntilFinished;
                progressBar.setMax((int)START_TIME_IN_MILLIS);
                progressBar.setProgress((int)START_TIME_IN_MILLIS+1000 - (int) mTimeLeftInMillis);
                updateCountDownText();
                actualStatus.setText("Work");
                mButtonStartPause.setText("Stop");

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
                        actualStatus.setText("Done");
                        mButtonStartPause.setText("Start");

                        if(Settings.switchOnOff == true)
                        {
                            View v = new View(Timer.this);
                            stopService(v);
                        }
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
                progressBar.setMax((int)PAUSE_TIMER_IN_MILLIS);
                progressBar.setProgress((int)PAUSE_TIMER_IN_MILLIS + 1000 - (int) mTimePauseLeftInMillis);
                actualStatus.setText("Pause");
            }

            @Override
            public void onFinish()
            {
                mTimePauseLeftInMillis = PAUSE_TIMER_IN_MILLIS;
                startTimer();
            }
        }.start();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

