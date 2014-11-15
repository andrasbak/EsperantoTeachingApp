package com.example.andras.esperantoapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Andras on 17-10-2014.
 */
public class LessonsPagerAdapter extends FragmentPagerAdapter {

    String[] lessons;

    public LessonsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch(i){

            case 0:
                return new Lesson1Frag();
            case 1:
                return new Lesson2Frag();
            case 2:
                return new Lesson3Frag();
            case 3:
                return new Lesson4Frag();

        }

        return null;

    }



    @Override
    public int getCount() {
        return 4; //No of Tabs
    }
}
