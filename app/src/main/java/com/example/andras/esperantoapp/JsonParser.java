package com.example.andras.esperantoapp;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Thomas on 17-10-2014.
 */

public class JsonParser {

    public static JSONObject getJSONfromURL(String url) {
        InputStream is = null;
        JSONObject jArray = null;

        // Download JSON data from URL
        try {

          String lokalHentetFil = FilCache.hentFil(url, false);
          is = new FileInputStream(lokalHentetFil);

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
      String result = inputStreamToString(is);


      try {

            jArray = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jArray;
    }

  public static String inputStreamToString(InputStream is) {
    String result = "";
    // Convert response to string
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        result = sb.toString();
    } catch (Exception e) {
        Log.e("log_tag", "Error converting result " + e.toString());
    }
    return result;
  }
}