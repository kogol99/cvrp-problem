package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.*;
import path.GreedyPathAlgorithm;
import path.RandomPathAlgorithm;
import solution.Config;
import solution.GenethicAlgorithm;
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
/*
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
*/ /*
        GenethicAlgorithm gA = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.TOURNAMENT_SELECTION, CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData);
        Path gAPath = gA.run();
        Solution solution2 = new Solution(gAPath, cvrpData);
        solution2.printPathSolution();
        solution2.printCostResult();
*/

        test1();
    }

    private static void test1(){
        FileRepository repository = new CvrpFileRepository();
        CvrpData cvrpData1 = repository.getCvrpData("src/resources/A-n32-k5.vrp");
        GenethicAlgorithm gA1 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.TOURNAMENT_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.INVERSION_MUTATION, cvrpData1);
        for(int i=0; i<1; i++){
            gA1.run();
        }

        /*
        CvrpData cvrpData2 = repository.getCvrpData("src/resources/A-n37-k6.vrp");
        GenethicAlgorithm gA2 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData2);
        gA2.run();
        CvrpData cvrpData3 = repository.getCvrpData("src/resources/A-n39-k5.vrp");
        GenethicAlgorithm gA3 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData3);
        gA3.run();
        CvrpData cvrpData4 = repository.getCvrpData("src/resources/A-n45-k6.vrp");
        GenethicAlgorithm gA4 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData4);
        gA4.run();
        CvrpData cvrpData5 = repository.getCvrpData("src/resources/A-n48-k7.vrp");
        GenethicAlgorithm gA5 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData5);
        gA5.run();
        CvrpData cvrpData6 = repository.getCvrpData("src/resources/A-n54-k7.vrp");
        GenethicAlgorithm gA6 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData6);
        gA6.run();
        CvrpData cvrpData7 = repository.getCvrpData("src/resources/A-n60-k9.vrp");
        GenethicAlgorithm gA7 = new GenethicAlgorithm(InitialiseType.RANDOM_INITIALISE, SelectionType.ROULETTE_SELECTION,
                CrossoverType.CYCLE_CROSSOVER, MutationType.SWAP_MUTATION, cvrpData7);
        gA7.run();
        */


        Config config = new Config();
        List<List<List<Double>>> list = new ArrayList<>();
        list.add(gA1.getEndedResultList());
        List<List<Double>> avgResultList = new ArrayList<>();
        for(int k=0; k<config.getgAIterationValue(); k++){
            double resultSum = 0;
            double resultMin = Double.MAX_VALUE;
            double resultMax = Double.MIN_VALUE;
            for(int j=0; j<config.getPopulationSize(); j++){
                for(int i=0; i<1; i++){
                    double actual = list.get(i).get(k).get(j);
                    resultSum += actual;
                    if(resultMin > actual) resultMin = actual;
                    if(resultMax < actual) resultMax = actual;
                }
            }
            ArrayList<Double> avgResult = new ArrayList<>();
            avgResult.add(resultMin);
            avgResult.add(resultSum/ config.getPopulationSize());
            avgResult.add(resultMax);
            avgResultList.add(avgResult);
        }
        System.out.println(avgResultList);
    }
}
