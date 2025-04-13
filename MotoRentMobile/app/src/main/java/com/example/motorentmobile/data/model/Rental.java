package com.example.motorentmobile.data.model;

import java.util.List;

public class Rental {
    private Long id;
    private double amoutPaid;
    private double totalPrice;
    private String startTime;
    private String endTime;
    private String paymentMethod;
    private String status;
    private List<RentalDetail> rentalDetails;

    public Rental(Long id, double amoutPaid, double totalPrice, String startTime, String endTime, String paymentMethod, String status, List<RentalDetail> rentalDetails) {
        this.id = id;
        this.amoutPaid = amoutPaid;
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
        this.amoutPaid = amoutPaid;
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
        return amoutPaid;
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
}
