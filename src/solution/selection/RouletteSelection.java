package solution.selection;

import model.Path;
import solution.SolutionEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RouletteSelection implements Selection{

    Random random = new Random();

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


        double randValue = random.nextDouble();

        double sum = 0;
        int iterator = 0;
        while (sum < randValue){
            sum += evalList2.get(iterator)/smallPopulationSum;
            iterator++;
        }
        return population.get(iterator - 1);
    }
}
