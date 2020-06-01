package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StepCounter extends AppCompatActivity implements SensorEventListener
{

    //-------------Variables from MainActivity------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //-----------------------------------------------------------

    //------------------General Variables------------------------
    SensorManager sensorManager;
    boolean running = false;
    public static int targetSteps;
    public static int actualSteps;
    //------------------------------------------------------------

    //--------------------Layout Variables------------------------
    private ProgressBar progressBar;
    private TextView actualStepsTV;
    //------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        targetSteps = Settings.targetSteps;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        progressBar=findViewById(R.id.stepprogress);

        actualStepsTV = findViewById(R.id.actualStepsTV);
        progressBar.setMax(targetSteps);
        progressBar.setProgress(actualSteps);

        actualStepsTV.setText(String.valueOf(actualSteps));
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSensor != null)
        {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {

        if(running)
        {
            actualSteps = (int) event.values[0];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
