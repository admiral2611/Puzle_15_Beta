package com.example.puzle_15_beta;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Timer;

public class AppCache{

    private static final String TIME_KEY = "time_key";
    private static final String Name_KEY = "name_key";
    private static AppCache appCache;
    private static SharedPreferences preferences;
    private AppCache(Context context) {
        preferences=context.getSharedPreferences("puzzle_15_Beta", Context.MODE_PRIVATE);
    }
    public  static  void  init(Context context){
        if (appCache==null){
            appCache=new AppCache(context);
        }
    }

    public static AppCache getObject() {
        return appCache;
    }

    public void saveTime(String timeCount){
        preferences.edit().putString(TIME_KEY,timeCount).apply();
    }


    public  String   getTime(){
        return preferences.getString(TIME_KEY,"0");
    }

    public void saveName(String name){
        preferences.edit().putString(Name_KEY,name).apply();
    }

    public  String   getName(){
        return preferences.getString(Name_KEY,"user");
    }



}
