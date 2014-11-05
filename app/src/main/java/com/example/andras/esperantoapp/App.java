package com.example.andras.esperantoapp;

import android.app.Application;

/**
 * Created by j on 05-11-14.
 */
public class App extends Application {
  @Override
  public void onCreate() {
    FilCache.init(getCacheDir());
    super.onCreate();
  }
}
