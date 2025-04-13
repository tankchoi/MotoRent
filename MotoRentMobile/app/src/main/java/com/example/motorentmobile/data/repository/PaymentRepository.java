package com.example.motorentmobile.data.repository;

import android.content.Context;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.RentalRequest;

import java.util.Map;

import retrofit2.Call;

public class PaymentRepository {
    private final ApiService apiService;

    public PaymentRepository(Context context) {
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public Call<Map<String, String>> createPayment(RentalRequest dto) {
        return apiService.createPayment(dto);
    }
}
