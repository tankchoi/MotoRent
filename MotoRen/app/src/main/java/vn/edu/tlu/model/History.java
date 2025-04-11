package vn.edu.tlu.model;

import java.time.LocalDateTime;

public class History {
    public History(String userId, double totalPrice, double amountPaid, LocalDateTime startTime, LocalDateTime endTime, String vehicleName) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.amountPaid = amountPaid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleName = vehicleName;
    }

    private String userId;
    private double totalPrice;
    private double amountPaid;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String vehicleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
