package com.example.carnet.controller;

import com.example.carnet.model.Rental;
import com.example.carnet.model.User;
import com.example.carnet.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/carnet/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('rental:read')")
    public List<Rental> getRental() {
        return rentalService.getRental();
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('rental:read')")
    public void getUserRentals(@RequestBody User user) {
        rentalService.getUserRentals(user);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('rental:read')")
    public int countUserRentals(@RequestBody User user) {
        return rentalService.countUserRentals(user);
    }

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('rental:read')")
    public List<Rental> getActiveRentals(@RequestParam("email") String email){
        return rentalService.getActiveRentals(email, Date.valueOf(LocalDate.now()));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('rental:write')")
    public void addRental(@RequestBody Rental rental) {
        rentalService.addRental(rental);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('rental:write')")
    public void deleteRental(@PathVariable("id") int id) {
        rentalService.deleteRental(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('rental:write')")
    public void updateRental(@RequestBody Rental rental) {rentalService.updateRental(rental);}
}
