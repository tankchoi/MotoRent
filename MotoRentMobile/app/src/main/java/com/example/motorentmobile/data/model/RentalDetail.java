package com.example.motorentmobile.data.model;

import java.io.Serializable;

public class RentalDetail implements Serializable {
    private Long id;
    private String vehicleName;
    private String vehicleBrand;
    private String licensePlate;
    private double pricePerDay;

    public RentalDetail(Long id, String vehicleName, String vehicleBrand, String licensePlate, double pricePerDay) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.vehicleBrand = vehicleBrand;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}
