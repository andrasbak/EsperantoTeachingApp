package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LessonTwo extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View lessonTwo = inflater.inflate(R.layout.fragment_lesson_two, container, false);
        textView = (TextView)lessonTwo.findViewById(R.id.textView);
        button = (Button)lessonTwo.findViewById(R.id.buttonl2);
        return lessonTwo;


    }

    public void onClick(View v){



    }


}