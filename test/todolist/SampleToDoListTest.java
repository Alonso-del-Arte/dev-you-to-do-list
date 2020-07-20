package todolist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleToDoListTest {

    @Test
    void testConstructor() {
        SampleToDoList sample = new SampleToDoList();
        Activity[] items = sample.getAll();
        assert items.length == 2 : "Sample to do list should have exactly two items";
    }

}