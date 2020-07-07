package todolist;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Activity implements Serializable {

    private static final long serialVersionUID = 4549631111164082995L;

    public static final double DEFAULT_IMPORTANCE = 0.5;

    private String description;

    private LocalDateTime dueDate;

    private double importanceScore;

    private boolean completedFlag = true;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String replacement) {
        this.description = replacement;
    }

    public double getImportance() {
        return this.importanceScore;
    }

    public void setImportance(double score) {
        this.importanceScore = score;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDateTime replacement) {
        this.dueDate = replacement;
    }

    public boolean completionStatus() {
        return this.completedFlag;
    }

    public void toggleCompletionStatus() {
//        this.completedFlag = !this.completedFlag;
    }

    public Activity(String description) {
        this(description, DEFAULT_IMPORTANCE, LocalDateTime.now());
    }

    public Activity(String description, double importance) {
        this(description, importance, LocalDateTime.now());
    }

    public Activity(String description, LocalDateTime dueDate) {
        this(description, DEFAULT_IMPORTANCE, dueDate);
    }

    public Activity(String description, double importance, LocalDateTime dueDate) {
        this.description = description;
        this.importanceScore = importance;
        this.dueDate = dueDate;
    }

}
