package com.example.motorentmobile.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.Account;
import com.example.motorentmobile.data.model.UpdateAccount;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private final ApiService apiService;
    private static final String TAG = "AccountRepository";

    // LiveData để theo dõi trạng thái
    private MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AccountRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }

    // Lấy thông tin tài khoản
    public void getAccountInfo(AccountCallback callback) {
        apiService.getCustomerInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    Log.e(TAG, "Fetch failed: code " + response.code());
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(TAG, "Fetch error: " + t.getMessage());
                callback.onFailure();
            }
        });
    }



    // Cập nhật tài khoản
    public void updateUser(UpdateAccount updateRequest) {
        // Tạo các RequestBody từ đối tượng UpdateRequest
        RequestBody emailRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), updateRequest.getEmail());
        RequestBody fullNameRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), updateRequest.getFullName());
        RequestBody phoneRequestBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), updateRequest.getPhone());

        // Tạo MultipartBody.Part cho ảnh CMND/CCCD nếu có
        MultipartBody.Part identityCardImagePart = null;
        if (updateRequest.getIdentityCardImage() != null) {
            RequestBody identityCardRequestBody = RequestBody.create(okhttp3.MediaType.parse("image/*"), updateRequest.getIdentityCardImage());
            identityCardImagePart = MultipartBody.Part.createFormData("identityCard",
                    updateRequest.getIdentityCardImage().getName(), identityCardRequestBody);
        }

        // Tạo MultipartBody.Part cho ảnh giấy phép lái xe nếu có
        MultipartBody.Part driverLicenseImagePart = null;
        if (updateRequest.getDriverLicenseImage() != null) {
            RequestBody driverLicenseRequestBody = RequestBody.create(okhttp3.MediaType.parse("image/*"), updateRequest.getDriverLicenseImage());
            driverLicenseImagePart = MultipartBody.Part.createFormData("driverLicense",
                    updateRequest.getDriverLicenseImage().getName(), driverLicenseRequestBody);
        }

        // Gọi API
        Call<Void> call = apiService.updateAccount(emailRequestBody, fullNameRequestBody, phoneRequestBody,
                identityCardImagePart, driverLicenseImagePart);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Cập nhật thành công");
                    isSuccess.setValue(true);
                } else {
                    try {
                        String errorResponse = response.errorBody() != null ? response.errorBody().string() : "Lỗi không xác định";
                        Log.e(TAG, "Lỗi phản hồi từ server: " + errorResponse);
                        errorMessage.setValue(errorResponse);
                    } catch (Exception e) {
                        Log.e(TAG, "Lỗi khi đọc errorBody: " + e.getMessage());
                        errorMessage.setValue("Lỗi đọc phản hồi: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Lỗi kết nối đến server: " + t.getMessage());
                errorMessage.setValue("Lỗi kết nối: " + t.getMessage());
            }

        });
    }

    // Getter cho LiveData
    public MutableLiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Callback interface
    public interface AccountCallback {
        void onSuccess(Account account);
        void onFailure();
    }

    public interface UpdateCallback {
        void onSuccess();
        void onFailure(Throwable t);
    }
}
