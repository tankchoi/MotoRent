package com.example.motorentmobile.data.api;

import com.example.motorentmobile.data.model.LoginRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("customers/login")
    Call<ResponseBody> login(@Body LoginRequest request);
}
