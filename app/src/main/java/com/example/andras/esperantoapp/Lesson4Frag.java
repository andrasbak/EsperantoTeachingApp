package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Lesson4Frag extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View lessonFour = inflater.inflate(R.layout.fragment_lesson_four, container, false);
        button = (Button)lessonFour.findViewById(R.id.buttonl4);
        button.setOnClickListener(this);
        button.setText("Lesson 4" + "\n" + "\n" + "\n" + "Begin");
        return lessonFour;


    }

    public void onClick(View v){



    }


}
