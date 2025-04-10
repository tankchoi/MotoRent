package vn.edu.tlu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseFragment;


public class RentalFragment extends BaseFragment {


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_rental;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rvVehicleList);
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Vision", R.drawable.airblade_5));
        vehicleList.add(new Vehicle("Air Blade", R.drawable.airblade_5));

        VehicleAdapter adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }
}