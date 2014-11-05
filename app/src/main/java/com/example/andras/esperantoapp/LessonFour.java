package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LessonFour extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View lessonFour = inflater.inflate(R.layout.fragment_lesson_four, container, false);
        textView = (TextView)lessonFour.findViewById(R.id.textView);
        button = (Button)lessonFour.findViewById(R.id.buttonl4);
        return lessonFour;


    }

    public void onClick(View v){



    }


}
