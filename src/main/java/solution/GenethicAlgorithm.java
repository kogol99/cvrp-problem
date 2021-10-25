package solution;

import model.*;
import path.CustomPathAlgorithm;
import path.GreedyPathAlgorithm;
import path.RandomPathAlgorithm;
import solution.crossover.Crossover;
import solution.crossover.CycleCrossover;
import solution.crossover.OrderedCrossover;
import solution.mutation.InversionMutation;
import solution.mutation.Mutation;
import solution.mutation.SwapMutation;
import solution.selection.RouletteSelection;
import solution.selection.Selection;
import solution.selection.TournamentSelection;

import java.util.*;

import static solution.SolutionEvaluator.evaluate;

public class GenethicAlgorithm {

    Random random = new Random();

    private CvrpData cvrpData;
    private Path path;
    private Config config;
    private List<List<Double>> endedResultList;

    private CustomPathAlgorithm pathInitialiseAlgorithm;
    private Selection selectionAlgorithm;
    private Crossover crossoverAlgorithm;
    private Mutation mutationAlgorithm;

    public GenethicAlgorithm(
            CustomPathAlgorithm pathInitialiseAlgorithm,
            Selection selectionAlgorithm,
            Crossover crossoverAlgorithm,
            Mutation mutationAlgorithm,
            CvrpData cvrpData
    ){
        this.pathInitialiseAlgorithm = pathInitialiseAlgorithm;
        this.selectionAlgorithm = selectionAlgorithm;
        this.crossoverAlgorithm = crossoverAlgorithm;
        this.mutationAlgorithm = mutationAlgorithm;
        this.cvrpData = cvrpData;
        this.config = new Config();
    }

    public Path run(){
        List<List<Double>> resultList = new ArrayList<>();
        int t = 0;
        double theBestValue = Integer.MAX_VALUE;
        Path theBestPath = null;
        List<Path> pathList = initialisePopulation();
        List<List<Path>> populationList = new ArrayList<>();
        populationList.add(pathList);
        resultList.add(evaluateFirstPopulation(pathList));
        int firstPopulationTheBestPathId = getIdTheBestValue(resultList.get(0));
        theBestValue = resultList.get(0).get(firstPopulationTheBestPathId);
        theBestPath = pathList.get(firstPopulationTheBestPathId);
        while(config.getgAIterationValue() > t){
            populationList.add(new ArrayList<>());
            resultList.add(new ArrayList<>());
            while(populationList.get(t+1).size() < config.getPopulationSize()){
                Path p1 = selectionAlgorithm.selection(populationList.get(t));
                Path p2 = selectionAlgorithm.selection(populationList.get(t));
                Path o1;
                if(random.nextDouble() < config.getPx()){
                    o1 = crossoverAlgorithm.Crossover(p1, p2);
                } else {
                    o1 = p1;
                }
                if(random.nextDouble() < config.getPm()){
                    o1 = mutationAlgorithm.mutation(o1);
                }
                boolean isGoodPath = cityChecker(o1);
                Solution solution = new Solution(o1, cvrpData);
                Path o1Result = solution.repairPath();
                double evalValue = evaluate(o1Result);
                populationList.get(t + 1).add(o1);
                if(theBestValue > evalValue){
                    theBestPath = o1;
                }
                resultList.get(t+1).add(evalValue);
            }
            t++;
        }
        //System.out.println(resultList);
        this.endedResultList = resultList;
        return theBestPath;
    }

    private List<Path> initialisePopulation(){
        List<Path> populationList = new ArrayList<>();
        for(int i=0; i<config.getPopulationSize(); i++){
            populationList.add(pathInitialiseAlgorithm.createOptimalPath(cvrpData));
        }
        return populationList;
    }

    private List<Double> evaluateFirstPopulation(List<Path> fistPopulation){
        List<Double> resultList = new ArrayList<>();
        for(Path path: fistPopulation){
            Solution solution = new Solution(path, cvrpData);
            path = solution.repairPath();
            resultList.add(evaluate(path));
        }
        return resultList;
    }

    private int getIdTheBestValue(List<Double> list){
        double theBestValue = Double.MAX_VALUE;
        int idTheBestValue = -1;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) < theBestValue){
                theBestValue = list.get(i);
                idTheBestValue = i;
            }
        }
        return idTheBestValue;
    }

    public void setEndedResultList(List<List<Double>> endedResultList) {
        this.endedResultList = endedResultList;
    }

    public List<List<Double>> getEndedResultList() {
        return endedResultList;
    }

    private boolean cityChecker(Path path){
        HashSet<Integer> cityNoList = new HashSet<>();
        for(CombinationCities combinationCities: path.getCombinationCitiesList()){
            cityNoList.add(combinationCities.getDestinationCity().getNumber());
        }
        if(cityNoList.size() == 32){
            return true;
        } else {
            return false;
        }
    }
}
