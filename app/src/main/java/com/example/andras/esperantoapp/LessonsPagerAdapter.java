package com.example.andras.esperantoapp;

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
                return new LessonOne();
            case 1:
                return new LessonTwo();
            case 2:
                return new LessonThree();
            case 3:
                return new LessonFour();

        }

        return null;

    }



    @Override
    public int getCount() {
        return 4; //No of Tabs
    }
}
