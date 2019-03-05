package com.example.myapplication.network;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkManager {
    BaseApiService apiInterface;
    private final static String TAG = NetworkManager.class.getSimpleName();
    Context context;

    public NetworkManager(Context context,String token){
        this.context = context;
        apiInterface = RetrofitClient.createService(BaseApiService.class,token);
    }

    public NetworkManager(Context context){
        this.context = context;
        apiInterface = RetrofitClient.createService(BaseApiService.class);
    }

    public void getResultList(String deviceID) throws IOException {

        // Sending get request
        URL url = new URL("http://example-url");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+" Actual bearer token issued by provider.");
        //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5jb206OjE6OjkwIiwiZXhwIjoxNTM3MzQyNTIxLCJpYXQiOjE1MzY3Mzc3MjF9.O33zP2l_0eDNfcqSQz29jUGJC-_THYsXllrmkFnk85dNRbAw66dyEKBP5dVcFUuNTA8zhA83kk3Y41_qZYx43T

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());

//
//        Call<List<Vendor>> call = apiInterface.getDetails(deviceID);
//        call.enqueue(new Callback<List<Vendor>>() {
//
//            @Override
//            public void onResponse(Call<List<Vendor>> call, Response<List<Vendor>> response) {
//                //  loadDataList(response.body());
//
//                System.out.println(response.code());
//
//                List vendorList = response.body();
//                System.out.println(vendorList);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Vendor>> call, Throwable throwable) {
//                Toast.makeText(context, "Unable to load vendorList", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
