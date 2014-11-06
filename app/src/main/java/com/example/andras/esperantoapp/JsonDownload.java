package com.example.andras.esperantoapp;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Andras Bak on 31-Oct-14.
 */
public class JsonDownload {

    private static JsonDownload jsonDownload;

    private JsonDownload() {
    }
    public static JsonDownload getInstance() {

        if (jsonDownload == null) {
            jsonDownload = new JsonDownload();
        }
        return jsonDownload;
    }

    public ArrayList<PartData> downloadJson(final String url) {
        JSONObject jsonObject;
        JSONArray jsonArray;
        ArrayList<PartData> partDataList;
        partDataList = new ArrayList<PartData>();
        // JSON file URL address
        jsonObject = JsonParser
            .getJSONfromURL(url);
        try {
            // Locate the NodeList name
            jsonArray = jsonObject.getJSONArray(LessonData.getInstance().getLessonNumber());
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                PartData partData = new PartData();

            if(jsonObject.optString("id").toString().equals(LessonData.getInstance().getInfo())){
                System.out.println("Titel: " + jsonObject.optString("title"));
                System.out.println("Picture: " + jsonObject.optString("picture"));
                System.out.println("Sound: " + jsonObject.optString("sound"));
                System.out.println("Phrase: " + jsonObject.optString("phrase"));
                System.out.println("Choise1: " + jsonObject.optString("choise1"));
                System.out.println("Choise2: " + jsonObject.optString("choise2"));
                System.out.println("Choise3: " + jsonObject.optString("choise3"));
                System.out.println("Correct: " + jsonObject.optString("correct"));
                System.out.println("Word1: " + jsonObject.optString("word1"));
                System.out.println("Word2: " + jsonObject.optString("word2"));
                System.out.println("Word3: " + jsonObject.optString("word3"));
                System.out.println("Word4: " + jsonObject.optString("word4"));
                System.out.println("Word5: " + jsonObject.optString("word5"));
                System.out.println("Word6: " + jsonObject.optString("word6"));



            }

            partData.setPhrase(jsonObject.optString("phrase"));
            partData.setPicture(jsonObject.optString("picture"));
            partData.setSound(jsonObject.optString("sound"));
            partData.setTitle(jsonObject.optString("title"));
            partData.setChoise1(jsonObject.optString("choise1"));
            partData.setChoise2(jsonObject.optString("choise2"));
            partData.setChoise3(jsonObject.optString("choise3"));
            partData.setCorrect(jsonObject.optString("correct"));
            partData.setWord1(jsonObject.optString("word1"));
            partData.setWord2(jsonObject.optString("word2"));
            partData.setWord3(jsonObject.optString("word3"));
            partData.setWord4(jsonObject.optString("word4"));
            partData.setWord5(jsonObject.optString("word5"));
            partData.setWord6(jsonObject.optString("word6"));



            partDataList.add(partData);
            }
        }
        catch (Exception e) {
            Log.e("Error Error", e.getMessage());
            e.printStackTrace();
        }
        return partDataList;
    }
}