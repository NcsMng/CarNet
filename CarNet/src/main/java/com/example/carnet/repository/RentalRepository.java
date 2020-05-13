package com.example.carnet.repository;

import com.example.carnet.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("rentalRepository")
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query(value = "SELECT * " +
            "FROM rentals" +
            "INNER JOIN users ON rentals.user_id = users.user_id" +
            "WHERE email =:email " +
            "AND rental_end>:currentDate " +
            "AND rental_start<=:currentDate", nativeQuery = true)
    List<Rental> getActiveRentals(String email, Date currentDate);
}
