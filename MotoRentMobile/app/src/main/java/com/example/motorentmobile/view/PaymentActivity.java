package com.example.motorentmobile.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.motorentmobile.R;
import com.example.motorentmobile.adapter.PaymentAdapter;
import com.example.motorentmobile.databinding.ActivityPaymentBinding;
import com.example.motorentmobile.util.FormatMoneyUtil;
import com.example.motorentmobile.viewmodel.PaymentViewModel;
import com.example.motorentmobile.viewmodel.RentalManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private ActivityPaymentBinding binding;
    private PaymentViewModel viewModel;
    private RentalManager rentalManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment);
        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        rentalManager = RentalManager.getInstance();

        setupUI();
        observeViewModel();
    }

    private void setupUI() {
        // Hiển thị thời gian thuê và trả
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date startDate = rentalManager.getStartTime();
        Date endDate = rentalManager.getEndTime();

        if (startDate != null)
            binding.tvStartTime.setText("Thời gian thuê: " + sdf.format(startDate));

        if (endDate != null)
            binding.tvEndTime.setText("Thời gian trả: " + sdf.format(endDate));

        // Hiển thị tổng tiền
        String formattedPrice = FormatMoneyUtil.format(rentalManager.getTotalPrice());
        binding.tvTotalPrice.setText("Giá tiền: " + formattedPrice);

        // Hiển thị mặc định trả full
        binding.rdioFull.setChecked(true);
        binding.tvAmountPaid.setText("Phải trả: " + formattedPrice);

        // Hiển thị danh sách xe
        binding.rvVehicleList.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rvVehicleList.setAdapter(new PaymentAdapter(rentalManager.getVehiclesLiveData().getValue()));

        // Chọn hình thức thanh toán
        binding.rdioFull.setOnClickListener(v -> {
            binding.tvAmountPaid.setText("Phải trả: " + formattedPrice);
        });

        binding.rdioDeposit.setOnClickListener(v -> {
            String halfPrice = FormatMoneyUtil.format(rentalManager.getTotalPrice() / 2);
            binding.tvAmountPaid.setText("Phải trả: " + halfPrice);
        });

        // Xác nhận thanh toán
        binding.btnConfirm.setOnClickListener(v -> {
            binding.btnConfirm.setEnabled(false);
            binding.btnConfirm.setText("Đang chờ thanh toán...");

            boolean payFull = binding.rdioFull.isChecked();
            double amount = payFull ? rentalManager.getTotalPrice() : rentalManager.getTotalPrice() / 2;
            viewModel.processPayment(amount);
        });

        // Nút quay lại
        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, RentalActivity.class));
            finish();
        });
    }

    private void observeViewModel() {
        viewModel.paymentUrl.observe(this, url -> {
            binding.btnConfirm.setEnabled(false);
            binding.btnConfirm.setText("Đã gửi yêu cầu thanh toán");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });

        viewModel.errorMessage.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, RentalActivity.class));
            finish();
        });
    }
}
