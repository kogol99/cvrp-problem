package solution.selection;

import model.CombinationCities;
import model.CvrpData;
import model.Path;
import solution.Config;
import solution.Solution;
import solution.SolutionEvaluator;

import java.util.*;

import static solution.SolutionEvaluator.evaluate;

public class RouletteSelection implements Selection{

    Random random = new Random();
    Config config = new Config();
    CvrpData cvrpData;

    @Override
    public Path selection(List<Path> population, CvrpData cvrpData) {
        this.cvrpData = cvrpData;
        double[] evalList = population.stream()
                                    .mapToDouble(SolutionEvaluator::evaluate)
                                    .toArray();

        double populationSum = Arrays.stream(evalList).sum();

        List<Double> evalList2 = new ArrayList<>();
        for(double value: evalList){
            evalList2.add(populationSum/value);
        }

        double smallPopulationSum = 0;
        for(double value: evalList2){
            smallPopulationSum += value;
        }

        int rouletteSize = config.getRouletteSize();
        int populationSize = population.size();
        int selectedQuantity = 0;
        List<Path> selectedPath = new ArrayList<>();
        if(rouletteSize > populationSize){
            rouletteSize = populationSize;
        }
        int anotherChoice = 0;
        while(selectedQuantity < rouletteSize){
            double randValue = random.nextDouble();

            double sum = 0;
            int iterator = 0;
            while (sum < randValue){
                sum += evalList2.get(iterator)/smallPopulationSum;
                iterator++;
            }
            selectedPath.add(population.get(iterator - 1));
            selectedQuantity++;
        }

        List<Path> selectedPathWithDepot = getRepairedPathsList(selectedPath);
        selectedPathWithDepot.sort(Comparator.comparing(RouletteSelection::calculateDistance));
        return deleteDepotInPath(selectedPathWithDepot.get(0));
    }

    private static double calculateDistance(Path path){
        return evaluate(path);
    }

    private List<Path> getRepairedPathsList(List<Path> paths){
        List<Path> repairedPaths = new ArrayList<>();
        for(Path path: paths){
            Path newPath = path.getClone();
            Solution solution = new Solution(newPath, this.cvrpData);
            repairedPaths.add(solution.repairPath());
        }
        return repairedPaths;
    }

    private List<Path> deleteDepothInPathList(List<Path> pathList){
        List<Path> newPathList = new ArrayList<>();
        for(Path path: pathList){
            newPathList.add(deleteDepotInPath(path));
        }
        return newPathList;
    }

    private Path deleteDepotInPath(Path path){
        Path newPath = path.getClone();
        Solution solution = new Solution(newPath, this.cvrpData);
        return solution.deleteDepot(newPath);
    }

}
