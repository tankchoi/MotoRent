package com.example.motorentmobile.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.RegisterRequest;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    private final ApiService apiService;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    // Sử dụng Application context để khởi tạo ApiService
    public CustomerRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }

    // Trả về LiveData cho ViewModel quan sát
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }
    // Phương thức đăng ký người dùng
    public void registerUser(RegisterRequest registerRequest) {
        // Tạo các RequestBody từ đối tượng RegisterRequest
        RequestBody emailRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), registerRequest.getEmail());
        RequestBody passwordRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), registerRequest.getPassword());
        RequestBody fullNameRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), registerRequest.getFullName());
        RequestBody phoneRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), registerRequest.getPhone());

        // Tạo MultipartBody.Part cho ảnh
        RequestBody identityCardRequestBody = RequestBody.create(okhttp3.MediaType.parse("image/*"), registerRequest.getIdentityCardImage());
        MultipartBody.Part identityCardImagePart = MultipartBody.Part.createFormData("identityCard",
                registerRequest.getIdentityCardImage().getName(), identityCardRequestBody);

        RequestBody driverLicenseRequestBody = RequestBody.create(okhttp3.MediaType.parse("image/*"), registerRequest.getDriverLicenseImage());
        MultipartBody.Part driverLicenseImagePart = MultipartBody.Part.createFormData("driverLicense",
                registerRequest.getDriverLicenseImage().getName(), driverLicenseRequestBody);

        // Gọi API
        Call<Void> call = apiService.registerUser(emailRequestBody, passwordRequestBody, fullNameRequestBody, phoneRequestBody,
                identityCardImagePart, driverLicenseImagePart);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    isSuccess.setValue(true);
                } else {
                    try {
                        String errorResponse = response.errorBody() != null ? response.errorBody().string() : "Lỗi không xác định";
                        errorMessage.setValue(errorResponse);
                    } catch (Exception e) {
                        errorMessage.setValue("Lỗi đọc phản hồi: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
