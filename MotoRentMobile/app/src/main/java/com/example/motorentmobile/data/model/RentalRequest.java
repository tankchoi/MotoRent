package com.example.motorentmobile.data.model;

import java.util.List;

public class RentalRequest {
    private double totalPrice;
    private double amountPaid;
    private String startTime;
    private String endTime;
    private String paymentMethod;
    private List<Long> vehicleIds;

    public RentalRequest(double totalPrice, double amountPaid, String startTime, String endTime, String paymentMethod, List<Long> vehicleIds) {
        this.totalPrice = totalPrice;
        this.amountPaid = amountPaid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.paymentMethod = paymentMethod;
        this.vehicleIds = vehicleIds;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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

    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
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

    public List<Long> getVehicleIds() {
        return vehicleIds;
    }
}
