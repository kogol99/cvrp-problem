package model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static file.DataPrefix.*;
import static file.DataPrefix.CAPACITY;
import static model.CityType.DELIVERY_CITY;

public class CvrpData {

    private final String name;
    private final String comment;
    private final String type;
    private final String edgeWeightType;
    private final int citiesAmount;
    private final int capacity;
    private final List<City> deliveryCities;
    private final City depotCity;

    public CvrpData(String name, String comment, String type, int citiesAmount, String edgeWeightType, int capacity,
                    List<City> deliveryCities, City depotCity){
        this.name = name;
        this.comment = comment;
        this.type = type;
        this.citiesAmount = citiesAmount;
        this.edgeWeightType = edgeWeightType;
        this.capacity = capacity;
        this.deliveryCities = deliveryCities;
        this.depotCity = depotCity;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }

    public String getEdgeWeightType() {
        return edgeWeightType;
    }

    public int getCitiesAmount() {
        return citiesAmount;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<City> getDeliveryCities() {
        return deliveryCities;
    }

    public City getDepotCity() {
        return depotCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CvrpData cvrpData)) return false;
        return getCitiesAmount() == cvrpData.getCitiesAmount() && getCapacity() == cvrpData.getCapacity() && getName().equals(cvrpData.getName()) && getComment().equals(cvrpData.getComment()) && getType().equals(cvrpData.getType()) && getEdgeWeightType().equals(cvrpData.getEdgeWeightType()) && getDeliveryCities().equals(cvrpData.getDeliveryCities()) && getDepotCity().equals(cvrpData.getDepotCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getComment(), getType(), getEdgeWeightType(), getCitiesAmount(), getCapacity(), getDeliveryCities(), getDepotCity());
    }

    @Override
    public String toString() {
        return "CvrpData{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", type='" + type + '\'' +
                ", edgeWeightType='" + edgeWeightType + '\'' +
                ", citiesAmount=" + citiesAmount +
                ", capacity=" + capacity +
                ", deliveryCities=" + deliveryCities +
                ", depotCity=" + depotCity +
                '}';
    }
}
