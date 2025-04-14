package com.example.motorentmobile.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.example.motorentmobile.util.SharedPreferencesHelper;
import com.example.motorentmobile.viewmodel.AccountViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AccountActivity extends BaseActivity {

    private ActivityAccountBinding binding;
    private AccountViewModel viewModel;
    private Uri identityCardUri, driverLicenseUri;
    private ProgressBar progressBar;
    private Button btnUpdate;
    private SharedPreferencesHelper prefs;

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
        prefs = SharedPreferencesHelper.getInstance(this);
        if(!prefs.isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
        viewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Log.d("AccountActivity", "LỖI từ ViewModel: " + errorMessage);  // 👈 Log lỗi
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getUpdateSuccess().observe(this, success -> {
            if (success != null) {
                Log.d("AccountActivity", "Cập nhật thành công? " + success);  // 👈 Log kết quả
                if (success) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnUpdate.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String fullName = binding.edtFullName.getText().toString();
            String phone = binding.edtPhone.getText().toString();


            File cccdFile = null;
            File gplxFile = null;

            try {
                if (identityCardUri != null) {
                    Bitmap cccdBitmap = decodeSampledBitmapFromUri(this, identityCardUri, 500, 500);
                    cccdFile = saveImageToFile(cccdBitmap, "identity_card.jpg");
                }

                if (driverLicenseUri != null) {
                    Bitmap gplxBitmap = decodeSampledBitmapFromUri(this, driverLicenseUri, 500, 500);
                    gplxFile = saveImageToFile(gplxBitmap, "driver_license.jpg");
                }

                // Gửi lên ViewModel, cho phép truyền null nếu người dùng không chọn ảnh mới
                viewModel.updateAccountInfo(email, fullName, phone, cccdFile, gplxFile);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Không thể xử lý ảnh", Toast.LENGTH_SHORT).show();
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
    private File saveImageToFile(Bitmap bitmap, String fileName) {
        File file = new File(getExternalFilesDir(null), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

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
