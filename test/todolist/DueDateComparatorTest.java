package todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DueDateComparatorTest {

    private static final LocalDate AUGUST_FOUR = LocalDate.of(2020, 8, 4);
    private static final LocalDate OCTOBER_16 = LocalDate.of(2020, 10, 16);
    private static final LocalDate NOVEMBER_THREE = LocalDate.of(2020, 11, 3);

    private static final LocalTime EIGHT_AM = LocalTime.of(8, 0);
    private static final LocalTime EIGHT_PM = LocalTime.of(20, 0);

    private static final LocalDateTime PRIMARY_VOTE_DEADLINE = LocalDateTime.of(AUGUST_FOUR, EIGHT_PM);
    private static final LocalDateTime CASTING_START = LocalDateTime.of(OCTOBER_16, EIGHT_AM);
    private static final LocalDateTime CASTING_END = LocalDateTime.of(OCTOBER_16, EIGHT_PM);
    private static final LocalDateTime GENERAL_VOTE_DEADLINE = LocalDateTime.of(NOVEMBER_THREE, EIGHT_PM);

    private static final Activity PRIMARY_VOTE
            = new Activity("Vote in the primary election", 1.0,
            PRIMARY_VOTE_DEADLINE);
    private static final Activity GENERAL_VOTE
            = new Activity("Vote in the general election", 1.0,
            GENERAL_VOTE_DEADLINE);
    private static final Activity CASTING_PREP
            = new Activity("Review Baskerville call sheet", 0.8,
            CASTING_START);
    private static final Activity CASTING_SELECT =
            new Activity("Choose Stapleton actor", 0.9, CASTING_END);

    @Test
    void testComparator() {
        ArrayList<Activity> expected = new ArrayList<>();
        expected.add(PRIMARY_VOTE);
        expected.add(CASTING_PREP);
        expected.add(CASTING_SELECT);
        expected.add(GENERAL_VOTE);
        ArrayList<Activity> actual = new ArrayList<>();
        actual.add(CASTING_SELECT);
        actual.add(GENERAL_VOTE);
        actual.add(PRIMARY_VOTE);
        actual.add(CASTING_PREP);
        Collections.sort(actual, new DueDateComparator());
        assertEquals(expected, actual);
    }

}