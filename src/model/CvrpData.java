package model;

import java.util.Collection;
import java.util.List;
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

}
