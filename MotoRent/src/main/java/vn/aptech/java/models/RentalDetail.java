package vn.aptech.java.models;

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
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public RentalDetail() {}
    public RentalDetail(Rental rental, Vehicle vehicle) {
        this.rental = rental;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public Rental getRental() {
        return rental;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
