package com.example.motorentmobile.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorentmobile.R;
import com.example.motorentmobile.databinding.ActivityRegisterBinding;
import com.example.motorentmobile.viewmodel.RegisterViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    private Bitmap identityCardBitmap;
    private Bitmap driverLicenseBitmap;

    private RegisterViewModel registerViewModel;
    private static final int REQUEST_CODE_IDENTITY_CARD = 1;
    private static final int REQUEST_CODE_DRIVER_LICENSE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        // Khởi tạo ViewModel
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Liên kết ViewModel với binding
        binding.setViewModel(registerViewModel);
        binding.setLifecycleOwner(this);

        // Quan sát LiveData
        registerViewModel.getIdentityCardImage().observe(this, image -> {
            if (image != null) {
                binding.imvIdentityCard.setImageBitmap(image);
            }
        });

        // Button chọn ảnh từ thư viện cho căn cước công dân
        binding.btnSelectIdentityCard.setOnClickListener(v -> {
            registerViewModel.selectImageFromGallery(this, REQUEST_CODE_IDENTITY_CARD);
        });

        // Button chọn ảnh từ thư viện cho giấy phép lái xe
        binding.btnSelectDriverLicense.setOnClickListener(v -> {
            registerViewModel.selectImageFromGallery(this, REQUEST_CODE_DRIVER_LICENSE);
        });
        registerViewModel.getIsSuccess().observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                // Hiển thị Toast khi đăng ký thất bại
                Toast.makeText(this, "Đăng ký thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Đăng ký
        binding.btnRegister.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String password = binding.edtPassword.getText().toString();
            String fullName = binding.edtFullName.getText().toString();
            String phone = binding.edtPhone.getText().toString();

            if(identityCardBitmap == null || driverLicenseBitmap == null) {
                Toast.makeText(this, "Vui lòng chọn ảnh căn cước công dân và giấy phép lái xe", Toast.LENGTH_SHORT).show();
                return;
            }
            File identityCardFile = saveImageToFile(identityCardBitmap, "identityCard.jpg");
            File driverLicenseFile = saveImageToFile(driverLicenseBitmap, "driverLicense.jpg");

            registerViewModel.registerUser(email, password, fullName, phone, identityCardFile, driverLicenseFile);
        });

        binding.tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
    // Lưu ảnh vào tệp
    private File saveImageToFile(Bitmap bitmap, String fileName) {
        File file = new File(getExternalFilesDir(null), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    // Xử lý kết quả khi chọn ảnh từ thư viện
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                // Giảm kích thước ảnh để tránh tràn bộ nhớ
                Bitmap bitmap = decodeSampledBitmapFromUri(this, selectedImageUri, 800, 800); // Truyền context vào
                if (requestCode == REQUEST_CODE_IDENTITY_CARD) {
                    registerViewModel.setIdentityCardImage(bitmap);
                    identityCardBitmap = bitmap;
                } else if (requestCode == REQUEST_CODE_DRIVER_LICENSE) {
                    driverLicenseBitmap = bitmap;
                    registerViewModel.setDriverLicenseImage(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
