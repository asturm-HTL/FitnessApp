package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShowPrograms extends AppCompatActivity
{

    //---------------------------Variables from Main---------------------------
    public static int id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;
    //---------------------------MainVariables-End-----------------------------

    public String stringReadFromSDCard;

    public ArrayList listFromSplit = new ArrayList();

    public RecyclerView recyclerViewProgramNames;
    public RecyclerViewShowProgramsAdapter rvadaptProgramNames;

    public ArrayList programNames = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_programs);

        //-------DEBUG---------
        System.out.println("MOIS_SP_id_"+id);
        System.out.println("MOIS_SP_firstname_"+firstname);
        System.out.println("MOIS_SP_lastname_"+lastname);
        System.out.println("MOIS_SP_username_"+username);
        System.out.println("MOIS_SP_password_"+password);
        //-----DEBUG-End------


        readFromSDCard();

        recyclerViewProgramNames = findViewById(R.id.programsRV);
        rvadaptProgramNames = new RecyclerViewShowProgramsAdapter(this, programNames);
        recyclerViewProgramNames.setAdapter(rvadaptProgramNames);
        recyclerViewProgramNames.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewProgramNames.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewProgramNames, new RecyclerTouchListener.ClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {

                Toast.makeText(ShowPrograms.this, listFromSplit.get(position).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void readFromSDCard()
    {
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, "SavedPrograms");

        if(file.exists())
        {
            StringBuilder text = new StringBuilder();

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while((line = br.readLine()) != null)
                {
                    text.append(line);
                    text.append('\n');
                }
            }
            catch(IOException e)
            {

            }

            stringReadFromSDCard = text.toString();
            splitSDCardString(stringReadFromSDCard);
        }
        else
        {
            System.out.println("MOIS_File doesn't exist");
        }

    }

    public void splitSDCardString(String sdcardstring)
    {
        String[] sdcardstringsplit1 = sdcardstring.split(";");
        ArrayList listFromFirstArray = new ArrayList();

        for(int i = 0; i < sdcardstringsplit1.length; i++)
        {
            listFromFirstArray.add(sdcardstringsplit1[i]);
        }

        listFromSplit = listFromFirstArray;

        for(int j = 0; j < listFromFirstArray.size(); j++)
        {
            String[] listfromfirstarraysplit1 = listFromFirstArray.get(j).toString().split("\\|");
            programNames.add(listfromfirstarraysplit1[1]);
        }

    }
}
