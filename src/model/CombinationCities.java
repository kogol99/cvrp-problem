package model;

import java.util.Objects;

public class CombinationCities {
    private final City originCity;
    private City destinationCity;

    public CombinationCities(City originCity, City destinationCity){
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

    public boolean containsCity(City city){
        return originCity.equals(city) || destinationCity.equals(city);
    }

    public City getOriginPlace() {
        return originCity;
    }

    public City getDestinationPlace() {
        return destinationCity;
    }

    public void setDestinationPlace(City destinationPlace) {
        this.destinationCity = destinationPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombinationCities that)) return false;
        return getOriginPlace().equals(that.getOriginPlace()) && getDestinationPlace().equals(that.getDestinationPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginPlace(), getDestinationPlace());
    }

    @Override
    public String toString() {
        return "CombinationCities{" +
                "originPlace=" + originCity +
                ", destinationPlace=" + destinationCity +
                '}';
    }
}
