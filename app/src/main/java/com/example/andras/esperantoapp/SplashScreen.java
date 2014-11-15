package com.example.andras.esperantoapp;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;

import org.json.JSONArray;
import org.json.JSONObject;

public class SplashScreen extends Activity {

    // Set Duration of the Splash Screen
    long Delay = 5000;
    private String[] lessonUrl = {"http://pastebin.com/raw.php?i=rSXM8DY3"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get the view from splash_screen.xml
        setContentView(R.layout.activity_splash_screen);

        // Create a Timer
        Timer RunSplash = new Timer();

        // Download and Parse JSON

        new AsyncTask() {

            public ArrayList<PartData> res;

            @Override
            protected Object doInBackground(Object... params) {

                for(int i=0; i < lessonUrl.length; i++) {

                    res = JsonDownload.getInstance().downloadJson(lessonUrl[i]);

                }
                return null;
            }
            @Override
            protected void onPostExecute(Object o){

                new AsyncTask(){

                    protected  Object doInBackground(Object... params){

                        for(int i = 0; i < JsonDownload.getInstance().pictureSound.size(); i++) {

                            JsonDownload.getInstance().downloadPictureSound();

                        }
                        return null;
                    }
                }.execute();
            }
        }.execute();



        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                // Start MainActivity.class
                Intent myIntent = new Intent(SplashScreen.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);

    }



}