package solution.selection;

import model.CombinationCities;
import model.Path;
import solution.Config;
import solution.SolutionEvaluator;

import java.util.*;

import static solution.SolutionEvaluator.evaluate;

public class RouletteSelection implements Selection{

    Random random = new Random();
    Config config = new Config();

    @Override
    public Path selection(List<Path> population) {
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

        selectedPath.sort(Comparator.comparing(RouletteSelection::calculateDistance));
        return selectedPath.get(0);
    }

    private static double calculateDistance(Path path){
        return evaluate(path);
    }
}
