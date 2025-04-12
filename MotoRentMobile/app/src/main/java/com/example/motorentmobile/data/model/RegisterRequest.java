package com.example.motorentmobile.data.model;

import java.io.File;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private File identityCardImage;
    private File driverLicenseImage;

    public RegisterRequest(String email, String password, String fullName, String phone,
                           File identityCardImage, File driverLicenseImage) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.identityCardImage = identityCardImage;
        this.driverLicenseImage = driverLicenseImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public File getIdentityCardImage() {
        return identityCardImage;
    }

    public void setIdentityCardImage(File identityCardImage) {
        this.identityCardImage = identityCardImage;
    }

    public File getDriverLicenseImage() {
        return driverLicenseImage;
    }

    public void setDriverLicenseImage(File driverLicenseImage) {
        this.driverLicenseImage = driverLicenseImage;
    }
}
