package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity
{

    public static String username;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final View onCreateView = new View(this);

        final EditText usernameET = findViewById(R.id.usernameET);
        final EditText passwordET = findViewById(R.id.passwordET);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView createAccountTV = findViewById(R.id.createAccountTV);


        //------------------usernameET------------------



        //------------------passwordET------------------



        //-------------------loginBTN-------------------

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password = passwordET.getText().toString();
                username = usernameET.getText().toString();
                getUsers(onCreateView);
            }
        });

        //---------------createAccountTV----------------

        createAccountTV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent createAccountIntent = new Intent(Login.this, CreateAccount.class);
                startActivity(createAccountIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

    //-----------------------GETUSERS----------------------

    public String outputString;

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
        Login.GetUsers task = new Login.GetUsers();
        task.execute();

        try
        {
            outputString  = task.get();

            if((!username.equals("")) && (!password.equals("")) && outputString.contains("\"username\":\""+username+"\"") && outputString.contains("\"password\":\""+password+"\""))
            {
                startMain();
            }
            else
            {
                Toast.makeText(this, "username or password incorrect!", Toast.LENGTH_SHORT).show();
            }
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
    //---------------------------startMain------------------------

    public void startMain()
    {
        Intent mainIntent = new Intent(Login.this, MainActivity.class);
        startActivity(mainIntent);
    }


}
