package model;

import java.util.ArrayList;
import java.util.List;

public class Path implements Cloneable{
    private List<CombinationCities> combinationCitiesList;

    public Path(List<CombinationCities> combinationCitiesList){
        this.combinationCitiesList = combinationCitiesList;
    }

    public List<CombinationCities> getCombinationCitiesList() {
        return combinationCitiesList;
    }

    public void setCombinationCitiesList(List<CombinationCities> combinationCitiesList) {
        this.combinationCitiesList = combinationCitiesList;
    }

    @Override
    public String toString() {
        return "Path{" +
                "combinationCitiesList=" + combinationCitiesList +
                '}';
    }

    public Path getClone() {
        try {
            return (Path) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Path cloned = (Path)super.clone();
        List<CombinationCities> clonedList = cloned.getCombinationCitiesList();
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        for(CombinationCities cities : clonedList) {
            newCombinationCitiesList.add(new CombinationCities(cities.getOriginCity(), cities.getDestinationCity()));
        }
        cloned.setCombinationCitiesList(newCombinationCitiesList);
        return cloned;
    }
}
