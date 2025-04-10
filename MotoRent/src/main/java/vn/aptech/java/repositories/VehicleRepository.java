package vn.aptech.java.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.java.models.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = """
    SELECT v.* FROM vehicles v
    WHERE NOT EXISTS (
        SELECT 1 FROM rental_details rd
        JOIN rentals r ON rd.rental_id = r.id
        WHERE rd.license_plate = v.license_plate
        AND (:startTime < r.end_time AND :endTime > r.start_time)
        AND r.status <> 'COMPLETED'
    )
""", nativeQuery = true)
    List<Vehicle> findAvailableVehicles(@Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);
    @Query(value = """
SELECT v.* FROM vehicles v
WHERE EXISTS (
    SELECT 1 FROM rental_details rd
    JOIN rentals r ON rd.rental_id = r.id
    WHERE rd.license_plate = v.license_plate
    AND (:startTime < r.end_time AND :endTime > r.start_time)
    AND r.status <> 'COMPLETED'
)
""", nativeQuery = true)
    List<Vehicle> findUnavailableVehicles(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT v FROM Vehicle v WHERE v.id IN :ids")
    List<Vehicle> findAllByIdWithLock(@Param("ids") List<Long> ids);

}
