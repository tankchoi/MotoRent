package com.example.motorentmobile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.VehicleImageAdapter;
import com.example.motorentmobile.databinding.ActivityVehicleDetailBinding;
import com.example.motorentmobile.viewmodel.RentalManager;
import com.example.motorentmobile.viewmodel.VehicleDetailViewModel;
import com.example.motorentmobile.data.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class VehicleDetailActivity extends BaseActivity {


    private VehicleDetailViewModel viewModel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_vehicle_detail;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Vehicle vehicle = (Vehicle) getIntent().getSerializableExtra("vehicle");

        // Khởi tạo ViewModel
        viewModel = new ViewModelProvider(this).get(VehicleDetailViewModel.class);

        // Cập nhật dữ liệu trong ViewModel
        if (vehicle != null) {
            viewModel.setVehicle(vehicle);
        }

        // DataBinding
        ActivityVehicleDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_detail);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Liên kết RecyclerView với Adapter
        VehicleImageAdapter imageAdapter = new VehicleImageAdapter(this, vehicle.getVehicleImages());
        binding.rvImageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvImageList.setAdapter(imageAdapter);

        binding.backBtn.setOnClickListener(v -> {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            String startTimeStr = isoFormat.format(RentalManager.getInstance().getStartTime());
            String endTimeStr = isoFormat.format(RentalManager.getInstance().getEndTime());

            Intent intent = new Intent(VehicleDetailActivity.this, VehicleActivity.class);
            intent.putExtra("startTime", startTimeStr);
            intent.putExtra("endTime", endTimeStr);
            startActivity(intent);

        });

        binding.btnAddToCart.setOnClickListener(v -> {
            RentalManager.getInstance().addVehicle(vehicle);
            Intent intent = new Intent(VehicleDetailActivity.this, RentalActivity.class);
            startActivity(intent);
        });
    }
}
