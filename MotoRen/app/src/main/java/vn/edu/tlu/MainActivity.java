package vn.edu.tlu;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private EditText edtNgayNhan;
    private EditText edtNgayTra;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        edtNgayNhan = findViewById(R.id.edtNgayNhan);
        edtNgayTra = findViewById(R.id.edtNgayTra);

        RecyclerView recyclerView = findViewById(R.id.rvVehicleList);
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Vision", R.drawable.airblade_5));
        vehicleList.add(new Vehicle("Air Blade", R.drawable.airblade_5));

        VehicleAdapter adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        BottomNavigationView bottomMenu = findViewById(R.id.bottomMenu);




    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        edtNgayNhan.setOnClickListener(v -> showDatePicker(edtNgayNhan));
        edtNgayTra.setOnClickListener(v -> showDatePicker(edtNgayTra));


    }

    private void showDatePicker(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

}