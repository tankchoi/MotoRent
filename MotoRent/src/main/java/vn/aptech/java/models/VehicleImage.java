package vn.aptech.java.models;

import jakarta.persistence.*;
import vn.aptech.java.listeners.AuditEntityListener;

@EntityListeners(AuditEntityListener.class)
@Entity
@Table(name = "vehicle_images")
public class VehicleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vehicleId;
    private String url;

    public VehicleImage() {}
    public VehicleImage(Long vehicleId, String url) {
        this.vehicleId = vehicleId;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}