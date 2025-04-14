package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.adapter.HistoryAdapter;
import com.example.motorentmobile.data.model.Rental;
import com.example.motorentmobile.data.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private final RentalRepository rentalRepository;
    private final MutableLiveData<List<Rental>> rentalList = new MutableLiveData<>();
    private final HistoryAdapter historyAdapter;
    public HistoryViewModel(@NonNull Application application) {
        super(application);
        rentalRepository = new RentalRepository(application);
        historyAdapter = new HistoryAdapter(application, new ArrayList<>());

        rentalRepository.getRentalList().observeForever(rentals -> {
            historyAdapter.updateRentalList(rentals);
        });
    }
    public LiveData<List<Rental>> getRentalList() {
        return rentalList;
    }
    public void fetchRentalList() {
        rentalRepository.fetchRentalList();
    }
    public HistoryAdapter getHistoryAdapter() {
        return historyAdapter;
    }
}
