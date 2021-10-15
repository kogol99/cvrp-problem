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

    public static double evaluate(Path solution) {
        List<CombinationCities> connections = solution.getCombinationCitiesList();

        return connections.stream()
                .mapToDouble(SolutionEvaluator::calculateDistance)
                .sum();
    }

    public static double calculateDistance(CombinationCities connection) {
        City originPlace = connection.getOriginPlace();
        City destinationPlace = connection.getDestinationPlace();
        return Point2D.distance(
                originPlace.getxCoord(),
                originPlace.getyCoord(),
                destinationPlace.getxCoord(),
                destinationPlace.getyCoord()
        );
    }
}
