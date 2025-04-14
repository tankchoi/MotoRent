package com.example.motorentmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.HistoryAdapter;
import com.example.motorentmobile.databinding.ActivityHistoryBinding;
import com.example.motorentmobile.util.SharedPreferencesHelper;
import com.example.motorentmobile.viewmodel.HistoryViewModel;

public class HistoryActivity extends BaseActivity{

    private ActivityHistoryBinding binding;
    private HistoryViewModel historyViewModel;
    private SharedPreferencesHelper prefs;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_history;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_history;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = SharedPreferencesHelper.getInstance(this);
        if (!prefs.isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.fetchRentalList();
        binding.setViewModel(historyViewModel);
        binding.setLifecycleOwner(this);
        HistoryAdapter adapter = historyViewModel.getHistoryAdapter();
        binding.rcvHistoryList.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvHistoryList.setAdapter(adapter);
        historyViewModel.getRentalList().observe(this, rentals -> {
            if (rentals != null) {
                adapter.updateRentalList(rentals);
            }
        });
    }
}
