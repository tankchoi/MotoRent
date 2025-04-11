package vn.edu.tlu;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import vn.edu.tlu.ui.BaseFragment;

public class AccountFragment extends BaseFragment {

    private ImageView ivCCCD;
    private ImageView ivGPLX;

    private boolean isSelectingCCCD = false;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView(View view) {
        ivCCCD = view.findViewById(R.id.ivCCCD);
        ivGPLX = view.findViewById(R.id.ivGPLX);
    }

    @Override
    protected void initData() {
        // Khởi tạo launcher chỉ một lần
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == android.app.Activity.RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            if (isSelectingCCCD) {
                                ivCCCD.setImageURI(selectedImageUri);
                            } else {
                                ivGPLX.setImageURI(selectedImageUri);
                            }
                        }
                    }
                }
        );
    }

    @Override
    protected void initListeners() {
        ivCCCD.setOnClickListener(v -> {
            isSelectingCCCD = true;
            openImagePicker();
        });

        ivGPLX.setOnClickListener(v -> {
            isSelectingCCCD = false;
            openImagePicker();
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}
