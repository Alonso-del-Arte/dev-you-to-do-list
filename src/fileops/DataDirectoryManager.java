package fileops;

import java.io.File;
import java.io.IOException;

public class DataDirectoryManager {

    private static final String DEFAULT_PATH
            = System.getProperty("java.io.tmpdir") + "toDoList";

    private static final File DEFAULT_DATA_DIRECTORY = new File(DEFAULT_PATH);

    private static final boolean DEFAULT_CREATED_JUST_NOW
            = DEFAULT_DATA_DIRECTORY.mkdir();

    private static File directory = null;

    static {
        if (DEFAULT_DATA_DIRECTORY.exists()) {
            directory = DEFAULT_DATA_DIRECTORY;
            if (DEFAULT_CREATED_JUST_NOW) {
                System.out.println("Successfully created default data directory at "
                        + DEFAULT_PATH);
            }
        }
    }

    public static File getDirectory() throws IOException {
        if (directory == null) {
            String excMsg = "Default data directory " + DEFAULT_PATH + " is unavailable";
            throw new IOException(excMsg);
        }
        return directory;
    }

    public static void setDirectory() {
        if (DEFAULT_DATA_DIRECTORY.exists()) {
            directory = DEFAULT_DATA_DIRECTORY;
        }
    }

    public static void setDirectory(File replacement) {
        if (!replacement.isDirectory()) {
            String excMsg = replacement.getPath() + " is not a directory";
            throw new IllegalArgumentException(excMsg);
        }
        DataDirectoryManager.directory = replacement;
    }

}
