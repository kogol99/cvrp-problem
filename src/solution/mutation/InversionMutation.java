package solution.mutation;

import model.City;
import model.CombinationCities;
import model.Path;
import solution.Config;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InversionMutation implements Mutation{

    Random random = new Random();
    private Config config = new Config();

    @Override
    public Path mutation(Path path) {
        Path newPath = path.getClone();
        if(random.nextDouble() < config.getPm()) {
            List<CombinationCities> oldCombinationCitiesList = newPath.getCombinationCitiesList();
            int firstPosition = random.nextInt(oldCombinationCitiesList.size() - 3);
            int secondPostion = firstPosition;
            while (firstPosition == secondPostion || firstPosition > secondPostion) {
                secondPostion = random.nextInt(oldCombinationCitiesList.size() - 2);
            }
            ArrayList<Integer> cityNoList;
            cityNoList = convertPathToCityNoList(newPath);
            return createPathAfterReverse(reverseCityBetween(firstPosition, secondPostion, cityNoList), oldCombinationCitiesList);
        } else {
            return newPath;
        }
    }

    private ArrayList<Integer> convertPathToCityNoList(Path path){
        ArrayList<Integer> cityNoList = new ArrayList<>();
        for(CombinationCities combinationCities: path.getCombinationCitiesList()){
            cityNoList.add(combinationCities.getDestinationCity().getNumber());
        }
        return cityNoList;
    }

    private ArrayList<Integer> reverseCityBetween(int fromId, int toId, ArrayList<Integer> cityNoList){
        List<Integer> beforeList = cityNoList.subList(0, fromId);
        List<Integer> toReverseList = cityNoList.subList(fromId, toId + 1);
        List<Integer> afterList = cityNoList.subList(toId + 1, cityNoList.size());
        Collections.reverse(toReverseList);

        ArrayList<Integer> result = new ArrayList<>();
        result = (ArrayList<Integer>) Stream.concat(beforeList.stream(), toReverseList.stream())
                .collect(Collectors.toList());
        result = (ArrayList<Integer>) Stream.concat(result.stream(), afterList.stream())
                .collect(Collectors.toList());
        return result;
    }

    private Path createPathAfterReverse(ArrayList<Integer> cityList, List<CombinationCities> combinationCitiesList){
        HashMap<Integer, City> cityHashMap = new HashMap<>();
        for(CombinationCities combinationCities: combinationCitiesList){
            cityHashMap.put(combinationCities.getDestinationCity().getNumber(), combinationCities.getDestinationCity());
        }
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        for(Integer cityNo: cityList){
            if(newCombinationCitiesList.size() == 0){
                newCombinationCitiesList.add(new CombinationCities(cityHashMap.get(1), cityHashMap.get(cityNo)));
            } else {
                newCombinationCitiesList.add(new CombinationCities(
                        cityHashMap
                                .get(newCombinationCitiesList
                                        .get(newCombinationCitiesList.size() - 1)
                                        .getDestinationCity().getNumber()),
                        cityHashMap.get(cityNo)));
            }
        }
        return new Path(newCombinationCitiesList);
    }

}
