package solution.selection;

import model.CvrpData;
import model.Path;

import java.util.List;

public interface Selection {

    public Path selection(List<Path> population, CvrpData cvrpData);

}
