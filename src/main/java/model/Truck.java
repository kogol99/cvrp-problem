package model;

public class Truck {

    private final int maxCapacity;
    private int availableCapacity;

    public Truck(int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.availableCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void load(){
        this.availableCapacity = getMaxCapacity();
    }

    public void unLoad(int toUnload){
        this.availableCapacity -= toUnload;
    }

}
