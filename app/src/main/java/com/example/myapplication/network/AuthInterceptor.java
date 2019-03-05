package com.example.myapplication.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    String token;

    public AuthInterceptor(String token){
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(token !=null && !token.isEmpty()){//essentially checking if the prefs has a non null token
            request = request.newBuilder()
                    .header("Authorization", token)
                    .build();
        }
        Response response = chain.proceed(request);
        return response;
    }
}
