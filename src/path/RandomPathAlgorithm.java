package path;

import model.City;
import model.CombinationCities;
import model.CvrpData;
import model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPathAlgorithm implements CustomPathAlgorithm {

    Random random = new Random();

    @Override
    public Path createOptimalPath(CvrpData cvrpData) {
        List<City> deliveryCities = cvrpData.getDeliveryCities();
        List<CombinationCities> combinationCitiesList = findRandomPath(deliveryCities);
        addDepotCity(combinationCitiesList, cvrpData.getDepotCity());
        return new Path(combinationCitiesList);
    }

    public List<CombinationCities> findRandomPath(List<City> deliveryCities) {
        List<CombinationCities> combinationCitiesList = new ArrayList<>();
        List<City> cityToAssign = new ArrayList<>(deliveryCities);
        City destinationCity = getRandomCityFromList(cityToAssign);

        while(!cityToAssign.isEmpty()){
            City originCity = destinationCity;
            cityToAssign.remove(originCity);
            destinationCity = getRandomCityFromList(cityToAssign);

            CombinationCities combinationCities = new CombinationCities(originCity, destinationCity);
            combinationCitiesList.add(combinationCities);
        }
        return combinationCitiesList;
    }

    private City getRandomCityFromList(List<City> cityList) {
        if(cityList.size() > 1)
            return cityList.get(random.nextInt(cityList.size() - 1));
        else if(cityList.size() == 1)
            return cityList.get(0);
        else
            return null;
    }

    public void addDepotCity(List<CombinationCities> combinationCitiesList, City depotCity){
        combinationCitiesList.add(0, new CombinationCities(depotCity, combinationCitiesList.get(0).getOriginPlace()));
        combinationCitiesList.get(combinationCitiesList.size() - 1).setDestinationPlace(depotCity);
    }
}
