package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.CvrpData;
import model.Path;
import path.GreedyPathAlgorithm;
import path.RandomPathAlgorithm;
import solution.Solution;
import solution.mutation.InversionMutation;
import solution.mutation.Mutation;
import solution.mutation.SwapMutation;

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
        System.out.println(pathAfterSwapMutation);

        Mutation mutation = new InversionMutation();
        Path pathAfterInveriosnMutation = mutation.mutation(greedyPath);
    }
}
