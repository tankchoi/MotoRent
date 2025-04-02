package vn.aptech.java.controllers.api;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private UserService userService;

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
        if (customer == null) {
            return ResponseEntity.status(404).body("Không tìm thấy khách hàng");
        }
        return ResponseEntity.ok(new CustomerDTO(customer));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@ModelAttribute @Valid UpdateCusomerDTO customerDto) {
        try {
            userService.updateCustomer(customerDto);
            return ResponseEntity.ok("Cập nhật thông tin khách hàng thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi cập nhật thông tin khách hàng");
        }
    }


}
