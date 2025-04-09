package com.example.motorentmobile.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.LoginRequest;
import com.example.motorentmobile.util.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;
    private final SharedPreferencesHelper prefs;
    public AuthRepository(Context context) {
        apiService = ApiClient.getClient(context).create(ApiService.class);
        prefs = SharedPreferencesHelper.getInstance(context);
    }
    public MutableLiveData<Boolean> login(LoginRequest request) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        prefs.saveLogin(request.getEmail(), request.getPassword());

        apiService.login(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    result.setValue(true);
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Log.e("AuthRepository", "Login failed: " + errorBody);
                    } catch (IOException e) {
                        Log.e("AuthRepository", "Error reading errorBody", e);
                    }
                    result.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("AuthRepository", "Login request failed", t);
                result.setValue(false);
            }
        });

        return result;
    }

}
