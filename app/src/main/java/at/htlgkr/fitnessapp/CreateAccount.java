package at.htlgkr.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateAccount extends AppCompatActivity
{

    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    public static String passwordRepeat;

    public static String responseFromServer;


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

        //------------Terms of Use-------------------------

        String termsOfUseText = "With creating an account you accept our TERMS OF USE";
        SpannableString spannableString = new SpannableString(termsOfUseText);

        ClickableSpan clickableSpan = new ClickableSpan()
        {
            @Override
            public void onClick(View widget)
            {
                startTermsOfUse();
            }
        };

        ForegroundColorSpan white = new ForegroundColorSpan(Color.WHITE);

        spannableString.setSpan(clickableSpan, 40, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(white, 40,52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        termsOfUseCreateTV.setText(spannableString);
        termsOfUseCreateTV.setMovementMethod(LinkMovementMethod.getInstance());

        //------------Terms of Use End---------------------

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
                            addUser(v);
                            if(responseFromServer.contains("code=200"))
                            {
                                startScreenBeforeMain(firstname);
                                Toast.makeText(CreateAccount.this, "Your account has been created!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(CreateAccount.this, "Couldn't create account!", Toast.LENGTH_LONG).show();
                            }
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

    //-------------------------------------------------------POST---------------------------------------------------

    public class AddUser extends AsyncTask<String, Integer, String>
    {

        private static final String linkURL = "http://www.fitnesscenter-mitter.at/userdata.php";

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings)
        {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("firstname", firstname)
                    .addFormDataPart("lastname", lastname)
                    .addFormDataPart("username", username)
                    .addFormDataPart("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url("http://www.fitnesscenter-mitter.at/userdata.php")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try
            {
                Response response = client.newCall(request).execute();
                return response.toString();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
        }

        }

    public void addUser(View view)
    {

        CreateAccount.AddUser task = new CreateAccount.AddUser();
        task.execute();

        try
        {
            responseFromServer = task.get();
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

    //----------------------------------------------------------------------------

    //-------------------------------startActivitys--------------------------------

    public void startScreenBeforeMain(String name)
    {
        ScreenBeforeMain.firstname = name;
        Intent startScreenBeforeMainIntent = new Intent(this, ScreenBeforeMain.class);
        startActivity(startScreenBeforeMainIntent);
    }

    public void startTermsOfUse()
    {
        Intent startTermsOfUse = new Intent(this, TermOfUse.class);
        startActivity(startTermsOfUse);
    }


}
