package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity
{

    public String string2;
    public String s03;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View view = new View(this);
        getAllToDos(view);
    }



//----------GETALLTODOS---------------- //TODO--TestTask noch richtig umändern

    private class TestTask extends AsyncTask<String, Integer, String>
    {

        private final String TAG = TestTask.class.getSimpleName();

        private static final String serverURL = "http://www.fitnesscenter-mitter.at/fitnessapp.php";

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
            outputRepoNames(s, string2);
            super.onPostExecute(s);
        }

        private void outputRepoNames(String s, String s2)
        {

            StringBuilder stringBuilder = new StringBuilder();

            try
            {
                JSONArray array = new JSONArray(s);
                for (int i=0 ; i < array.length() ; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    stringBuilder.append(jsonObject);
                    stringBuilder.append(("||"));
                }
            } catch (JSONException e)
            {
                Log.d(TAG, e.getLocalizedMessage());
            }
            s2 = stringBuilder.toString();

        }
    }

    public void getAllToDos(View view)
    {

        MainActivity.TestTask task = new MainActivity.TestTask();
        task.execute();

        try
        {
            s03  = task.get();

            Toast.makeText(this, "MOIS_: " +s03, Toast.LENGTH_LONG).show();
            System.out.println("MOIS_: " + s03);
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
}
