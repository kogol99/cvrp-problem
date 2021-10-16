package solution.mutation;

import model.CombinationCities;
import model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InversionMutation implements Mutation{

    Random random = new Random();

    @Override
    public Path mutation(Path path) {
        Path newPath = path.getClone();
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        List<CombinationCities> oldCombinationCitiesList = newPath.getCombinationCitiesList();
        int firstPosition = random.nextInt(oldCombinationCitiesList.size());
        int secondPostion = -1;
        while(firstPosition == secondPostion || firstPosition > secondPostion){
            secondPostion = random.nextInt(oldCombinationCitiesList.size() - 1);
        }
        System.out.println("First" + firstPosition);
        System.out.println("Second" + secondPostion);
        int iterator = 0;
        while (iterator < firstPosition){
            newCombinationCitiesList.add(oldCombinationCitiesList.get(iterator).getClone());
            iterator++;
        }
        while (iterator < secondPostion){
            CombinationCities oldCombinationCitiesListElement = oldCombinationCitiesList.get(firstPosition + secondPostion - iterator - 1);
            if(iterator == firstPosition){
                newCombinationCitiesList.get(newCombinationCitiesList.size() - 1).setDestinationCity(oldCombinationCitiesListElement.getDestinationCity());
            }
            newCombinationCitiesList.add(
                    new CombinationCities(
                            oldCombinationCitiesListElement.getDestinationCity(),
                            oldCombinationCitiesListElement.getOriginCity()
                    )
            );
            iterator++;
        }
        while (iterator < oldCombinationCitiesList.size()){
            newCombinationCitiesList.add(oldCombinationCitiesList.get(iterator).getClone());
            if(iterator == secondPostion){
                newCombinationCitiesList.get(newCombinationCitiesList.size() - 1).setOriginCity(oldCombinationCitiesList.get(firstPosition + secondPostion - iterator).getOriginCity());
            }
            iterator++;
        }

        return new Path(newCombinationCitiesList);
    }
}
