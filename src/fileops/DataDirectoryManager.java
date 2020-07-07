package fileops;

import java.io.File;

public class DataDirectoryManager {

    public static File getDirectory() {
        String path = System.getProperty("java.io.tmpdir");
        File dir = new File(path);
        return dir;
    }

}
