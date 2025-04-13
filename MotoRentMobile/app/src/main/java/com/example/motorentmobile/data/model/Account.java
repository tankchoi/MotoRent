package com.example.motorentmobile.data.model;

import java.time.LocalDateTime;

public class Account {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String identityCard;
    private String driverLicense;
    private String role;
    private String createdAt;
    private String updatedAt;

    public Account(Long id, String fullName, String email, String phone, String identityCard, String driverLicense, String role, String createdAt, String updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.identityCard = identityCard;
        this.driverLicense = driverLicense;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
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

    public String getIdentityCard() {
        return identityCard;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Account{fullName=" + fullName + ", phone='" + phone + "', email='" + email + "'}";
    }

}
