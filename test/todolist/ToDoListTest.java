package todolist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void testSetFileID() {
        System.out.println("setFileID");
        ToDoList list = new ToDoList();
        int expected = 1729;
        list.setFileID(expected);
        int actual = list.getFileID();
        assertEquals(expected, actual);
    }

    @Test
    void testGeneratedFileIDEqualsHashCode() {
        ToDoList list = new ToDoList();
        int expected = list.hashCode();
        if (expected < 0) {
            expected *= -1;
        }
        System.out.println("Expected generated file ID is " + expected);
        int actual = list.getFileID();
        assertEquals(expected, actual);
    }

    @Test
    void testSize() {
        System.out.println("size");
        fail("Haven't written test yet");
    }

    @Test
    void testGet() {
        System.out.println("get");
        fail("Haven't written test yet");
    }

}