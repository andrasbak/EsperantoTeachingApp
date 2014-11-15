package com.example.andras.esperantoapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andras.esperantoapp.App;
import com.example.andras.esperantoapp.R;
import com.example.andras.esperantoapp.skrald.LessonData;

import org.json.JSONObject;


public class LessonFrag extends Fragment implements View.OnClickListener {

    TextView textView;
    Button button;
  private JSONObject lessonJson;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View lessonOne = inflater.inflate(R.layout.fragment_lesson_one, container, false);
        button = (Button)lessonOne.findViewById(R.id.buttonl1);
        button.setOnClickListener(this);


      int lessonNr = getArguments().getInt("lesson");
      lessonJson =  App.lessons.get(lessonNr);

      button.setText(lessonJson.optString("title") + "\n"  + "Begin");

        LessonData.getInstance().setDataCounter(0);


        return lessonOne;

    }


    public void onClick(View v){

        if(v == button){
          // .putExtras(getArguments() overfører alle data fra fragmentets argument til intentet
          startActivity(new Intent(getActivity(), LessonActivity.class).putExtras(getArguments()));

        }

       }


}
