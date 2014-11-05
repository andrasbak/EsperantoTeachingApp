package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LessonThree extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View lessonThree = inflater.inflate(R.layout.fragment_lesson_three, container, false);
        textView = (TextView)lessonThree.findViewById(R.id.textView);
        button = (Button)lessonThree.findViewById(R.id.buttonl3);
        return lessonThree;

    }

    public void onClick(View v){

        if(v == button){

            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            //.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
            //android.R.animator.fade_in, android.R.animator.fade_out);

            ft.replace(android.R.id.content, new LessonPartThree());
            ft.addToBackStack(null);
            ft.commit();

        }

    }


}
