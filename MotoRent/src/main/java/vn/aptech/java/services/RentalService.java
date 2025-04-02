package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.aptech.java.models.Rental;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.RentalRepository;
import vn.aptech.java.repositories.UserRepository;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalRepository rentalRepository;
    public List<Rental> getRentalsByLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByEmail(currentUsername);
        if (user == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        return rentalRepository.findByUserId(user.getId());
    }
}
