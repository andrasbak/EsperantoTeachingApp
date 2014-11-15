package com.example.andras.esperantoapp.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.andras.esperantoapp.App;
import com.example.andras.esperantoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LessonActivity extends FragmentActivity {


  ViewPager viewPager;
  LessonPagerAdapter lessonsPagerAdapter;

  ArrayList<JSONObject> skærme = new ArrayList<JSONObject>();
  private int synligTilSkærmbilledeNr;
  private JSONObject lessonJson;
  private String lessionTitle;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    setContentView(R.layout.activity_main);

    int lessonNr = getIntent().getIntExtra("lesson", 0);
    lessonJson = App.lessons.get(lessonNr);
    lessionTitle = lessonJson.optString("title");
    setTitle(lessionTitle);

    JSONArray parts = null;
    try {
      parts = lessonJson.getJSONArray("parts");
      String titel = lessonJson.getString("title");
      skærme.add(new JSONObject().put("title", lessionTitle + " " + titel));
      for (int partsNr = 0; partsNr < parts.length(); partsNr++) {
        JSONObject part = parts.getJSONObject(partsNr);
        skærme.add(part);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    synligTilSkærmbilledeNr = 1;
    lessonsPagerAdapter = new LessonPagerAdapter(getSupportFragmentManager());

    viewPager = (ViewPager) findViewById(R.id.pager);
    viewPager.setAdapter(lessonsPagerAdapter);
    //  viewPager.setPageMargin(-250);

  }


  private class LessonPagerAdapter extends FragmentPagerAdapter {

    public LessonPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public int getCount() {
      return synligTilSkærmbilledeNr;
    }

    @Override
    public Fragment getItem(int i) {
      Fragment f;
      JSONObject json = skærme.get(i);
      String type = json.optString("type", "mellemskærm");
      if ("bildodemando".equals(type)) f = new ScreenTypeBildoDemando();
      else if ("demando".equals(type)) f = new ScreenTypeDemando();
      else if ("frazo".equals(type)) f = new ScreenTypeFrazo();
      else f = new ScreenTypeNyPart();
      Bundle args = new Bundle();
      args.putString("jsondata", json.toString());
      f.setArguments(args);
      return f;
    }
  }
}
