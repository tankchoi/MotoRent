package vn.aptech.java.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import java.io.IOException;
import java.nio.file.*;
@Service
public class FileStorageService {
    private String uploadDir = "uploads";
    public String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa có
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return uploadDir + "/" + fileName;
    }
    public void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            } else {
                System.out.println("File không tồn tại tại: " + path.toString());
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi xóa file: " + e.getMessage());
        }
    }

}


