package com.example.andras.esperantoapp.ui;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andras.esperantoapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ScreenTypeFinished extends Fragment implements View.OnClickListener {

  Button button;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View viewFinished = inflater.inflate(R.layout.fragment_type_finished, container, false);

      button = (Button)viewFinished.findViewById(R.id.buttonFinish);
      button.setOnClickListener(this);



    return viewFinished;
  }
  public void onClick(View v){

      if(v.equals(button)){

          ((LessonActivity) getActivity()).finish();
          ((LessonActivity) getActivity()).resetIndex();

      }

  }
}
