package co.netguru.todolist.data.local.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import org.threeten.bp.LocalDate;

@Entity(tableName = "task")
public class TaskDb {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    @ColumnInfo(name = "is_done")
    private boolean isDone;

    @Nullable
    @ColumnInfo(name = "due_date")
    private LocalDate dueDate;

    public TaskDb(int id, String title, String description, boolean isDone, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
