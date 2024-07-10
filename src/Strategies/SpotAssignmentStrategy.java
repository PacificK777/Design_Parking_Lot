package Strategies;

import Models.Gate;
import Models.ParkingSpot;
import Models.VehicleType;

public interface SpotAssignmentStrategy {

    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
