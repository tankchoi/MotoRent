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

    public void login(LoginRequest request, AuthCallback callback) {
        prefs.saveLogin(request.getEmail(), request.getPassword());

        apiService.login(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess();
                } else {
                    try {
                        String error = response.errorBody() != null ? response.errorBody().string() : "Lỗi không xác định";
                        callback.onError(error);
                    } catch (IOException e) {
                        callback.onError("Không thể đọc lỗi từ máy chủ");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    public interface AuthCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
}
