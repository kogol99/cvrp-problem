package solution;

import model.City;
import model.CombinationCities;
import model.Path;

import java.awt.geom.Point2D;
import java.util.List;

public class SolutionEvaluator
{
    private SolutionEvaluator() {
    }

    public static double evaluate(Path solutionPath) {
        List<CombinationCities> connections = solutionPath.getCombinationCitiesList();

        return connections.stream()
                .mapToDouble(SolutionEvaluator::calculateDistance)
                .sum();
    }

    public static double calculateDistance(CombinationCities connection) {
        City originPlace = connection.getOriginCity();
        City destinationPlace = connection.getDestinationCity();
        return Point2D.distance(
                originPlace.getxCoord(),
                originPlace.getyCoord(),
                destinationPlace.getxCoord(),
                destinationPlace.getyCoord()
        );
    }
}
