package vn.aptech.java.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.aptech.java.services.UserService;

@Controller("/customers")
public class CustomerMvcController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", userService.getAllCustomers());
        return "pages/customer/index";
    }
    
}
