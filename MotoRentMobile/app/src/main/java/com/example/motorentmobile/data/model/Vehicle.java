package com.example.motorentmobile.model;

import java.util.List;

public class Vehicle {
    private Long id;
    private String name;
    private String brand;
    private double pricePerDay;
    private String description;
    private String licensePlate;
    private List<VehicleImage> vehicleImages;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public List<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }
}
