package vn.aptech.java.controllers.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.java.dtos.CreateVehicleDTO;
import vn.aptech.java.dtos.UpdateVehicleDTO;
import vn.aptech.java.services.VehicleService;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/vehicles")
public class VehicleMvcController {
    @Autowired
    private VehicleService vehicleService;
    @GetMapping
    public String getAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "pages/vehicle/index";
    }
    @GetMapping("/{id}")
    public String getVehicleById(@PathVariable Long id, Model model) {
        var vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return "redirect:/vehicles";
        }
        model.addAttribute("vehicle", vehicle);
        return "pages/vehicle/update";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("vehicle", new CreateVehicleDTO());
        return "pages/vehicle/create";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVehicle(@Valid @ModelAttribute CreateVehicleDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        vehicleService.createVehicle(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public String updateVehicle(
            @Valid @ModelAttribute("vehicle") UpdateVehicleDTO vehicleDTO,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "pages/vehicle/update";
        }

        vehicleService.updateVehicle(vehicleDTO);
        return "redirect:/vehicles";
    }
    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(id);
        redirectAttributes.addFlashAttribute("success", "Xóa xe thành công.");
        return "redirect:/vehicles";
    }

}