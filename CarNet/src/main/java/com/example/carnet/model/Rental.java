package com.example.carnet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Table(name = "rentals")
@Entity
public class Rental {

    @Id
    private int rental_id;

    private Date rental_start;

    private Date rental_end;

    private float owed_amount;

    // bi-directional many-to-one association to User

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // bi-directional many-to-one association to Vehicle
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    public Rental() {
    }

    /*public Rental(String license_plate, Date rental_start, Date rental_end, Category category, String email) {

        LocalDate start = rental_start.toLocalDate();
        LocalDate end = rental_end.toLocalDate();
        Period period = Period.between(start, end);

        int months = period.getMonths();
        int weeks = period.getDays() / 7;
        int days = period.getDays() - (weeks * 7);

        float contributoGiorni = category.getDailyFee() * days;
        float contributoSettimane = category.getWeeklyFee() * weeks;
        float contributoMesi = category.getMonthlyFee() * months;
        float owed_amount = contributoGiorni + contributoSettimane + contributoMesi;



        this.rental_start = rental_start;
        this.rental_end = rental_end;
        this.owed_amount = owed_amount;
        this.user = user;
        this.vehicle = veicolo;
    }*/

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Date getRental_start() {
        return rental_start;
    }

    public void setRental_start(Date rental_start) {
        this.rental_start = rental_start;
    }

    public Date getRental_end() {
        return rental_end;
    }

    public void setRental_end(Date rental_end) {
        this.rental_end = rental_end;
    }

    public float getOwed_amount() {
        return owed_amount;
    }

    public void setOwed_amount(float owed_amount) {
        this.owed_amount = owed_amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
