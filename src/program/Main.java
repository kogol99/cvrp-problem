package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.*;
import path.CustomPathAlgorithm;
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
    private static final int TEST_REPEAT_AMOUNT = 10;

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

        CustomPathAlgorithm randomPathInitialiseAlgorithm = new RandomPathAlgorithm();
        CustomPathAlgorithm greedyPathInitialiseAlgorithm = new GreedyPathAlgorithm();
        Selection tournamentSelectionAlgorithm = new TournamentSelection();
        Selection rouletteSelectionAlgorithm = new RouletteSelection();
        Crossover cycleCrossoverAlgorithm = new CycleCrossover();
        Crossover orderedCossoverAlgorithm = new OrderedCrossover();
        Mutation inversionMutationAlgorithm = new InversionMutation();
        Mutation swapMutationAlgorithm = new SwapMutation();
        String filePathN32K5 = "src/resources/A-n32-k5.vrp";
        String filePathN37K6 = "src/resources/A-n37-k6.vrp";
        String filePathN39K5 = "src/resources/A-n39-k5.vrp";
        String filePathN45K6 = "src/resources/A-n45-k6.vrp";
        String filePathN48K7 = "src/resources/A-n48-k7.vrp";
        String filePathN54K7 = "src/resources/A-n54-k7.vrp";
        String filePathN60K9 = "src/resources/A-n60-k9.vrp";
        // test1
/*        System.out.println("filePathN32K5");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        System.out.println("filePathN37K6");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN37K6);
        System.out.println("filePathN39K5");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN39K5);
        System.out.println("filePathN45K6");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN45K6);
        System.out.println("filePathN48K7");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN48K7);
        System.out.println("filePathN54K7");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN54K7);
        System.out.println("filePathN60K9");
        test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN60K9);*/
        // test2
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        // test3
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        test(randomPathInitialiseAlgorithm, rouletteSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        // test4
        //System.out.println("orderedCossoverAlgorithm");
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        //System.out.println("cycleCrossoverAlgorithm");
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, cycleCrossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        // test5
        //System.out.println("swapMutationAlgorithm");
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, swapMutationAlgorithm, filePathN32K5);
        //System.out.println("inversionMutationAlgorithm");
        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, orderedCossoverAlgorithm, inversionMutationAlgorithm, filePathN32K5);
        // test6
        // test losowej metody 10k razy
        /*
        FileRepository repositoryRandom = new CvrpFileRepository();
        CvrpData cvrpDataRandom = repositoryRandom.getCvrpData(filePathN32K5);
        for(int i=0; i<10000; i++){
            RandomPathAlgorithm randomPathAlgorithm = new RandomPathAlgorithm();
            Path randomPath = randomPathAlgorithm.createOptimalPath(cvrpDataRandom);
            Solution solution2 = new Solution(randomPath, cvrpDataRandom);
            solution2.printCostResult();
        }
         */

        //greedy
        /*
        FileRepository repositoryGreedy = new CvrpFileRepository();
        CvrpData cvrpDataGreedy = repositoryGreedy.getCvrpData(filePathN45K6);
        GreedyPathAlgorithm greedyPathAlgorithm = new GreedyPathAlgorithm();
        Path greedyPath = greedyPathAlgorithm.createOptimalPath(cvrpDataGreedy);
        Solution solution1 = new Solution(greedyPath, cvrpDataGreedy);
        Solution repairedSolution1 = new Solution(solution1.repairPath(), cvrpDataGreedy);
        repairedSolution1.printCostResult();

         */

        //test(randomPathInitialiseAlgorithm, tournamentSelectionAlgorithm, cycleCrossoverAlgorithm, inversionMutationAlgorithm, filePathN45K6);


    }

    private static void test(
            CustomPathAlgorithm pathInitialiseAlgorithm,
            Selection selectionAlgorithm,
            Crossover crossoverAlgorithm,
            Mutation mutationAlgorithm,
            String filePath
    ){

        FileRepository repository = new CvrpFileRepository();
        Config config = new Config();
        CvrpData cvrpData1 = repository.getCvrpData(filePath);
        GenethicAlgorithm gA1 = new GenethicAlgorithm(pathInitialiseAlgorithm, selectionAlgorithm,
                crossoverAlgorithm, mutationAlgorithm, cvrpData1);
        gA1.run();

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
        if(TEST_REPEAT_AMOUNT > 1){
            for(int testRepeat=0; testRepeat<TEST_REPEAT_AMOUNT - 1; testRepeat++){
                gA1 = new GenethicAlgorithm(pathInitialiseAlgorithm, selectionAlgorithm,
                        crossoverAlgorithm, mutationAlgorithm, cvrpData1);
                gA1.run();
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
                    avgResultList.get(k).set(0, avgResultList.get(k).get(0) + resultMin);
                    avgResultList.get(k).set(1, avgResultList.get(k).get(1) + (resultSum/ config.getPopulationSize()));
                    avgResultList.get(k).set(2, avgResultList.get(k).get(2) + resultMax);
                }
            }
            for(int k=0; k<config.getgAIterationValue(); k++){
                for(int j=0; j<3; j++){
                    avgResultList.get(k).set(j, avgResultList.get(k).get(j)/TEST_REPEAT_AMOUNT);
                }
            }

        }
        System.out.println(avgResultList);
    }
}
