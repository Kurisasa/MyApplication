package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.utils.LocaleHelper;


/**
 * Created by manish on 16/7/18.
 */

public class MiniPosApp extends Application {
    public static final String TAG = MiniPosApp.class
            .getSimpleName();
    private static MiniPosApp miniPosApp;
    private String pdfUrl = "";

    @Override
    public void onCreate() {
        super.onCreate();
        miniPosApp = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public static synchronized MiniPosApp getInstance() {
        return miniPosApp;
    }

    public void logout(){

    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
