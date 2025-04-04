package vn.aptech.java.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
     @GetMapping("/logon")
     public String login() {
         return "pages/login/login";
     }
}
