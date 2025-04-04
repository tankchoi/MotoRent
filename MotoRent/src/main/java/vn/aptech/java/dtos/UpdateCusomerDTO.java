package vn.aptech.java.dtos;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class UpdateCusomerDTO {
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại phải có 10 số và bắt đầu bằng số 0")
    private String phone;

    private MultipartFile identityCard; // Ảnh CMND/CCCD

    private MultipartFile driverLicense; // Ảnh giấy phép lái xe
    public UpdateCusomerDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public MultipartFile getIdentityCard() {
        return identityCard;
    }

    public MultipartFile getDriverLicense() {
        return driverLicense;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setIdentityCard(MultipartFile identityCard) {
        this.identityCard = identityCard;
    }

    public void setDriverLicense(MultipartFile driverLicense) {
        this.driverLicense = driverLicense;
    }
}
