package todolist;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Activity implements Serializable {

    private static final long serialVersionUID = 4549631111164082995L;

    public static final double DEFAULT_IMPORTANCE = 0.5;

    public static final short DISCR = 10000;

    public static final long FAR_OFF_FUTURE_DISTANCE_YEARS = 900;

    public static final LocalDateTime FAR_OFF_FUTURE_MARKER
            = LocalDateTime.now().plusYears(FAR_OFF_FUTURE_DISTANCE_YEARS);

    private String description;

    private LocalDateTime dueDate;

    private double importanceScore;

    private boolean completedFlag = false;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String replacement) {
        this.description = replacement;
    }

    public double getImportance() {
        return this.importanceScore;
    }

    public double priority() {
        if (this.completedFlag) return Double.MAX_VALUE;
        long days = ChronoUnit.DAYS.between(LocalDateTime.now(), this.dueDate);
        double recip;
        if (days == 0L) {
            recip = 0.0;
        } else {
            recip = 1.0 / days;
        }
        return this.importanceScore * recip;
    }

    public void setImportance(double score) {
        if (score > 1.0) {
            score = 1.0;
        }
        if (Double.isNaN(score)) {
            score = DEFAULT_IMPORTANCE;
        }
        if (score < 0.0) {
            score = 0.0;
        }
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
        this.completedFlag = !this.completedFlag;
    }

    @Override
    public String toString() {
        String str = this.description;
        if (this.dueDate.isBefore(FAR_OFF_FUTURE_MARKER)) {
            str = str + " Due: " + this.dueDate.toString();
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        final Activity other = (Activity) obj;
        if (!this.description.equalsIgnoreCase(other.description)) return false;
        short impRound = (short) Math.floor(this.importanceScore * DISCR);
        short otherImp = (short) Math.floor(other.importanceScore * DISCR);
        if (impRound != otherImp) return false;
        return this.dueDate.equals(other.dueDate);
    }

    @Override
    public int hashCode() {
        int hash = 0;// this.description.hashCode();
//        short score = (short) Math.floor(this.importanceScore * DISCR);
//        hash += score;
//        if (this.dueDate.isBefore(FAR_OFF_FUTURE_MARKER)) {
//            hash = 443 * hash + this.dueDate.hashCode();
//        }
        return hash;
    }

    public Activity(String description) {
        this(description, DEFAULT_IMPORTANCE,
                LocalDateTime.now().plusYears(2048));
    }

    public Activity(String description, double importance) {
        this(description, importance, LocalDateTime.now().plusYears(2048));
    }

    public Activity(String description, LocalDateTime dueDate) {
        this(description, DEFAULT_IMPORTANCE, dueDate);
    }

    public Activity(String description, double importance, LocalDateTime dueDate) {
        if (importance > 1.0) {
            importance = 1.0;
        }
        if (Double.isNaN(importance)) {
            importance = 0.5;
        }
        if (importance < 0.0) {
            importance = 0.0;
        }
        this.description = description;
        this.importanceScore = importance;
        this.dueDate = dueDate;
    }

}
