package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;
import com.example.myapplication.utils.LocaleHelper;
import com.example.myapplication.utils.Logger;
import com.example.myapplication.utils.SharedPrefs;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SelectLanguageActivity extends AppCompatActivity {
    private final static String TAG = SelectLanguageActivity.class.getSimpleName();
    TextView save_btn;
    NiceSpinner spinner;
    ArrayAdapter<String> mAdapter;
    SharedPrefs sharedPrefs;
    MaterialSpinner materialSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        save_btn = findViewById(R.id.save_btn);
        spinner = findViewById(R.id.spLanguage);
        materialSpinner = findViewById(R.id.spinner);
        sharedPrefs = new SharedPrefs(this);
        List<String> dataset = new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.language_option)));
        spinner.attachDataSource(dataset);

        materialSpinner.setItems("Select Language", "English", "French");
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Context context;
                Resources resources;
                Logger.d(TAG,"Position "+ position);
                switch (position) {
                    case 1:
                        context = LocaleHelper.setLocale(SelectLanguageActivity.this, "en");
                        resources = context.getResources();
                        proceed();
                        break;
                    case 2:
                        context = LocaleHelper.setLocale(SelectLanguageActivity.this, "fr");
                        resources = context.getResources();
                        proceed();
                        break;
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });
    }


    private void proceed(){
        Logger.d(TAG,LocaleHelper.getLanguage(this));
        LocaleHelper.setLanguageSelected(SelectLanguageActivity.this,true);
        if (sharedPrefs.getBoolean(SharedPrefs.SharedPrefsVariable.isLogin)){
            Intent intent = new Intent(SelectLanguageActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SelectLanguageActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
