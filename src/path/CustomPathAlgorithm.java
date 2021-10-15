package path;

import model.CvrpData;
import model.Path;

public interface CustomPathAlgorithm {

    Path createOptimalPath(CvrpData cvrpData);

}
