package vn.aptech.java.controllers.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.aptech.java.dtos.CreateVehicleDTO;
import vn.aptech.java.dtos.UpdateVehicleDTO;
import vn.aptech.java.services.VehicleService;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/vehicles")
public class VehicleController {
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
    public String createVehicle(@ModelAttribute("vehicle") @Valid CreateVehicleDTO dto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pages/vehicle/create";
        }
        vehicleService.createVehicle(dto);
        return "redirect:/vehicles";
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
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

}
