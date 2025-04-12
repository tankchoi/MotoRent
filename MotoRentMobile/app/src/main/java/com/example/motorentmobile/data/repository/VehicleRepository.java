package com.example.motorentmobile.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.Vehicle;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepository {
    private final ApiService apiService;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<List<Vehicle>> vehicleList = new MutableLiveData<>();

    public VehicleRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<Vehicle>> getVehicleList() {
        return vehicleList;
    }

    public void fetchAvailableVehicles(String startTime, String endTime) {
        Call<List<Vehicle>> call = apiService.getAvailableVehicles(startTime, endTime);
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    vehicleList.setValue(response.body());
                } else {
                    errorMessage.setValue("Không thể lấy danh sách xe: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                errorMessage.setValue("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
