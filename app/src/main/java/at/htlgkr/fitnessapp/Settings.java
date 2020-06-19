package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Set;

public class Settings extends AppCompatActivity
{

    public static int targetSteps = 0;
    private Switch notifSwitch;
    private EditText settingsStepsET;
    private Button saveSettingsBtn;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String NOTIFSWITCH = "notifSwitch";

    private String stepsAsString;
    public static boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notifSwitch = findViewById(R.id.allowNotifSW);
        settingsStepsET = findViewById(R.id.settingsStepsET);
        saveSettingsBtn = findViewById(R.id.saveSettingsBtn);

        saveSettingsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveData();
                startMain();
            }
        });

        loadData();
        updateViews();
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, settingsStepsET.getText().toString());
        editor.putBoolean(NOTIFSWITCH, notifSwitch.isChecked());

        targetSteps = Integer.parseInt(settingsStepsET.getText().toString());

        editor.apply();

        Toast.makeText(this, "Settings have been updated!", Toast.LENGTH_SHORT).show();
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        stepsAsString = sharedPreferences.getString(TEXT, "10000");
        switchOnOff = sharedPreferences.getBoolean(NOTIFSWITCH, true);
    }

    public void updateViews()
    {
        settingsStepsET.setText(stepsAsString);
        notifSwitch.setChecked(switchOnOff);
        targetSteps = Integer.parseInt(stepsAsString);
    }

    public void startMain()
    {
        Intent mintent = new Intent(Settings.this, MainActivity.class);
        startActivity(mintent);
    }
}
