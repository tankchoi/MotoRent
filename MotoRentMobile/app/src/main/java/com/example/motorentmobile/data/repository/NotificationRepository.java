package com.example.motorentmobile.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.api.ApiService;
import com.example.motorentmobile.data.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private final ApiService apiService;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<List<Notification>> notificationList = new MutableLiveData<>();

    public NotificationRepository(Application application) {
        apiService = ApiClient.getClient(application.getApplicationContext()).create(ApiService.class);
    }

    public void loadNotifications() {
        apiService.getNotifications().enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (response.isSuccessful()) {
                    List<Notification> notifications = response.body();
                    Log.d("NotificationRepository", "API Response: " + (notifications != null ? notifications.size() : 0) + " notifications received");

                    if (notifications != null) {
                        for (int i = 0; i < notifications.size(); i++) {
                            Notification n = notifications.get(i);
                            Log.d("NotificationRepository", "Item " + i + ": createdAt = " + n.getCreatedAt() + ", message = " + n.getMessage());
                        }

                        notificationList.setValue(notifications);
                    } else {
                        Log.d("NotificationRepository", "No notifications found.");
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                // Log khi không thể kết nối tới API
                Log.e("NotificationRepository", "API Failure: " + t.getMessage());
                errorMessage.setValue("API Failure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<Notification>> getNotificationList() {
        return notificationList;
    }
}


