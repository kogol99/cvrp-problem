package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.CombinationCities;
import model.CvrpData;
import model.Path;
import path.GreedyPathAlgorithm;
import path.RandomPathAlgorithm;
import solution.Solution;
import solution.crossover.Crossover;
import solution.crossover.CycleCrossover;
import solution.crossover.OrderedCrossover;
import solution.mutation.InversionMutation;
import solution.mutation.Mutation;
import solution.mutation.SwapMutation;
import solution.selection.RouletteSelection;
import solution.selection.Selection;
import solution.selection.TournamentSelection;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileRepository repository = new CvrpFileRepository();
        CvrpData cvrpData = repository.getCvrpData("src/resources/A-n32-k5.vrp");
        System.out.println(cvrpData);

        RandomPathAlgorithm randomPathAlgorithm = new RandomPathAlgorithm();
        Path randomPath = randomPathAlgorithm.createOptimalPath(cvrpData);

        Solution solution = new Solution(randomPath, cvrpData);
        solution.printPathSolution();
        solution.printCostResult();

        GreedyPathAlgorithm greedyPathAlgorithm = new GreedyPathAlgorithm();
        Path greedyPath = greedyPathAlgorithm.createOptimalPath(cvrpData);

        Solution solution1 = new Solution(greedyPath, cvrpData);
        solution1.printPathSolution();
        solution1.printCostResult();

        Mutation swapMutation = new SwapMutation();
        Path pathAfterSwapMutation = swapMutation.mutation(greedyPath);

        Mutation mutation = new InversionMutation();
        Path pathAfterInveriosnMutation = mutation.mutation(greedyPath);

        Crossover OC = new OrderedCrossover();
        Path oCPath = OC.Crossover(greedyPath, randomPath);

        Crossover CC = new CycleCrossover();
        Path cCPath = CC.Crossover(greedyPath, randomPath);

        Selection tournamentSelection = new TournamentSelection();
        List<Path> pathList = new ArrayList<>();
        pathList.add(greedyPath);
        pathList.add(randomPath);
        tournamentSelection.selection(pathList);

        Selection rouletteSelection = new RouletteSelection();
        rouletteSelection.selection(pathList);

    }
}
