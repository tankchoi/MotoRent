package vn.aptech.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.aptech.java.models.Rental;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(Long userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Rental r WHERE r.id = :id")
    void deleteByRentalId(@Param("id") Long id);
    @Query("SELECT r FROM Rental r WHERE r.status = :status AND r.createdAt < :timeoutThreshold")
    List<Rental> findByStatusAndCreatedAtBefore(@Param("status") Rental.RentalStatus status,
                                                @Param("timeoutThreshold") LocalDateTime timeoutThreshold);
    List<Rental> findByUserIdOrderByCreatedAtDesc(Long userId);



}
