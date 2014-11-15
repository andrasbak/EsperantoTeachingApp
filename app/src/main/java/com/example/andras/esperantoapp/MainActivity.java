package com.example.andras.esperantoapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener{


    ViewPager viewPager;
    LessonsPagerAdapter lessonsPagerAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonsPagerAdapter = new LessonsPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(lessonsPagerAdapter);
        viewPager.setPageMargin(-100);

        actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Lesson 1").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Lesson 2").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Lesson 3").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Lesson 4").setTabListener(this));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected ( int arg0){
                actionBar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

            });



    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }


}
