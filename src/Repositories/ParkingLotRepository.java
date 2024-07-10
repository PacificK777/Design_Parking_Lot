package Repositories;

import Models.ParkingLot;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {

    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
    // Hypothetical map to simulate the relationship between gates and parking lots
    private Map<Long, Long> gateToParkingLotMap = new HashMap<>();

    public ParkingLot getParkingLotByGateID(Long gateID){
        Long parkingLotID = gateToParkingLotMap.get(gateID);
        if(parkingLotID != null){
            return parkingLotMap.get(parkingLotID);
        }
        return null;
    }
}