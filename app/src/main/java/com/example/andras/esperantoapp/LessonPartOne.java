package com.example.andras.esperantoapp;


import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class LessonPartOne extends Fragment implements View.OnClickListener {


    ImageView imageView;
    ImageButton imageButton;
    TextView textView, titleView;
    Button button;
    private String[] lessonInfo1 = {"111", "112", "113", "114", "115", "116",
            "117", "118", "119"};
    private String[] lessonInfo2 = {"211", "212", "213", "214", "215", "216",
            "217", "218", "219"};
    private String[] lessonInfo3 = {"311", "312", "313", "314", "315", "316",
            "317", "318", "319"};
    private String[] lessonInfo4 = {"411", "412", "413", "414", "415", "416",
            "417", "418", "419"};


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

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dataFromJson();


        new AsyncTask() {

            public ArrayList<PartData> res;

            @Override
            protected Object doInBackground(Object... params) {
                res = JsonDownload.getInstance().downloadJson(LessonData.getInstance().
                        getLessonUrl());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                System.out.println("Counter: "+LessonData.getInstance().getCounter());
                System.out.println("Counter: "+LessonData.getInstance().getDataCounter());
                final PartData l = res.get(LessonData.getInstance().getDataCounter());

                new DownloadImageTask(imageView).execute(l.getPicture());

                titleView.setText(l.getTitle());
                textView.setText(l.getPhrase());


                
            }

        }.execute();

        return lessonpart1;

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

        if(v.equals(imageButton)){

            button.setVisibility(View.VISIBLE);

        }

        else if(v.equals(button)){

            if (LessonData.getInstance().getCounter() < 8) {

                LessonData.getInstance().setCounter(LessonData.getInstance().getCounter() + 1);
                LessonData.getInstance().setDataCounter(LessonData.getInstance().
                        getDataCounter() + 1);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartOne());
                //ft.addToBackStack(null);
                ft.commit();
            }
            else{
                LessonData.getInstance().setCounter(0);
                LessonData.getInstance().setDataCounter(LessonData.getInstance().
                        getDataCounter() + 1);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartTwo());
                //ft.addToBackStack(null);
                ft.commit();
            }

        }


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }



}
