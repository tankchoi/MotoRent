package vn.aptech.java.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import vn.aptech.java.models.Rental;
import vn.aptech.java.services.RentalService;
import java.util.List;
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping("/customer-rentals")
    public ResponseEntity<List<Rental>> getCustomerRentals() {
        List<Rental> rentals = rentalService.getRentalsByLoggedInCustomer();
        return ResponseEntity.ok(rentals);
    }
}
