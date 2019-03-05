package com.example.myapplication.network;
import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://dgi.simp.africa/backend/";
    public static final String PDF_URL = "http://minipos.esoft.co.za";
    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String token) {
        AuthInterceptor interceptor = null;
        if (!TextUtils.isEmpty(token)) {
            interceptor =
                    new AuthInterceptor(token);
        }
        if (interceptor != null && !httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
            builder.client(httpClient.build());
        }
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

}
