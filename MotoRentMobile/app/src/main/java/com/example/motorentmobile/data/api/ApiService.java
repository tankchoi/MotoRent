package com.example.motorentmobile.data.api;

import com.example.motorentmobile.data.model.Account;
import com.example.motorentmobile.data.model.LoginRequest;
import com.example.motorentmobile.data.model.Notification;
import com.example.motorentmobile.data.model.Rental;
import com.example.motorentmobile.data.model.RentalRequest;
import com.example.motorentmobile.data.model.Vehicle;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("api/customers/customer-info")
    Call<Account> getCustomerInfo();

    @Multipart
    @PUT("api/customers/update")
    Call<Void> updateAccount(
            @Part("email") RequestBody email,
            @Part("fullName") RequestBody fullName,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part identityCardImage,
            @Part MultipartBody.Part driverLicenseImage
    );

    @POST("api/payment/vnpay/create")
    Call<Map<String, String>> createPayment(@Body RentalRequest dto);

    @GET("api/notifications")
    Call<List<Notification>> getNotifications();

    @GET("api/rentals/customer-rentals")
    Call<List<Rental>> getRentals();
}
