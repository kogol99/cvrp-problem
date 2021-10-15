package program;

import file.CvrpFileRepository;
import file.FileRepository;
import model.CvrpData;

public class Main {

    public static void main(String[] args) {
        FileRepository repository = new CvrpFileRepository();
        CvrpData cvrpData = repository.getCvrpData("src/resources/A-n32-k5.vrp");
        System.out.println(cvrpData);
    }
}
