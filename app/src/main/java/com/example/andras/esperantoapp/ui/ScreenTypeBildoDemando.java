package com.example.andras.esperantoapp.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.FilCache;
import com.example.andras.esperantoapp.R;

import org.json.JSONObject;

import java.io.File;


public class ScreenTypeBildoDemando extends Fragment implements View.OnClickListener {


    ImageView imageView;
    Button button1, button2, button3;
    ImageButton continueButton2;
    String correct = "";

    private JSONObject jsondata;

    /*
    Creates the TypeBildoDemando screen fragment with all the textviews, buttons and imageviews, and
    inserts the JSON data into the appropriate places.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View viewBildoDemando = inflater.inflate(R.layout.fragment_type_bildodemando, container, false);

        button1 = (Button)viewBildoDemando.findViewById(R.id.buttonPart2_1);
        button1.setOnClickListener(this);
        button2 = (Button)viewBildoDemando.findViewById(R.id.buttonPart2_2);
        button2.setOnClickListener(this);
        button3 = (Button)viewBildoDemando.findViewById(R.id.buttonPart2_3);
        button3.setOnClickListener(this);
        imageView = (ImageView)viewBildoDemando.findViewById(R.id.imageViewPart2);
        continueButton2 = (ImageButton)viewBildoDemando.findViewById(R.id.continueButton2);
        continueButton2.setOnClickListener(this);
        continueButton2.setVisibility(View.INVISIBLE);

        try
        {
            jsondata = new JSONObject(getArguments().getString("jsondata"));
            imageView.setImageURI(Uri.fromFile(new File(FilCache.findLokaltFilnavn(jsondata.optString("picture")))));
            button1.setText(jsondata.optString("choise1"));
            button2.setText(jsondata.optString("choise2"));
            button3.setText(jsondata.optString("choise3"));
            correct = jsondata.optString("correct");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return viewBildoDemando;
    }

    /*
    toast method creates a toast.
    */
    private void toast()
    {

        Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_SHORT).show();

    }

    /*
    Defines what happens when a click event occurs. If a button is pressed the text is checked
    to see it is equal to the correct answer.
     */
    public void onClick(View v)
    {

        if (v.equals(button1))
        {

            if (button1.getText().equals(correct))
            {
                continueButton2.setVisibility(View.VISIBLE);
            }
            else
            {
                toast();
            }
        }
        else if (v.equals(button2))
        {
            if (button2.getText().equals(correct))
            {
                continueButton2.setVisibility(View.VISIBLE);
            }
            else
            {
                toast();
            }
        }
        else if (v.equals(button3))
        {
            if (button3.getText().equals(correct))
            {
                continueButton2.setVisibility(View.VISIBLE);
            }
            else
            {
                toast();
            }
        }
        else if(v.equals(continueButton2))
        {
            //Creates a new fragment and shifts to that fragment.
            ((LessonActivity) getActivity()).addFragment();
            ((LessonActivity) getActivity()).skiftBillede();

        }
    }
}

