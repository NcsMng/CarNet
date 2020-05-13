package com.example.carnet.services;

import com.example.carnet.model.Category;
import com.example.carnet.model.Vehicle;
import com.example.carnet.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(@Qualifier("vehicleRepository") VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehicleByCategory(Category category) {
        Vehicle v = new Vehicle();
        v.setCategory(category);
        Example<Vehicle> vehicleExample = Example.of(v);
        return vehicleRepository.findAll(vehicleExample);
    }

    public Vehicle getVehicleByLicensePlate(String license_plate) {
        Vehicle v = new Vehicle();
        v.setLicensePlate(license_plate);
        Example<Vehicle> vehicleExample = Example.of(v);
        Optional<Vehicle> maybeVehicle = vehicleRepository.findOne(vehicleExample);
        return maybeVehicle.orElse(null);
    }

    public List<Vehicle> getAvailableVehicles(String category_name, Date end_date, Date start_date) {
        return vehicleRepository.getAvailableVehicles(category_name, end_date, start_date);
    }

}
