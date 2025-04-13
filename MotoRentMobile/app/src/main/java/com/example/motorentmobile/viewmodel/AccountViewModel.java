package com.example.motorentmobile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.Account;
import com.example.motorentmobile.data.repository.AccountRepository;
import com.example.motorentmobile.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccountViewModel extends AndroidViewModel {

    private final AccountRepository repository;
    private final MutableLiveData<Account> account = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> updateSuccess = new MutableLiveData<>();

    public AccountViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
    }

    public LiveData<Account> getAccount() {
        return account;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getUpdateSuccess() {
        return updateSuccess;
    }

    // Tải thông tin tài khoản
    public void fetchAccountInfo() {
        isLoading.setValue(true);
        repository.getAccountInfo(new AccountRepository.AccountCallback() {
            @Override
            public void onSuccess(Account accountData) {
                isLoading.postValue(false);
                account.postValue(accountData);

                loadAndSaveImage(accountData.getIdentityCard());
                loadAndSaveImage(accountData.getDriverLicense());
            }

            @Override
            public void onFailure() {
                isLoading.postValue(false);
                errorMessage.postValue("Không thể tải thông tin tài khoản.");
            }
        });
    }

    // Tải và lưu ảnh từ URL
    private void loadAndSaveImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) return;

        new Thread(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                Uri imageUri = saveBitmapToCache(getApplication(), bitmap, "image_" + System.currentTimeMillis() + ".jpg");

                Account currentAccount = account.getValue();
                if (currentAccount != null) {
                    if (imageUrl.equals(currentAccount.getIdentityCard())) {
                        currentAccount.setIdentityCard(imageUri.toString());
                    } else if (imageUrl.equals(currentAccount.getDriverLicense())) {
                        currentAccount.setDriverLicense(imageUri.toString());
                    }
                    account.postValue(currentAccount);
                }
            } catch (Exception e) {
                Log.e("AccountViewModel", "Error loading image: " + e.getMessage());
            }
        }).start();
    }

    // Cập nhật tài khoản
    public void updateAccountInfo(String email, String fullName, String phone, Uri identityCardUri, Uri driverLicenseUri) {
        try {
            Log.d("AccountViewModel", "Updating account with email: " + email);
            Log.d("AccountViewModel", "Identity Card Uri: " + identityCardUri);
            Log.d("AccountViewModel", "Driver License Uri: " + driverLicenseUri);
            File identityFile = identityCardUri != null ? getFileFromUri(getApplication(), identityCardUri) : null;
            File licenseFile = driverLicenseUri != null ? getFileFromUri(getApplication(), driverLicenseUri) : null;

            RequestBody emailBody = toRequestBody(email);
            RequestBody fullNameBody = toRequestBody(fullName);
            RequestBody phoneBody = toRequestBody(phone);

            MultipartBody.Part identityPart = toMultipartBody("identityCardImage", identityFile);
            MultipartBody.Part licensePart = toMultipartBody("driverLicenseImage", licenseFile);
            if (identityFile != null) {
                Log.d("AccountViewModel", "Identity card file path: " + identityFile.getAbsolutePath());
            } else {
                Log.d("AccountViewModel", "Identity card file is null");
            }
            if (licenseFile != null) {
                Log.d("AccountViewModel", "Driver license file path: " + licenseFile.getAbsolutePath());
            } else {
                Log.d("AccountViewModel", "Driver license file is null");
            }

            repository.updateAccount(emailBody, fullNameBody, phoneBody, identityPart, licensePart, new AccountRepository.UpdateCallback() {
                @Override
                public void onSuccess() {
                    updateSuccess.postValue(true);
                }

                @Override
                public void onFailure(Throwable t) {
                    errorMessage.postValue("Lỗi: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            errorMessage.setValue("Lỗi khi xử lý ảnh: " + e.getMessage());
        }
    }

    // Chuyển đổi String thành RequestBody
    private RequestBody toRequestBody(String value) {
        return RequestBody.create(value, okhttp3.MediaType.parse("text/plain; charset=UTF-8"));
    }

    // Chuyển đổi Uri thành File và sau đó thành MultipartBody.Part
    private MultipartBody.Part toMultipartBody(String partName, File file) {
        if (file == null) {
            return null;
        }

        RequestBody requestBody = RequestBody.create(file, okhttp3.MediaType.parse("image/*"));
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }

    // Lấy File từ Uri
    private File getFileFromUri(Context context, Uri uri) throws IOException {
        File file = FileUtils.getFileFromUri(context, uri);
        if (file != null) {
            return file;
        }
        throw new IOException("Không thể lấy file từ Uri");
    }

    // Lưu Bitmap vào Cache
    private Uri saveBitmapToCache(Context context, Bitmap bitmap, String fileName) throws IOException {
        File file = new File(context.getCacheDir(), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        }
        return Uri.fromFile(file);
    }
}
