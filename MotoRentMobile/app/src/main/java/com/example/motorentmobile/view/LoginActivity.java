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

        setupObserver();
        setupListeners();
    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                binding.progressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(email, password);
            }
        });
    }

    private void setupObserver() {
        loginViewModel.getLoginResult().observe(this, isSuccess -> {
            binding.progressBar.setVisibility(View.GONE);
            if (isSuccess != null && isSuccess) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
