package file;

import model.CvrpData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CvrpFileRepository implements FileRepository{

    public CvrpFileRepository(){

    }

    @Override
    public CvrpData getCvrpData(String filePath) {
        Path path = Paths.get(filePath);
        List<String> fileData;
        try {
            fileData = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file path: " + e);
        }

        CvrpFileDataMapper mapper = new CvrpFileDataMapper(fileData);
        return mapper.mapCvrpFileData();
    }
}
