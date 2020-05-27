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
    public static String firstname;
    public static String lastname;
    public static int id;

    public String userString;

    public EditText usernameET;
    public EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final View onCreateView = new View(this);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
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

    public String userOutputString;

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
            userOutputString  = task.get();

            if((!username.equals("")) && (!password.equals("")) && userOutputString.contains("\"username\":\""+username+"\"") && userOutputString.contains("\"password\":\""+password+"\""))
            {
                //------get index of username to get the last '{' ----------

                int indexOfUsername = userOutputString.indexOf(username);
                char userOutputStringChar = userOutputString.charAt(indexOfUsername);
                char bracketAtBeginningOfUser = '{';
                int counter = 0;

                //------get the counter of times to get to '{' ------------

                while(userOutputStringChar != bracketAtBeginningOfUser)
                {
                    indexOfUsername = indexOfUsername -1;
                    counter++;
                    userOutputStringChar = userOutputString.charAt(indexOfUsername);
                }

                //-------- split the userOutputString into userString by subsequence ----------

                if(userOutputStringChar == bracketAtBeginningOfUser)
                {
                    userString = userOutputString.subSequence((userOutputString.indexOf(username)-counter), (userOutputString.indexOf("}", userOutputString.indexOf(username)))+1).toString();
                    System.out.println("MOISString_"+userString);

                    //------userString cleaning --------

                    userString = userString.replace("{", "");
                    userString = userString.replace("}","");
                    userString = userString.replace("\"", "");

                    //-----userString - splits for userData--------

                    String[] parts = userString.split(",");
                    String[] idsplit = parts[0].toString().split(":");
                    String[] firstnamesplit = parts[1].toString().split(":");
                    String[] lastnamesplit = parts[2].toString().split(":");
                    String[] usernamesplit = parts[3].toString().split(":");
                    String[] passwordsplit = parts[4].toString().split(":");

                    //---------give the variables the data of the string----------

                    id = Integer.parseInt(idsplit[1].toString());
                    firstname = firstnamesplit[1].toString();
                    lastname = lastnamesplit[1].toString();
                    username = usernamesplit[1].toString();
                    password = passwordsplit[1].toString();

                    //--------set the variables from MainActivity-------------

                    MainActivity.id = id;
                    MainActivity.firstname = firstname;
                    MainActivity.lastname = lastname;
                    MainActivity.username = username;
                    MainActivity.password = password;
                }

                startScreenAfterLogin(firstname);
            }
            else
            {
                if((username.equals("")))
                {
                    usernameET.setError("Username can't be empty!");
                }
                else if(password.equals(""))
                {
                    passwordET.setError("Password can't be empty!");
                }
                else
                {
                    Toast.makeText(this, "username or password incorrect!", Toast.LENGTH_SHORT).show();
                }
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

    public void startScreenAfterLogin(String name)
    {
        ScreenAfterLogin.firstname = name;
        Intent startScreenAfterLoginIntent = new Intent(this, ScreenAfterLogin.class);
        startActivity(startScreenAfterLoginIntent);
    }


}
