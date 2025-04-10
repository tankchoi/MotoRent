package vn.aptech.java.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.aptech.java.listeners.AuditEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@EntityListeners(AuditEntityListener.class)
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private double pricePerDay;
    private String description;
    private String licensePlate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VehicleImage> vehicleImages = new ArrayList<>();;

    public Vehicle() {}

    public Vehicle(String name, String brand, double pricePerDay, String description, String licensePlate) {
        this.name = name;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.description = description;
        this.licensePlate = licensePlate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public List<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }
    public void setVehicleImages(List<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }


}