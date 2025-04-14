package com.example.motorentmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri data = getIntent().getData();
        if (data != null) {
            String status = data.getQueryParameter("status");
            String orderId = data.getQueryParameter("orderId");

            if ("success".equals(status)) {
                Toast.makeText(this, "🎉 Thanh toán thành công! Mã đơn: " + orderId, Toast.LENGTH_LONG).show();
            } else if ("failure".equals(status)) {
                Toast.makeText(this, "❌ Thanh toán thất bại!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "⚠ Đã xảy ra lỗi trong quá trình thanh toán!", Toast.LENGTH_LONG).show();
            }
        }
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
        finish();
    }
}
