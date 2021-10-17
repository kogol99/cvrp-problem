package solution.selection;

import model.Path;
import solution.SolutionEvaluator;

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

        double randValue = random.nextDouble();

        int iterator = 0;
        while (populationSum < randValue){
            populationSum += evalList[iterator]/populationSum;
            iterator++;
        }
        return population.get(iterator);
    }
}
