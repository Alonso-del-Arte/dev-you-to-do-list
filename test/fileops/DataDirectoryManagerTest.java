package fileops;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataDirectoryManagerTest {

    @Test
    void testGetDirectory() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        File expected = new File(path);
        DataDirectoryManager.setDirectory(expected);
        File actual = DataDirectoryManager.getDirectory();
        assertEquals(expected, actual);
    }

    /**
     * Test of the setDirectory procedure in the DataDirectoryManager class.
     * Without a parameter, it should set the default directory as a
     * subdirectory of the temporary directory.
     * @throws IOException If <code>getDirectory()</code> throws it.
     */
    @Test
    void testSetDefaultDirectory() throws IOException {
        DataDirectoryManager.setDirectory();
        File dir = DataDirectoryManager.getDirectory();
        String expected = System.getProperty("java.io.tmpdir");
        String actual = dir.getParent() + File.separatorChar;
        assertEquals(expected, actual);
    }

    @Test
    void testSetDirectory() throws IOException {
        System.out.println("setDirectory");
        String path = System.getProperty("user.home") + File.separatorChar
                + "Documents";
        File expected = new File(path);
        DataDirectoryManager.setDirectory(expected);
        File actual = DataDirectoryManager.getDirectory();
        assertEquals(expected, actual);
    }

    @Test
    void testSetDirectoryRejectsFiles() throws IOException {
        String path = System.getProperty("user.home") + File.separatorChar
                + "Documents" + File.separatorChar + "testFile"
                + this.hashCode() + ".txt";
        File file = new File(path);
        String msg = "File " + path + " is not a directory";
        assert !file.isDirectory() : msg;
        if (file.exists() || file.createNewFile()) {
            System.out.println("Successfully created file " + path);
            System.out.println("(this file will be deleted if this test runs successfully)");
            try {
                DataDirectoryManager.setDirectory(file);
                msg = "Since " + path
                        + " is not a directory, it should be rejected by setDirectory";
                fail(msg);
            } catch (IllegalArgumentException iae) {
                System.out.println("Trying to use file " + path
                        + " as a directory correctly caused IllegalArgumentException");
                System.out.println("\"" + iae.getMessage() + "\"");
            } catch (Exception e) {
                msg = e.getClass().getName()
                        + " is the wrong exception to throw for trying to use file "
                        + path + " as a directory";
                fail(msg);
            }
            if (file.delete()) {
                System.out.println("Successfully deleted file " + path);
            } else {
                msg = "Unable to delete file " + path;
                fail(msg);
            }
        } else {
            msg = "Unable to create " + path;
            fail(msg);
        }
    }

}