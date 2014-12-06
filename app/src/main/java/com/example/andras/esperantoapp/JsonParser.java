package com.example.andras.esperantoapp;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Thomas on 17-10-2014.
 */

public class JsonParser {

    public static String inputStreamToString(InputStream is) {
        String result = "";
        // Convert response to string
        try {
            // Read text from the character-input stream is, which is the JSON passed from App.
            // InputStreamReader converts bytes stream to characters
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            // Creates StringBuilder sb, so all Strings from JSON-file can be appended to the String.
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Reads every line in the JSON and adds each line to the stringbuilder sb
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            // Sets result to the stringbuilder and converts it to a String.
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        // Returns the complete JSON in String format so filCache can use it do download the files.
        return result;
    }
}