package solution.mutation;

import model.City;
import model.CombinationCities;
import model.Path;

import java.util.Random;

public class SwapMutation implements Mutation{

    Random random = new Random();

    @Override
    public Path mutation(Path path) {
        Path newPath = path.getClone();
        int firstPosition = random.nextInt(newPath.getCombinationCitiesList().size());
        int secondPostion = firstPosition;
        while(firstPosition == secondPostion){
            secondPostion = random.nextInt(newPath.getCombinationCitiesList().size() - 1);
        }
        CombinationCities latestFirstCombination = newPath.getCombinationCitiesList().get(firstPosition);
        CombinationCities latestSecondCombination = newPath.getCombinationCitiesList().get(secondPostion);
        City newFirstPosition = latestSecondCombination.getDestinationCity();
        City newSecondPosition = latestFirstCombination.getDestinationCity();
        latestFirstCombination.setDestinationCity(newFirstPosition);
        newPath.getCombinationCitiesList().get(firstPosition + 1).setOriginCity(newFirstPosition);
        latestSecondCombination.setDestinationCity(newSecondPosition);
        newPath.getCombinationCitiesList().get(secondPostion + 1).setOriginCity(newSecondPosition);

        return newPath;
    }
}
