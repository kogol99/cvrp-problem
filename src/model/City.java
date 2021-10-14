package model;

import java.util.Objects;

import static model.CityType.DELIVERY_CITY;
import static model.CityType.DEPOT_CITY;

public class City {

    private final int number;
    private final int xCoord;
    private final int yCoord;
    private final int demand;
    private CityType cityType;

    public City(int number,
                int xCoordinate,
                int yCoordinate,
                int demand) {
        this.number = number;
        this.xCoord = xCoordinate;
        this.yCoord = yCoordinate;
        this.demand = demand;
        cityType = DELIVERY_CITY;
    }

    public void setCityDepot(){
        this.setCityType(DEPOT_CITY);
    }

    public int getNumber() {
        return number;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getDemand() {
        return demand;
    }

    public CityType getCityType() {
        return cityType;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getNumber() == city.getNumber() && getxCoord() == city.getxCoord() && getyCoord() == city.getyCoord() && getDemand() == city.getDemand() && getCityType() == city.getCityType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getxCoord(), getyCoord(), getDemand(), getCityType());
    }

    @Override
    public String toString() {
        return "City{" +
                "number=" + number +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", demand=" + demand +
                ", cityType=" + cityType +
                '}';
    }
}
