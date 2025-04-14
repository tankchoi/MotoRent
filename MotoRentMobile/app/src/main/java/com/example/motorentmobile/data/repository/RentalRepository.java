package com.example.motorentmobile.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.Rental;
import com.example.motorentmobile.data.api.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalRepository {
    private final ApiService apiService;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<List<Rental>> rentalList = new MutableLiveData<>();
    public RentalRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    public LiveData<List<Rental>> getRentalList() {
        return rentalList;
    }
    public void fetchRentalList() {
        Call<List<Rental>> call = apiService.getRentals();
        call.enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rentalList.setValue(response.body());
                } else {
                    errorMessage.setValue("Không thể lấy danh sách xe: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                errorMessage.setValue("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

}
