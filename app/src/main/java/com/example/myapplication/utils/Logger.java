package com.example.myapplication.utils;

import android.util.Log;

public class Logger {

    public static void i(String TAG, String message){
        if (message != null && !message.isEmpty())
            Log.e(TAG,message);
    }

    public static void d(String TAG, String message){
        if (message != null && !message.isEmpty())
            Log.e(TAG,message);
    }

    public static void e(String TAG, String message){
        if (message != null && !message.isEmpty())
            Log.e(TAG,message);
    }
}
