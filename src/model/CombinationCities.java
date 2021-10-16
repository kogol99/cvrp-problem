package model;

import java.util.Objects;

public class CombinationCities{
    private City originCity;
    private City destinationCity;

    public CombinationCities(City originCity, City destinationCity){
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

    public boolean containsCity(City city){
        return originCity.equals(city) || destinationCity.equals(city);
    }

    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationPlace) {
        this.destinationCity = destinationPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombinationCities that)) return false;
        return getOriginCity().equals(that.getOriginCity()) && getDestinationCity().equals(that.getDestinationCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginCity(), getDestinationCity());
    }

    @Override
    public String toString() {
        return "CombinationCities{" +
                "originPlace=" + originCity +
                ", destinationPlace=" + destinationCity +
                '}';
    }
}
