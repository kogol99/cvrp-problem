package solution.crossover;

import model.CombinationCities;
import model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CycleCrossover implements Crossover{

    Random random = new Random();

    @Override
    public Path Crossover(Path path1, Path path2) {
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        List<CombinationCities> path1CombinationCitiestList = path1.getCombinationCitiesList();
        List<CombinationCities> path2CombinationCitiestList = path2.getCombinationCitiesList();

        int firstPostion = random.nextInt(path1CombinationCitiestList.size() - 1);
        int actualId = firstPostion;
        List<Integer> cycledList = new ArrayList<>();
        cycledList.add(path1CombinationCitiestList.get(firstPostion).getDestinationCity().getNumber());
        List<Integer> listIdFromFirstPath = new ArrayList<>();
        listIdFromFirstPath.add(firstPostion);
        int cityNumberFromSecondPath = path2CombinationCitiestList.get(actualId).getDestinationCity().getNumber();
        while(!cycledList.contains(cityNumberFromSecondPath)){
            int secondIterator = 0;
            while (path1CombinationCitiestList.get(secondIterator).getDestinationCity().getNumber() != cityNumberFromSecondPath){
                secondIterator++;
            }
            actualId = secondIterator;
            cycledList.add(path1CombinationCitiestList.get(actualId).getDestinationCity().getNumber());
            listIdFromFirstPath.add(actualId);
            cityNumberFromSecondPath = path2CombinationCitiestList.get(actualId).getDestinationCity().getNumber();
        }

        for(int i=0; i < path1CombinationCitiestList.size(); i++){
            if(listIdFromFirstPath.contains(i)){
                newCombinationCitiesList.add(new CombinationCities(null, path1CombinationCitiestList.get(i).getDestinationCity()));
            } else {
                newCombinationCitiesList.add(new CombinationCities(null, path2CombinationCitiestList.get(i).getDestinationCity()));
            }
        }

        newCombinationCitiesList.get(0).setOriginCity(path1CombinationCitiestList.get(0).getOriginCity());
        for(int i=1; i<newCombinationCitiesList.size(); i++){
            newCombinationCitiesList.get(i).setOriginCity(newCombinationCitiesList.get(i - 1).getDestinationCity());
        }
        return new Path(newCombinationCitiesList);
    }
}
