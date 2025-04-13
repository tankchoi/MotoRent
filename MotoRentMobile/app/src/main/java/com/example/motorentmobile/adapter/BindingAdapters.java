package com.example.motorentmobile.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.R;

public class BindingAdapters {

    private static final String BASE_URL = "http://192.168.2.3:8080/";

    // BindingAdapter để tải ảnh từ đường dẫn
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Glide.with(view.getContext())
                    .load(BASE_URL + imagePath)  // Ghép BASE_URL với imagePath
                    .placeholder(R.drawable.avatar_5)  // Ảnh placeholder
                    .into(view);
        } else {
            view.setImageResource(R.drawable.avatar_5);  // Nếu không có ảnh, dùng ảnh mặc định
        }
    }
}