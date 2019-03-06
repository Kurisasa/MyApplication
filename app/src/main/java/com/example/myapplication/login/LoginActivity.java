package com.example.myapplication.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.network.BaseApiService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.session.AlertDialogManager;
import com.example.myapplication.session.SessionManager;
import com.example.myapplication.utils.LocaleHelper;
import com.example.myapplication.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = LoginActivity.class.getSimpleName();
    private View view;
    private ProgressDialog progressDialog;
    private EditText et_username,et_email_id,et_password;
    private TextView login_btn,tv_forget_password;
    String input_merchant_code;
    AlertDialog alertDialog;
    private DatabaseHelper databaseHelper;
    Context mContext;
    BaseApiService mApiService;
    ProgressDialog loading;
    String token;
    // Session Manager Class
    SessionManager session;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Session Manager
        session = new SessionManager(getApplicationContext());
        init();
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    private void init(){
        //loginPresenter = new LoginPresenterImpl(this,this);
        view = findViewById(R.id.parent);
        et_username = findViewById(R.id.et_username);
        et_email_id = findViewById(R.id.et_email_id);
        et_password = findViewById(R.id.et_password);
        login_btn = findViewById(R.id.login_btn);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        login_btn.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        getScreenDimensions();
        databaseHelper = new DatabaseHelper(this);
        mContext = this;
        //mApiService = UtilsApi.getAPIService();
        mApiService = RetrofitClient.createService(BaseApiService.class,token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                String email_id = et_email_id.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                loading = ProgressDialog.show(this, null, "Login user..", true, false);
                requestLogin(email_id,password);
                break;

            case R.id.tv_forget_password:
                showForgetPasswordDialog();
        }
    }

    private void requestLogin(String username, String password){

        HashMap<String,Object> objectHashMap = new HashMap<>();

        objectHashMap.put("username",username);
        objectHashMap.put("password",password);
        objectHashMap.put("languageCode", LocaleHelper.getLanguage(this));
        objectHashMap.put("ip","");

        mApiService.getLoginResponse(objectHashMap)

                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                String fullname = jsonRESULTS.getString("fullname");
                                String languageCode = jsonRESULTS.getString("languageCode");
                                String token =  jsonRESULTS.getString("token");
                                String username = jsonRESULTS.getString("username");
                                // Creating user login session
                                // For testing i am stroing name, email as follow
                                // Use user real data
                                session.createLoginSession(fullname, languageCode,token,username);

                                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }


    private void showForgetPasswordDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.forgot_password);
        builder.setMessage(R.string.enter_merchant_code);

        final EditText input = new EditText(LoginActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setIcon(R.drawable.login_logo);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.send, null);
        builder.setNegativeButton(R.string.cancel,null);
        alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        input_merchant_code = input.getText().toString();
                        if (!input_merchant_code.isEmpty()) {
                                /*Toast.makeText(getApplicationContext(),
                                        "Password Matched", Toast.LENGTH_SHORT).show();*/
                            //loginPresenter.forgetPassowrd(input_merchant_code);
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    R.string.please_enter_merchant_code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Button negative = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        alertDialog.cancel();
                    }
                });

            }
        });

        alertDialog.show();
    }

    public void getScreenDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.heightPixels;
        int screenHeight = displayMetrics.widthPixels;
        Logger.d(MainActivity.class.getSimpleName(),"Width: " + screenWidth + ", Height: " + screenHeight);
    }
}
