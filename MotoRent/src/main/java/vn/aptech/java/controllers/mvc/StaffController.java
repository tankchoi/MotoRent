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
public class StaffController {
    @Autowired
    private UserService userService;
    @GetMapping("/account")
    public String getAccount(Model model) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User staff = userService.findByEmail(currentEmail);
        model.addAttribute("staff", new StaffDTO(staff));
        return "pages/account/account";
    }
    @PostMapping("/account/update")
    public String updateAccount(@Valid @ModelAttribute UpdateStaffDTO staffDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Thông tin không hợp lệ!");
            return "pages/account/account";
        }
        userService.updateStaff(staffDTO);
        redirectAttributes.addFlashAttribute("success", "Cập nhật tài khoản thành công!");
        return "redirect:/account";
    }

}
