package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.aptech.java.models.Rental;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.NotificationRepository;
import vn.aptech.java.repositories.RentalRepository;
import vn.aptech.java.repositories.UserRepository;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private NotificationService notificationService;
    public List<Rental> getRentalsByLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByEmail(currentUsername);
        if (user == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        return rentalRepository.findByUserId(user.getId());
    }
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }
    public void updateRentalStatus(Long id, Rental.RentalStatus status) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental == null) {
            throw new IllegalArgumentException("Rental không tồn tại");
        }
        if (rental.getStatus() == Rental.RentalStatus.CANCELLED) {
            throw new IllegalArgumentException("Rental đã bị hủy");
        }
        rental.setStatus(status);
        if (status == Rental.RentalStatus.COMPLETED) {
            rental.setAmountPaid(rental.getTotalPrice());

            notificationService.sendNotificationToUser(
                    rental.getUser(),
                    "Đơn thuê #" + rental.getId() + " đã hoàn thành. Cảm ơn bạn đã sử dụng dịch vụ!"
            );
        }
        if (status == Rental.RentalStatus.CANCELLED) {
            notificationService.sendNotificationToUser(
                    rental.getUser(),
                    "Đơn thuê #" + rental.getId() + " đã bị huỷ."
            );
        }
        if (status == Rental.RentalStatus.RENTED) {
            if(rental.getTotalPrice() == rental.getAmountPaid()) {
                rental.setAmountPaid(rental.getTotalPrice()/2);
            }
            notificationService.sendNotificationToUser(
                    rental.getUser(),
                    "Đơn thuê #" + rental.getId() + " đã được xác nhận và đang trong thời gian thuê. Chúc bạn có trải nghiệm tốt!"
            );
        }


        rentalRepository.save(rental);
    }

}
