package com.example.motorentmobile.data.model;

import java.io.File;

public class UpdateAccount {
    private String email;
    private String fullName;
    private String phone;
    private File identityCardImage;
    private File driverLicenseImage;

    public UpdateAccount(String email, String fullName, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
