package vn.aptech.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.java.models.RentalDetail;

public interface RentalDetailRepository extends JpaRepository<RentalDetail, Long> {
    @Modifying
    @Query("DELETE FROM RentalDetail rd WHERE rd.rental.id = :id")
    void deleteDetailsByRentalId(@Param("id") Long id);

}
