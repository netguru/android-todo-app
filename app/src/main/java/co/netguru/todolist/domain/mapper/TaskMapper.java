package co.netguru.todolist.domain.mapper;

import java.util.List;

import co.netguru.todolist.data.local.model.ChecklistItemDb;
import co.netguru.todolist.data.local.model.TaskDb;
import co.netguru.todolist.domain.ChecklistItem;
import co.netguru.todolist.domain.Task;

public class TaskMapper {

    private TaskMapper() {
        throw new AssertionError();
    }

    public static TaskDb toTaskDb(Task task) {
        return new TaskDb(task.getId(), task.getTitle(),
                task.getDescription(), task.isDone(),
                task.getDueDate());
    }

    public static Task fromTaskDbAndChecklistDbList(TaskDb taskDb, List<ChecklistItemDb> checklist) {
        List<ChecklistItem> checklistItems = ChecklistItemMapper.toChecklistItemList(checklist);
        return new Task(taskDb.getId(), taskDb.getTitle(), taskDb.getDescription(),
                taskDb.isDone(), taskDb.getDueDate(), checklistItems);
    }
}
