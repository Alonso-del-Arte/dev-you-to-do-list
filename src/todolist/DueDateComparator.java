package todolist;

import java.util.Comparator;

public class DueDateComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity task1, Activity task2) {
        return task1.getDueDate().compareTo(task2.getDueDate());
    }

}
