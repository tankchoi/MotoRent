package com.example.motorentmobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.motorentmobile.adapter.HistoryDetailAdapter;
import com.example.motorentmobile.data.model.Rental;

import java.util.ArrayList;

public class HistoryDetailViewModel extends AndroidViewModel {
    private final MutableLiveData<Rental> rental = new MutableLiveData<>();
    private HistoryDetailAdapter historyDetailAdapter;

    public HistoryDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Rental> getRental() {
        return rental;
    }

    public void setRental(Rental rentalData) {
        rental.setValue(rentalData);
        historyDetailAdapter = new HistoryDetailAdapter(getApplication(), rentalData.getRentalDetails());
    }

    public HistoryDetailAdapter getHistoryDetailAdapter() {
        return historyDetailAdapter;
    }
}
