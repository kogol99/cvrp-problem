package path;

import model.City;
import model.CombinationCities;
import model.CvrpData;
import model.Path;
import solution.SolutionEvaluator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyPathAlgorithm implements CustomPathAlgorithm {
    @Override
    public Path createOptimalPath(CvrpData cvrpData) {
        List<CombinationCities> combinationCitiesList = findGreedyPath(cvrpData);
        addDepotCity(combinationCitiesList, cvrpData.getDepotCity());
        return new Path(combinationCitiesList);
    }

    private List<CombinationCities> findGreedyPath(CvrpData cvrpData){
        List<CombinationCities> combinationCitiesList = new ArrayList<>();
        List<City> cityToAssign = new ArrayList<>(cvrpData.getDeliveryCities());
        City destinationCity = cvrpData.getDepotCity();

        while(!cityToAssign.isEmpty()){
            City originCity = destinationCity;
            destinationCity = findNearestCity(cityToAssign, originCity);
            cityToAssign.remove(destinationCity);

            CombinationCities combinationCities = new CombinationCities(originCity, destinationCity);
            combinationCitiesList.add(combinationCities);
        }
        return combinationCitiesList;
    }

    private City findNearestCity(List<City> deliveryCities, City originCity){
        if(deliveryCities.size() != 0){
            List<CombinationCities> availableConnections = new ArrayList<>();
            for(City city : deliveryCities){
                availableConnections.add(new CombinationCities(originCity, city));
            }
            availableConnections.sort(Comparator.comparing(SolutionEvaluator::calculateDistance));
            return availableConnections.get(0).getDestinationCity();
        } else {
            return null;
        }
    }

    public void addDepotCity(List<CombinationCities> combinationCitiesList, City depotCity){
        combinationCitiesList.get(combinationCitiesList.size() - 1).setDestinationCity(depotCity);
    }

}
