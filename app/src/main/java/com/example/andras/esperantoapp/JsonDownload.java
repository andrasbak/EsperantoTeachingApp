package com.example.andras.esperantoapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Andras Bak on 31-Oct-14.
 */
public class JsonDownload {

    private static JsonDownload jsonDownload;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<PartData> partDataList;
    ArrayList<String> pdata;
    public String correct;
    public InputStream inputStream;
    public String sound;

    private JsonDownload() {
    }

    public static JsonDownload getInstance() {

        if (jsonDownload == null) {

            jsonDownload = new JsonDownload();

        }

        return jsonDownload;

    }

    public String downloadJson() {

        new AsyncTask() {
            private String phrase, picture, title, sound;
            private int img;

            @Override
            protected Object doInBackground(Object... params) {
                partDataList = new ArrayList<PartData>();
                pdata = new ArrayList<String>();
                // JSON file URL address
                jsonObject = JsonParser
                        .getJSONfromURL("https://api.myjson.com/bins/4tpyv");
                try {
                    // Locate the NodeList name
                    jsonArray = jsonObject.getJSONArray(LessonData.getInstance().getLessonNumber());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        PartData partData = new PartData();
//                    if(jsonobject.optString("part").toString().equals("2") && jsonobject.optString("opg").equals("1")) {
                        if(jsonObject.optString("id").toString().equals(LessonData.getInstance().getInfo())){
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
                        partData.setPhrase(jsonObject.optString("phrase"));
                        partData.setPicture(jsonObject.optString("picture"));
                        partData.setSound(jsonObject.optString("sound"));
                        partData.setTitle(jsonObject.optString("title"));
                        partDataList.add(partData);

                        // Populate spinner with country names
                        pdata.add(jsonObject.optString("title"));
                    }

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void args) {


            }

        }.execute();


    }

}