package com.example.andras.esperantoapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ScreenTypeDemando extends Fragment implements View.OnClickListener {


    TextView textView, titleView;
    Button button1, button2;

    private String[] lessonInfo1 = {"131", "132", "133", "134", "135"};
    private String[] lessonInfo2 = {"231", "232", "233", "234", "235"};
    private String[] lessonInfo3 = {"331", "332", "333", "334", "335"};
    private String[] lessonInfo4 = {"431", "432", "433", "434", "435"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonPart3 = inflater.inflate(R.layout.fragment_lesson_part3, container, false);

        button1 = (Button)lessonPart3.findViewById(R.id.buttonPart3_1);
        button1.setOnClickListener(this);
        button2 = (Button)lessonPart3.findViewById(R.id.buttonPart3_2);
        button2.setOnClickListener(this);
        textView = (TextView)lessonPart3.findViewById(R.id.textViewPart3);
        titleView = (TextView)lessonPart3.findViewById(R.id.titlePart3);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dataFromJson();


        return lessonPart3;

    }

    public void dataFromJson(){

        if(LessonData.getInstance().getLessonNumber().equals("Lesson 1")) {
            LessonData.getInstance().setInfo(lessonInfo1[LessonData.getInstance().getCounter()]);
            LessonData.getInstance().setLessonUrl("http://pastebin.com/raw.php?i=rSXM8DY3");
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 2")) {
            LessonData.getInstance().setInfo(lessonInfo2[LessonData.getInstance().getCounter()]);
            LessonData.getInstance().setLessonUrl("");
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 3")) {
            LessonData.getInstance().setInfo(lessonInfo3[LessonData.getInstance().getCounter()]);
            LessonData.getInstance().setLessonUrl("");
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 4")) {
            LessonData.getInstance().setInfo(lessonInfo4[LessonData.getInstance().getCounter()]);
            LessonData.getInstance().setLessonUrl("");
        }
    }

    public void onClick(View v){

        if(v.equals(button1)){
            if(button1.getText().equals(LessonData.getInstance().getCorrect())){
                dialog();
            }
            else{
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();}
        }
        else if(v.equals(button2)){
            if(button2.getText().equals(LessonData.getInstance().getCorrect())){
                dialog();
            }
            else{
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();}
        }
    }

    public void dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Correct!");
        dialog.setPositiveButton("Continue", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        dialog.show();
    }

}
