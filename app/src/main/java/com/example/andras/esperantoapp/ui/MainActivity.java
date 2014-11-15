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
      //  viewPager.setPageMargin(-250);

        actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        for (JSONObject l : App.lessons) {
          String title = l.optString("title");
          actionBar.addTab(actionBar.newTab().setText(title).setTabListener(this));
        }

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




  /**
   * Created by Andras on 17-10-2014.
   */
  public static class LessonsPagerAdapter extends FragmentPagerAdapter {

    public LessonsPagerAdapter(FragmentManager fm) {
      super(fm);

    }

    @Override
    public CharSequence getPageTitle(int position) {
      return App.lessons.get(position).optString("title");
    }

    @Override
    public Fragment getItem(int i) {

      LessonFrag f = new LessonFrag();
      Bundle b = new Bundle();
      b.putInt("lesson", i);
      f.setArguments(b);

      return f;

    }



    @Override
    public int getCount() {
      return App.lessons.size(); //No of Tabs
    }
  }
}
