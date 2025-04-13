package com.example.motorentmobile.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notification {
    private String message;
    private String createdAt;

    public Notification(String createdAt, String message) {
        this.createdAt = createdAt;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public String getFormattedCreatedAt() {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = input.parse(createdAt);
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            return output.format(date);
        } catch (Exception e) {
            return createdAt;
        }
    }


}
