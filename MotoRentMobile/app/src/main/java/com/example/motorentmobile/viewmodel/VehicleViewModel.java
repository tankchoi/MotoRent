package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableField;

import com.example.motorentmobile.adapter.VehicleAdapter;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.data.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

    private final VehicleRepository vehicleRepository;
    private final MutableLiveData<List<Vehicle>> vehicleList = new MutableLiveData<>();
    private final ObservableField<String> searchQuery = new ObservableField<>("");

    private final VehicleAdapter vehicleAdapter;

    public VehicleViewModel(@NonNull Application application) {
        super(application);
        vehicleRepository = new VehicleRepository(application);
        vehicleAdapter = new VehicleAdapter(application, new ArrayList<>());

        vehicleRepository.getVehicleList().observeForever(vehicles -> {
            vehicleAdapter.updateVehicleList(vehicles);
        });
        searchQuery.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                filterList(searchQuery.get());
            }
        });
    }

    public LiveData<List<Vehicle>> getVehicleList() {
        return vehicleList;
    }

    public ObservableField<String> getSearchQuery() {
        return searchQuery;
    }

    public VehicleAdapter getVehicleAdapter() {
        return vehicleAdapter;
    }

    public void fetchAvailableVehicles(String startTime, String endTime) {
        vehicleRepository.fetchAvailableVehicles(startTime, endTime);
    }
    private void filterList(String query) {
        List<Vehicle> originalList = vehicleRepository.getVehicleList().getValue();
        if (originalList != null) {
            List<Vehicle> filteredList = new ArrayList<>();
            for (Vehicle vehicle : originalList) {
                if (vehicle.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(vehicle);
                }
            }
            vehicleAdapter.updateVehicleList(filteredList);
        }
    }

    public void onFilterClick() {

    }
}
