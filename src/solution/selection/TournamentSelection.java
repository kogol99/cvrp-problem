package solution.selection;

import model.Path;
import solution.Config;
import static solution.SolutionEvaluator.evaluate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TournamentSelection implements Selection{

    Random random = new Random();

    @Override
    public Path selection(List<Path> population) {
        Config config = new Config();
        int tournamentSize = config.getTournamentSize();
        int populationSize = population.size();
        int selectedQuantity = 0;
        List<Path> selectedPath = new ArrayList<>();
        if(tournamentSize > populationSize){
            tournamentSize = populationSize;
        }

        while(selectedQuantity < tournamentSize){
            int randId = random.nextInt(populationSize - 1);
            if(!selectedPath.contains(population.get(randId))){
                selectedQuantity++;
                selectedPath.add(population.get(randId));
            }
        }

        selectedPath.sort(Comparator.comparing(TournamentSelection::calculateDistance));
        return selectedPath.get(0);
    }

    private static double calculateDistance(Path path){
        return evaluate(path);
    }
}