package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.aptech.java.dtos.CreateRentalDTO;
import vn.aptech.java.models.Rental;
import vn.aptech.java.models.RentalDetail;
import vn.aptech.java.models.User;
import vn.aptech.java.models.Vehicle;
import vn.aptech.java.repositories.NotificationRepository;
import vn.aptech.java.repositories.RentalDetailRepository;
import vn.aptech.java.repositories.RentalRepository;
import vn.aptech.java.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private UserService userService;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RentalDetailRepository rentalDetailRepository;
    @Autowired
    private VehicleService vehicleService;

    public List<Rental> getRentalsByLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByEmail(currentUsername);
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
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rental không tồn tại"));

        if (rental.getStatus() == Rental.RentalStatus.CANCELLED) {
            throw new IllegalArgumentException("Rental đã bị hủy");
        }

        rental.setStatus(status);

        String message = null;
        Long rentalId = rental.getId();

        switch (status) {
            case COMPLETED:
                rental.setAmountPaid(rental.getTotalPrice());
                message = "Đơn thuê #" + rentalId + " đã hoàn thành. Cảm ơn bạn đã sử dụng dịch vụ!";
                break;

            case PENDING:
                message = "Đơn thuê #" + rentalId + " thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!";
                break;

            case CANCELLED:
                message = "Đơn thuê #" + rentalId + " đã bị huỷ.";
                break;

            case RENTED:
                if (rental.getTotalPrice() == rental.getAmountPaid()) {
                    rental.setAmountPaid(rental.getTotalPrice() / 2);
                }
                message = "Đơn thuê #" + rentalId + " đã được xác nhận và đang trong thời gian thuê. Chúc bạn có trải nghiệm tốt!";
                break;
        }

        if (message != null) {
            notificationService.sendNotificationToUser(rental.getUser(), message);
        }

        rentalRepository.save(rental);
    }

    @Transactional
    public Rental createRental(CreateRentalDTO dto) {
        List<Vehicle> lockedVehicles = vehicleService.lockVehiclesByIds(dto.getVehicleIds());

        List<Vehicle> unavailableVehicles = vehicleService.getUnavailableVehicles(dto.getStartTime(), dto.getEndTime());

        for (Vehicle v : unavailableVehicles) {
            if (dto.getVehicleIds().contains(v.getId())) {
                throw new IllegalArgumentException("Xe " + v.getName() + " đã được đặt trong khoảng thời gian này.");
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByEmail(currentUsername);
        if (user == null) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setStartTime(dto.getStartTime());
        rental.setEndTime(dto.getEndTime());
        rental.setTotalPrice(dto.getTotalPrice());
        rental.setAmountPaid(dto.getAmountPaid());
        rental.setPaymentMethod(dto.getPaymentMethod());
        rental.setStatus(Rental.RentalStatus.UNPAID);

        List<Vehicle> vehicles = vehicleService.findAllById(dto.getVehicleIds());
        if (vehicles.size() != dto.getVehicleIds().size()) {
            throw new IllegalArgumentException("Một hoặc nhiều xe không tồn tại");
        }

        List<RentalDetail> rentalDetails = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            RentalDetail detail = new RentalDetail();
            detail.setRental(rental);
            detail.setVehicleName(vehicle.getName());
            detail.setVehicleBrand(vehicle.getBrand());
            detail.setLicensePlate(vehicle.getLicensePlate());
            detail.setPricePerDay(vehicle.getPricePerDay());
            rentalDetails.add(detail);
        }

        rental.setRentalDetails(rentalDetails);
        return rentalRepository.save(rental);
    }
    @Transactional
    public void deleteRental(Long id) {
        rentalDetailRepository.deleteDetailsByRentalId(id);
        rentalRepository.deleteByRentalId(id);
    }
    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void autoCancelUnpaidRentals() {
        LocalDateTime timeoutThreshold = LocalDateTime.now().minusMinutes(15);
        List<Rental> expiredUnpaid = rentalRepository.findByStatusAndCreatedAtBefore(Rental.RentalStatus.UNPAID, timeoutThreshold);
        for (Rental rental : expiredUnpaid) {
            deleteRental(rental.getId());
            System.out.println("Auto-cancelled rental id: " + rental.getId());
        }
    }




}
