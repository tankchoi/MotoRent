package vn.aptech.java.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class CreateRentalDTO {
    private double totalPrice;
    private double amountPaid;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String paymentMethod;
    private List<Long> vehicleIds;

    public CreateRentalDTO() {
    }

    public CreateRentalDTO(double totalPrice, double amountPaid, LocalDateTime startTime, LocalDateTime endTime, String paymentMethod, List<Long> vehicleIds) {
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<Long> getVehicleIds() {
        return vehicleIds;
    }
}
