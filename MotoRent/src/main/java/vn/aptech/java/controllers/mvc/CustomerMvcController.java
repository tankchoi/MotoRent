package vn.aptech.java.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vn.aptech.java.services.UserService;

@Controller
public class CustomerMvcController {
    @Autowired
    private UserService userService;

}
