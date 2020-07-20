package todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityComparatorTest {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TWENTY_DAYS_PRIOR = TODAY.minusDays(20);
    private static final LocalDate THIRTY_DAYS_LATER = TODAY.plusDays(30);
    private static final LocalDate SIXTY_DAYS_LATER = TODAY.plusDays(60);
    private static final LocalDate NINETY_DAYS_LATER = TODAY.plusDays(90);

    private static final LocalTime EIGHT_AM = LocalTime.of(8, 0);
    private static final LocalTime EIGHT_PM = LocalTime.of(20, 0);

    private static final LocalDateTime PAST_DATE_TIME = LocalDateTime.of(TWENTY_DAYS_PRIOR, EIGHT_PM);
    private static final LocalDateTime FUTURE_30_DAYS = LocalDateTime.of(THIRTY_DAYS_LATER, EIGHT_AM);
    private static final LocalDateTime FUTURE_60_DAYS = LocalDateTime.of(SIXTY_DAYS_LATER, EIGHT_PM);
    private static final LocalDateTime FUTURE_90_DAYS = LocalDateTime.of(NINETY_DAYS_LATER, EIGHT_PM);

    private static final Activity PAST_DUE_INCOMPLETE_TASK
            = new Activity("Review software manuals", 1.0,
            PAST_DATE_TIME);
    private static final Activity PAST_DUE_COMPLETE_TASK
            = new Activity("Renew software licenses", 1.0,
            PAST_DATE_TIME);
    private static final Activity OIL_CHANGE_TASK
            = new Activity("Get oil change", 0.99,
            FUTURE_90_DAYS);
    private static final Activity BOOK_CLUB_TASK
            = new Activity("Read book for book club", 0.45,
            FUTURE_30_DAYS);
    private static final Activity HAIRCUT_TASK =
            new Activity("Get haircut", 0.68, FUTURE_60_DAYS);

    @Test
    void testComparator() {
        System.out.println("Comparator");
        PAST_DUE_COMPLETE_TASK.toggleCompletionStatus();
        ArrayList<Activity> expected = new ArrayList<>();
        expected.add(PAST_DUE_INCOMPLETE_TASK);
        expected.add(OIL_CHANGE_TASK);
        expected.add(HAIRCUT_TASK);
        expected.add(BOOK_CLUB_TASK);
        expected.add(PAST_DUE_COMPLETE_TASK);
        ArrayList<Activity> actual = new ArrayList<>();
        actual.add(PAST_DUE_COMPLETE_TASK);
        actual.add(HAIRCUT_TASK);
        actual.add(OIL_CHANGE_TASK);
        actual.add(BOOK_CLUB_TASK);
        actual.add(PAST_DUE_INCOMPLETE_TASK);
        actual.sort(new PriorityComparator());
        assertEquals(expected, actual);
    }

}