package com.example.andras.esperantoapp.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

import com.example.andras.esperantoapp.FilCache;
import com.example.andras.esperantoapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ScreenTypeFrazo extends Fragment implements View.OnClickListener
{

    ImageView imageView;
    ImageButton imageButton, continueButton1;
    TextView textView;
    MediaPlayer mp = new MediaPlayer();


    private JSONObject jsondata;

    /*
    Creates the TypeFrazo screen fragment the textviews, imageviews and buttons, and inserts the JSON
    data into the appropriate textviews. The soundfile is also prepared to be played.
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View viewFrazo = inflater.inflate(R.layout.fragment_type_frazo, container, false);
        imageView = (ImageView)viewFrazo.findViewById(R.id.imageViewPart1);
        imageButton = (ImageButton)viewFrazo.findViewById(R.id.imageButtonPart1);
        imageButton.setOnClickListener(this);
        continueButton1 = (ImageButton)viewFrazo.findViewById(R.id.continueButton1);
        continueButton1.setOnClickListener(this);
        continueButton1.setVisibility(View.INVISIBLE);
        textView = (TextView)viewFrazo.findViewById(R.id.phrasePart1);
        try
        {

            jsondata = new JSONObject(getArguments().getString("jsondata"));
            textView.setText(jsondata.optString("phrase"));
            imageView.setImageURI(Uri.fromFile(new File(FilCache.findLokaltFilnavn(jsondata.optString("picture")))));
            mp.reset();
            System.out.println("SOUND: " + FilCache.findLokaltFilnavn(jsondata.optString("sound")));
            mp.setDataSource(new FileInputStream(FilCache.findLokaltFilnavn(jsondata.optString("sound"))).getFD());
            mp.prepare();

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            System.out.println("hej");
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }


        return viewFrazo;
    }

    /*
    onClick defines what should happen when a click event occurs.
    */
    public void onClick(View v)
    {

        if(v.equals(imageButton))
        {
            //plays the soundfile loaded into the media player
            mp.start();
            //sets the continue button to be visible
            continueButton1.setVisibility(View.VISIBLE);
        }
        else if(v.equals(continueButton1))
        {
            //Creates a new fragment and shifts to that fragment.
            ((LessonActivity) getActivity()).addFragment();
            ((LessonActivity) getActivity()).skiftBillede();
        }
    }

}
