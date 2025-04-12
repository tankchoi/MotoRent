package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.data.model.Vehicle;

public class VehicleDetailViewModel extends AndroidViewModel {

    private final MutableLiveData<Vehicle> vehicle = new MutableLiveData<>();

    public VehicleDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle.setValue(vehicle);
    }
}
