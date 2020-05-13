package com.example.carnet.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private int category_id;

    private String category_name;

    private float daily_fee;

    private String description;

    private float monthly_fee;

    @Column(name = "weekly_fee")
    private float weeklyFee;

    // bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy = "category")
    private List<Vehicle> vehicles;

    public Category() {
    }

    public int getCategoryId() {
        return this.category_id;
    }

    public void setCategoryId(int categoryId) {
        this.category_id = categoryId;
    }

    public String getCategoryName() {
        return this.category_name;
    }

    public void setCategoryName(String categoryName) {
        this.category_name = categoryName;
    }

    public float getDailyFee() {
        return this.daily_fee;
    }

    public void setDailyFee(float dailyFee) {
        this.daily_fee = dailyFee;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMonthlyFee() {
        return this.monthly_fee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthly_fee = monthlyFee;
    }

    public float getWeeklyFee() {
        return this.weeklyFee;
    }

    public void setWeeklyFee(float weeklyFee) {
        this.weeklyFee = weeklyFee;
    }

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        getVehicles().add(vehicle);
        vehicle.setCategory(this);

        return vehicle;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        getVehicles().remove(vehicle);
        vehicle.setCategory(null);

        return vehicle;
    }

}