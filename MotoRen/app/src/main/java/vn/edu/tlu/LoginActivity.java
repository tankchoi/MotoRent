package vn.edu.tlu;

import android.content.Intent;
import android.widget.TextView;

import vn.edu.tlu.ui.BaseActivity;

public class LoginActivity  extends BaseActivity {
    private TextView tvRegister;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvRegister = findViewById(R.id.tvRegister);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            // Handle register button click
            startActivity(intent);
        });

    }
}
