package vn.aptech.java.controllers.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.aptech.java.dtos.RegisterCustomerDTO;
import vn.aptech.java.dtos.CustomerDTO;
import vn.aptech.java.dtos.UpdateCusomerDTO;
import vn.aptech.java.models.User;
import vn.aptech.java.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        try {
            if (userService.loginCustomer(email, password)) {
                return ResponseEntity.ok("Đăng nhập thành công");
            } else {
                return ResponseEntity.badRequest().body("Đăng nhập thất bại");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi đăng nhập");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@ModelAttribute @Valid RegisterCustomerDTO customerDto) {
        try {
            userService.registerCustomer(customerDto);
            return ResponseEntity.ok("Đăng ký khách hàng thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi đăng ký khách hàng");
        }
    }

    @GetMapping("/customer-info")
    public ResponseEntity<?> getCustomerInfo(@AuthenticationPrincipal UserDetails userDetails) {
        User customer = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(new CustomerDTO(customer));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@ModelAttribute @Valid UpdateCusomerDTO customerDto) {
        try {
            // Logging các trường dữ liệu text
            System.out.println("===> [PUT] /api/customers/update được gọi");
            System.out.println("Email: " + customerDto.getEmail());
            System.out.println("Full Name: " + customerDto.getFullName());
            System.out.println("Phone: " + customerDto.getPhone());

            // Logging ảnh CCCD
            if (customerDto.getIdentityCard() != null && !customerDto.getIdentityCard().isEmpty()) {
                System.out.println("Identity Card Image: " + customerDto.getIdentityCard().getOriginalFilename());
                System.out.println("Size: " + customerDto.getIdentityCard().getSize());
            } else {
                System.out.println("Identity Card Image: null or empty");
            }

            // Logging ảnh GPLX
            if (customerDto.getDriverLicense() != null && !customerDto.getDriverLicense().isEmpty()) {
                System.out.println("Driver License Image: " + customerDto.getDriverLicense().getOriginalFilename());
                System.out.println("Size: " + customerDto.getDriverLicense().getSize());
            } else {
                System.out.println("Driver License Image: null or empty");
            }

            userService.updateCustomer(customerDto);
            return ResponseEntity.ok("Cập nhật thông tin khách hàng thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // In stack trace để debug kỹ hơn
            return ResponseEntity.internalServerError().body("Lỗi khi cập nhật thông tin khách hàng");
        }
    }



}
