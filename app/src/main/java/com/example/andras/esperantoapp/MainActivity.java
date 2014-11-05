package com.example.andras.esperantoapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;



public class MainActivity extends FragmentActivity {


    ViewPager viewPager;
    LessonsPagerAdapter lessonsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonsPagerAdapter = new LessonsPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(lessonsPagerAdapter);
        viewPager.setPageMargin(-100);

    }




}
