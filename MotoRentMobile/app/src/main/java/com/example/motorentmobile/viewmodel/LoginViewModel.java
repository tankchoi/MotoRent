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
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String message) {
        errorMessage.setValue(message);
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        authRepository.login(request, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                loginResult.postValue(true);
                errorMessage.postValue(null);
            }

            @Override
            public void onError(String error) {
                loginResult.postValue(false);
                errorMessage.postValue(error);
            }
        });
    }
}
