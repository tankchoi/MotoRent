package vn.aptech.java.dtos;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateVehicleDTO {

    @NotBlank(message = "Tên xe không được để trống")
    private String name;

    @NotBlank(message = "Hãng xe không được để trống")
    private String brand;

    @NotNull(message = "Giá thuê không được để trống")
    @Positive(message = "Giá thuê phải lớn hơn 0")
    private Double pricePerDay;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Biển số xe không được để trống")
    private String licensePlate;

    @NotEmpty(message = "Vui lòng chọn ít nhất một hình ảnh")
    private List<MultipartFile> images;

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
