package model;

import java.util.List;

public class Path {
    private final List<CombinationCities> combinationCitiesList;

    public Path(List<CombinationCities> combinationCitiesList){
        this.combinationCitiesList = combinationCitiesList;
    }

    public List<CombinationCities> getCombinationCitiesList() {
        return combinationCitiesList;
    }
}
