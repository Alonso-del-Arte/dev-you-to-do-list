package todolist;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
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
    void testExcessSetImportanceDownToOne() {
        Activity toDoListItem = new Activity("Review documents", 0.8);
        toDoListItem.setImportance(Double.MAX_VALUE);
        double expected = 1.0;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testNaNSetImportanceMiddling() {
        Activity toDoListItem = new Activity("Review documents", 0.8);
        toDoListItem.setImportance(Double.NaN);
        double expected = 0.5;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testSetUnderImportanceUpToZero() {
        Activity toDoListItem = new Activity("Read 'Infinite Jest'", 0.1);
        toDoListItem.setImportance(-22.803);
        double expected = 0.0;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testPriority() {
        System.out.println("priority");
        double importance = 0.8;
        LocalDateTime dueDate = LocalDateTime.now().plusDays(20);
        Activity toDoListItem = new Activity("Get haircut", importance, dueDate);
        double expected = 0.04;
        double actual = toDoListItem.priority();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testPriorityDueToday() {
        double importance = 1.0;
        LocalDateTime dueDate = LocalDateTime.now();
        Activity toDoListItem = new Activity("Urgent task", importance, dueDate);
        double expected = 0.0;
        double actual = toDoListItem.priority();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testPriorityPastDueIncomplete() {
        double importance = 1.0;
        LocalDateTime dueDate = LocalDateTime.now().minusDays(20);
        Activity toDoListItem = new Activity("Renew software licenses",
                importance, dueDate);
        double priority = toDoListItem.priority();
        String msg = "Priority " + priority + " of past due incomplete item should be negative";
        assert priority < 0.0 : msg;
    }

    @Test
    void testPriorityComplete() {
        double importance = 1.0;
        LocalDateTime dueDate = LocalDateTime.now().minusDays(20);
        Activity toDoListItem = new Activity("Renew software licenses",
                importance, dueDate);
        toDoListItem.toggleCompletionStatus();
        double priority = toDoListItem.priority();
        double threshold = 373760.5;
        String msg = "Priority " + priority
                + " of completed item should be greater than " + threshold;
        assert priority > threshold : msg;
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
    void testCompletionStatus() {
        System.out.println("completionStatus");
        Activity toDoListItem = new Activity("Reorganize contacts list");
        String msg = "Newly constructed Activity should not be marked completed";
        assert !toDoListItem.completionStatus() : msg;
    }

    @Test
    void testToggleCompletionStatus() {
        System.out.println("toggleCompletionStatus");
        Activity toDoListItem = new Activity("Reorganize contacts list");
        toDoListItem.toggleCompletionStatus();
        String msg = "Newly constructed Activity should be marked completed after toggle";
        assert toDoListItem.completionStatus() : msg;
    }

    @Test
    void testToString() {
        System.out.println("toString");
        String description = "Buy milk";
        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);
        Activity toDoListItem = new Activity("Buy milk", dueDate);
        String str = toDoListItem.toString();
        String msg = "toString() should include description \"" + description + "\"";
        assert str.contains(description) : msg;
        msg = "toString() should include due date " + dueDate.toString();
        assert str.contains(dueDate.toString()) : msg;
    }

    @Test
    void testToStringNotIncludeFarOffFutureDueDates() {
        Activity toDoListItem = new Activity("Read 'Infinite Jest'");
        LocalDateTime generatedDueDate = toDoListItem.getDueDate();
        String dueDateStr = generatedDueDate.toString();
        String msg = "toString() does not include far off due date " + dueDateStr;
        assert !toDoListItem.toString().contains(dueDateStr) : msg;
    }

    @Test
    void testReferentialEquality() {
        Activity toDoListItem = new Activity("Buy milk");
        assertEquals(toDoListItem, toDoListItem);
    }

    @Test
    void testNotEqualsNull() {
        Activity toDoListItem = new Activity("Build a canoe");
        assertNotEquals(toDoListItem, null);
    }

    @Test
    void testNotEqualsObjectOtherClass() {
        Activity toDoListItem = new Activity("Invite team to Slack");
        assertNotEquals(toDoListItem, LocalDateTime.now());
    }

    @Test
    void testNotEqualsMismatchedDescription() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);
        Activity toDoTask1 = new Activity("Buy milk", dueDate);
        Activity toDoTask2 = new Activity("Buy soy milk", dueDate);
        assertNotEquals(toDoTask1, toDoTask2);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);
        Activity someTask = new Activity("Buy milk", dueDate);
        Activity sameTask = new Activity("Buy milk", dueDate);
        assertEquals(someTask, sameTask);
    }

    @Test
    void testNotEqualsDifferentDueDates() {
        Activity task1 = new Activity("Get haircut", LocalDateTime.now().plusDays(30));
        Activity task2 = new Activity("Get haircut", LocalDateTime.now().plusDays(60));
        assertNotEquals(task1, task2);
    }

    @Test
    void testNotEqualsDifferentImportance() {
        LocalDateTime dueDate = LocalDateTime.now().plusMonths(7);
        Activity task1 = new Activity("Read Shakespeare sonnets", 0.7, dueDate);
        Activity task2 = new Activity("Read Shakespeare sonnets", 0.4, dueDate);
        assertNotEquals(task1, task2);
    }

    @Test
    void testDoNotDistinguishSlightImportanceDifferences() {
        LocalDateTime dueDate = LocalDateTime.now().plusMonths(7);
        Activity someTask = new Activity("Read Shakespeare sonnets", 0.740001, dueDate);
        Activity sameTask = new Activity("Read Shakespeare sonnets", 0.740002, dueDate);
        assertEquals(someTask, sameTask);
    }

    private ArrayList<Activity> makeTestItems() {
        ArrayList<Activity> items = new ArrayList<>();
        return items;
    }

    @Test
    void testHashCode() {
        fail("Haven't written test yet");
    }

    @Test
    void testConstructorTrimsExcessImportance() {
        Activity toDoListItem = new Activity("Balance checkbook", Double.MAX_VALUE);
        double expected = 1.0;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testConstructorMiddlesNaNImportance() {
        Activity toDoListItem = new Activity("Balance checkbook", Double.NaN);
        double expected = 0.5;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testConstructorUpsUnderImportance() {
        Activity toDoListItem = new Activity("Learn to play didgeridoo", -43.75);
        double expected = 0.0;
        double actual = toDoListItem.getImportance();
        assertEquals(expected, actual, TEST_DELTA);
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

    @Test
    void testConstructorDefaultDueDateFarOffInFutureImportanceGiven() {
        LocalDateTime farOffInFuture = LocalDateTime.now().plusYears(1024);
        Activity toDoListItem = new Activity("Consult lawyer, re: LLC", 0.95);
        LocalDateTime dueDate = toDoListItem.getDueDate();
        String msg = "Default due date " + dueDate.toString()
                + " should be no earlier than " + farOffInFuture.toString();
        assert dueDate.isAfter(farOffInFuture) : msg;
    }

    @Test
    void testConstructorDefaultDueDateFarOffInFuture() {
        LocalDateTime farOffInFuture = LocalDateTime.now().plusYears(1024);
        Activity toDoListItem = new Activity("Consult lawyer, re: LLC");
        LocalDateTime dueDate = toDoListItem.getDueDate();
        String msg = "Default due date " + dueDate.toString()
                + " should be no earlier than " + farOffInFuture.toString();
        assert dueDate.isAfter(farOffInFuture) : msg;
    }

}