package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class CreateAccount extends AppCompatActivity
{

    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    public static String passwordRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText firstnameCreateET = findViewById(R.id.firstnameCreateET);
        final EditText lastnameCreateET = findViewById(R.id.lastnameCreateET);
        final EditText usernameCreateET = findViewById(R.id.usernameCreateET);
        final EditText passwordCreateET = findViewById(R.id.passwordCreateET);
        final EditText passwordRepeatCreateET = findViewById(R.id.passwordReapeatCreateET);
        Button createAccountBtn = findViewById(R.id.createAccountBtn);
        TextView termsOfUseCreateTV = findViewById(R.id.termOfUseCreateTV);

        createAccountBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getUsers(v);
                lastname = lastnameCreateET.getText().toString();
                firstname = firstnameCreateET.getText().toString();
                username = usernameCreateET.getText().toString();
                password = passwordCreateET.getText().toString();
                passwordRepeat = passwordRepeatCreateET.getText().toString();

                if((!lastname.equals("")) && (!firstname.equals("")) && (!username.equals("")) && (!password.equals("")) && (!passwordRepeat.equals("")))
                {
                    if((!outputString.contains("\"username\":\""+username+"\"")))
                    {
                        if(password.equals(passwordRepeat))
                        {
                            //TODO -- POST Request und User anlegen
                            //TODO --- username, password, firstname,... in der Main festlegen (Mainactivity.set(...))
                            startScreenBeforeMain(firstname);
                        }
                        else
                        {
                            passwordRepeatCreateET.setError("Doesn't match your password!");
                        }
                    }
                    else
                    {
                        usernameCreateET.setError("username is already taken!");
                    }
                }
                else
                {
                    if((lastname.equals("")))
                    {
                        lastnameCreateET.setError("Lastname can't be empty!");
                    }
                    else if((firstname.equals("")))
                    {
                        firstnameCreateET.setError("Firstname can't be empty!");
                    }
                    else if((password.equals("")))
                    {
                        passwordCreateET.setError("Password can't be empty!");
                    }
                    else if((passwordRepeat.equals("")))
                    {
                        passwordRepeatCreateET.setError("Password-Repeat can't be empty!");
                    }
                    else if((username.equals("")))
                    {
                        usernameCreateET.setError("Username can't be empty!");
                    }
                }

            }
        });

    }

    //-----------------------GETUSERS----------------------

    public static String outputString;

    private class GetUsers extends AsyncTask<String, Integer, String>
    {



        private final String TAG = GetUsers.class.getSimpleName();

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/userdata.php";

        @Override
        protected String doInBackground(String... strings)
        {
            String sJson = "";

            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(serverURL).openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    sJson = readResponseStream(reader);
                }

            }
            catch (IOException e)
            {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return sJson;
        }

        private String readResponseStream(BufferedReader reader) throws IOException
        {
            Log.d(TAG, "entered readResponseStreaulat");
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ( (line=reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s)
        {
            Log.d(TAG, "entered onPostExecute");
            outputRepoNames(s);
            super.onPostExecute(s);
        }

        private void outputRepoNames(String s)
        {

            StringBuilder stringBuilder = new StringBuilder();

            try
            {
                JSONArray array = new JSONArray(s);
                for (int i=0 ; i < array.length() ; i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    stringBuilder.append(jsonObject);
                    stringBuilder.append(("||"));
                }
            }
            catch (JSONException e)
            {
                Log.d(TAG, e.getLocalizedMessage());
            }

        }
    }

    public void getUsers(View view)
    {
        GetUsers task = new GetUsers();
        task.execute();

        try
        {
            outputString  = task.get();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    //-------------------------------startScreenBeforeMain--------------------------------

    public void startScreenBeforeMain(String name)
    {
        ScreenBeforeMain.firstname = name;
        Intent startScreenBeforeMainIntent = new Intent(this, ScreenBeforeMain.class);
        startActivity(startScreenBeforeMainIntent);
    }


}
