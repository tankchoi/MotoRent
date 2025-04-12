package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
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

        // Lắng nghe dữ liệu từ repository
        vehicleRepository.getVehicleList().observeForever(vehicles -> {
            vehicleAdapter.updateVehicleList(vehicles); // Cập nhật danh sách xe vào adapter
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

    public void onFilterClick() {
        String query = searchQuery.get();
        if (query != null && !query.isEmpty()) {
            vehicleRepository.fetchAvailableVehicles("2025-04-12T08:00:00", "2025-04-12T18:00:00"); // Thực thi lại tìm kiếm
        }
    }
}
