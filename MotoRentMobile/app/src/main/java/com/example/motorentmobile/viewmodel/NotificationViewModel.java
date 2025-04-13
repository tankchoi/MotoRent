package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.Notification;
import com.example.motorentmobile.data.repository.NotificationRepository;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {
    private final NotificationRepository repository;
    private final MutableLiveData<List<Notification>> notificationList;
    private final MutableLiveData<String> errorMessage;

    public NotificationViewModel(Application application) {
        super(application);
        repository = new NotificationRepository(application);
        notificationList = repository.getNotificationList();
        errorMessage = repository.getErrorMessage();
    }

    // Phương thức để tải thông báo
    public void loadNotifications() {
        repository.loadNotifications();
    }

    // LiveData cho danh sách thông báo
    public LiveData<List<Notification>> getNotificationList() {
        return notificationList;
    }

    // LiveData cho thông báo lỗi
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
