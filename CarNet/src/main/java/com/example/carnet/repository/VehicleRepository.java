package com.example.carnet.repository;

import com.example.carnet.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query(value = "SELECT * " +
            "FROM vehicles " +
            "INNER JOIN categories on vehicles.category_id = categories.category_id " +
            "WHERE category_name =:category_name " +
            "AND license_plate Not In " +
            "(SELECT vehicles.license_plate " +
            " FROM vehicles " +
            " INNER JOIN rentals ON vehicles.vehicle_id = rentals.vehicle_id " +
            " WHERE rentals.rental_start<=:end_date  " +
            " AND rentals.rental_end>=:start_date) " +
            "ORDER BY vehicles.category_id", nativeQuery = true)
    List<Vehicle> getAvailableVehicles(String category_name, Date end_date, Date start_date);
}
