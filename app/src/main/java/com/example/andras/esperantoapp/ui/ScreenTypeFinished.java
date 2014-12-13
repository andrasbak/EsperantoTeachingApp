package com.example.andras.esperantoapp.ui;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andras.esperantoapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ScreenTypeFinished extends Fragment implements View.OnClickListener
{

    ImageButton button;

    /*
    Creates the TypeFinished screen fragment with a button
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View viewFinished = inflater.inflate(R.layout.fragment_type_finished, container, false);

        button = (ImageButton)viewFinished.findViewById(R.id.buttonFinish);
        button.setOnClickListener(this);

        return viewFinished;
    }

    /*
    defines what happens when the button is pressed. It returns the user to the main screen, and resets
    the index keeping track of how far in an lesson you are.
    */
    public void onClick(View v)
    {
        if(v.equals(button))
        {

            ((LessonActivity) getActivity()).finish();
            ((LessonActivity) getActivity()).resetIndex();

        }

    }
}
