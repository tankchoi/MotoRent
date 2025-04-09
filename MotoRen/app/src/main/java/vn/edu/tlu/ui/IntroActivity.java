package vn.edu.tlu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import vn.edu.tlu.MainActivity;
import vn.edu.tlu.R;

public class IntroActivity extends BaseActivity {
    private Button btnStart;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initView() {
        btnStart = findViewById(R.id.btnStart);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        btnStart.setOnClickListener(v -> {
            // Chuyển sang MainActivity
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish();
        });

    }
}
