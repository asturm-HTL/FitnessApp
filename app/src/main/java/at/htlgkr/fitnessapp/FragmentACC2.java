package at.htlgkr.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentACC2 extends Fragment
{
    private FragmentBListener listener;


    private TextView uidTV;
    private TextView uunTV;
    private TextView ufnTV;
    private TextView ulnTV;
    private TextView upwTV;

    private TextView idTV;
    private TextView unTV;
    private TextView fnTV;
    private TextView lnTV;
    private TextView pwTV;

    public interface FragmentBListener
    {

        void onInputBSent(CharSequence input);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_acc2, container, false);

        idTV = v.findViewById(R.id.userIDTVFRA);
        fnTV = v.findViewById(R.id.userfirstnameTVFRA);
        lnTV = v.findViewById(R.id.userlastnameTVFRA);
        unTV = v.findViewById(R.id.userusernameTVFRA);
        pwTV = v.findViewById(R.id.userpasswordTVFRA);

        uidTV = v.findViewById(R.id.userIDTV);
        ufnTV = v.findViewById(R.id.userfirstnameTV);
        ulnTV = v.findViewById(R.id.userlastnameTV);
        uunTV = v.findViewById(R.id.userusernameTV);
        upwTV = v.findViewById(R.id.userpasswordTV);

        return v;
    }

    public void updateEditText(CharSequence uid, CharSequence fn, CharSequence ln, CharSequence un, CharSequence pw)
    {
        idTV.setText(uid);
        fnTV.setText(fn);
        lnTV.setText(ln);
        unTV.setText(un);
        pwTV.setText(pw);

        uidTV.setText("User-ID:");
        ufnTV.setText("Firstname:");
        ulnTV.setText("Lastname:");
        uunTV.setText("Username:");
        upwTV.setText("Password:");
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        if(context instanceof FragmentBListener)
        {
            listener = (FragmentBListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement FragmentBListener");
        }

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}