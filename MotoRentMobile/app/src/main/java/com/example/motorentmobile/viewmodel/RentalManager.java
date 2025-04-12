package com.example.motorentmobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.Vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalManager {
    private static RentalManager instance;

    private double totalPrice;

    private Date startTime;
    private Date endTime;

    private final MutableLiveData<List<Vehicle>> vehiclesLiveData = new MutableLiveData<>(new ArrayList<>());

    private RentalManager() {}

    public static synchronized RentalManager getInstance() {
        if (instance == null) {
            instance = new RentalManager();
        }
        return instance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;

    }

    public LiveData<List<Vehicle>> getVehiclesLiveData() {
        return vehiclesLiveData;
    }

    public void addVehicle(Vehicle vehicle) {
        List<Vehicle> current = new ArrayList<>(vehiclesLiveData.getValue());
        current.add(vehicle);
        vehiclesLiveData.setValue(current);
        recalculateTotalPrice();
    }

    public void removeVehicle(Vehicle vehicle) {
        List<Vehicle> current = new ArrayList<>(vehiclesLiveData.getValue());
        current.remove(vehicle);
        vehiclesLiveData.setValue(current);
        recalculateTotalPrice();
    }

    public void clearVehicles() {
        vehiclesLiveData.setValue(new ArrayList<>());
        recalculateTotalPrice();
    }

    public boolean containsVehicle(Vehicle vehicle) {
        return vehiclesLiveData.getValue().contains(vehicle);
    }

    public int getVehicleCount() {
        return vehiclesLiveData.getValue().size();
    }

    public void clear() {
        vehiclesLiveData.setValue(new ArrayList<>());
        startTime = null;
        endTime = null;
        totalPrice = 0;
    }

    private void recalculateTotalPrice() {
        long days = 1;
        if (startTime != null && endTime != null) {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            startCal.setTime(startTime);
            endCal.setTime(endTime);

            long diffMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();
            long diffDays = diffMillis / (1000 * 60 * 60 * 24);
            days = Math.max(diffDays, 1);
        }

        double price = 0;
        for (Vehicle v : vehiclesLiveData.getValue()) {
            price += v.getPricePerDay() * days;
        }
        this.totalPrice = price;
    }
}
