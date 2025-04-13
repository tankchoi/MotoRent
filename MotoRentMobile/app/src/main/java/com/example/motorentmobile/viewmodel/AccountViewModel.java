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
import com.example.motorentmobile.data.model.UpdateAccount;
import com.example.motorentmobile.data.repository.AccountRepository;
import com.example.motorentmobile.util.FileUtils;

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

    // Fetch account info from the repository
    public void fetchAccountInfo() {
        isLoading.setValue(true);
        repository.getAccountInfo(new AccountRepository.AccountCallback() {
            @Override
            public void onSuccess(Account accountData) {
                isLoading.postValue(false);
                account.postValue(accountData);

                // Download and save images for identity card and driver's license
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

    // Load and save images from URLs
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

    // Update account information
    public void updateAccountInfo(String email, String fullName, String phone, Uri identityCardUri, Uri driverLicenseUri) {
        try {
            // Convert URI to File
            File tempIdentity = identityCardUri != null
                    ? FileUtils.getFileFromUri(getApplication(), identityCardUri)
                    : null;
            File tempLicense = driverLicenseUri != null
                    ? FileUtils.getFileFromUri(getApplication(), driverLicenseUri)
                    : null;

            // Copy file into app's storage
            File realIdentity = null, realLicense = null;
            if (tempIdentity != null) {
                realIdentity = new File(getApplication().getFilesDir(), tempIdentity.getName());
                FileUtils.copy(tempIdentity, realIdentity);
            }
            if (tempLicense != null) {
                realLicense = new File(getApplication().getFilesDir(), tempLicense.getName());
                FileUtils.copy(tempLicense, realLicense);
            }

            // Log file details
            Log.d("AccountViewModel", "Identity card file: " + (realIdentity != null ? realIdentity.getAbsolutePath() : "null"));
            Log.d("AccountViewModel", "Driver license file: " + (realLicense != null ? realLicense.getAbsolutePath() : "null"));

            // Create UpdateAccount object
            UpdateAccount updateRequest = new UpdateAccount(email, fullName, phone, realIdentity, realLicense);

            // Call repository to update user info
            repository.updateUser(updateRequest);
        } catch (Exception e) {
            Log.e("AccountViewModel", "Error when processing images: " + e.getMessage());
            errorMessage.setValue("Lỗi khi xử lý ảnh: " + e.getMessage());
        }
    }

    // Convert File to MultipartBody.Part for API call
    private MultipartBody.Part toMultipartBody(String partName, File file) {
        if (file == null) {
            return null;
        }
        RequestBody requestBody = RequestBody.create(file, okhttp3.MediaType.parse("image/*"));
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }

    // Save Bitmap image to cache and return the URI
    private Uri saveBitmapToCache(Context context, Bitmap bitmap, String fileName) throws IOException {
        File file = new File(context.getCacheDir(), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        }
        return Uri.fromFile(file);
    }
}
