package com.example.andras.esperantoapp.ui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.andras.esperantoapp.App;
import com.example.andras.esperantoapp.R;

import org.json.JSONObject;

public class MainActivity extends FragmentActivity
{
    ViewPager viewPager;
    LessonsPagerAdapter lessonsPagerAdapter;

    /*
    Created by Andras on 17-10-2014.

    this class creates our main window view pages, that enables us to swipe between the different
    lessons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lessonsPagerAdapter = new LessonsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(lessonsPagerAdapter);

        for (JSONObject l : App.lessons)
        {
          String title = l.optString("title");
        }
    }


    public static class LessonsPagerAdapter extends FragmentPagerAdapter
    {
        public LessonsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return App.lessons.get(position).optString("title");
        }

        // creates the fragments displayed in the viewPager
        @Override
        public Fragment getItem(int i)
        {
            LessonFrag f = new LessonFrag();
            Bundle b = new Bundle();
            b.putInt("lesson", i);
            f.setArguments(b);
            return f;
        }

        @Override
        public int getCount()
        {
            return App.lessons.size(); //No of Tabs
        }
    }
}
