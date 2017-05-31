package co.netguru.todolist.domain;

public class ChecklistItem {

    private int id;

    private String name;

    private boolean isDone;

    public ChecklistItem(int id, String name, boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public ChecklistItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
