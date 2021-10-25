package solution;

import model.CvrpData;
import model.Path;
import path.CustomPathAlgorithm;
import solution.mutation.Mutation;
import org.apache.commons.collections.buffer.CircularFifoBuffer;
import java.util.Collection;

import java.util.*;
import java.util.stream.Collectors;

import static solution.SolutionEvaluator.evaluate;

public class TabuSearch {

    Random random = new Random();
    private CvrpData cvrpData;
    private Path path;
    private Config config;
    private List<List<Double>> endedResultList; //[[best, actual, avg, worst],[...],...]
    private int t;

    private CustomPathAlgorithm pathInitialiseAlgorithm;
    private Mutation neighborhoodAlgorithm;

    public TabuSearch(CustomPathAlgorithm pathInitialiseAlgorithm, Mutation neighborhoodAlgorithm, CvrpData cvrpData) {
        this.cvrpData = cvrpData;
        this.pathInitialiseAlgorithm = pathInitialiseAlgorithm;
        this.neighborhoodAlgorithm = neighborhoodAlgorithm;
        this.config = new Config();
    }

    public Path run(){
        endedResultList = new ArrayList<>();
        Collection<Path> tabuList = new CircularFifoBuffer(config.gettS_TSize());
        t = 0;
        double theBestValue = Integer.MAX_VALUE;
        Path actualPath = pathInitialiseAlgorithm.createOptimalPath(cvrpData);
        Path theBestPath = actualPath.getClone();
        while (t < config.gettS_iterAtions()){
            endedResultList.add(new ArrayList<>());
            List<Path> neighborhoodList = new ArrayList<>(
                    createNeighborhoodList(
                            neighborhoodAlgorithm,
                            actualPath,
                            config.gettS_NSize()
                    )
            );
            List<Path> availableNeighborhoodListWithAddedDepot = findAvailableNeighborhoodWithAddedDepot(neighborhoodList, tabuList);
            actualPath = deleteDepotInPath(availableNeighborhoodListWithAddedDepot.get(0));
            double actualDistance = calculateDistance(availableNeighborhoodListWithAddedDepot.get(0));
            if(actualDistance < theBestValue){
                theBestValue = actualDistance;
                theBestPath = actualPath.getClone();
            }
            tabuList.add(actualPath.getClone());

            OptionalDouble avg = availableNeighborhoodListWithAddedDepot.stream()
                    .mapToDouble(TabuSearch::calculateDistance)
                    .average();
            Double avgToList = avg.getAsDouble();
            endedResultList.get(t).add(theBestValue);
            endedResultList.get(t).add(actualDistance);
            endedResultList.get(t).add(avgToList);
            endedResultList.get(t).add(calculateDistance(availableNeighborhoodListWithAddedDepot.get(availableNeighborhoodListWithAddedDepot.size() - 1)));

            t++;
        }
        return theBestPath;
    }

    private List<Path> createNeighborhoodList(Mutation neighborhoodAlgorithm, Path actualPath, int listSize){
        List<Path> neighborhoodList = new ArrayList<>();
        for(int i=0; i<listSize; i++){
            neighborhoodList.add(neighborhoodAlgorithm.mutation(actualPath));
        }
        return neighborhoodList;
    }

    private List<Path> findAvailableNeighborhoodWithAddedDepot(List<Path> candidateList, Collection<Path> tabuList){
        List<Path> repairedCandidateList = getRepairedPathsList(candidateList);
        List<Path> availableNeighborhood = repairedCandidateList.stream()
                .filter(candidate -> tabuList.stream()
                        .noneMatch(tabuPath -> tabuPath.equals(candidate)))
                .sorted(Comparator.comparing(TabuSearch::calculateDistance))
                .collect(Collectors.toList());
        return availableNeighborhood;
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

    public List<List<Double>> getEndedResultList() {
        return endedResultList;
    }
}
