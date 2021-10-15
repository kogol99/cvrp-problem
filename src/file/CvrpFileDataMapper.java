package file;

import model.City;
import model.CvrpData;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static file.DataPrefix.*;
import static model.CityType.*;

public class CvrpFileDataMapper {

    private static final int DEFAULT_DEPOT_CITY_NO = 1;
    private final List<String> fileData;

    CvrpFileDataMapper(List<String> fileData){
        this.fileData = fileData;
    }

    public CvrpData mapCvrpFileData(){
        String name = extractValue(NAME);
        String comment = extractValue(COMMENT);
        String type = extractValue(TYPE);
        int citiesAmount = Integer.parseInt(extractValue(DIMENSION));
        String edgeWeightType = extractValue(EDGE_WEIGHT_TYPE);
        int capacity = Integer.parseInt(extractValue(CAPACITY));

        Collection<City> cities = getCities(citiesAmount);
        City depotCity = getDepotCity(cities);
        cities = cities.stream()
                    .filter(city -> city.getCityType() == DELIVERY_CITY)
                    .collect(Collectors.toList());
        return new CvrpData(name, comment, type, citiesAmount, edgeWeightType, capacity, (List<City>) cities, depotCity);
    }

    private City getDepotCity(Collection<City> cities) {
        Optional<City> searchedCity = cities.stream()
                                        .filter(city -> city.getNumber() == DEFAULT_DEPOT_CITY_NO)
                                        .findFirst();
        City depotCity = searchedCity.get();
        depotCity.setCityDepot();
        return depotCity;
    }

    private Collection<City> getCities(int citiesAmount) {
        Collection<City> cities = new HashSet<>();
        for(int i=0; i<citiesAmount; i++){
            cities.add(getCityWithDetails(i + 1));
        }
        return cities;
    }

    private City getCityWithDetails(int cityNo) {
        String[] coords = extractCityDetail(cityNo, NODE_COORD_SECTION).split(" ");
        String[] demands = extractCityDetail(cityNo, DEMAND_SECTION).split(" ");

        int xCoord = Integer.parseInt(coords[2]);
        int yCoord = Integer.parseInt(coords[3]);
        int demand = Integer.parseInt(demands[1]);

        return new City(cityNo, xCoord, yCoord, demand);
    }

    private String extractCityDetail(int cityNo, DataPrefix dataPrefix) {
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).contains(dataPrefix.name())) {
                return fileData.get(i + cityNo);
            }
        }
        //throw new Exception("No data available on " + dataPrefix.toString());
        return null;
    }

    private String extractValue(DataPrefix dataPrefix) {
        Optional<String> value = fileData.stream()
                .filter(line -> line.contains(dataPrefix.name()))
                .map(this::extractSingleLineValue)
                .findFirst();

        if (value.isEmpty()) {
            //throw new Exception("There is no occurrence in the file: " + dataPrefix.toString());
        }
        return value.get();
    }

    private String extractSingleLineValue(String line) {
        String[] fieldNameWithValue = line.split(" : ");
        if (fieldNameWithValue.length != 2) {
            //throw new Exception("The data on the " + line + " line could not be processed.");
        }
        return fieldNameWithValue[1];
    }
}
