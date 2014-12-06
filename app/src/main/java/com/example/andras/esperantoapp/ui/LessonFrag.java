package com.example.andras.esperantoapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.andras.esperantoapp.App;
import com.example.andras.esperantoapp.R;

import org.json.JSONObject;


public class LessonFrag extends Fragment implements View.OnClickListener {

    TextView textView;
    Button beginButton;
  private JSONObject lessonJson;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View lessonOne = inflater.inflate(R.layout.fragment_lesson, container, false);
        beginButton = (Button)lessonOne.findViewById(R.id.beginButton);
        beginButton.setFocusable(true);
        beginButton.setOnClickListener(this);

      textView = (TextView)lessonOne.findViewById(R.id.lessonTitle);


      int lessonNr = getArguments().getInt("lesson");
      lessonJson =  App.lessons.get(lessonNr);

      textView.setText(lessonJson.optString("title") + "\n"  + "Begin");

        return lessonOne;

    }


    public void onClick(View v){

        if(v == beginButton){
          // .putExtras(getArguments() overfører alle data fra fragmentets argument til intentet
          startActivity(new Intent(getActivity(), LessonActivity.class).putExtras(getArguments()));

        }

       }


}
