package todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ToDoList implements Serializable {

    private static final long serialVersionUID = 4549634654512102195L;

    private int numberOfItems = -1;
    private int fileID = 0;

    private boolean fileIDHasBeenSet = false;

    public int getFileID() {
        if (!this.fileIDHasBeenSet) {
            int hash = this.hashCode();
            if (hash < 0) {
                hash *= -1;
            }
            this.setFileID(hash);
        }
        return this.fileID;
    }

    public void setFileID(int replacement) {
        this.fileID = replacement;
        this.fileIDHasBeenSet = true;
    }

    public int size() {
        return this.numberOfItems;
    }

    public Activity get(int index) {
        return new Activity("Sorry, not implemented yet");
    }

    public Activity[] getAll() {
        Activity[] array = {};
        return array;
    }

    public void add(Activity toDoListItem) {
        // TODO: Implement
    }

}
