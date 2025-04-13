package com.example.motorentmobile.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.Account;
import com.example.motorentmobile.data.model.UpdateAccount;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private final ApiService apiService;
    private static final String TAG = "AccountRepository";

    public AccountRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }

    // Get account info from API with callback
    public void getAccountInfo(AccountCallback callback) {
        apiService.getCustomerInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body()); // Gọi callback.onSuccess với account data
                } else {
                    callback.onFailure(); // Gọi callback.onFailure nếu không lấy được dữ liệu
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                callback.onFailure(); // Gọi callback.onFailure khi thất bại
            }
        });
    }

    // Callback interface for account fetch result
    public interface AccountCallback {
        void onSuccess(Account account);
        void onFailure();
    }

    // Update account info via API
    public void updateAccount(
            RequestBody email,
            RequestBody fullName,
            RequestBody phone,
            MultipartBody.Part identityCardImage,
            MultipartBody.Part driverLicenseImage,
            UpdateCallback callback
    ) {
        apiService.updateAccount(email, fullName, phone, identityCardImage, driverLicenseImage)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure(new Exception("Update failed with code " + response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        callback.onFailure(t);
                    }
                });
    }

    public interface UpdateCallback {
        void onSuccess();
        void onFailure(Throwable t); // truyền lỗi cụ thể

        void onFailure();
    }



    public LiveData<Account> getAccountLiveData() {
        MutableLiveData<Account> data = new MutableLiveData<>();
        apiService.getCustomerInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
