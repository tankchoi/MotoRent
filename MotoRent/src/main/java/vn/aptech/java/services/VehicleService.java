package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.java.models.Vehicle;
import vn.aptech.java.repositories.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleRepository.findAvailableVehicles(startTime, endTime);
    }
}
