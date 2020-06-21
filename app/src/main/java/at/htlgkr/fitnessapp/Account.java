package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class Account extends AppCompatActivity implements FragmentACC1.FragmentAListener, FragmentACC2.FragmentBListener
{

    //-------------Variables which get changed from MainActivity------------------
    public static String id;
    public static String firstname;
    public static String lastname;
    public static String username;
    public static String password;

    //---------------------------------------------------

    private FragmentACC1 fragmentA;
    private FragmentACC2 fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        fragmentA = new FragmentACC1();
        fragmentB = new FragmentACC2();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_a, fragmentA).replace(R.id.container_b, fragmentB).commit();
    }

    @Override
    public void onInputASent(CharSequence uid, CharSequence fn, CharSequence ln, CharSequence un, CharSequence pw)
    {
        fragmentB.updateEditText(uid, fn, ln, un, pw);
    }

    @Override
    public void onInputBSent(CharSequence input) {

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
