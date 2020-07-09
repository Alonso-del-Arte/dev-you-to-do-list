package todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityComparatorTest {

    @Test
    void testComparator() {
        ArrayList<Activity> expected = new ArrayList<>();
        ArrayList<Activity> actual = new ArrayList<>();
        actual.sort(new PriorityComparator().reversed());
        assertEquals(expected, actual);
        fail("Haven't written test yet");
    }

}