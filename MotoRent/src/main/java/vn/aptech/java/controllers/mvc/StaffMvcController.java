package vn.aptech.java.controllers.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.java.dtos.CreateStaffDTO;
import vn.aptech.java.dtos.UpdateStaffByAdminDTO;
import vn.aptech.java.services.UserService;

@Controller
@RequestMapping("/staffs")
public class StaffMvcController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAllStaffs(Model model) {
        model.addAttribute("staffs", userService.getAllStaffs());
        return "pages/staff/index";
    }
    @GetMapping("/{id}")
    public String getStaffById(@PathVariable Long id, Model model) {
        var staff = userService.getUserById(id);
        if (staff == null) {
            return "redirect:/staffs";
        }
        model.addAttribute("staff", staff);
        return "pages/staff/update";
    }
    @GetMapping("/create")
    public String createStaff(Model model) {
        model.addAttribute("staff", new CreateStaffDTO());
        return "pages/staff/create";
    }
    @PostMapping("/create")
    public String createStaff(
            @Valid @ModelAttribute("staff") CreateStaffDTO staffDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "pages/staff/create";
        }

        try {
            userService.createStaff(staffDTO);
            redirectAttributes.addFlashAttribute("success", "Tạo nhân viên thành công.");
            return "redirect:/staffs";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("staff", staffDTO);
            return "pages/staff/create";
        }
    }
    @PostMapping("/update")
    public String updateStaffByAdmin(
            @Valid @ModelAttribute("staff") UpdateStaffByAdminDTO staffDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "pages/staff/update";
        }

        try {
            userService.updateStaffByAdmin(staffDTO);
            redirectAttributes.addFlashAttribute("success", "Cập nhật nhân viên thành công.");
            return "redirect:/staffs";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("staff", staffDTO);
            return "pages/staff/update";
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteStaff(id);
            redirectAttributes.addFlashAttribute("success", "Xóa nhân viên thành công.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/staffs";

    }
}
