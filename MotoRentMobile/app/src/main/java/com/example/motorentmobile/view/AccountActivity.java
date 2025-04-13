package com.example.motorentmobile.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.R;
import com.example.motorentmobile.databinding.ActivityAccountBinding;
import com.example.motorentmobile.viewmodel.AccountViewModel;

import java.io.IOException;
import java.io.InputStream;

public class AccountActivity extends BaseActivity {

    private ActivityAccountBinding binding;
    private AccountViewModel viewModel;
    private Uri identityCardUri, driverLicenseUri;
    private ProgressBar progressBar;
    private Button btnUpdate;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_account;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_account;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        FrameLayout container = findViewById(R.id.container);
        container.addView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);


        binding.backBtn.setOnClickListener(v -> {
            // Điều hướng về HomeActivity
            Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Đảm bảo rằng HomeActivity sẽ được mở lại
            startActivity(intent);
            finish(); // Đóng AccountActivity sau khi chuyển đến HomeActivity
        });

        btnUpdate = binding.btnUpdate; // Nút cập nhật tài khoản

        // Quan sát dữ liệu tài khoản
        viewModel.fetchAccountInfo();
        viewModel.getAccount().observe(this, account -> {
            if (account != null) {
                binding.setAccount(account);
                Glide.with(this).load(account.getIdentityCard()).into(binding.ivCCCD);  // Hiển thị ảnh CCCD
                Glide.with(this).load(account.getDriverLicense()).into(binding.ivGPLX);  // Hiển thị ảnh GPLX
            } else {
                Toast.makeText(this, "Không thể tải dữ liệu tài khoản", Toast.LENGTH_SHORT).show();
            }
        });

        // Quan sát trạng thái cập nhật
        viewModel.getUpdateSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý cập nhật tài khoản
        btnUpdate.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String fullName = binding.edtFullName.getText().toString();
            String phone = binding.edtPhone.getText().toString();

            if (identityCardUri != null && driverLicenseUri != null) {
                viewModel.updateAccountInfo(email, fullName, phone, identityCardUri, driverLicenseUri);
            } else {
                Toast.makeText(this, "Vui lòng chọn ảnh CCCD và GPLX", Toast.LENGTH_SHORT).show();
            }
        });

        // Bấm vào ảnh CCCD để chọn ảnh mới
        binding.ivCCCD.setOnClickListener(v -> openFilePicker.launch("image/*"));

        // Bấm vào ảnh GPLX để chọn ảnh mới
        binding.ivGPLX.setOnClickListener(v -> openFilePicker.launch("image/*"));
    }

    // Xử lý chọn ảnh CCCD và GPLX
    private final ActivityResultLauncher<String> openFilePicker = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    try {
                        Bitmap bitmap = decodeSampledBitmapFromUri(this, uri, 500, 500);
                        if (identityCardUri == null) {
                            identityCardUri = uri;
                            binding.ivCCCD.setImageBitmap(bitmap);
                        } else {
                            driverLicenseUri = uri;
                            binding.ivGPLX.setImageBitmap(bitmap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Không thể xử lý ảnh", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    /**
     * Hàm giảm kích thước ảnh trước khi giải mã
     */
    public static Bitmap decodeSampledBitmapFromUri(Context context, Uri uri, int reqWidth, int reqHeight) throws IOException {
        // Đọc dữ liệu ảnh và tính toán kích thước ảnh thực tế
        InputStream input = context.getContentResolver().openInputStream(uri);  // Sử dụng context.getContentResolver()
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // Chỉ lấy thông tin về kích thước ảnh mà không tải toàn bộ
        BitmapFactory.decodeStream(input, null, options);
        input.close();

        // Tính toán tỷ lệ phù hợp
        int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Giải mã ảnh với tỷ lệ phù hợp
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        input = context.getContentResolver().openInputStream(uri);  // Sử dụng context.getContentResolver()
        return BitmapFactory.decodeStream(input, null, options);
    }

    /**
     * Tính toán tỷ lệ giảm kích thước ảnh
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Kích thước ảnh gốc
        final int height = options.outHeight;
        final int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Tính toán tỷ lệ phù hợp
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Kiểm tra tỷ lệ nhỏ nhất
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
