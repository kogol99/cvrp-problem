package solution.crossover;

import model.CombinationCities;
import model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderedCrossover implements Crossover{

    Random random = new Random();

    @Override
    public Path Crossover(Path path1, Path path2) {
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        Path path1Clone = path1.getClone();
        Path path2Clone = path2.getClone();
        List<CombinationCities> path1CombinationCitiestList = path1Clone.getCombinationCitiesList();
        List<CombinationCities> path2CombinationCitiestList = path2Clone.getCombinationCitiesList();
        int firstPosition = random.nextInt(path1.getCombinationCitiesList().size() - 3);
        int secondPostion = firstPosition;
        while(firstPosition == secondPostion || firstPosition > secondPostion){
            secondPostion = random.nextInt(path1.getCombinationCitiesList().size() - 2);
        }

        List<Integer> unchangedCities = new ArrayList<>();
        for(int i=firstPosition; i<secondPostion + 1; i++){
            unchangedCities.add(path1CombinationCitiestList.get(i).getDestinationCity().getNumber());
        }

        int iterator = 0;
        int secondPathIterator = 0;
        while (iterator < firstPosition){
            while(unchangedCities.contains(path2CombinationCitiestList.get(secondPathIterator).getDestinationCity().getNumber())) {
                secondPathIterator++;
            }
            newCombinationCitiesList.add(new CombinationCities(null, path2CombinationCitiestList.get(secondPathIterator).getDestinationCity()));
            secondPathIterator++;
            iterator++;
        }
        while (iterator < secondPostion + 1){
            newCombinationCitiesList.add(new CombinationCities(null, path1CombinationCitiestList.get(iterator).getDestinationCity()));
            iterator++;
        }
        while (iterator < path1CombinationCitiestList.size()){
            while(unchangedCities.contains(path2CombinationCitiestList.get(secondPathIterator).getDestinationCity().getNumber())) {
                secondPathIterator++;
            }
            newCombinationCitiesList.add(new CombinationCities(null, path2CombinationCitiestList.get(secondPathIterator).getDestinationCity()));
            secondPathIterator++;
            iterator++;
        }

        newCombinationCitiesList.get(0).setOriginCity(path1CombinationCitiestList.get(0).getOriginCity());
        for(int i=1; i<newCombinationCitiesList.size(); i++){
            newCombinationCitiesList.get(i).setOriginCity(newCombinationCitiesList.get(i - 1).getDestinationCity());
        }
        return new Path(newCombinationCitiesList);
    }
}
