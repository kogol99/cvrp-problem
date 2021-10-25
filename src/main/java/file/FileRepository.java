package file;

import model.CvrpData;

public interface FileRepository {

    CvrpData getCvrpData(String path);
}
