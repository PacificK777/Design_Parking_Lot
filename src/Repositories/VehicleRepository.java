package Repositories;

import Models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<String, Vehicle> vehicles = new HashMap<>();

    public Optional<Vehicle> findVehicleByVehicleNumber(String vehicleNumber){
        return Optional.ofNullable(vehicles.get(vehicleNumber));
    }

    public Vehicle save(Vehicle vehicle){
        vehicles.put(vehicle.getVehicleNumber(), vehicle);
        return vehicle;
    }
}