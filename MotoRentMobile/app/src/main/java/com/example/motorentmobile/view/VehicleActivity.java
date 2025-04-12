package com.example.motorentmobile.view;

import android.os.Bundle;
import android.widget.FrameLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.VehicleAdapter;
import com.example.motorentmobile.databinding.ActivityHomeBinding;
import com.example.motorentmobile.databinding.ActivityVehicleBinding;
import com.example.motorentmobile.viewmodel.VehicleViewModel;

public class VehicleActivity extends BaseActivity {

    private VehicleViewModel vehicleViewModel;
    private ActivityVehicleBinding binding;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_vehicle;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVehicleBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());
        // Lấy dữ liệu từ Intent
        String startTime = getIntent().getStringExtra("startTime");
        String endTime = getIntent().getStringExtra("endTime");


        // Khởi tạo ViewModel
        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
        if (startTime != null && endTime != null) {
            // Gọi phương thức trong ViewModel để tải danh sách xe
            vehicleViewModel.fetchAvailableVehicles(startTime, endTime);
        }
        // Liên kết ViewModel với Layout thông qua DataBinding
        binding.setViewModel(vehicleViewModel);
        binding.setLifecycleOwner(this);

        // Thiết lập adapter cho RecyclerView
        VehicleAdapter adapter = vehicleViewModel.getVehicleAdapter();
        binding.rvVehicleList.setLayoutManager(new GridLayoutManager(this,1));
        binding.rvVehicleList.setAdapter(adapter);

        // Quan sát LiveData để cập nhật danh sách xe
        vehicleViewModel.getVehicleList().observe(this, vehicles -> {
            if (vehicles != null) {
                adapter.updateVehicleList(vehicles);
            }
        });

    }
}


