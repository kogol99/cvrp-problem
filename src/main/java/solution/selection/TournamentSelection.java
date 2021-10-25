package solution.selection;

import model.CvrpData;
import model.Path;
import solution.Config;
import solution.Solution;

import static solution.SolutionEvaluator.evaluate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TournamentSelection implements Selection{

    Random random = new Random();
    CvrpData cvrpData;

    @Override
    public Path selection(List<Path> population, CvrpData cvrpData) {
        this.cvrpData = cvrpData;
        Config config = new Config();
        int tournamentSize = config.getTournamentSize();
        int populationSize = population.size();
        int selectedQuantity = 0;
        List<Path> selectedPath = new ArrayList<>();
        if(tournamentSize > populationSize){
            tournamentSize = populationSize;
        }

        int anotherChoice = 0;
        while(selectedQuantity < tournamentSize){

            int randId = random.nextInt(populationSize - 1);
            //if(!selectedPath.contains(population.get(randId)) || anotherChoice > populationSize/4*3){
                selectedQuantity++;
                selectedPath.add(population.get(randId));
            //}
            anotherChoice++;
        }
        List<Path> selectedPathWithDepot = getRepairedPathsList(selectedPath);
        selectedPathWithDepot.sort(Comparator.comparing(TournamentSelection::calculateDistance));
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
