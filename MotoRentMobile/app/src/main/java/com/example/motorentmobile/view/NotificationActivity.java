package com.example.motorentmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.NotificationAdapter;
import com.example.motorentmobile.databinding.ActivityNotificationBinding;
import com.example.motorentmobile.util.SharedPreferencesHelper;
import com.example.motorentmobile.viewmodel.NotificationViewModel;
import com.example.motorentmobile.data.model.Notification;

import java.util.List;

public class NotificationActivity extends BaseActivity {

    private NotificationViewModel viewModel;
    private ActivityNotificationBinding binding;
    private NotificationAdapter adapter;
    private SharedPreferencesHelper prefs;
    private static final String TAG = "NotificationActivity"; // Thêm tag cho các log

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_notification;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_notification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = SharedPreferencesHelper.getInstance(this);
        if (!prefs.isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());

        Log.d(TAG, "onCreate: Activity started");

        // Khởi tạo ViewModel và binding
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Khởi tạo adapter và gán vào RecyclerView ngay từ đầu
        adapter = new NotificationAdapter();
        binding.recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewNotifications.setAdapter(adapter);

        // Quan sát dữ liệu thông báo từ ViewModel
        viewModel.getNotificationList().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                Log.d(TAG, "observe: Notifications received - " + (notifications != null ? notifications.size() : 0) + " notifications");
                if (notifications != null) {
                    if (notifications.isEmpty()) {
                        binding.emptyText.setVisibility(View.VISIBLE);
                        Log.d(TAG, "observe: No notifications available");
                    } else {
                        binding.emptyText.setVisibility(View.GONE);
                        adapter.setData(notifications);  // Gọi setData sau khi adapter đã được khởi tạo
                        Log.d(TAG, "observe: Notifications displayed");
                    }
                }
            }
        });

        // Quan sát thông báo lỗi
        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();  // Hiển thị lỗi
                Log.e(TAG, "Error: " + error); // Ghi log lỗi
            }
        });

        // Load notifications
        Log.d(TAG, "loadNotifications: Requesting notifications");
        viewModel.loadNotifications(); // Gọi phương thức để load dữ liệu thông báo

        // Thiết lập sự kiện click cho nút quay lại
        binding.backBtn.setOnClickListener(v -> {
            Log.d(TAG, "backBtn clicked: Navigating back to HomeActivity");

            // Điều hướng về HomeActivity với một chút delay để tránh WindowLeaked
            Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Đảm bảo rằng HomeActivity sẽ được mở lại
            startActivity(intent);

            // Delay finish một chút
            new android.os.Handler(getMainLooper()).postDelayed(this::finish, 100);
        });
    }
}
