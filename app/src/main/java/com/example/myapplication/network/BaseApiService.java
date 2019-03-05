package com.example.myapplication.network;

import com.example.myapplication.model.Vendor;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    @POST("api/Account/login")
    Call<ResponseBody>  getLoginResponse(@Body HashMap<String, Object> body);

    @GET("api/Vendor/getdevicevendordata/{deviceGuid}")
    Call<ResponseBody> getDetailDosen(@Path("deviceGuid") String deviceGuid);

    @GET("api/Vendor/getdevicevendordata/{deviceGuid}")
    Call<List<Vendor>> getDetails(@Path("deviceGuid") String deviceGuid);
}
