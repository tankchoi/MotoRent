package vn.edu.tlu;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.tlu.R;
import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseFragment;

public class HomeFragment extends BaseFragment {
    private EditText edtNgayNhan;
    private EditText edtNgayTra;
    private TextView tvWelcome;

    @Override
    protected int getLayoutResId() {
        // Gán layout tương ứng
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        edtNgayNhan = view.findViewById(R.id.edtNgayNhan);
        edtNgayTra = view.findViewById(R.id.edtNgayTra);

        RecyclerView recyclerView = view.findViewById(R.id.rvVehicleList);
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Vision", R.drawable.airblade_5));
        vehicleList.add(new Vehicle("Air Blade", R.drawable.airblade_5));

        VehicleAdapter adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void initData() {
        // Gán dữ liệu mặc định
        tvWelcome.setText("Chào mừng đến với ứng dụng!");
    }

    @Override
    protected void initListeners() {
        // Gắn sự kiện nếu cần
        edtNgayNhan.setOnClickListener(v -> showDatePicker(edtNgayNhan));
        edtNgayTra.setOnClickListener(v -> showDatePicker(edtNgayTra));

    }

    private void showDatePicker(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
}
