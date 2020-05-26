package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity
{

    public String exercisesGetRequest;

    //-------------Variables which get changed from LoginActivity------------------
        public static int id;
        public static String firstname;
        public static String lastname;
        public static String username;
        public static String password;

    //---------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = new View(this);
        getExercises(view);
        //addToDo(view);

        System.out.println("MOIS_M_id_"+id);
        System.out.println("MOIS_M_firstname_"+firstname);
        System.out.println("MOIS_M_lastname_"+lastname);
        System.out.println("MOIS_M_username_"+username);
        System.out.println("MOIS_M_password_"+password);

    }



    //----------GetExercises---------------- //TODO--TestTask noch richtig umändern

    private class GetExercises extends AsyncTask<String, Integer, String>
    {

        private final String TAG = GetExercises.class.getSimpleName();

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/exercises.php";

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
            Log.d(TAG, "entered readResponseStrem");

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
            outputExercises(s);
            super.onPostExecute(s);
        }

        private void outputExercises(String s)
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

    public void getExercises(View view)
    {

        MainActivity.GetExercises task = new MainActivity.GetExercises();
        task.execute();

        try
        {
            exercisesGetRequest  = task.get();

            System.out.println("MOIS_Exercises_: " + exercisesGetRequest);
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
    //---------------------------GETENDE------------------------

    //-----------------POSTTEST-------------------------------- //TODO --- für CreateAccount

    public static String testusername = "testusername";
    public static String testlastname = "testlastname";
    public static String testfirstname = "testfirstname";
    public static String testpassword = "tespassword";
    public static String userstring;

    public class ToDoAddTask extends AsyncTask<String, Integer, String>
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
        protected String doInBackground(String... strings) {
            Log.d("TAG", "doInBackground: ");

            try
            {
                HttpURLConnection connection = (HttpURLConnection) new URL(linkURL + "?firstname="+testfirstname+"&lastname="+testlastname+"&username="+testusername+"&password="+testpassword).openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json");
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(encodeUserInfos(testfirstname, testlastname, testusername, testpassword));
                System.out.println("IS_username: " +testusername);
                System.out.println("IS_password: " +testpassword);
                System.out.println("IS_firstname: " +testfirstname);
                System.out.println("IS_lastname: " +testlastname);

                writer.flush();
                writer.close();
                os.close();


                int responseCode = connection.getResponseCode();
                Log.d("ResponseMOIS", "ResponseMOIS: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while((line = br.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    br.close();

                    return sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        private String encodeUserInfos(String... strings)
        {
            String s = "{\n " + "\"firstname\": \"" + strings[0] + "\",\n " + "\"lastname\": \""+ strings[1] +"\",\n" + "\"username\" : \""+ strings[2] +"\",\n" + "\"password\" : \""+ strings[3]+"\"\n" + "}";
            return s;

        }
    }

    public void addToDo(View view)
    {

        MainActivity.ToDoAddTask task = new MainActivity.ToDoAddTask();
        task.execute();

        try
        {
            userstring = task.get();
            System.out.println("MOIS_list_"+userstring);

        }
        catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //-------------------------------------
}
