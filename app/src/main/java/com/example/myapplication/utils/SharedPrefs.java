package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kurisani on 16/7/18.
 */

public class SharedPrefs {
    private SharedPreferences sharedPreferences;
    private Context context;

    public interface SharedPrefsVariable{
        String sharedPrefName = "MiniPos";
        String isFirstTimeLaunch = "isFirstTimeLaunch";
        String isLogin = "isLogin";
        String Merchant = "Merchant";
    }

    public SharedPrefs(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SharedPrefsVariable.sharedPrefName, Context.MODE_PRIVATE);
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }

    public void putBoolean(String key,boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    public void putInt(String key,int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public int getInt(String key){
        return sharedPreferences.getInt(key,-1);
    }

    public void putFloat(String key,Float value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key,value);
        editor.commit();
    }

    public float getFloat(String key){
        return sharedPreferences.getFloat(key,0.0f);
    }

}
