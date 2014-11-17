package com.example.andras.esperantoapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by j on 05-11-14.
 */
public class App extends Application {


  private String[] lessonUrl = {"http://pastebin.com/raw.php?i=rSXM8DY3"};
  public static SharedPreferences prefs;
  public static JSONObject grundata;
  public static ArrayList<JSONObject> lessons;


  @Override
  public void onCreate() {
    Log.d("ESPERANTO", " =================== START =======================");
    FilCache.init(getCacheDir());
    super.onCreate();

    prefs = PreferenceManager.getDefaultSharedPreferences(this);

    String grundataStr = prefs.getString("grunddata", null);
    if (grundataStr==null) grundataStr = JsonParser.inputStreamToString(getResources().openRawResource(R.raw.grunddata));

    try {
      grundata = new JSONObject(grundataStr);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    hentLessonsUdenNetværk();


    // Download and Parse JSON

    new AsyncTask() {

      @Override
      protected Object doInBackground(Object... params) {

        hentLessonsNetværk();
        hentPictureandSound();
        return null;
      }
      @Override
      protected void onPostExecute(Object o){
        Toast.makeText(getApplicationContext(),"Det nyeste er nu hentet", Toast.LENGTH_SHORT).show();

      }
    }.execute();

  }

  private void hentLessonsUdenNetværk() {
    ArrayList<JSONObject> lessions = new ArrayList<JSONObject>();
    try {
      JSONArray lessonsjson = grundata.getJSONArray("lessons");
      for (int i=0; i<lessonsjson.length(); i++) {
        JSONObject l = lessonsjson.getJSONObject(i);
        String url = l.getString("url");
        File f = new File(FilCache.findLokaltFilnavn(url));
        String lekData;
        if (f.exists()) lekData = JsonParser.inputStreamToString(new FileInputStream(f));
        else {
          String fallback = l.getString("fallback");
          int id = this.getResources().getIdentifier(fallback, "raw", this.getPackageName());
          Log.d("ESPERANTO", "Bruger fallback "+fallback+" med id "+id);
          lekData = JsonParser.inputStreamToString(getResources().openRawResource(id));
        }
        Log.d("ESPERANTO", "lekdata "+i+": "+lekData);
        lessions.add(new JSONObject(lekData));
      }
      lessons = lessions;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

      private void hentLessonsNetværk() {
            ArrayList<JSONObject> lessions = new ArrayList<JSONObject>();
            try {
                  JSONArray lessonsjson = grundata.getJSONArray("lessons");
                  for (int i=0; i<lessonsjson.length(); i++) {
                        JSONObject l = lessonsjson.getJSONObject(i);
                        String url = l.getString("url");
                        File f = new File(FilCache.hentFil(url, false));
                        String lekData = JsonParser.inputStreamToString(new FileInputStream(f));
                        try {
                              lessions.add(new JSONObject(lekData));
                        } catch (Exception e) {
                              e.printStackTrace();
                              f.delete(); // Fejl i parsning - slet filen
                        }
                  }
                      lessons = lessions;
            } catch (Exception e) {
                e.printStackTrace();
            }
      }

    private void hentPictureandSound(){
        ArrayList<String> pictureSound = new ArrayList<String>();

        for (JSONObject l : App.lessons) {
            pictureSound.add(l.optString("title"));
            pictureSound.add(l.optString("sound"));
        }

        for(int i = 0; i < pictureSound.size(); i++){

            String lokalHentetFil = null;
            FileInputStream is;

            try {
                lokalHentetFil = FilCache.hentFil(pictureSound.get(i), true);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
