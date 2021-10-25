package solution;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static solution.SolutionEvaluator.evaluate;

public class Solution {
    private Path path;
    private CvrpData cvrpData;

    public Solution(Path path, CvrpData cvrpData){
        this.path = path;
        this.cvrpData = cvrpData;
    }

    public void printCostResult(){
        double solutionEvaluationResult = evaluate(path);
        System.out.println("Evaluation result: " + solutionEvaluationResult);
    }

    public void printPathSolution(){
        Path repairedPath = repairPath();
        System.out.println("Path: ");
        for(CombinationCities combinationCities : repairedPath.getCombinationCitiesList()){
            System.out.println(combinationCities.getOriginCity() + " -> " + combinationCities.getDestinationCity().toString());
        }
    }

    public Path repairPath(){
        Path newPath = this.path.getClone();
        List<CombinationCities> combinationCitiesList = newPath.getCombinationCitiesList();
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        Truck truck = new Truck(cvrpData.getCapacity());
        City depotCity = cvrpData.getDepotCity();

        for (CombinationCities actualCombinationCities : combinationCitiesList) {
            int toUnloadValue = actualCombinationCities.getDestinationCity().getDemand();
            if (toUnloadValue <= truck.getAvailableCapacity()) {
                newCombinationCitiesList.add(actualCombinationCities);
            } else {
                CombinationCities depotToCityCombinationCities = new CombinationCities(depotCity, actualCombinationCities.getDestinationCity());
                actualCombinationCities.setDestinationCity(depotCity);
                newCombinationCitiesList.add(actualCombinationCities);
                newCombinationCitiesList.add(depotToCityCombinationCities);
                truck.load();
            }
            truck.unLoad(toUnloadValue);
        }
        return new Path(newCombinationCitiesList);
    }

    public Path deleteDepot(Path path) {
        Path newPath = this.path.getClone();
        List<CombinationCities> combinationCitiesList = newPath.getCombinationCitiesList();
        List<CombinationCities> newCombinationCitiesList = new ArrayList<>();
        Truck truck = new Truck(cvrpData.getCapacity());
        City depotCity = cvrpData.getDepotCity();

        boolean isFirstCity = true;
        for (CombinationCities actualCombinationCities : combinationCitiesList) {
            if (actualCombinationCities.getOriginCity().getNumber() == depotCity.getNumber() && !isFirstCity) {
                newCombinationCitiesList.get(newCombinationCitiesList.size() - 1).setDestinationCity(actualCombinationCities.getDestinationCity());
            } else {
                newCombinationCitiesList.add(actualCombinationCities);
            }
            isFirstCity = false;
        }
        return new Path(newCombinationCitiesList);
    }
}
