package com.example.motorentmobile.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.R;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.util.FormatMoneyUtil;

public class BindingAdapters {



    // BindingAdapter để tải ảnh từ đường dẫn
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Glide.with(view.getContext())
                    .load(ApiClient.BASE_URL + imagePath)  // Ghép BASE_URL với imagePath
                    .placeholder(R.drawable.avatar_5)  // Ảnh placeholder
                    .into(view);
        } else {
            view.setImageResource(R.drawable.avatar_5);  // Nếu không có ảnh, dùng ảnh mặc định
        }
    }

    @BindingAdapter("formattedPaidText")
    public static void setFormattedMoneyText(@NonNull TextView view, double amountPaid) {
        String formattedText = FormatMoneyUtil.format(amountPaid);
        view.setText("Đã thanh toán: "+formattedText);
    }
    @BindingAdapter("formattedTotalText")
    public static void setFormattedTotalPriceText(TextView view, double totalPrice) {
        String formattedText = FormatMoneyUtil.format(totalPrice);
        view.setText("Tổng tiền: "+formattedText);
    }

    @BindingAdapter("textStatus")
    public static void setStatus(TextView textView, String status) {
        if (status == null) return;

        switch (status) {
            case "PENDING":
                textView.setText("Chờ lấy");
                break;
            case "RENTED":
                textView.setText("Đang thuê");
                break;
            case "COMPLETED":
                textView.setText("Hoàn thành");
                break;
            case "CANCELLED":
                textView.setText("Đã hủy");
                break;
            default:
                textView.setText("");  // Default text in case status is invalid
        }
    }

    @BindingAdapter("textColorStatus")
    public static void setTextColorStatus(TextView textView, String status) {
        if (status == null) return;

        switch (status) {
            case "PENDING":
                textView.setTextColor(textView.getContext().getResources().getColor(android.R.color.holo_orange_dark));
                break;
            case "RENTED":
                textView.setTextColor(textView.getContext().getResources().getColor(android.R.color.holo_blue_dark));
                break;
            case "COMPLETED":
                textView.setTextColor(textView.getContext().getResources().getColor(android.R.color.holo_green_dark));
                break;
            case "CANCELLED":
                textView.setTextColor(textView.getContext().getResources().getColor(android.R.color.holo_red_dark));
                break;
            default:
                textView.setTextColor(textView.getContext().getResources().getColor(android.R.color.black));
        }
    }

}