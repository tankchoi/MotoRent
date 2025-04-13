package com.example.motorentmobile.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.R;
import com.example.motorentmobile.data.repository.AccountRepository;
import com.example.motorentmobile.databinding.ActivityAccountBinding;
import com.example.motorentmobile.utils.FileUtils;
import com.example.motorentmobile.viewmodel.AccountViewModel;

import java.io.IOException;
import java.io.InputStream;

public class AccountActivity extends BaseActivity {

    private ActivityAccountBinding binding;
    private AccountViewModel viewModel;
    private Uri identityCardUri, driverLicenseUri;
    private int currentRequestCode = -1;

    private static final int REQUEST_CODE_IDENTITY_CARD = 1;
    private static final int REQUEST_CODE_DRIVER_LICENSE = 2;
    private ProgressBar progressBar;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        progressBar = binding.progressBar; // Khởi tạo ProgressBar

        // Quan sát dữ liệu tài khoản
        viewModel.fetchAccount();
        viewModel.getAccount().observe(this, account -> {
            if (account != null) {
                binding.setAccount(account);  // Đảm bảo gọi này sau khi dữ liệu có
                Glide.with(this).load(account.getIdentityCard()).into(binding.ivCCCD);
                Glide.with(this).load(account.getDriverLicense()).into(binding.ivGPLX);
                Log.d("AccountActivity", "Account data loaded successfully: " + account.toString());
            } else {
                Toast.makeText(this, "Không thể tải dữ liệu tài khoản", Toast.LENGTH_SHORT).show();
                Log.e("AccountActivity", "Failed to load account data.");
            }
        });


        // Xử lý chọn ảnh CCCD
        binding.ivCCCD.setOnClickListener(v -> {
            currentRequestCode = REQUEST_CODE_IDENTITY_CARD;
            openFilePicker.launch("image/*");
            Log.d("AccountActivity", "Selected to pick Identity Card image.");
        });

        // Xử lý chọn ảnh GPLX
        binding.ivGPLX.setOnClickListener(v -> {
            currentRequestCode = REQUEST_CODE_DRIVER_LICENSE;
            openFilePicker.launch("image/*");
            Log.d("AccountActivity", "Selected to pick Driver License image.");
        });

        // Xử lý nút cập nhật
        binding.btnUpdate.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String fullName = binding.edtFullName.getText().toString();
            String phone = binding.edtPhone.getText().toString();

            // Kiểm tra các trường nhập vào
            if (fullName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(AccountActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                Log.d("AccountActivity", "Incomplete information: FullName: " + fullName + ", Phone: " + phone + ", Email: " + email);
                return;
            }

            // Disable nút để tránh spam
            binding.btnUpdate.setEnabled(false);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            Log.d("AccountActivity", "Starting account update for email: " + email);

            try {
                viewModel.updateAccount(
                        this,
                        email,
                        fullName,
                        phone,
                        identityCardUri,
                        driverLicenseUri,
                        new AccountRepository.UpdateCallback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(AccountActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                binding.btnUpdate.setEnabled(true);
                                Log.d("AccountActivity", "Account update successful.");
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(AccountActivity.this, "Cập nhật thất bại! " + t.getMessage(), Toast.LENGTH_LONG).show();
                                binding.btnUpdate.setEnabled(true);
                                Log.e("AccountActivity", "Account update failed with error: " + t.getMessage());
                            }

                            @Override
                            public void onFailure() {
                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                Toast.makeText(AccountActivity.this, "Cập nhật thất bại! Không thể kết nối với máy chủ", Toast.LENGTH_LONG).show();
                                binding.btnUpdate.setEnabled(true);
                                Log.e("AccountActivity", "Account update failed due to connection issue.");
                            }
                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("AccountActivity", "Error during account update: " + e.getMessage());
            }
        });
    }

    /**
     * Launcher chọn ảnh CCCD hoặc GPLX
     */
    private final ActivityResultLauncher<String> openFilePicker = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    try {
                        Log.d("AccountActivity", "Image selected: " + uri.toString());
                        Bitmap bitmap = decodeSampledBitmapFromUri(this, uri, 800, 800);
                        if (currentRequestCode == REQUEST_CODE_IDENTITY_CARD) {
                            identityCardUri = FileUtils.saveBitmapToCache(this, bitmap, "cccd.jpg");
                            binding.ivCCCD.setImageBitmap(bitmap);
                            Log.d("AccountActivity", "Identity Card image set.");
                        } else if (currentRequestCode == REQUEST_CODE_DRIVER_LICENSE) {
                            driverLicenseUri = FileUtils.saveBitmapToCache(this, bitmap, "gplx.jpg");
                            binding.ivGPLX.setImageBitmap(bitmap);
                            Log.d("AccountActivity", "Driver License image set.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Không thể xử lý ảnh", Toast.LENGTH_SHORT).show();
                        Log.e("AccountActivity", "Error processing image: " + e.getMessage());
                    }
                }
            }
    );

    /**
     * Giảm kích thước ảnh trước khi giải mã
     */
    public static Bitmap decodeSampledBitmapFromUri(Context context, Uri uri, int reqWidth, int reqHeight) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);
        input.close();

        int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        input = context.getContentResolver().openInputStream(uri);
        return BitmapFactory.decodeStream(input, null, options);
    }

    /**
     * Tính toán tỷ lệ giảm kích thước ảnh
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
