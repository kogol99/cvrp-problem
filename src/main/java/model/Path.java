package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return getCombinationCitiesList().equals(path.getCombinationCitiesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCombinationCitiesList());
    }
}
