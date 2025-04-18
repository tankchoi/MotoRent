package com.example.motorentmobile.data.model;

import com.example.motorentmobile.data.model.VehicleImage;
import com.example.motorentmobile.util.FormatMoneyUtil;

import java.io.Serializable;
import java.util.List;

public class Vehicle implements Serializable {
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
    public String getFormatPrice(){
        return FormatMoneyUtil.format(pricePerDay) + " / ngày";
    }
}
