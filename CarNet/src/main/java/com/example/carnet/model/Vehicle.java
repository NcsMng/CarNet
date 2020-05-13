package com.example.carnet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    private int vehicle_id;

    private float boot_capacity;

    private String brand;

    private String color;

    private String license_plate;

    private String model;

    @Column(name = "no_seats")
    private int noSeats;

    // bi-directional many-to-one association to Rental

    @OneToMany(mappedBy = "vehicle")
    private List<Rental> rentals;

    // bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public Vehicle() {
    }

    public int getVehicleId() {
        return this.vehicle_id;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicle_id = vehicleId;
    }

    public float getBootCapacity() {
        return this.boot_capacity;
    }

    public void setBootCapacity(float boot_capacity) {
        this.boot_capacity = boot_capacity;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return this.license_plate;
    }

    public void setLicensePlate(String licensePlate) {
        this.license_plate = licensePlate;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNoSeats() {
        return this.noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public List<Rental> getRentals() {
        return this.rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public Rental addRental(Rental rental) {
        getRentals().add(rental);
        rental.setVehicle(this);

        return rental;
    }

    public Rental removeRental(Rental rental) {
        getRentals().remove(rental);
        rental.setVehicle(null);

        return rental;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

