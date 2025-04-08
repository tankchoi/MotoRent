package vn.aptech.java.controllers.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.aptech.java.dtos.CreateRentalDTO;
import vn.aptech.java.models.Rental;
import vn.aptech.java.repositories.RentalRepository;
import vn.aptech.java.services.PaymentService;
import vn.aptech.java.services.RentalService;


import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class PaymentApiController
{
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/api/payment/vnpay/create")
    public ResponseEntity<?> createPayment(HttpServletRequest request, @RequestBody CreateRentalDTO dto) throws UnsupportedEncodingException {

        Rental rental = rentalService.createRental(dto);
        String paymentUrl = paymentService.createVnPayPayment(request, rental.getId(), dto.getAmountPaid());

        return ResponseEntity.ok(Collections.singletonMap("paymentUrl", paymentUrl));
    }
    @GetMapping("/api/payment/vnpay/return")
    public ResponseEntity<String> handleVnPayReturn(@RequestParam Map<String, String> params) {
        String responseCode = params.get("vnp_ResponseCode");
        String rentalId = params.get("vnp_TxnRef");

        Optional<Rental> rentalOpt = rentalRepository.findById(Long.valueOf(rentalId));
        if (rentalOpt.isEmpty()) return ResponseEntity.badRequest().body("Rental not found");

        Rental rental = rentalOpt.get();

        if ("00".equals(responseCode)) {
            rental.setStatus(Rental.RentalStatus.PENDING);
            rentalRepository.save(rental);
            return ResponseEntity.ok("Thanh toán thành công");
        } else {
            rentalRepository.delete(rental);
            return ResponseEntity.ok("Thanh toán thất bại");
        }
    }




}
