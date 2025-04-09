package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.LoginRequest;
import com.example.motorentmobile.data.repository.AuthRepository;

public class LoginViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        authRepository.login(request).observeForever(result -> loginResult.setValue(result));
    }
}
