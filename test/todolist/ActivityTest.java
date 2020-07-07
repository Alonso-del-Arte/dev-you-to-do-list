package todolist;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private static final double TEST_DELTA = 0.0001;

    @Test
    void testGetDescription() {
        System.out.println("getDescription");
        String expected = "Buy milk";
        Activity toDoListItem = new Activity(expected);
        String actual = toDoListItem.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    void testSetDescription() {
        System.out.println("setDescription");
        Activity toDoListItem = new Activity("Buy milk");
        String expected = "Buy soy milk";
        toDoListItem.setDescription(expected);
        String actual = toDoListItem.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    void testGetImportance() {
        System.out.println("getImportance");
        double expected = 0.8;
        Activity toDoListItem = new Activity("Review documents", expected);
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testSetImportance() {
        System.out.println("setImportance");
        Activity toDoListItem = new Activity("Review documents", 0.8);
        double expected = 0.9;
        toDoListItem.setImportance(expected);
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testGetDueDate() {
        System.out.println("getDueDate");
        LocalDateTime expected = LocalDateTime.now().plusDays(14);
        Activity toDoListItem = new Activity("Get oil change", expected);
        LocalDateTime actual = toDoListItem.getDueDate();
        assertEquals(expected, actual);
    }

    @Test
    void testSetDueDate() {
        System.out.println("setDueDate");
        LocalDateTime originalDueDate = LocalDateTime.now().plusDays(14);
        Activity toDoListItem = new Activity("Get oil change", originalDueDate);
        LocalDateTime expected = originalDueDate.minusDays(7);
        toDoListItem.setDueDate(expected);
        LocalDateTime actual = toDoListItem.getDueDate();
        assertEquals(expected, actual);
    }

    @Test
    void testConstructorDefaultImportanceZeroPointFiveDueDateGiven() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(28);
        Activity toDoListItem = new Activity("Pick up gift for birthday party", dueDate);
        double expected = 0.5;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testConstructorDefaultImportanceZeroPointFive() {
        Activity toDoListItem = new Activity("Pick up gift for birthday party");
        double expected = 0.5;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

}