package com.example.andras.esperantoapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by j on 05-11-14.
 */
public class App extends Application {
  private String[] lessonUrl = {"http://pastebin.com/raw.php?i=rSXM8DY3"};


  @Override
  public void onCreate() {
    FilCache.init(getCacheDir());
    super.onCreate();


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



  }
}
