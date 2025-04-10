package vn.aptech.java.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.java.models.Rental;
import vn.aptech.java.services.RentalService;

@Controller
@RequestMapping("/rentals")
public class RentalMvcController {
    @Autowired
    private RentalService rentalService;
    @GetMapping
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "pages/rental/index";
    }
    @GetMapping("/{id}")
    public String getRentalById(@PathVariable Long id, Model model) {
        var rental = rentalService.getRentalById(id);
        if (rental == null) {
            return "redirect:/rentals";
        }
        model.addAttribute("rental", rental);
        return "pages/rental/update";
    }
    @PostMapping("/update-status")
    public String updateRentalStatus(@RequestParam Long id,
                                     @RequestParam Rental.RentalStatus status,
                                     RedirectAttributes redirectAttributes) {
        try {
            rentalService.updateRentalStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái thành công.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi không xác định.");
        }

        return "redirect:/rentals/" + id;
    }




}
