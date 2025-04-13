package com.example.motorentmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    // Lưu Bitmap vào bộ nhớ cache với định dạng và chất lượng có thể cấu hình
    public static Uri saveBitmapToCache(Context context, Bitmap bitmap, String fileName, Bitmap.CompressFormat format, int quality) throws IOException {
        File file = new File(context.getCacheDir(), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(format, quality, fos);
        }
        return Uri.fromFile(file);
    }

    // Chuyển Uri thành File
    public static File getFileFromUri(Context context, Uri uri) throws IOException {
        File file = new File(context.getCacheDir(), "upload_" + System.currentTimeMillis() + ".jpg");

        try (InputStream inputStream = context.getContentResolver().openInputStream(uri);
             FileOutputStream outputStream = new FileOutputStream(file)) {

            if (inputStream == null) {
                throw new IOException("Cannot open input stream from URI");
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        return file;
    }
}
