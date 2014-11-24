package com.example.andras.esperantoapp.ui;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.FilCache;
import com.example.andras.esperantoapp.R;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;


public class ScreenTypeDemando extends Fragment implements View.OnClickListener {


    TextView textView, titleView;
    Button button1, button2;
    ImageButton continueButton3, soundButton;

    JSONObject jsondata = null;
    String correct = "";

    MediaPlayer mp = new MediaPlayer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewDemando = inflater.inflate(R.layout.fragment_type_demando, container, false);

        button1 = (Button)viewDemando.findViewById(R.id.buttonPart3_1);
        button1.setOnClickListener(this);
        button2 = (Button)viewDemando.findViewById(R.id.buttonPart3_2);
        button2.setOnClickListener(this);
        textView = (TextView)viewDemando.findViewById(R.id.textViewPart3);
        titleView = (TextView)viewDemando.findViewById(R.id.titlePart3);
        continueButton3 = (ImageButton)viewDemando.findViewById(R.id.continueButton3);
        continueButton3.setOnClickListener(this);
        continueButton3.setVisibility(View.INVISIBLE);
        soundButton = (ImageButton) viewDemando.findViewById(R.id.imageButtonPart3);
        soundButton.setOnClickListener(this);

        try{
            jsondata = new JSONObject(getArguments().getString("jsondata"));
            button1.setText(jsondata.optString("choise1"));
            button2.setText(jsondata.optString("choise2"));
            textView.setText(jsondata.optString("phrase"));
            correct = jsondata.optString("correct");
            System.out.println("SOUND: " + new FileInputStream(FilCache.findLokaltFilnavn(jsondata.optString("sound"))).getFD());
            mp.setDataSource(new FileInputStream(FilCache.findLokaltFilnavn(jsondata.optString("sound"))).getFD());
            mp.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return viewDemando;
    }

    private void toast(){

        Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_SHORT).show();

    }

    public void onClick(View v){

        if(v.equals(button1)){
            if(button1.getText().equals(correct)){
                continueButton3.setVisibility(View.VISIBLE);
            }
            else{
                toast();}
        }
        else if(v.equals(button2)){
            if(button2.getText().equals(correct)){
                continueButton3.setVisibility(View.VISIBLE);
            }
            else{
                toast();}
        }
        else if(v.equals(continueButton3)){
            ((LessonActivity) getActivity()).addFragment();
            ((LessonActivity) getActivity()).skiftBillede();
        }
        else if (v.equals(soundButton)){



        }
    }

}
