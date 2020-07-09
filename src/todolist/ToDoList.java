package todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ToDoList implements Serializable {

    private static final long serialVersionUID = 4549634654512102195L;

    private int numberOfItems = -1;

    public int size() {
        return this.numberOfItems;
    }

}
