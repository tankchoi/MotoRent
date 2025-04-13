package com.example.motorentmobile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.Account;
import com.example.motorentmobile.data.model.UpdateAccount;
import com.example.motorentmobile.data.repository.AccountRepository;


import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository accountRepository;
    private MutableLiveData<Account> accountLiveData;

    public AccountViewModel(Application application) {
        super(application);
        accountRepository = new AccountRepository(application);
        accountLiveData = new MutableLiveData<>();
    }

    public LiveData<Account> getAccount() {
        return accountLiveData;
    }

    // Fetch account info from API
    public void fetchAccount() {
        Log.d("AccountViewModel", "fetchAccount() called");


        LiveData<Account> repoLiveData = accountRepository.getAccountLiveData();

        repoLiveData.observeForever(account -> {
            if (account != null) {
                accountLiveData.setValue(account);
                Log.d("AccountViewModel", "Account data received: " + account.toString());

            }
        });
    }





    // Update account info via API
    public void updateAccount(Context context,
                              String email,
                              String fullName,
                              String phone,
                              Uri identityCardUri,
                              Uri driverLicenseUri,
                              AccountRepository.UpdateCallback callback) throws IOException {

        // Tạo RequestBody từ chuỗi
        RequestBody emailPart = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), fullName);
        RequestBody phonePart = RequestBody.create(MediaType.parse("text/plain"), phone);

        // Convert Uri -> File
        File identityCardFile = com.example.motorentmobile.utils.FileUtils.getFileFromUri(context, identityCardUri);
        File driverLicenseFile = com.example.motorentmobile.utils.FileUtils.getFileFromUri(context, driverLicenseUri);

        // Dùng hàm tiện ích để tạo MultipartBody.Part
        MultipartBody.Part identityCardImage = prepareImagePart("identityCardImage", identityCardFile);
        MultipartBody.Part driverLicenseImage = prepareImagePart("driverLicenseImage", driverLicenseFile);

        // Gọi hàm update từ Repository
        accountRepository.updateAccount(emailPart, namePart, phonePart, identityCardImage, driverLicenseImage, callback);
    }

    private MultipartBody.Part prepareImagePart(String partName, File file) {
        if (file != null) {
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            return MultipartBody.Part.createFormData(partName, file.getName(), body);
        }
        return null;
    }
}
