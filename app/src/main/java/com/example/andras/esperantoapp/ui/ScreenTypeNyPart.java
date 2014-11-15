package com.example.andras.esperantoapp.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andras.esperantoapp.R;
import com.example.andras.esperantoapp.skrald.LessonData;

import org.json.JSONException;
import org.json.JSONObject;

public class ScreenTypeNyPart extends Fragment implements View.OnClickListener {
  ImageView imageView;
  ImageButton imageButton;
  TextView textView, titleView;
  MediaPlayer mp = new MediaPlayer();

  private JSONObject jsondata;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View lessonpart1 = inflater.inflate(R.layout.fragment_lesson_part1, container, false);
    imageView = (ImageView)lessonpart1.findViewById(R.id.imageViewPart1);
    imageButton = (ImageButton)lessonpart1.findViewById(R.id.imageButtonPart1);
    imageButton.setOnClickListener(this);
    textView = (TextView)lessonpart1.findViewById(R.id.phrasePart1);
    titleView = (TextView)lessonpart1.findViewById(R.id.titlePart1);

    try {
      jsondata = new JSONObject(getArguments().getString("jsondata"));
      textView.setText("xxxx"+jsondata);
    } catch (JSONException e) {
      e.printStackTrace();
    }


    return lessonpart1;
  }
  public void onClick(View v){
  }
}
