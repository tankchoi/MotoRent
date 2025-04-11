package vn.edu.tlu;

import android.content.Intent;
import android.widget.TextView;

import vn.edu.tlu.ui.BaseActivity;

public class RegisterActivity extends BaseActivity {
    private TextView tvLogin;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tvLogin = findViewById(R.id.tvLogin);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            // Handle register button click
            startActivity(intent);
        });

    }
}
