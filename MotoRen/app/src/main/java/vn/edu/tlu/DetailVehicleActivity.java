package vn.edu.tlu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.adapter.ImageAdapter;
import vn.edu.tlu.model.VehicleImage;
import vn.edu.tlu.ui.BaseActivity;

public class DetailVehicleActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_vehicle;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.rvImageList);
        List<VehicleImage> vehicleImageList = new ArrayList<>();
        vehicleImageList.add(new VehicleImage(R.drawable.airblade_5));
        vehicleImageList.add(new VehicleImage(R.drawable.airblade_5));

        ImageAdapter adapter = new ImageAdapter(vehicleImageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }
}
