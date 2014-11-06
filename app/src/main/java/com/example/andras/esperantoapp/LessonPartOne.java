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

public class LessonPartOne extends Fragment implements View.OnClickListener {


    ImageView imageView;
    ImageButton imageButton;
    TextView textView, titleView;
    Button button;
    private String[] lessonInfo1 = {"111", "112", "113", "114", "115", "116",
            "117", "118", "119"};
    private String[] lessonInfo2 = {"121", "122", "123", "124", "125", "126",
            "127", "128", "129"};
    private String[] lessonInfo3 = {"131", "132", "133", "134", "135", "136",
            "137", "138", "139"};
    private String[] lessonInfo4 = {"141", "142", "143", "144", "145", "146",
            "147", "148", "149"};
    String title, phrase, sound, picture;


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
        button = (Button)lessonpart1.findViewById(R.id.buttonPart1);
        button.setOnClickListener(this);
        button.setVisibility(View.INVISIBLE);
        dataFromJson();


        new AsyncTask() {

            public ArrayList<PartData> res;

            @Override
            protected Object doInBackground(Object... params) {
                res = JsonDownload.getInstance().downloadJson("http://pastebin.com/raw.php?i=KfVJNjrX");
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                PartData l = res.get(LessonData.getInstance().getCounter());
                titleView.setText("hej1 "+l.getTitle());
                textView.setText("hej2 "+l.getPhrase());
                System.out.println("Title: "+l.getTitle()+"\n"+"Phrase: "+l.getPhrase());
                
            }

        }.execute();

        return lessonpart1;

    }

    public void dataFromJson(){

        if(LessonData.getInstance().getLessonNumber().equals("Lesson 1")) {
            LessonData.getInstance().setInfo(lessonInfo1[LessonData.getInstance().getCounter()]);
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 2")) {
            LessonData.getInstance().setInfo(lessonInfo2[LessonData.getInstance().getCounter()]);
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 3")) {
            LessonData.getInstance().setInfo(lessonInfo3[LessonData.getInstance().getCounter()]);
        }
        else if(LessonData.getInstance().getLessonNumber().equals("Lesson 4")) {
            LessonData.getInstance().setInfo(lessonInfo4[LessonData.getInstance().getCounter()]);
        }



    }


    public void onClick(View v){

        if(v.equals(imageButton)){

            button.setVisibility(View.VISIBLE);

        }

        else if(v.equals(button)){

            if (LessonData.getInstance().getCounter() < 8) {

                LessonData.getInstance().setCounter(1);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartOne());
                //ft.addToBackStack(null);
                ft.commit();
            }
            else{
                LessonData.getInstance().setCounter(0);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartTwo());
                //ft.addToBackStack(null);
                ft.commit();
            }

        }


    }



}
