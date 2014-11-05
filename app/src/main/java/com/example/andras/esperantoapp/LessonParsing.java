package com.example.andras.esperantoapp;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Andras Bak on 04-Nov-14.
 */
public class LessonParsing {

    static ArrayList<PartData> partData = new ArrayList<PartData>();
    ParseKlipAsyncTask klipAsyncTask = new ParseKlipAsyncTask();


    private static ArrayList<PartData> parseRss(InputStream is) throws Exception {

        String phrase, picture, title, sound;
        JSONObject jsonObject;
        JSONArray jsonArray;


        try {
            // Locate the NodeList name
            jsonArray = jsonObject.getJSONArray(LessonData.getInstance().getLessonNumber());
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                PartData dataObject = new PartData();
//                    if(jsonobject.optString("part").toString().equals("2") && jsonobject.optString("opg").equals("1")) {
                if (jsonObject.optString("id").toString().equals(LessonData.getInstance().getInfo())) {
                    System.out.println("Titel: " + jsonObject.optString("title"));
                    System.out.println("Picture: " + jsonObject.optString("picture"));
                    System.out.println("Sound: " + jsonObject.optString("sound"));
                    System.out.println("Phrase: " + jsonObject.optString("phrase"));

                    String mDrawableName = jsonObject.optString("picture").toString();
                    //System.out.println("KKKKKKKKKKKKKKKKKKKKk         " + mDrawableName);
                    //int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
                    //img = resID;
                    title = (jsonObject.optString("title"));
                    picture = (jsonObject.optString("picture"));
                    phrase = (jsonObject.optString("phrase"));
                    sound = (jsonObject.optString("sound"));
                    //String lol = "";
                }
                //System.out.println("OBJECT: " + jsonObject);
                dataObject.setPhrase(jsonObject.optString("phrase"));
                dataObject.setPicture(jsonObject.optString("picture"));
                dataObject.setSound(jsonObject.optString("sound"));
                dataObject.setTitle(jsonObject.optString("title"));
                partData.add(dataObject);

                // Populate spinner with country names
                pdata.add(jsonObject.optString("title"));


                return null;
            }
        }
        catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }



            public class ParseKlipAsyncTask extends AsyncTask {

                @Override
                protected Object doInBackground(Object... arg0) {
                    try {

                        InputStream is;

                        is = new FileInputStream(FilCache.hentFil("http://pastebin.com/raw.php?i=KfVJNjrX", false));

                        ArrayList<PartData> pData = parseRss(is);
                        is.close();
                        partData.addAll(pData);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    publishProgress();
                    for (PartData k : partData) {
                        try {
                            System.out.println("Title: " + k.getTitle());
                            System.out.println("Phrase: " + k.getPhrase());
                            System.out.println("Picture: " + k.getPicture());
                            System.out.println("Sound: " + k.getSound());
//                    if (k.thumburl != null) {
//                        k.thumb = BitmapFactory.decodeFile(FilCache.hentFil(k.thumburl, true));
//                    }
                            publishProgress();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    return "ok";
                }

                @Override
                protected void onProgressUpdate(Object... values) {

                }
            }
}

