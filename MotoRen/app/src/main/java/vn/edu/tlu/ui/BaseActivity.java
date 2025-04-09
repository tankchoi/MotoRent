package vn.edu.tlu.ui;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.R;

public abstract class BaseActivity extends AppCompatActivity {

    // Gọi ở tất cả Activity con
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
        initData();
        initListeners();
    }

    // Bắt buộc override ở lớp con
    protected abstract int getLayoutRes();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListeners();

    // Show toast nhanh
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Ẩn bàn phím
    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // Hiện bàn phím
    protected void showKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
