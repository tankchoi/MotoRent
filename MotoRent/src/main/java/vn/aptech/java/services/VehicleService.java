package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.java.dtos.CreateVehicleDTO;
import vn.aptech.java.dtos.UpdateVehicleDTO;
import vn.aptech.java.models.Vehicle;
import vn.aptech.java.models.VehicleImage;
import vn.aptech.java.repositories.VehicleImageRepository;
import vn.aptech.java.repositories.VehicleRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleImageRepository vehicleImageRepository;
    @Autowired
    private FileStorageService fileStorageService;

    public List<Vehicle> getAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleRepository.findAvailableVehicles(startTime, endTime);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public void createVehicle(CreateVehicleDTO dto) {
        Vehicle vehicle = new Vehicle(
                dto.getName(),
                dto.getBrand(),
                dto.getPricePerDay(),
                dto.getDescription(),
                dto.getLicensePlate()
        );
        vehicleRepository.save(vehicle);
        for (MultipartFile image : dto.getImages()) {
            try {
                String filePath = fileStorageService.saveFile(image);
                VehicleImage vehicleImage = new VehicleImage(vehicle.getId(), filePath);
                vehicleImageRepository.save(vehicleImage);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi lưu ảnh: " + e.getMessage());
            }
        }
    }

    public void updateVehicle(UpdateVehicleDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Xe không tồn tại"));

        vehicle.setName(dto.getName());
        vehicle.setBrand(dto.getBrand());
        vehicle.setPricePerDay(dto.getPricePerDay());
        vehicle.setDescription(dto.getDescription());
        vehicle.setLicensePlate(dto.getLicensePlate());

        vehicleRepository.save(vehicle);


        List<MultipartFile> newImages = dto.getImages();
        if (newImages != null && !newImages.isEmpty() && !newImages.get(0).isEmpty()) {

            List<VehicleImage> oldImages = vehicleImageRepository.findByVehicleId(vehicle.getId());
            for (VehicleImage img : oldImages) {
                fileStorageService.deleteFile(img.getUrl());
                vehicleImageRepository.delete(img);
            }

            for (MultipartFile file : newImages) {
                try {
                    String imagePath = fileStorageService.saveFile(file);
                    VehicleImage newImg = new VehicleImage(vehicle.getId(), imagePath);
                    vehicleImageRepository.save(newImg);
                } catch (IOException e) {
                    throw new RuntimeException("Lỗi lưu ảnh: " + e.getMessage());
                }
            }
        }
    }
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle == null) {
            throw new IllegalArgumentException("Xe không tồn tại");
        }

        List<VehicleImage> images = vehicleImageRepository.findByVehicleId(id);
        for (VehicleImage image : images) {
            fileStorageService.deleteFile(image.getUrl()); 
            vehicleImageRepository.delete(image);
        }

        vehicleRepository.delete(vehicle);
    }

}
