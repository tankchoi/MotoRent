package vn.aptech.java.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageMvcController {
    @GetMapping
    public String getHomePage() {
        return "pages/homepage/index";
    }

}
