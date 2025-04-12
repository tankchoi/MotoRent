package com.example.motorentmobile.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.RegisterRequest;
import com.example.motorentmobile.data.repository.CustomerRepository;

import java.io.File;

public class RegisterViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    private final MutableLiveData<Bitmap> identityCardImage = new MutableLiveData<>();
    private final MutableLiveData<Bitmap> driverLicenseImage = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
    }

    // Đăng ký người dùng
    public void registerUser(String email, String password, String fullName, String phone,
                             File identityCardImage, File driverLicenseImage) {

        // Tạo đối tượng RegisterRequest
        RegisterRequest registerRequest = new RegisterRequest(email, password, fullName, phone,
                identityCardImage, driverLicenseImage);

        // Gọi phương thức registerUser từ CustomerRepository
        customerRepository.registerUser(registerRequest);
    }

    public LiveData<String> getErrorMessage() {
        return customerRepository.getErrorMessage();
    }

    public LiveData<Boolean> getIsSuccess() {
        return customerRepository.getIsSuccess();
    }

    // Getter và Setter cho ảnh
    public LiveData<Bitmap> getIdentityCardImage() {
        return identityCardImage;
    }

    public LiveData<Bitmap> getDriverLicenseImage() {
        return driverLicenseImage;
    }

    public void setIdentityCardImage(Bitmap bitmap) {
        identityCardImage.setValue(bitmap);
    }

    public void setDriverLicenseImage(Bitmap bitmap) {
        driverLicenseImage.setValue(bitmap);
    }
    public void selectImageFromGallery(Context context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }
}
