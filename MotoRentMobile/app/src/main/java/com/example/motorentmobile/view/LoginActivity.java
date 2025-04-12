package com.example.motorentmobile.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorentmobile.databinding.ActivityLoginBinding;
import com.example.motorentmobile.viewmodel.LoginViewModel;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        setupListeners();
        setupObserver();
    }

    private void setupListeners() {
        binding.tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                loginViewModel.setErrorMessage("Vui lòng nhập đầy đủ thông tin");
                return;
            }

            binding.progressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(email, password);
        });
    }

    private void setupObserver() {
        loginViewModel.getLoginResult().observe(this, isSuccess -> {
            binding.progressBar.setVisibility(View.GONE);
            if (isSuccess != null && isSuccess) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        });
    }
}
