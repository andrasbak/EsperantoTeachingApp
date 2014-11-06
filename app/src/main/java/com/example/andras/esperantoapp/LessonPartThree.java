package com.example.andras.esperantoapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class LessonPartThree extends Fragment implements View.OnClickListener {


    TextView textView, titleView;
    Button button1, button2, buttonContinue ;

    private String[] lessonInfo1 = {"131", "132", "133", "134", "135"};
    private String[] lessonInfo2 = {"231", "232", "233", "234", "235"};
    private String[] lessonInfo3 = {"331", "332", "333", "334", "335"};
    private String[] lessonInfo4 = {"431", "432", "433", "434", "435"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonPart3 = inflater.inflate(R.layout.fragment_lesson_part3, container, false);

        buttonContinue = (Button)lessonPart3.findViewById(R.id.buttonPart3);
        buttonContinue.setOnClickListener(this);
        buttonContinue.setVisibility(View.INVISIBLE);
        button1 = (Button)lessonPart3.findViewById(R.id.buttonPart3_1);
        button1.setOnClickListener(this);
        button2 = (Button)lessonPart3.findViewById(R.id.buttonPart3_2);
        button2.setOnClickListener(this);
        textView = (TextView)lessonPart3.findViewById(R.id.textViewPart3);
        titleView = (TextView)lessonPart3.findViewById(R.id.titlePart3);

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
                textView.setText(l.getPhrase());
                button1.setText(l.getChoise1());
                button2.setText(l.getChoise2());
                LessonData.getInstance().setCorrect(l.getCorrect());

            }

        }.execute();


        return lessonPart3;

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
                buttonContinue.setVisibility(View.VISIBLE);
            }
        }
        else if(v.equals(button2)){
            if(button2.getText()==LessonData.getInstance().getCorrect()){
                buttonContinue.setVisibility(View.VISIBLE);
            }
        }

        else if(v.equals(buttonContinue)){
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
