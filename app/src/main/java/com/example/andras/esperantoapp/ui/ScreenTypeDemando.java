package com.example.andras.esperantoapp.ui;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.FilCache;
import com.example.andras.esperantoapp.R;
import com.example.andras.esperantoapp.skrald.LessonData;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;


public class ScreenTypeDemando extends Fragment implements View.OnClickListener {


    TextView textView, titleView;
    Button button1, button2;

    JSONObject jsondata = null;
    String correct = "";

    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonPart3 = inflater.inflate(R.layout.fragment_type_demando, container, false);

        button1 = (Button)lessonPart3.findViewById(R.id.buttonPart3_1);
        button1.setOnClickListener(this);
        button2 = (Button)lessonPart3.findViewById(R.id.buttonPart3_2);
        button2.setOnClickListener(this);
        textView = (TextView)lessonPart3.findViewById(R.id.textViewPart3);
        titleView = (TextView)lessonPart3.findViewById(R.id.titlePart3);

        try{
            jsondata = new JSONObject(getArguments().getString("jsondata"));
            button1.setText(jsondata.optString("choise1"));
            button2.setText(jsondata.optString("choise2"));
            correct = jsondata.optString("correct");

            mp.setDataSource(new FileInputStream(FilCache.findLokaltFilnavn(jsondata.optString("sound"))).getFD());
            mp.prepare();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return lessonPart3;

    }

    public void onClick(View v){

        if(v.equals(button1)){
            if(button1.getText().equals(correct)){

            }
            else{
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();}
        }
        else if(v.equals(button2)){
            if(button2.getText().equals(correct)){

            }
            else{
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();}
        }
    }

}
