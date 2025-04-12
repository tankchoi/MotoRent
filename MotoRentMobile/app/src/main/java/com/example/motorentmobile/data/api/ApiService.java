package com.example.motorentmobile.data.api;

import com.example.motorentmobile.data.model.LoginRequest;
import com.example.motorentmobile.data.model.Vehicle;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/customers/login")
    Call<ResponseBody> login(@Body LoginRequest request);
    @Multipart
    @POST("api/customers/register")
    Call<Void> registerUser(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("fullName") RequestBody fullName,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part identityCardImage,
            @Part MultipartBody.Part driverLicenseImage
    );
    @GET("api/vehicles/available")
    Call<List<Vehicle>> getAvailableVehicles(
            @Query("startTime") String startTime,
            @Query("endTime") String endTime
    );
}
