package vn.aptech.java.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.java.models.User;
import vn.aptech.java.services.UserService;

@Controller
@RequestMapping("/customers")
public class CustomerMvcController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", userService.getAllCustomers());
        return "pages/customer/index";
    }
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        var customer = userService.getUserById(id);
        if (customer == null || customer.getRole() != User.Role.CUSTOMER) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "pages/customer/update";
    }
    
}
