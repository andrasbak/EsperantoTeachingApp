package com.example.andras.esperantoapp.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

import com.example.andras.esperantoapp.FilCache;
import com.example.andras.esperantoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenTypeFrazo extends Fragment implements View.OnClickListener {

    ImageView imageView;
    ImageButton imageButton, continueButton;
    TextView textView, titleView;
    MediaPlayer mp = new MediaPlayer();


  private JSONObject jsondata;

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonpart1 = inflater.inflate(R.layout.fragment_type_frazo, container, false);
        imageView = (ImageView)lessonpart1.findViewById(R.id.imageViewPart1);
        imageButton = (ImageButton)lessonpart1.findViewById(R.id.imageButtonPart1);
        imageButton.setOnClickListener(this);
        continueButton = (ImageButton)lessonpart1.findViewById(R.id.continueButton1);
        continueButton.setFocusable(true);
        continueButton.setOnClickListener(this);
        continueButton.setVisibility(View.INVISIBLE);
        textView = (TextView)lessonpart1.findViewById(R.id.phrasePart1);
        titleView = (TextView)lessonpart1.findViewById(R.id.titlePart1);
      try {

          jsondata = new JSONObject(getArguments().getString("jsondata"));
          textView.setText(jsondata.optString("phrase"));
          imageView.setImageURI(Uri.fromFile(new File(FilCache.findLokaltFilnavn(jsondata.optString("picture")))));
          mp.reset();
          mp.setDataSource(new FileInputStream(FilCache.findLokaltFilnavn(jsondata.optString("sound"))).getFD());
          mp.prepare();



      } catch (JSONException e) {
          e.printStackTrace();
          System.out.println("hej");
      } catch (IOException e) {
          e.printStackTrace();
      }

      setInformation();

        return lessonpart1;
    }
    public void onClick(View v){

        if(v.equals(imageButton)){

            mp.start();
            continueButton.setVisibility(View.VISIBLE);

        }
        else if(v.equals(continueButton)){



        }

    }

    public void setInformation(){



    }


}
