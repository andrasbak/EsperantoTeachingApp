package com.example.andras.esperantoapp.ui;


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

import org.json.JSONException;
import org.json.JSONObject;

public class ScreenTypeFinished extends Fragment implements View.OnClickListener {

  private JSONObject jsondata;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View lessonpart1 = inflater.inflate(R.layout.fragment_type_frazo, container, false);


    try {
      jsondata = new JSONObject(getArguments().getString("jsondata"));
      //textView.setText("xxxx"+jsondata);
    } catch (JSONException e) {
      e.printStackTrace();
    }


    return lessonpart1;
  }
  public void onClick(View v){
  }
}
