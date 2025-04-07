package vn.aptech.java.controllers.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.java.dtos.StaffDTO;
import vn.aptech.java.dtos.UpdateStaffDTO;
import vn.aptech.java.models.User;
import vn.aptech.java.services.UserService;

@Controller
public class ProfileMvcController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public String getProfile(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User staff = userService.findByEmail(currentEmail);
        model.addAttribute("staff", new StaffDTO(staff));
        return "pages/profile/index";
    }
    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute("staff") UpdateStaffDTO staffDTO,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pages/profile/index";
        }

        try {
            userService.updateStaff(staffDTO);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/profile";
    }


}
