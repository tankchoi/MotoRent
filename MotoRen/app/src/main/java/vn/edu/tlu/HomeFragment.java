package vn.edu.tlu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.tlu.adapter.VehicleAdapter;
import vn.edu.tlu.model.Vehicle;
import vn.edu.tlu.ui.BaseFragment;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback {

    private EditText edtNgayNhan;
    private EditText edtNgayTra;
    private MapView mapView;
    private GoogleMap googleMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        edtNgayNhan = view.findViewById(R.id.edtNgayNhan);
        edtNgayTra = view.findViewById(R.id.edtNgayTra);
        mapView = view.findViewById(R.id.mapView);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        if (mapView != null) {
            mapView.onCreate(mapViewBundle);
            mapView.getMapAsync(this);
        }
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
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

        // Nhấn vào Marker → mở Google Maps chỉ đường
        googleMap.setOnMarkerClickListener(marker -> {
            openGoogleMapsForDirections(marker.getPosition());
            return true;
        });

        // Nhấn giữ bản đồ → mở chỉ đường
        googleMap.setOnMapLongClickListener(latLng -> {
            openGoogleMapsForDirections(latLng);
        });
    }

    private void openGoogleMapsForDirections(LatLng destination) {
        String uri = "http://maps.google.com/maps?daddr=" + destination.latitude + "," + destination.longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "Google Maps chưa được cài đặt", Toast.LENGTH_SHORT).show();
        }
    }

    // ------- Vòng đời MapView -------
    @Override
    public void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mapView != null) mapView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mapView != null) mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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
