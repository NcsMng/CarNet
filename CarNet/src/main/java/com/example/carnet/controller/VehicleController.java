package com.example.carnet.controller;

import com.example.carnet.model.Category;
import com.example.carnet.model.Vehicle;
import com.example.carnet.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/carnet/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicle:read')")
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/category")
    @PreAuthorize("hasAuthority('vehicle:read')")
    public List<Vehicle> getVehicleByCategory(@RequestBody Category category) {
        return vehicleService.getVehicleByCategory(category);
    }

    @GetMapping(path = "{licenseplate}")
    @PreAuthorize("hasAuthority('vehicle:read')")
    public Vehicle getVehicleByLicensePlate(@PathVariable("licenseplate") String licenseplate) {
        return vehicleService.getVehicleByLicensePlate(licenseplate);
    }

    @GetMapping("/available")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public List<Vehicle> getAvailableVehicles(@RequestParam("categoryname") String category_name, @RequestParam("enddate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate end_date, @RequestParam("startdate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate start_date) {
        return vehicleService.getAvailableVehicles(category_name, Date.valueOf(end_date), Date.valueOf(start_date));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('vehicle:write')")
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
    }
}
