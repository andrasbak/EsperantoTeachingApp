package com.example.andras.esperantoapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LessonPartTwo extends Fragment implements View.OnClickListener {


    ImageView imageView;
    TextView titleView;
    Button button, button1, button2, button3;
    private String[] lessonInfo1 = {"121", "122", "123", "124", "125"};
    private String[] lessonInfo2 = {"221", "222", "223", "224", "225"};
    private String[] lessonInfo3 = {"321", "322", "323", "324", "325"};
    private String[] lessonInfo4 = {"421", "422", "423", "424", "425"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonPart2 = inflater.inflate(R.layout.fragment_lesson_part2, container, false);

        button = (Button)lessonPart2.findViewById(R.id.buttonPart2);
        button.setOnClickListener(this);
        button.setVisibility(View.INVISIBLE);
        button1 = (Button)lessonPart2.findViewById(R.id.buttonPart2_1);
        button1.setOnClickListener(this);
        button2 = (Button)lessonPart2.findViewById(R.id.buttonPart2_2);
        button2.setOnClickListener(this);
        button3 = (Button)lessonPart2.findViewById(R.id.buttonPart2_3);
        button3.setOnClickListener(this);
        imageView = (ImageView)lessonPart2.findViewById(R.id.imageViewPart2);
        titleView = (TextView)lessonPart2.findViewById(R.id.titlePart2);


        dataFromJson();

        new AsyncTask() {

            public ArrayList<PartData> res;

            @Override
            protected Object doInBackground(Object... params) {
                res = JsonDownload.getInstance().downloadJson(LessonData.getInstance().getLessonUrl().toString());
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                System.out.println("Counter: "+LessonData.getInstance().getCounter());
                PartData l = res.get(LessonData.getInstance().getCounter());
                titleView.setText(l.getTitle());
                button1.setText(l.getChoise1());
                button2.setText(l.getChoise2());
                button3.setText(l.getChoise3());
                LessonData.getInstance().setCorrect(l.getCorrect());
            }
        }.execute();

        return lessonPart2;
    }

    public void dataFromJson(){

        if(LessonData.getInstance().getLessonNumber().equals("Lesson 1")) {
            LessonData.getInstance().setInfo(lessonInfo1[LessonData.getInstance().getCounter()]);
            LessonData.getInstance().setLessonUrl("http://pastebin.com/raw.php?i=KfVJNjrX");
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
            if(button1.getText()==LessonData.getInstance().getCorrect()){
                button.setVisibility(View.VISIBLE);
            }
        }
        else if(v.equals(button2)){
            if(button2.getText()==LessonData.getInstance().getCorrect()){
                button.setVisibility(View.VISIBLE);
            }
        }
        else if(v.equals(button3)){
            if(button3.getText()==LessonData.getInstance().getCorrect()){
                button.setVisibility(View.VISIBLE);
            }
        }
        else if(v.equals(button)){
            if (LessonData.getInstance().getCounter() < 4) {
                LessonData.getInstance().setCounter(LessonData.getInstance().getCounter()+1);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartTwo());
                //ft.addToBackStack(null);
                ft.commit();
            }
            else{
                LessonData.getInstance().setCounter(0);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartThree());
                //ft.addToBackStack(null);
                ft.commit();
            }
        }
    }
}
