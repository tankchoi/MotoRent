package com.example.motorentmobile.data.model;

import com.example.motorentmobile.util.FormatMoneyUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Rental implements Serializable {
    private Long id;
    private double amountPaid;
    private double totalPrice;
    private String startTime;
    private String endTime;
    private String paymentMethod;
    private String status;
    private List<RentalDetail> rentalDetails;

    public Rental(Long id, double amountPaid, double totalPrice, String startTime, String endTime, String paymentMethod, String status, List<RentalDetail> rentalDetails) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.totalPrice = totalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.rentalDetails = rentalDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmoutPaid(double amoutPaid) {
        this.amountPaid = amoutPaid;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRentalDetails(List<RentalDetail> rentalDetails) {
        this.rentalDetails = rentalDetails;
    }

    public Long getId() {
        return id;
    }

    public double getAmoutPaid() {
        return amountPaid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public List<RentalDetail> getRentalDetails() {
        return rentalDetails;
    }

    public String getFormattedDateRange() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            Date startDate = inputFormat.parse(startTime);
            Date endDate = inputFormat.parse(endTime);

            return "Thời gian: " + outputFormat.format(startDate) + " → " + outputFormat.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }



}
