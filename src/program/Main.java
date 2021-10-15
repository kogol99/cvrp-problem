package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.CombinationCities;
import model.CvrpData;
import model.Path;
import solution.Solution;

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


    }
}
