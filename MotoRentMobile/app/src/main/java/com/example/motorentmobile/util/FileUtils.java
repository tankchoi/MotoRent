package com.example.motorentmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static Uri saveBitmapToCache(Context context, Bitmap bitmap, String fileName) throws IOException {
        File file = new File(context.getCacheDir(), fileName);
        FileOutputStream fos = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        fos.close();
        return Uri.fromFile(file);
    }

    public static File getFileFromUri(Context context, Uri uri) throws IOException {
        File file = new File(context.getCacheDir(), "upload_" + System.currentTimeMillis() + ".jpg");
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri);
             FileOutputStream outputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        return file;
    }
}
