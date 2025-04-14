package com.example.motorentmobile.view;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.HistoryDetailAdapter;
import com.example.motorentmobile.databinding.ActivityHistoryDetailBinding;
import com.example.motorentmobile.viewmodel.HistoryDetailViewModel;
import com.example.motorentmobile.data.model.Rental;

public class HistoryDetailActivity extends BaseActivity {

    private HistoryDetailViewModel historyDetailViewModel;
    private ActivityHistoryDetailBinding binding;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_history;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryDetailBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());

        // Lấy đối tượng Rental từ Intent
        Rental rental = (Rental) getIntent().getSerializableExtra("RENTAL_DATA");

        if (rental != null) {
            historyDetailViewModel = new ViewModelProvider(this).get(HistoryDetailViewModel.class);
            historyDetailViewModel.setRental(rental);

            binding.setViewModel(historyDetailViewModel);
            binding.setLifecycleOwner(this);

            HistoryDetailAdapter adapter = historyDetailViewModel.getHistoryDetailAdapter();
            binding.rcvHistoryDetailList.setLayoutManager(new LinearLayoutManager(this));
            binding.rcvHistoryDetailList.setAdapter(adapter);

            historyDetailViewModel.getRental().observe(this, updatedRental -> {
                if (updatedRental != null) {
                    adapter.updateRentalDetailList(updatedRental.getRentalDetails());
                }
            });
        }
    }
}
