package com.example.andras.esperantoapp.ui;

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

public class LessonActivity extends FragmentActivity
{

    ViewPager viewPager;
    LessonPagerAdapter lessonsPagerAdapter;
    ArrayList<JSONObject> småobj = new ArrayList<JSONObject>();
    ArrayList<String> exerciseTitles = new ArrayList<String>();
    private static int synligTilSkærmbilledeNr = 0;
    private JSONObject lessonJson;
    private String lessionTitle, exerciseTitle;

    /*
    Creates the view pager that contains all the exercises as they are created
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int lessonNr = getIntent().getIntExtra("lesson", 0);
        lessonJson = App.lessons.get(lessonNr);
        lessionTitle = lessonJson.optString("title");
        setTitle(lessionTitle);
        //partsarray - objekt - titel,lesson - array - objet - titel,
        JSONArray parts = null;
        try
        {
            parts = lessonJson.getJSONArray("parts");
            for(int i = 0; i < parts.length(); i++)
            {
                JSONObject part = parts.getJSONObject(i);
                JSONArray questions = part.getJSONArray("questions");
                for (int j = 0; j < questions.length(); j++)
                {
                    småobj.add(questions.getJSONObject(j));
                    //System.out.println("SMÅ OBJEKTER: " + småobj.size());
                }
            }
            for(int i = 0; i <= småobj.size(); i++){
                if(i == småobj.size()){
                    exerciseTitle = "Finished";
                }
                else{
                    exerciseTitle = lessionTitle + ", demando " + (i+1) + " el " + småobj.size();
                }
                exerciseTitles.add(exerciseTitle);
            }
        }
        catch (JSONException e)
        {
        e.printStackTrace();
        }

        //synligTilSkærmbilledeNr = 0;
        lessonsPagerAdapter = new LessonPagerAdapter(getSupportFragmentManager());

        if(savedInstanceState == null)addFragment();

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(lessonsPagerAdapter);
    }

    //Increments synligTilSkærmbilledeNr with 1 allowing 1 more
    //fragment to be visible
    public void addFragment()
    {
        synligTilSkærmbilledeNr = synligTilSkærmbilledeNr +1;
    }
    //Changes the current item in the viewPager to the
    //newest fragment
    public void skiftBillede()
    {
        lessonsPagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(synligTilSkærmbilledeNr);
    }

    public void resetIndex()
    {
        synligTilSkærmbilledeNr = 0;
    }

    private class LessonPagerAdapter extends FragmentPagerAdapter
    {
        public LessonPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return synligTilSkærmbilledeNr;
        }

        //Creates the fragments which are displayed in the viewPager.
        //The fragment created is dependant of the type of exercise.
        @Override
        public Fragment getItem(int i)
        {
            Fragment f=null;
            if(i < småobj.size())
            {
            JSONObject json = småobj.get(i);
            System.out.println(json);
            System.out.println("LESSONS: " + App.lessons +"---------------------------------------------------------------");
            String type = json.optString("type", "mellemskærm");
            System.out.println("type: " + type);
            if ("bildodemando".equals(type)) f = new ScreenTypeBildoDemando();
            else if ("demando".equals(type)) f = new ScreenTypeDemando();
            else if ("frazo".equals(type)) f = new ScreenTypeFrazo();
            else if ("vortludoj".equals(type)) f = new ScreenTypeVortludoj();
            Bundle args = new Bundle();
            args.putString("jsondata", json.toString());
            f.setArguments(args);
            }
        else f = new ScreenTypeFinished();
        return f;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
               return exerciseTitles.get(position);
        }
    }
}
