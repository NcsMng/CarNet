package com.example.carnet.services;

import com.example.carnet.model.Rental;
import com.example.carnet.model.User;
import com.example.carnet.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(@Qualifier("rentalRepository") RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getRental() {
        return rentalRepository.findAll();
    }

    public void addRental(Rental rental) {

        LocalDate start = rental.getRental_start().toLocalDate();
        LocalDate end = rental.getRental_end().toLocalDate();
        Period period = Period.between(start, end);

        int months = period.getMonths();
        int weeks = period.getDays() / 7;
        int days = period.getDays() - (weeks * 7);

        float feeFromDays = rental.getVehicle().getCategory().getDailyFee() * days;
        float feeFromWeeks = rental.getVehicle().getCategory().getWeeklyFee() * weeks;
        float feeFromMonths = rental.getVehicle().getCategory().getMonthlyFee() * months;
        float owed_amount = feeFromDays + feeFromWeeks + feeFromMonths;

        rental.setOwed_amount(owed_amount);
        rentalRepository.save(rental);
    }

    public List<Rental> getUserRentals(User user) {
        Rental r = new Rental();
        r.setUser(user);
        Example<Rental> userExample = Example.of(r);
        return rentalRepository.findAll(userExample);
    }

    public int countUserRentals(User user) {
        return rentalRepository.findAll().size();
    }

    public void deleteRental(int id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> getActiveRentals(String email, Date currentDate) {
        return rentalRepository.getActiveRentals(email, currentDate);
    }
    public void updateRental(Rental rental) { rentalRepository.save(rental);}
}
