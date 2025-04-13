package com.example.motorentmobile.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorentmobile.R;
import com.example.motorentmobile.data.model.Notification;
import com.example.motorentmobile.databinding.ItemNotificationBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Notification> notificationList = new ArrayList<>();

    public void setData(List<Notification> list) {
        this.notificationList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Dùng binding ở đây
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification item = notificationList.get(position);
        Log.d("Adapter", "Bind: " + item.getMessage());

        holder.binding.setNotification(item); // Đặt dữ liệu vào biến `notification`
        holder.binding.executePendingBindings(); // Cập nhật ngay
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNotificationBinding binding;

        public ViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}



