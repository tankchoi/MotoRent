package vn.aptech.java.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import vn.aptech.java.listeners.AuditEntityListener;

@EntityListeners(AuditEntityListener.class)
@Entity
@Table(name = "rental_details")
public class RentalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "price_per_day")
    private double pricePerDay;

    public RentalDetail() {}

    public RentalDetail(Rental rental, String vehicleName, String vehicleBrand, String licensePlate, double pricePerDay) {
        this.rental = rental;
        this.vehicleName = vehicleName;
        this.vehicleBrand = vehicleBrand;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public Rental getRental() {
        return rental;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
