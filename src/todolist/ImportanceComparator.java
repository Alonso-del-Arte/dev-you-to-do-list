package todolist;

import java.util.Comparator;

public class ImportanceComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity task1, Activity task2) {
        double diff = task1.getImportance() - task2.getImportance();
        if (diff < 0) return -1;
        if (diff > 0) return 1;
        return 0;
    }

}
