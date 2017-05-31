package co.netguru.todolist.data.local.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "check_list_item",
        indices = @Index("task_id"),
        foreignKeys = @ForeignKey(
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE,
                entity = TaskDb.class,
                parentColumns = "id",
                childColumns = "task_id"
        ))
public class ChecklistItemDb {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "task_id")
    private int taskId;

    @ColumnInfo(name = "first_name")
    private String name;

    @ColumnInfo(name = "is_done")
    private boolean isDone;

    public ChecklistItemDb(int id, int taskId, String name, boolean isDone) {
        this.id = id;
        this.taskId = taskId;
        this.name = name;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
