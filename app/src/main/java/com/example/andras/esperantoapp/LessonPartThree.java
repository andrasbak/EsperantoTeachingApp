package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LessonPartThree extends Fragment implements View.OnClickListener {


    TextView textView;
    Button buttonJes, buttonNo, buttonContinue ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l1p3 = inflater.inflate(R.layout.fragment_lesson_part3, container, false);




        return l1p3;

    }


    public void onClick(View v){

        switch(1){

            case 0:
                //buttonJes
            break;
            case 1:
                //buttonNo
            break;
            case 2:
                //buttonContinue

        }


    }

}
