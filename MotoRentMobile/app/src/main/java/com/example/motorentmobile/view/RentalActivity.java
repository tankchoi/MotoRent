package com.example.motorentmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.databinding.ActivityRentalBinding;
import com.example.motorentmobile.viewmodel.RentalManager;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.adapter.RentalAdapter;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RentalActivity extends BaseActivity {

    private RentalManager rentalManager;
    private ActivityRentalBinding binding;
    private RentalAdapter rentalAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_rental;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_rental;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRentalBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());
        binding.backBtn.setOnClickListener(v -> {
            Intent intent;
            if (rentalManager.getStartTime() == null || rentalManager.getEndTime() == null) {
                intent = new Intent(this, HomeActivity.class);
            } else {
                SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                intent = new Intent(this, VehicleActivity.class);
                intent.putExtra("startTime", isoFormat.format(rentalManager.getStartTime()));
                intent.putExtra("endTime", isoFormat.format(rentalManager.getEndTime()));
            }
            startActivity(intent);
        });
        rentalManager = RentalManager.getInstance();

        setupRecyclerView();
        observeRentalData();

    }

    private void setupRecyclerView() {
        rentalAdapter = new RentalAdapter();
        binding.rvVehicleList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvVehicleList.setAdapter(rentalAdapter);
    }

    private void observeRentalData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date startDate = rentalManager.getStartTime();
        Date endDate = rentalManager.getEndTime();

        if (startDate != null) {
            binding.tvStartTime.setText("Thời gian thuê: " + sdf.format(startDate));
        }

        if (endDate != null) {
            binding.tvEndTime.setText("Thời gian trả: " + sdf.format(endDate));
        }

        rentalManager.getVehiclesLiveData().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                rentalAdapter.setVehicles(vehicles);

                long days = 1; // Default to at least 1 day
                if (startDate != null && endDate != null) {
                    Calendar startCal = Calendar.getInstance();
                    Calendar endCal = Calendar.getInstance();
                    startCal.setTime(startDate);
                    endCal.setTime(endDate);

                    long diffMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();
                    long diffDays = diffMillis / (1000 * 60 * 60 * 24);
                    days = Math.max(diffDays, 1); // Ensure at least 1 day
                }

                double totalPrice = 0;
                for (Vehicle v : vehicles) {
                    totalPrice += v.getPricePerDay() * days;
                }

                rentalManager.setTotalPrice(totalPrice);
                binding.tvTotalPrice.setText(String.format("%,.0f đ", totalPrice));
            }
        });
    }






}
