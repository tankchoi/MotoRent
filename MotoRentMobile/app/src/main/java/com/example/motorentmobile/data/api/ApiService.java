package com.example.motorentmobile.data.api;

import com.example.motorentmobile.data.model.LoginRequest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @POST("customers/login")
    Call<ResponseBody> login(@Body LoginRequest request);
    @Multipart
    @POST("customers/register")
    Call<Void> registerUser(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("fullName") RequestBody fullName,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part identityCardImage,
            @Part MultipartBody.Part driverLicenseImage
    );
}
