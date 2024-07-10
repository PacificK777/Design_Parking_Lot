package Factory;

import Models.SpotAssignmentStrategyType;
import Strategies.CheapestSpotAssignmentStrategy;
import Strategies.NearestSpotAssignmentStrategy;
import Strategies.RandomSpotAssignmentStrategy;
import Strategies.SpotAssignmentStrategy;

public class SpotAssignmentStrategyFactory {

    public static SpotAssignmentStrategy getSpotAssignmentStrategyForType(
            SpotAssignmentStrategyType spotAssignmentStrategyType){

        if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)){
            return new NearestSpotAssignmentStrategy();
        }
        else if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)){
            return new CheapestSpotAssignmentStrategy();
        }
        return new RandomSpotAssignmentStrategy();
    }
}
