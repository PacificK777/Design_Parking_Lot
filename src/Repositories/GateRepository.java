package Repositories;

import Models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {

    private Map<Long, Gate> gateMap = new HashMap<>();

    public Optional<Gate> findGateById(Long gateID){
        if(gateMap.containsKey(gateID)){
            return Optional.of(gateMap.get(gateID));
        }
        return Optional.empty();
    }
}
