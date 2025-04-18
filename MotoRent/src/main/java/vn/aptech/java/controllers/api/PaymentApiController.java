package vn.aptech.java.controllers.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.aptech.java.dtos.CreateRentalDTO;
import vn.aptech.java.models.Rental;
import vn.aptech.java.repositories.RentalRepository;
import vn.aptech.java.services.PaymentService;
import vn.aptech.java.services.RentalService;


import java.io.IOException;
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
    private PaymentService paymentService;
    @PostMapping("/api/payment/vnpay/create")
    public ResponseEntity<?> createPayment(HttpServletRequest request, @RequestBody CreateRentalDTO dto) throws UnsupportedEncodingException {
        try {
            Rental rental = rentalService.createRental(dto);
            String paymentUrl = paymentService.createVnPayPayment(request, rental.getId(), dto.getAmountPaid());
            return ResponseEntity.ok(Collections.singletonMap("paymentUrl", paymentUrl));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Đã xảy ra lỗi trong quá trình tạo thanh toán."));
        }
    }

    @GetMapping("/api/payment/vnpay/return")
    public void handleVnPayReturn(
            @RequestParam Map<String, String> params,
            HttpServletResponse response) throws IOException {

        String responseCode = params.get("vnp_ResponseCode");
        String rentalId = params.get("vnp_TxnRef");

        try {
            Long id = Long.valueOf(rentalId);

            if ("00".equals(responseCode)) {
                rentalService.updateRentalStatus(id, Rental.RentalStatus.PENDING);
                String deepLink = "motorrent://payment-return?status=success&orderId=" + rentalId;
                response.sendRedirect(deepLink);
                System.out.println(deepLink);
            } else {
                rentalService.deleteRental(id);
                String deepLink = "motorrent://payment-return?status=failure&orderId=" + rentalId;
                response.sendRedirect(deepLink);
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
            String deepLink = "motorrent://payment-return?status=error";
            response.sendRedirect(deepLink);
        }
    }





}
