package vn.edu.tlu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseActivity;

public class SearchActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.rvVehicleList);
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Vision", R.drawable.airblade_5));
        vehicleList.add(new Vehicle("Air Blade", R.drawable.airblade_5));

        VehicleAdapter adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }
}
