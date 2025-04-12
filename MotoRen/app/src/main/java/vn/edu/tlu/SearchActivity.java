package vn.edu.tlu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseActivity;

public class SearchActivity extends BaseActivity {
    private ImageView btnFilter;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        btnFilter = findViewById(R.id.btnFilter);

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


        btnFilter.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_filter, null);

            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true
            );

            popupWindow.setElevation(10);
            popupWindow.showAsDropDown(v, -50, 0); // Điều chỉnh vị trí nếu cần

            // Các checkbox và nút lọc
            CheckBox cbVision = popupView.findViewById(R.id.cbVision);
            CheckBox cbAirBlade = popupView.findViewById(R.id.cbAirBlade);
            Button btnApplyFilter = popupView.findViewById(R.id.btnApplyFilter);

            btnApplyFilter.setOnClickListener(view -> {
                boolean showVision = cbVision.isChecked();
                boolean showAirBlade = cbAirBlade.isChecked();

                // Lọc danh sách (ví dụ: tạo list mới rồi cập nhật adapter)
                List<Vehicle> filteredList = new ArrayList<>();
                if (showVision) filteredList.add(new Vehicle("Vision", R.drawable.airblade_5));
                if (showAirBlade) filteredList.add(new Vehicle("Air Blade", R.drawable.airblade_5));

                // Cập nhật RecyclerView
                RecyclerView recyclerView = findViewById(R.id.rvVehicleList);
                VehicleAdapter adapter = new VehicleAdapter(filteredList);
                recyclerView.setAdapter(adapter);

                popupWindow.dismiss();
            });
        });
    }

}
