package co.netguru.todolist.domain.usecase;

import javax.inject.Inject;

import co.netguru.todolist.data.local.TasksRepository;
import co.netguru.todolist.domain.Task;
import co.netguru.todolist.domain.mapper.ChecklistItemMapper;
import co.netguru.todolist.domain.mapper.TaskMapper;
import io.reactivex.Completable;

public class SaveTaskUseCase {

    private TasksRepository tasksRepository;

    @Inject
    public SaveTaskUseCase(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Completable execute(Task task) {
        return tasksRepository.saveTaskWithChecklist(
                TaskMapper.toTaskDb(task),
                ChecklistItemMapper.toChecklistItemDbList(task.getId(), task.getChecklistItemList())
        );
    }
}
