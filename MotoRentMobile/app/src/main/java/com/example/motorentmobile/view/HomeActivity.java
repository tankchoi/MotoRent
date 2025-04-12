package com.example.motorentmobile.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.motorentmobile.R;
import com.example.motorentmobile.databinding.ActivityHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends BaseActivity implements OnMapReadyCallback {

    private ActivityHomeBinding binding;
    private MapView mapView;
    private GoogleMap googleMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());

        mapView = binding.mapView;

        // Khởi tạo MapView
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        if (mapView != null) {
            mapView.onCreate(mapViewBundle);
            mapView.getMapAsync(this);
        }


        binding.edtStartTime.setOnClickListener(v -> showDatePicker(binding.edtStartTime));
        binding.edtEndTime.setOnClickListener(v -> showDatePicker(binding.edtEndTime));

        binding.btnDatXe.setOnClickListener(v -> {
            String startDateStr = binding.edtStartTime.getText().toString();
            String endDateStr = binding.edtEndTime.getText().toString();

            if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn thời gian", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

            try {
                Date startDate = inputFormat.parse(startDateStr);
                Date endDate = inputFormat.parse(endDateStr);
                Date now = new Date();

                if (startDate.before(now)) {
                    Toast.makeText(this, "Không thể thuê xe trong quá khứ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (endDate.before(startDate)) {
                    Toast.makeText(this, "Ngày trả xe phải sau hoặc bằng ngày mượn", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Format lại ngày theo chuẩn ISO để truyền sang Activity khác
                String formattedStart = isoFormat.format(startDate);
                String formattedEnd = isoFormat.format(endDate);

                Intent intent = new Intent(this, VehicleActivity.class);
                intent.putExtra("startTime", formattedStart);
                intent.putExtra("endTime", formattedEnd);
                startActivity(intent);

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi định dạng ngày", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void showDatePicker(android.widget.EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        LatLng haNoi = new LatLng(21.0285, 105.8542);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(haNoi, 15));
        googleMap.addMarker(new MarkerOptions().position(haNoi).title("Hà Nội").snippet("Thủ đô Việt Nam"));

        googleMap.setOnMarkerClickListener(marker -> {
            openGoogleMapsForDirections(marker.getPosition());
            return true;
        });

        googleMap.setOnMapLongClickListener(this::openGoogleMapsForDirections);
    }

    private void openGoogleMapsForDirections(LatLng destination) {
        String uri = "http://maps.google.com/maps?daddr=" + destination.latitude + "," + destination.longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Google Maps chưa được cài đặt", Toast.LENGTH_SHORT).show();
        }
    }

    // --- Lifecycle MapView ---
    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    protected void onPause() {
        if (mapView != null) mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (mapView != null) mapView.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mapView != null) mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
            if (mapViewBundle == null) {
                mapViewBundle = new Bundle();
                outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
            }
            mapView.onSaveInstanceState(mapViewBundle);
        }
    }
}
