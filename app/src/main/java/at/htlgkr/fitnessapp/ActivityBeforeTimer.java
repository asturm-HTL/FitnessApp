package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class ActivityBeforeTimer extends AppCompatActivity
{

    public static int timeStart;
    public static int timePause;
    public static int rounds;

    private Spinner spinnerwork;
    private Spinner spinnerpause;
    private Spinner spinnerrounds;
    private Button admitBtn;
    private ArrayList<TimeList> timeList = new ArrayList();
    private TimeSpinnerAdapter timeSpinnerAdapter;

    public TimeList timeListClicked;

    private ArrayList<TimeList> roundList = new ArrayList();
    private TimeSpinnerAdapter roundSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_timer);

        initList();
        initRounds();
        admitBtn = findViewById(R.id.admitBtn);
        spinnerwork = findViewById(R.id.spinnerwork);
        spinnerpause = findViewById(R.id.spinnerpause);
        spinnerrounds = findViewById(R.id.spinnerrounds);

        timeSpinnerAdapter = new TimeSpinnerAdapter(this, timeList);
        roundSpinnerAdapter = new TimeSpinnerAdapter(this, roundList);

        spinnerwork.setAdapter(timeSpinnerAdapter);
        spinnerpause.setAdapter(timeSpinnerAdapter);
        spinnerrounds.setAdapter(roundSpinnerAdapter);


        //--------------------------------SpinnerWork--------------------------------
        spinnerwork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                timeListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedMuscleName = timeListClicked.getTime();               //clickedmusclename = clickedtime
                //Toast.makeText(ActivityBeforeTimer.this, clickedMuscleName +" selected", Toast.LENGTH_SHORT).show();

                if(clickedMuscleName == "5 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 5000;
                }
                else if(clickedMuscleName == "10 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 10000;
                }
                else if(clickedMuscleName == "15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 15000;
                }
                else if(clickedMuscleName == "20 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 20000;
                }
                else if(clickedMuscleName == "25 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 25000;
                }
                else if(clickedMuscleName == "30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 30000;
                }
                else if(clickedMuscleName == "40 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 40000;
                }
                else if(clickedMuscleName == "45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 45000;
                }
                else if(clickedMuscleName == "1 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 60000;
                }
                else if(clickedMuscleName == "1 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 75000;
                }
                else if(clickedMuscleName == "1 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 90000;
                }
                else if(clickedMuscleName == "1 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 105000;
                }
                else if(clickedMuscleName == "2 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 120000;
                }
                else if(clickedMuscleName == "2 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 135000;
                }
                else if(clickedMuscleName == "2 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 150000;
                }
                else if(clickedMuscleName == "2 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 165000;
                }
                else if(clickedMuscleName == "3 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 180000;
                }
                else if(clickedMuscleName == "3 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 195000;
                }
                else if(clickedMuscleName == "3 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 210000;
                }
                else if(clickedMuscleName == "3 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 225000;
                }
                else if(clickedMuscleName == "4 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 240000;
                }
                else if(clickedMuscleName == "4 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 255000;
                }
                else if(clickedMuscleName == "4 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 270000;
                }
                else if(clickedMuscleName == "4 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 285000;
                }
                else if(clickedMuscleName == "5 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 300000;
                }
                else if(clickedMuscleName == "5 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 315000;
                }
                else if(clickedMuscleName == "5 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 330000;
                }
                else if(clickedMuscleName == "5 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 345000;
                }
                else if(clickedMuscleName == "6 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 360000;
                }
                else if(clickedMuscleName == "6 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 375000;
                }
                else if(clickedMuscleName == "6 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 390000;
                }
                else if(clickedMuscleName == "6 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 405000;
                }
                else if(clickedMuscleName == "7 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 420000;
                }
                else if(clickedMuscleName == "7 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 435000;
                }
                else if(clickedMuscleName == "7 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 450000;
                }
                else if(clickedMuscleName == "7 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 465000;
                }
                else if(clickedMuscleName == "8 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 480000;
                }
                else if(clickedMuscleName == "8 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 495000;
                }
                else if(clickedMuscleName == "8 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 510000;
                }
                else if(clickedMuscleName == "8 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 525000;
                }
                else if(clickedMuscleName == "9 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 540000;
                }
                else if(clickedMuscleName == "9 min 15 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 555000;
                }
                else if(clickedMuscleName == "9 min 30 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 570000;
                }
                else if(clickedMuscleName == "9 min 45 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 585000;
                }
                else if(clickedMuscleName == "10 min 00 sec")
                {
                    Timer.START_TIME_IN_MILLIS = 600000;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //--------------------------------SpinnerWork-End--------------------------------

        //--------------------------------SpinnerPause--------------------------------
        spinnerpause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                timeListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedMuscleName = timeListClicked.getTime();               //clickedmusclename = clickedtime

                if(clickedMuscleName == "5 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 5000;
                }
                else if(clickedMuscleName == "10 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 10000;
                }
                else if(clickedMuscleName == "15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 15000;
                }
                else if(clickedMuscleName == "20 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 20000;
                }
                else if(clickedMuscleName == "25 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 25000;
                }
                else if(clickedMuscleName == "30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 30000;
                }
                else if(clickedMuscleName == "40 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 40000;
                }
                else if(clickedMuscleName == "45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 45000;
                }
                else if(clickedMuscleName == "1 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 60000;
                }
                else if(clickedMuscleName == "1 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 75000;
                }
                else if(clickedMuscleName == "1 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 90000;
                }
                else if(clickedMuscleName == "1 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 105000;
                }
                else if(clickedMuscleName == "2 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 120000;
                }
                else if(clickedMuscleName == "2 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 135000;
                }
                else if(clickedMuscleName == "2 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 150000;
                }
                else if(clickedMuscleName == "2 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 165000;
                }
                else if(clickedMuscleName == "3 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 180000;
                }
                else if(clickedMuscleName == "3 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 195000;
                }
                else if(clickedMuscleName == "3 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 210000;
                }
                else if(clickedMuscleName == "3 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 225000;
                }
                else if(clickedMuscleName == "4 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 240000;
                }
                else if(clickedMuscleName == "4 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 255000;
                }
                else if(clickedMuscleName == "4 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 270000;
                }
                else if(clickedMuscleName == "4 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 285000;
                }
                else if(clickedMuscleName == "5 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 300000;
                }
                else if(clickedMuscleName == "5 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 315000;
                }
                else if(clickedMuscleName == "5 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 330000;
                }
                else if(clickedMuscleName == "5 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 345000;
                }
                else if(clickedMuscleName == "6 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 360000;
                }
                else if(clickedMuscleName == "6 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 375000;
                }
                else if(clickedMuscleName == "6 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 390000;
                }
                else if(clickedMuscleName == "6 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 405000;
                }
                else if(clickedMuscleName == "7 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 420000;
                }
                else if(clickedMuscleName == "7 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 435000;
                }
                else if(clickedMuscleName == "7 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 450000;
                }
                else if(clickedMuscleName == "7 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 465000;
                }
                else if(clickedMuscleName == "8 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 480000;
                }
                else if(clickedMuscleName == "8 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 495000;
                }
                else if(clickedMuscleName == "8 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 510000;
                }
                else if(clickedMuscleName == "8 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 525000;
                }
                else if(clickedMuscleName == "9 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 540000;
                }
                else if(clickedMuscleName == "9 min 15 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 555000;
                }
                else if(clickedMuscleName == "9 min 30 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 570000;
                }
                else if(clickedMuscleName == "9 min 45 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 585000;
                }
                else if(clickedMuscleName == "10 min 00 sec")
                {
                    Timer.PAUSE_TIMER_IN_MILLIS = 600000;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //--------------------------------SpinnerPause-End--------------------------------

        //--------------------------------SpinnerRounds--------------------------------
        spinnerrounds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                timeListClicked = (TimeList) parent.getItemAtPosition(position);
                String clickedRound = timeListClicked.getTime();

                if(clickedRound == "1 round")
                {
                    Timer.NUMBER_OF_ROUNDS = 1;
                }
                else if(clickedRound == "2 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 2;
                }
                else if(clickedRound == "3 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 3;
                }
                else if(clickedRound == "4 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 4;
                }
                else if(clickedRound == "5 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 5;
                }
                else if(clickedRound == "6 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 6;
                }
                else if(clickedRound == "7 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 7;
                }
                else if(clickedRound == "8 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 8;
                }
                else if(clickedRound == "9 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 9;
                }
                else if(clickedRound == "10 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 10;
                }
                else if(clickedRound == "11 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 11;
                }
                else if(clickedRound == "12 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 12;
                }
                else if(clickedRound == "13 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 13;
                }
                else if(clickedRound == "14 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 14;
                }
                else if(clickedRound == "15 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 15;
                }
                else if(clickedRound == "16 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 16;
                }
                else if(clickedRound == "17 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 17;
                }
                else if(clickedRound == "18 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 18;
                }
                else if(clickedRound == "19 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 19;
                }
                else if(clickedRound == "20 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 20;
                }
                else if(clickedRound == "21 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 21;
                }
                else if(clickedRound == "22 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 22;
                }
                else if(clickedRound == "23 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 23;
                }
                else if(clickedRound == "24 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 24;
                }
                else if(clickedRound == "25 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 25;
                }
                else if(clickedRound == "26 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 26;
                }
                else if(clickedRound == "27 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 27;
                }
                else if(clickedRound == "28 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 28;
                }
                else if(clickedRound == "29 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 29;
                }
                else if(clickedRound == "30 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 30;
                }
                else if(clickedRound == "31 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 31;
                }
                else if(clickedRound == "32 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 32;
                }
                else if(clickedRound == "33 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 33;
                }
                else if(clickedRound == "34 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 34;
                }
                else if(clickedRound == "35 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 35;
                }
                else if(clickedRound == "36 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 36;
                }
                else if(clickedRound == "37 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 37;
                }
                else if(clickedRound == "38 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 38;
                }
                else if(clickedRound == "39 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 39;
                }
                else if(clickedRound == "40 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 40;
                }
                else if(clickedRound == "41 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 41;
                }
                else if(clickedRound == "42 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 42;
                }
                else if(clickedRound == "43 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 43;
                }
                else if(clickedRound == "44 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 44;
                }
                else if(clickedRound == "45 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 45;
                }
                else if(clickedRound == "46 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 46;
                }
                else if(clickedRound == "47 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 47;
                }
                else if(clickedRound == "48 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 48;
                }
                else if(clickedRound == "49 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 49;
                }
                else if(clickedRound == "50 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 50;
                }
                else if(clickedRound == "51 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 51;
                }
                else if(clickedRound == "52 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 52;
                }
                else if(clickedRound == "53 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 53;
                }
                else if(clickedRound == "54 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 54;
                }
                else if(clickedRound == "55 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 55;
                }
                else if(clickedRound == "56 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 56;
                }
                else if(clickedRound == "57 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 57;
                }
                else if(clickedRound == "58 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 58;
                }
                else if(clickedRound == "59 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 59;
                }
                else if(clickedRound == "60 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 60;
                }
                else if(clickedRound == "61 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 61;
                }
                else if(clickedRound == "62 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 62;
                }
                else if(clickedRound == "63 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 63;
                }
                else if(clickedRound == "64 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 64;
                }
                else if(clickedRound == "65 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 65;
                }
                else if(clickedRound == "66 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 66;
                }
                else if(clickedRound == "67 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 67;
                }
                else if(clickedRound == "68 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 68;
                }
                else if(clickedRound == "69 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 69;
                }
                else if(clickedRound == "70 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 70;
                }
                else if(clickedRound == "71 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 71;
                }
                else if(clickedRound == "72 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 72;
                }
                else if(clickedRound == "73 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 73;
                }
                else if(clickedRound == "74 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 74;
                }
                else if(clickedRound == "75 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 75;
                }
                else if(clickedRound == "76 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 76;
                }
                else if(clickedRound == "77 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 77;
                }
                else if(clickedRound == "78 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 78;
                }
                else if(clickedRound == "79 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 79;
                }
                else if(clickedRound == "80 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 80;
                }
                else if(clickedRound == "81 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 81;
                }
                else if(clickedRound == "82 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 82;
                }
                else if(clickedRound == "83 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 83;
                }
                else if(clickedRound == "84 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 84;
                }
                else if(clickedRound == "85 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 85;
                }
                else if(clickedRound == "86 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 86;
                }
                else if(clickedRound == "87 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 87;
                }
                else if(clickedRound == "88 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 88;
                }
                else if(clickedRound == "89 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 89;
                }
                else if(clickedRound == "90 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 90;
                }
                else if(clickedRound == "91 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 91;
                }
                else if(clickedRound == "92 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 92;
                }
                else if(clickedRound == "93 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 93;
                }
                else if(clickedRound == "94 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 94;
                }
                else if(clickedRound == "95 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 95;
                }
                else if(clickedRound == "96 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 96;
                }
                else if(clickedRound == "97 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 97;
                }
                else if(clickedRound == "98 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 98;
                }
                else if(clickedRound == "99 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 99;
                }
                else if(clickedRound == "100 rounds")
                {
                    Timer.NUMBER_OF_ROUNDS = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //--------------------------------SpinnerRounds-End--------------------------------

        admitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startTimer();
            }
        });

    }


    public void startTimer()
    {
        Intent tintent = new Intent(this, Timer.class);
        startActivity(tintent);
    }

    public void initList()
    {
        timeList.add(new TimeList("5 sec"));
        timeList.add(new TimeList("10 sec"));
        timeList.add(new TimeList("15 sec"));
        timeList.add(new TimeList("20 sec"));
        timeList.add(new TimeList("25 sec"));
        timeList.add(new TimeList("30 sec"));
        timeList.add(new TimeList("40 sec"));
        timeList.add(new TimeList("45 sec"));
        timeList.add(new TimeList("1 min 00 sec"));
        timeList.add(new TimeList("1 min 15 sec"));
        timeList.add(new TimeList("1 min 30 sec"));
        timeList.add(new TimeList("1 min 45 sec"));
        timeList.add(new TimeList("2 min 00 sec"));
        timeList.add(new TimeList("2 min 15 sec"));
        timeList.add(new TimeList("2 min 30 sec"));
        timeList.add(new TimeList("2 min 45 sec"));
        timeList.add(new TimeList("3 min 00 sec"));
        timeList.add(new TimeList("3 min 15 sec"));
        timeList.add(new TimeList("3 min 30 sec"));
        timeList.add(new TimeList("3 min 45 sec"));
        timeList.add(new TimeList("4 min 00 sec"));
        timeList.add(new TimeList("4 min 15 sec"));
        timeList.add(new TimeList("4 min 30 sec"));
        timeList.add(new TimeList("4 min 45 sec"));
        timeList.add(new TimeList("5 min 00 sec"));
        timeList.add(new TimeList("5 min 15 sec"));
        timeList.add(new TimeList("5 min 30 sec"));
        timeList.add(new TimeList("5 min 45 sec"));
        timeList.add(new TimeList("6 min 00 sec"));
        timeList.add(new TimeList("6 min 15 sec"));
        timeList.add(new TimeList("6 min 30 sec"));
        timeList.add(new TimeList("6 min 45 sec"));
        timeList.add(new TimeList("7 min 00 sec"));
        timeList.add(new TimeList("7 min 15 sec"));
        timeList.add(new TimeList("7 min 30 sec"));
        timeList.add(new TimeList("7 min 45 sec"));
        timeList.add(new TimeList("8 min 00 sec"));
        timeList.add(new TimeList("8 min 15 sec"));
        timeList.add(new TimeList("8 min 30 sec"));
        timeList.add(new TimeList("8 min 45 sec"));
        timeList.add(new TimeList("9 min 00 sec"));
        timeList.add(new TimeList("9 min 15 sec"));
        timeList.add(new TimeList("9 min 30 sec"));
        timeList.add(new TimeList("9 min 45 sec"));
        timeList.add(new TimeList("10 min 00 sec"));
    }

    public void initRounds()
    {
        roundList.add(new TimeList("1 round"));
        roundList.add(new TimeList("2 rounds"));
        roundList.add(new TimeList("3 rounds"));
        roundList.add(new TimeList("4 rounds"));
        roundList.add(new TimeList("5 rounds"));
        roundList.add(new TimeList("6 rounds"));
        roundList.add(new TimeList("7 rounds"));
        roundList.add(new TimeList("8 rounds"));
        roundList.add(new TimeList("9 rounds"));
        roundList.add(new TimeList("10 rounds"));
        roundList.add(new TimeList("11 rounds"));
        roundList.add(new TimeList("12 rounds"));
        roundList.add(new TimeList("13 rounds"));
        roundList.add(new TimeList("14 rounds"));
        roundList.add(new TimeList("15 rounds"));
        roundList.add(new TimeList("16 rounds"));
        roundList.add(new TimeList("17 rounds"));
        roundList.add(new TimeList("18 rounds"));
        roundList.add(new TimeList("19 rounds"));
        roundList.add(new TimeList("20 rounds"));
        roundList.add(new TimeList("21 rounds"));
        roundList.add(new TimeList("22 rounds"));
        roundList.add(new TimeList("23 rounds"));
        roundList.add(new TimeList("24 rounds"));
        roundList.add(new TimeList("25 rounds"));
        roundList.add(new TimeList("26 rounds"));
        roundList.add(new TimeList("27 rounds"));
        roundList.add(new TimeList("28 rounds"));
        roundList.add(new TimeList("29 rounds"));
        roundList.add(new TimeList("30 rounds"));
        roundList.add(new TimeList("31 rounds"));
        roundList.add(new TimeList("32 rounds"));
        roundList.add(new TimeList("33 rounds"));
        roundList.add(new TimeList("34 rounds"));
        roundList.add(new TimeList("35 rounds"));
        roundList.add(new TimeList("36 rounds"));
        roundList.add(new TimeList("37 rounds"));
        roundList.add(new TimeList("38 rounds"));
        roundList.add(new TimeList("39 rounds"));
        roundList.add(new TimeList("40 rounds"));
        roundList.add(new TimeList("41 rounds"));
        roundList.add(new TimeList("42 rounds"));
        roundList.add(new TimeList("43 rounds"));
        roundList.add(new TimeList("44 rounds"));
        roundList.add(new TimeList("45 rounds"));
        roundList.add(new TimeList("46 rounds"));
        roundList.add(new TimeList("47 rounds"));
        roundList.add(new TimeList("48 rounds"));
        roundList.add(new TimeList("49 rounds"));
        roundList.add(new TimeList("50 rounds"));
        roundList.add(new TimeList("51 rounds"));
        roundList.add(new TimeList("52 rounds"));
        roundList.add(new TimeList("53 rounds"));
        roundList.add(new TimeList("54 rounds"));
        roundList.add(new TimeList("55 rounds"));
        roundList.add(new TimeList("56 rounds"));
        roundList.add(new TimeList("57 rounds"));
        roundList.add(new TimeList("58 rounds"));
        roundList.add(new TimeList("59 rounds"));
        roundList.add(new TimeList("60 rounds"));
        roundList.add(new TimeList("61 rounds"));
        roundList.add(new TimeList("62 rounds"));
        roundList.add(new TimeList("63 rounds"));
        roundList.add(new TimeList("64 rounds"));
        roundList.add(new TimeList("65 rounds"));
        roundList.add(new TimeList("66 rounds"));
        roundList.add(new TimeList("67 rounds"));
        roundList.add(new TimeList("68 rounds"));
        roundList.add(new TimeList("69 rounds"));
        roundList.add(new TimeList("70 rounds"));
        roundList.add(new TimeList("71 rounds"));
        roundList.add(new TimeList("72 rounds"));
        roundList.add(new TimeList("73 rounds"));
        roundList.add(new TimeList("74 rounds"));
        roundList.add(new TimeList("75 rounds"));
        roundList.add(new TimeList("76 rounds"));
        roundList.add(new TimeList("77 rounds"));
        roundList.add(new TimeList("78 rounds"));
        roundList.add(new TimeList("79 rounds"));
        roundList.add(new TimeList("80 rounds"));
        roundList.add(new TimeList("81 rounds"));
        roundList.add(new TimeList("82 rounds"));
        roundList.add(new TimeList("83 rounds"));
        roundList.add(new TimeList("84 rounds"));
        roundList.add(new TimeList("85 rounds"));
        roundList.add(new TimeList("86 rounds"));
        roundList.add(new TimeList("87 rounds"));
        roundList.add(new TimeList("88 rounds"));
        roundList.add(new TimeList("89 rounds"));
        roundList.add(new TimeList("90 rounds"));
        roundList.add(new TimeList("91 rounds"));
        roundList.add(new TimeList("92 rounds"));
        roundList.add(new TimeList("93 rounds"));
        roundList.add(new TimeList("94 rounds"));
        roundList.add(new TimeList("95 rounds"));
        roundList.add(new TimeList("96 rounds"));
        roundList.add(new TimeList("97 rounds"));
        roundList.add(new TimeList("98 rounds"));
        roundList.add(new TimeList("99 rounds"));
        roundList.add(new TimeList("100 rounds"));

    }

    public void startTimerIntent(int work, int pause, int rounds)
    {
        Intent abtintent = new Intent(ActivityBeforeTimer.this, Timer.class);
        startActivity(abtintent);
    }

    }
