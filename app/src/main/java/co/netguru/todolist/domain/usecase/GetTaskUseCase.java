package co.netguru.todolist.domain.usecase;

import javax.inject.Inject;

import co.netguru.todolist.data.local.TasksRepository;
import co.netguru.todolist.domain.Task;
import io.reactivex.Flowable;

public class GetTaskUseCase {

    private final TasksRepository tasksRepository;

    @Inject
    public GetTaskUseCase(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

   /* public Flowable<Task> execute() {
        //// TODO: 31.05.2017
        //return tasksRepository.getDoneTasks();
    }*/
}
