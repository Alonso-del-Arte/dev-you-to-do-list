package fileops;

import java.io.File;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataDirectoryManagerTest {

    @Test
    void getDirectory() {
        String path = System.getProperty("java.io.tmpdir");
        File expected = new File(path);
        File actual = DataDirectoryManager.getDirectory();
        assertEquals(expected, actual);
    }
}