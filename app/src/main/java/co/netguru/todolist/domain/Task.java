package co.netguru.todolist.domain;

import android.support.annotation.Nullable;

import org.threeten.bp.LocalDate;

import java.util.List;

public class Task {

    private int id;

    private String title;

    private String description;

    private boolean isDone;

    @Nullable
    private LocalDate dueDate;

    private List<ChecklistItem> checklistItemList;

    public Task() {
    }

    public Task(int id, String title, String description,
                boolean isDone, LocalDate dueDate, List<ChecklistItem> checklistItemList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.checklistItemList = checklistItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Nullable
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(@Nullable LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<ChecklistItem> getChecklistItemList() {
        return checklistItemList;
    }

    public void setChecklistItemList(List<ChecklistItem> checklistItemList) {
        this.checklistItemList = checklistItemList;
    }
}
