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

public class FragmentACC1 extends Fragment
{
    private FragmentAListener listener;


    private TextView userDataTV;

    public interface FragmentAListener
    {

        void onInputASent(CharSequence uid, CharSequence fn, CharSequence ln, CharSequence un, CharSequence pw);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_acc1, container, false);

        userDataTV = v.findViewById(R.id.userDataTVFRA);

        userDataTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onInputASent(Account.id, Account.firstname, Account.lastname, Account.username, Account.password);
            }
        });

        return v;
    }


    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        if(context instanceof FragmentAListener)
        {
            listener = (FragmentAListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement Fragment1Listener");
        }

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}
